package com.example.demo.ACM;


import java.util.Arrays;
import java.util.Scanner;


/**
 * @ClassName HDU1059
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/27 8:18
 * @Version 1.0
 **/
public class HDU1059  {
    final static int maxn=100005;
    public static int [] v = new int[maxn];
    public static int [] w = new int [maxn];
    public static int [] dp = new int [maxn];
    public static int [] num =new int [10];
    public  static  void main(String []args){
        Scanner cin = new Scanner(System.in);
        int t= 1;
        int time=0;
        while(true){
            t=1;
            int cnt=0;
            int sum=0;
            for(int i=0;i<6;i++){
                num[i]=cin.nextInt();
                sum+=num[i]*(i+1);
                if(num[i]!=0){
                    t=0;
                }
                for(int j=1;j<=num[i];j*=2){
                    v[cnt]=j*(i+1);
                    cnt++;
                    num[i]-=j;
                }
                if(num[i]>0){
                    v[cnt]=num[i]*(i+1);
                    cnt++;
                }
            }
            if(t==1){
                break;
            }
            System.out.println("Collection #"+(++time)+":" );
            if((sum&1)!=0){
                System.out.println("Can't be divided.");
                continue;
            }
            Arrays.fill(dp,0);
            for(int i=0;i<cnt;i++){
                for(int j=sum/2;j>=v[i];j--){
                        dp[j]= Math.max(dp[j],dp[j-v[i]]+v[i]);
                }
            }
            if(dp[sum/2]==sum/2){
                System.out.println("Can be divided.");
            }
            else{
                System.out.println("Can't be divided.");
            }
        }
    }
}
