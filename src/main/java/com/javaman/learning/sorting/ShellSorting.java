package com.javaman.learning.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/24 11:23
 * @description 希尔排序
 * 希尔排序也是一种插入排序,它是简单插入排序经过改进后的一个更高效版本,也称最小增量排序
 * 思想:
 * 把记录按下标的一定增量分组,对每组使用直接插入排序算法排序,随着增量逐渐减少
 * 每组包含的关键词越多,当增量减至1时,整个文件恰被分成一组,算法便终止
 */
@Slf4j
public class ShellSorting {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSortingByShift(arr);
    }

    public static void shellSort(int[] arr) {
        log.info("Shell排序前:{}", arr);
        /**
         * 希尔逐步推导
         */
        int temp = 0;
        //第一轮排序
        //因为第一轮排序,是将是个数据分成了5组(10/2)
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共有5组,每组有两个元素),步长是5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前这个元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        log.info("Shell第一轮排序后:{}", arr);

        //第二轮 分成2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共有5组,每组有两个元素),步长是5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前这个元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        log.info("Shell第二轮排序后:{}", arr);

        //第三轮 分成1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共有5组,每组有两个元素),步长是5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前这个元素大于加上步长后的那个元素,说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        log.info("Shell第三轮排序后:{}", arr);
    }

    /**
     * 交换法实现Shell排序
     *
     * @param arr 需要排序的数组
     */
    public static void shellSortingBySwap(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //第一轮排序
            //因为第一轮排序,是将是个数据分成了gap组,步长gap
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共有5组,每组有两个元素),步长是5
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前这个元素大于加上步长后的那个元素,说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            log.info("Shell排序第{}轮后：{}", ++count, arr);
        }
    }

    /**
     * 移位法实现Shell排序
     *
     * @param arr
     */
    public static void shellSortingByShift(int[] arr) {
        //增量gap,并逐步缩小gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素,诸葛对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出while循环后,就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }

            log.info("Shell排序后：{}", arr);
        }
    }
}
