package com.javaman.algorithms4.datastructure.stack;

/**
 * @author pengzhe
 * @date 2020/5/3 16:53
 * @description 字符定容栈
 * 数组中的元素顺序和它们被插入的顺序相同
 * 当currentIndexSize为0时,栈为空
 * 栈的顶部位于a[currentIndexSize-1](如果栈非空)
 */

public class FixedCapacityStackStringsImplementsByArray {

    private final String[] data;
    private int currentIndexSize;

    public FixedCapacityStackStringsImplementsByArray(int capacity) {
        this.data = new String[capacity];
    }

    public boolean isEmpty() {
        return currentIndexSize == 0;
    }

    public int size() {
        return currentIndexSize;
    }

    public void push(String item) {
        data[currentIndexSize++] = item;
    }

    public String pop() {
        return data[--currentIndexSize];
    }
}
