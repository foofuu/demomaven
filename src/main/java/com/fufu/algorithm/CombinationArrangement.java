package com.fufu.algorithm;

import java.util.Arrays;

/**
 * 排列组合算法类
 * Created by zhoujunfu on 2018/8/1.
 */
public class CombinationArrangement {

    /**
     * 计算阶乘函数，递归实现
     * @param n n的阶乘
     * @return
     */
    public static long factorial(int n) {
        return n > 1 ?  n * factorial(n - 1) : 1;
    }

    /**
     * 计算排列数 A(n,m)=n!/(n-m)!
     * @param n
     * @param m
     * @return
     */
    public static long arrangement(int n, int m) {
        return n >= m ? factorial(n) / factorial(n - m ) : 0;
    }

    /**
     * 计算组合数 C(n,m)=A(n,m)/m!=n!/(m!*(n-m)!)=C(n,n-m)
     * @param n
     * @param m
     * @return
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / (factorial(m) * factorial(n-m)) : 0;

    }

   public static void combinationSelect(String[] dataList, int n) {
        combinationSelect(dataList, 0, new String[n], 0);
   }

   public static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length; // 需要的结果数组长度
        int resultCount = resultIndex + 1; // 目前选择的是第几个结果数据
        if (resultCount > resultLen) {     // 全部选择完，输出结果
            System.out.println(Arrays.asList(resultList));
            return;
        }
        for (int i = dataIndex; i < dataList.length - resultLen + resultCount; i++) {
           resultList[resultIndex] = dataList[i];
           combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
       }

   }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(new String[]{"1","2","3","4","5"}));

        combinationSelect(new String[]{"1","2","3","4","5"}, 3);
    }


}
