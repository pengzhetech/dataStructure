package com.javaman.algorithms4.datastructure.stack;

import java.util.Iterator;

/**
 * @author pengzhe
 * @date 2020/5/3 17:32
 * @description
 */

public class FixedCapacityStackImplementsByArray<Item> implements Iterable<Item> {

    private Item[] data;
    /**
     * 栈顶元素索引,数组从下到上数据索引依次增加
     * 入栈后++,出栈后--
     */
    private int popIndex;

    public FixedCapacityStackImplementsByArray(int capacity) {
        this.data = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return popIndex == 0;
    }

    public int size() {
        return popIndex;
    }

    public void push(Item item) {
        if (data.length == popIndex) {
            resize(2 * data.length);
        }
        data[popIndex++] = item;
    }

    public Item pop() {
        Item item = data[--popIndex];
        //避免对象游离
        data[popIndex] = null;
        //栈的利用率永远不会低于1/4
        if (popIndex > 0 && popIndex == data.length / 4) {
            //缩容
            resize(data.length / 2);
        }
        return item;
    }

    private void resize(int maxSize) {
        //将大小为currentIndexSize<=max的栈移动到一个新的大小为max的数组中
        Item[] temp = (Item[]) new Object[maxSize];
        for (int i = 0; i < popIndex; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int stackPopIndex = popIndex;

        public boolean hasNext() {
            return stackPopIndex > 0;
        }

        public Item next() {
            return data[--stackPopIndex];
        }

        public void remove() {

        }
    }
}
