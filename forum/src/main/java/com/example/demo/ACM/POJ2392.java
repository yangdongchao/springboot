package com.example.demo.ACM;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @ClassName POJ2392
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/28 8:29
 * @Version 1.0
 **/
public class POJ2392  {
    final static int maxn=1000005;
    public static int [] dp = new int [maxn];

    public static void main(String [] args) {
        int k;
        Scanner cin = new Scanner(System.in);
        k = cin.nextInt();
        Point [] p = new Point[k+100];
        int M=0;
        for(int i=0;i<k;i++){
            int x,y,z;
            x=cin.nextInt();
            y=cin.nextInt();
            z=cin.nextInt();
            p[i] =new Point();
            p[i].h=x;
            p[i].a=y;
            p[i].c=z;
            M=Math.max(M,p[i].a);
        }
        Arrays.sort(p,new cmp());
        int cnt;
        for(int i=0;i<k;i++){
            for(int j=1;j<=p[i].c;j*=2){
                cnt=j*p[i].h;
                for(int t=p[i].a;t>=cnt;t--){
                    dp[t]=Math.max(dp[t],dp[t-cnt]+cnt);
                }
                p[i].c-=j;
            }
            if(p[i].c>0){
                cnt=p[i].c*p[i].h;
                for(int t=p[i].a;t>=cnt;t--){
                    dp[t]=Math.max(dp[t],dp[t-cnt]+cnt);
                }
            }
        }
        int ans=0;
        for(int i=M;i>=0;i--){
            ans=Math.max(ans,dp[i]);
        }
        System.out.println(ans);
        cin.close();
    }
}

class cmp implements Comparator<Point>{
    public int compare(Point a,Point b){
        return a.a-b.a;
    }
}

class Point{
    int h,a,c;
}
