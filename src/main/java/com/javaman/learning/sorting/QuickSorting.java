package com.javaman.learning.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author pengzhe
 * @date 2020/5/24 12:12
 * @description 快速排序
 * 快速排序是对冒泡排序的一种改进,其基本思想是:
 * 通过一趟排序对将要排序的数据分割成独立的两部分,
 * 其中一部分的所有数据都比另外一部分的所有数据都要小,
 * 然后再按此方法对这两部分数据分别进行快速排序
 * 整个排序过程可以递归进行,以此达到整个数据变成有序序列
 */
@Slf4j
public class QuickSorting {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        log.info(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下表
        int r = right;//右下标
        //中州
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量,作为交换时使用
        //while循环的目的是让比pivot值小的放到左边,比pivot大的放到右边
        while (l < r) {
            //在pivot的左边一直找,找到一个大于等于pivot的值才退出
            while (arr[l] < pivot) {
                //
                l += 1;
            }
            //在pivot的右边一直找,找到一个小于等于pivot的值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r成立,说明pivot的左右两边的值,已经按照左边全部是小于等于pivot的值
            //右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后,发现arr[l]=pivot, r-=1;前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r,必须l++;r--否则栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }


    }

}
