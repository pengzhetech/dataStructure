package com.javaman.training.search;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/25 22:30
 * @description
 */
@Slf4j
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int i = seqSearch(arr, 34);
        log.info("i::{}", i);
    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
