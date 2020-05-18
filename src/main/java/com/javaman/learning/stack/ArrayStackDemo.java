package com.javaman.learning.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author pengzhe
 * @date 2020/5/18 20:16
 * @description 使用数组模拟栈
 * 1:定义一个top来表示栈顶,初始化为-1
 * 2:入栈,当有数据加入到栈时,top++;stack[top]=data
 * 3:出栈,int value=stack[top];top--;return value;
 */
@Slf4j
public class ArrayStackDemo {

    public static void main(String[] args) {

        //创建一个ArrayStack
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            log.info("show:表示显示栈");
            log.info("exit:表示退出程序");
            log.info("push:表示添加数据到栈(入栈)");
            log.info("pop:表示从栈取出数据(出栈)");
            log.info("");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    log.info("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        log.info("出栈的数据是{}", pop);
                    } catch (Exception e) {
                        log.error("{}", e.fillInStackTrace());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        log.info("程序已经退出");

    }

    @Slf4j
    static class ArrayStack {

        int maxSize;//栈的大小
        int[] stack;//数组模拟栈,数据存放在该数组中
        int top = -1;//栈顶

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int value) {
            //先判断栈是否满
            if (isFull()) {
                log.info("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }

        /**
         * 出栈,将栈顶的数据返回
         *
         * @return
         */
        public int pop() {
            if (isEmpty()) {
                //
                throw new RuntimeException("栈空,没有数据");
            }
            int value = stack[top];
            top--;
            return value;
        }

        public void list() {
            if (isEmpty()) {
                log.info("栈空,没有数据");
                return;
            }
            for (int i = top; i >= 0; i--) {
                log.info("stack{},{}", i, stack[i]);
            }
        }
    }
}
