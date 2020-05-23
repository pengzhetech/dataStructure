package com.javaman.learning.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/23 12:19
 * @description 递归调用机制:
 * 当程序执行到一个方法时,就会开辟一个独立的空间(压栈)
 *
 */

@Slf4j
public class RecursionTest {

    public static void main(String[] args) {
        test(4);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        log.info("n={}", n);

    }
}
