package com.example.demo.tf;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.tensorflow.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName TensorflowModel
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/14 8:24
 * @Version 1.0
 **/
@Service
public class TensorflowModel  {

    public String cnn(String content){
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<Integer,String> mp = new HashMap<>();
        mp.put(0,"体育");
        mp.put(1,"娱乐");
        mp.put(2,"家居");
        mp.put(3,"房产");
        mp.put(4,"教育");
        mp.put(5,"时尚");
        mp.put(6,"时政");
        mp.put(7,"游戏");
        mp.put(8,"科技");
        mp.put(9,"财经");
        String p="";
        try {
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(),"data/vocab.txt");
//            File upload2 = new File(path.getAbsolutePath(),"model2");
           // p=upload2.getAbsolutePath();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\data\\vocab.txt")),
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
        int input [][] =new int[1][600];
        for(int i=0;i<1;i++){
            for(int j=0;j<600;j++){
                input[i][j]=0;
            }
        }

        int sz = content.length();
        int k=0;
        for(int i=0;i<sz&&k<600;i++){
            String tmp = String.valueOf(content.charAt(i));
            if(map.get(tmp)!=null){
                input[0][k++]=map.get(tmp);
            }

        }
        SavedModelBundle b = SavedModelBundle.load("C:\\model2", "mytag");
        Session tfSession = b.session();
        Operation operationPredict = b.graph().operation("predict");   //要执行的op
        Output output = new Output(operationPredict, 0);
        Tensor input_X = Tensor.create(input);
        Tensor out= tfSession.runner().feed("input_x",input_X).fetch(output).run().get(0);
        float [][] ans = new float[1][10];
        out.copyTo(ans);
        int index1 =getMax(ans[0]);
        return mp.get(index1);
    }
    private static int getMax(float[] a){
        float M=0;
        int index2=0;
        for(int i=0;i<10;i++){
            if(a[i]>M){
                M=a[i];
                index2=i;
            }
        }
        return index2;
    }
}
