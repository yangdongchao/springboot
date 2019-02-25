package com.example.demo.ACM;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Max;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName Solution
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/17 18:17
 * @Version 1.0
 **/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        Set<Character> se = new HashSet<>();
        int M=0,i=0,j=0;
        while(i<n&&j<n){
            if(!se.contains(s.charAt(j))){
                se.add(s.charAt(j++));
                M=Math.max(M,j-i);
            }
            else{
                se.remove(s.charAt(i++));
            }
        }
        return M;
    }
}