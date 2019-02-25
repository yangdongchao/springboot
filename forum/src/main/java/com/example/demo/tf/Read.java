package com.example.demo.tf;

/**
 * @ClassName Read
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/12 8:21
 * @Version 1.0
 **/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  java.math.*;
import java.util.Random;
import org.tensorflow.*;
public class Read {

    private static final Integer ONE = 1;

    public static void main(String[] args) {
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
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./src/main/resources/data/vocab.txt")),
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
        int max=1000;
        int min=1;
        Random random = new Random();
        for(int i=0;i<1;i++){
            for(int j=0;j<600;j++){
               // input[i][j]=random.nextInt(max)%(max-min+1) + min;
                input[i][j]=0;
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./src/main/resources/data/test.txt")),
                    "utf-8"));
            String lineTxt = null;
            int idx =0 ;
            while ((lineTxt = br.readLine()) != null) {
                int sz =lineTxt.length();
                System.out.println(lineTxt);
                for(int k=0;k<1;k++) {
                    for (int i = 0; i < sz; i++) {
                        String tmp = String.valueOf(lineTxt.charAt(i));
                        //System.out.print(tmp+" ");
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
        for(int i=0;i<600;i++){
            System.out.print(input[0][i]+" " );
            if(i%100==0){
                System.out.println();
            }
        }
        SavedModelBundle b = SavedModelBundle.load("./src/main/resources/model2", "mytag");
        Session tfSession = b.session();
        Operation operationPredict = b.graph().operation("predict");   //要执行的op
        Output output = new Output(operationPredict, 0);
        Tensor input_X = Tensor.create(input);
        Tensor out= tfSession.runner().feed("input_x",input_X).fetch(output).run().get(0);
        System.out.println(out);
        float [][] ans = new float[1][10];
        out.copyTo(ans);
        float M=0;
        int index1=0;
        index1 =getMax(ans[0]);
        System.out.println(index1);
        System.out.println("------");
        System.out.println(mp.get(index1));

        //System.out.println(mp.get(getMax(ans[1])));
    }

    public static int getMax(float[] a){
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