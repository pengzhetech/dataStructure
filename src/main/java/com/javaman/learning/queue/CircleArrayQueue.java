package com.javaman.learning.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author pengzhe
 * @date 2020/5/13 21:52
 * @description
 */
@Slf4j
public class CircleArrayQueue {

    public static void main(String[] args) {
        log.info("数组模拟环形队列");
        CircleArray circleArray = new CircleArray(3);
        circleArray.addQueue(1);
        circleArray.addQueue(2);
        circleArray.addQueue(3);
        circleArray.showQueue();
    }
}

@Slf4j
//数组模拟环形队列
class CircleArray {

    /**
     * 数组最大容量
     */
    private final int maxSize;
    /**
     * 指向队列的第一个元素,也就是说arr[front]
     */
    private int front;
    /**
     * 队列尾的后一个元素,因为希望空出一个空间做约定
     */
    private int real;

    /**
     * 存储数据
     */
    private final int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        real = 0;
    }

    /**
     * 队列满
     *
     * @return
     */
    public boolean isFull() {
        return (real + 1) % maxSize == front;
    }

    /**
     * 队列空
     *
     * @return test
     */
    public boolean isEmpty() {
        return real == front;
    }

    /**
     * 入队
     *
     * @param n test
     */
    public void addQueue(int n) {
        if (isFull()) {
            log.info("队列满,无法入队");
            return;
        }
        arr[real] = n;
        //将real后移,这里必须考虑取模
        real = (real + 1) % maxSize;
    }

    /**
     * 出队
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            log.info("队列空");
            throw new RuntimeException("队列空");
        }
        //这里需要分析出front是指向队列的第一个元素
        //先把front对应的值保留到一个临时变量
        //将front后移,考虑取模,不然会数组越界
        //将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            log.error("队列空");
            return;
        }
        //从front开始遍历,遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * @return 当前队列有效数据的个数
     */
    public int size() {
        return (real + maxSize - front) % maxSize;
    }

    /**
     * @return 显示头元素
     */
    public int headQueue() {
        if (isEmpty()) {
            log.error("队列空");
            throw new RuntimeException("队列空 没有数据");
        }
        return arr[front];
    }
}
