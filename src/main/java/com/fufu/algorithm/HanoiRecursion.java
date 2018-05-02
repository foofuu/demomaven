package com.fufu.algorithm;

/**
 * Created by zhoujunfu on 2018/4/28.
 */
public class HanoiRecursion {

    /**
     *
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public void hanoi(int n, char a, char b, char c) {
        if (n == 0) {
            return;
        } else {
            hanoi(n-1, a, c, b);
            System.out.println(a + "-->" + b);
            hanoi(n-1, c, b, a);
        }
    }

    public int hanoiCount(int n, char a, char b, char c) {
        int moveCount = 0;
        if (n == 0) {
            return 0;
        } else {
            moveCount += hanoiCount(n-1, a, c, b);
            moveCount += 1;
            moveCount += hanoiCount(n-1, c, b, a);
        }
        return moveCount;
    }

    public static void main(String[] args) {
        HanoiRecursion hanoiRecursion = new HanoiRecursion();
        hanoiRecursion.hanoi(2, 'A', 'B', 'C');
    }
}
