package com.javaman.training.sorting;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author pengzhe
 * @date 2020/5/24 10:03
 * @description 选择排序
 * 1:选择排序一共有数组-1轮排序
 * 2:每一轮排序,又是一个循环,循环的规则(代码)
 * 2.1:先假定当前这个数是最小数
 * 2.2:然后和后面的每个数进行比较,如果发现有比当前更小的数,就重新确定最小数,并得到下标
 * 2.3:当遍历到数组的最后时,就得到本轮最小数和下标
 * 2.4:交换即可
 */
@Slf4j
public class SelectSorting {
    public static void main(String[] args) {

        int[] arr = {101, 34, 119, 1};
        // log.info("排序前:{}", arr);
        // selectSorting(arr);
        // log.info("排序后:{}", arr);

        int[] arr2 = new int[80000];
        for (int i = 0; i < 80_000; i++) {
            arr2[i] = (int) (Math.random() * 8000000);
        }

        Date time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beforeTime = simpleDateFormat.format(time);
        log.info("排序前的时间:{}", beforeTime);
        selectSorting(arr2);

        Date time2 = new Date();
        String afterTime = simpleDateFormat.format(time2);
        log.info("排序前的时间:{}", afterTime);
    }

    public static void selectSort(int[] arr) {

        log.info("初始数组:{}", Arrays.toString(arr));

        //逐步推导
        /**
         * 在推导过程中发现规律
         */
        //原始数组:101,34,119,1
        //第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值并不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值放在arr[0],即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }


        log.info("第一轮后:{}", Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值并不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值放在arr[1],即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        log.info("第二轮后:{}", Arrays.toString(arr));

        //第二轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定的最小值并不是最小的
                min = arr[j];//重置min
                minIndex = j;//重置minIndex
            }
        }
        //将最小值放在arr[2],即交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        log.info("第三轮后:{}", Arrays.toString(arr));
    }

    public static void selectSorting(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值并不是最小的
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            //将最小值放在arr[],即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
