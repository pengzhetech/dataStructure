package com.javaman.learning.sorting;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/24 10:42
 * @description 插入排序
 */
@Slf4j
public class InsertSorting {

    public static void main(String[] args) {
        int[] arr = {334324,101, 34, 119, 1,-11,0};
        log.info("排序前:{}", arr);
        insertSorting(arr);
    }

    public static void insertSort(int[] arr) {
        /**
         * 逐步推导
         */
        //第一轮
        //定义待插入的数
        int insertValue = arr[1];
        //arr[1]的前面这个数的下标
        int insertIndex = 1 - 1;
        //给insertValue找到插入的位置
        //insertIndex >= 0保证给insertValue找插入位置时,不越界
        //insertValue < arr[insertIndex说明待插入的数还没有找到适当的插入位置
        //需要将insertIndex的值向右移动 arr[insertIndex]
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex + 1] = insertValue;
        log.info("第一轮插入后:{}", arr);

        //第二轮

        //定义待插入的数
        insertValue = arr[2];
        //arr[1]的前面这个数的下标
        insertIndex = 2 - 1;
        //给insertValue找到插入的位置
        //insertIndex >= 0保证给insertValue找插入位置时,不越界
        //insertValue < arr[insertIndex说明待插入的数还没有找到适当的插入位置
        //需要将insertIndex的值向右移动 arr[insertIndex]
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex + 1] = insertValue;
        log.info("第二轮插入后:{}", arr);


        //第三轮

        //定义待插入的数
        insertValue = arr[3];
        //arr[1]的前面这个数的下标
        insertIndex = 3 - 1;
        //给insertValue找到插入的位置
        //insertIndex >= 0保证给insertValue找插入位置时,不越界
        //insertValue < arr[insertIndex说明待插入的数还没有找到适当的插入位置
        //需要将insertIndex的值向右移动 arr[insertIndex]
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex + 1] = insertValue;
        log.info("第三轮插入后:{}", arr);
    }

    public static void insertSorting(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertValue = arr[i];
            //arr[1]的前面这个数的下标
            int insertIndex = i - 1;
            //给insertValue找到插入的位置
            //insertIndex >= 0保证给insertValue找插入位置时,不越界
            //insertValue < arr[insertIndex说明待插入的数还没有找到适当的插入位置
            //需要将insertIndex的值向右移动 arr[insertIndex]
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明插入的位置找到,insertIndex+1
            arr[insertIndex + 1] = insertValue;
            log.info("第{}轮插入后:{}", i, arr);
        }
    }
}
