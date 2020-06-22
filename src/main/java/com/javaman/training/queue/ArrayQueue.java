package com.javaman.training.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author pengzhe
 * @date 2020/5/13 21:52
 * @description
 */
@Slf4j
public class ArrayQueue {

    public static void main(String[] args) {
        Queue queue = new Queue(3);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.showQueue();
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            log.info("s(show):显示队列");
            log.info("e(exit):对出程序");
            log.info("a(add):添加队列元素");
            log.info("s(show):显示队列");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    log.info("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据%d\n", res);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    break;
            }
        }
    }
}

@Slf4j
//数组模拟队列
class Queue {

    /**
     * 数组最大容量
     */
    private final int maxSize;
    /**
     * 队列头(不含),即队列头的前一个位置
     */
    private int front;
    /**
     * 队列尾(含)
     */
    private int real;

    /**
     * 存储数据
     */
    private final int[] arr;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        real = -1;
    }

    /**
     * 队列满
     *
     * @return
     */
    public boolean isFull() {
        return real == maxSize - 1;
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
        real++;
        arr[real] = n;
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
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            log.error("队列空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            log.error("队列空");
            throw new RuntimeException("队列空 没有数据");
        }
        return arr[front + 1];
    }
}
