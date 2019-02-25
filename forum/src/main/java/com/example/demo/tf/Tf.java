package com.example.demo.tf;
import org.apache.commons.io.IOUtils;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName Tf
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/12 16:57
 * @Version 1.0
 **/
public class Tf {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\tf\\cnews\\cnews.vocab.txt")),
                    "UTF-8"));
            String lineTxt = null;
            int idx =0 ;
            while ((lineTxt = br.readLine()) != null) {
                map.put(lineTxt,idx);
                idx++;
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
        int input [][] =new int[2][600];
        int max=1000;
        int min=1;
        Random random = new Random();
        for(int i=0;i<2;i++){
            for(int j=0;j<600;j++){
                input[i][j]=random.nextInt(max)%(max-min+1) + min;
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\tf\\nba\\test.txt")),
                    "gbk"));
            String lineTxt = null;
            int idx =0 ;
            while ((lineTxt = br.readLine()) != null) {
                int sz =lineTxt.length();
                for(int k=0;k<2;k++) {
                    for (int i = 0; i < Math.min(600,sz); i++) {
                        String tmp = String.valueOf(lineTxt.charAt(i));
                        if(map.get(tmp)==null){
                            System.out.println(tmp);
                            continue;
                        }
                        input[k][i] = map.get(tmp);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }



        try (Graph graph = new Graph()) {
            //导入图
            byte[] graphBytes = IOUtils.toByteArray(new FileInputStream("./src/main/resources/model2/saved_model.pb"));
            graph.importGraphDef(graphBytes);

            //根据图建立Session
            try(Session session = new Session(graph)){
                //相当于TensorFlow Python中的sess.run(z, feed_dict = {'x': 10.0})
                 System.out.println(session.runner()
                        .feed("input_x", Tensor.create(input))
                        .fetch("predict").run().get(1));
                //System.out.println("z = " + z);
            }
        }

    }
}
