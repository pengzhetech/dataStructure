package com.javaman.learning.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/23 12:19
 * @description 递归调用机制: 递归就是方法自己调用自己,每次调用不同的变量.
 * 当程序执行到一个方法时,就会开辟一个独立的空间(压栈)
 */

@Slf4j
public class RecursionTest {

    public static void main(String[] args) {
        // test(4);

        int result = factorial(30);
        log.info("result={}", result);

    }

    /**
     * 打印问题
     *
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }//else {
        log.info("n={}", n);
        //}
    }

    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }

    }
}
