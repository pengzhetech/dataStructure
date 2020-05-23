package com.javaman.learning.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/23 12:19
 * @description 递归调用机制: 递归就是方法自己调用自己,每次调用不同的变量.
 * 当程序执行到一个方法时,就会开辟一个独立的空间(压栈)
 * 每个空间的数据(局部变量)是独立的
 * <p>
 * 递归:
 * 1:执行一个方法时,就会创建一个新的独立的受保护的栈空间
 * 2:方法中的变量,是独立的,不会相互影响
 * 3:如果方法中使用的是引用类型的变量,就会共享该引用类型的数据
 * 4:递归必须向退出递归的条件逼近,否则就是无限递归,StackOverFlowError
 * 5:当一个方法执行完毕,或者遇到return,就会返回,遵守谁调用,就将结果返回给谁
 * 同时当方法执行完毕或者返回时,该方法也就执行完毕
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
