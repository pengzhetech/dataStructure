package com.javaman.learning.sorting;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author pengzhe
 * @date 2020/5/23 23:54
 * @description 冒泡排序 O(n^2)
 * 通过对待排序序列从前向后(从下标较小的元素开始),依次比较相邻元素的值,若发现
 * 逆序则交换,使值较大的元素逐渐从前移向后部,就像水底的气泡一样逐渐向上冒
 * <p>
 * 一共进行数组大小-1次大的循环
 * 每一趟排序的次数在逐渐的减少
 * 如果我们发现在某次排序中,没有发现一次交换,可以提前结束冒泡排序,这个就是优化
 */
@Slf4j
public class BubbleSorting {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        /**
         * 为了容易理解,我们把冒泡排序的演变过程展示
         * 第一趟排序:就是将最大的元素排在最后
         */

  /*      int temp = 0;//临时变量

        for (int j = 0; j < arr.length - 1 - 0; j++) {
            //如果前面的数比后面的数大,则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        log.info("第一趟排序后的数据");
        log.info(Arrays.toString(arr));

        //第二趟排序,就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数比后面的数大,则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        log.info("第二趟排序后的数据");
        log.info(Arrays.toString(arr));  //第三趟排序,就是将第二大的数排在倒数第三位
        for (int j = 0; j < arr.length - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大,则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        log.info("第三趟排序后的数据");
        log.info(Arrays.toString(arr));
        //第四趟排序,就是将第二大的数排在倒数第四位
        for (int j = 0; j < arr.length - 1 - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大,则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        log.info("第四趟排序后的数据");
        log.info(Arrays.toString(arr));*/

        log.info("=============================");
        bubble(arr);
    }

    public static void bubble(int[] arr) {
        int temp = 0;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大,则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            log.info("第{}趟排序后的数据", i + 1);
            log.info(Arrays.toString(arr));
        }
    }

}
