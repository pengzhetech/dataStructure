package com.javaman.learning.stack;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/18 22:30
 * @description 用栈完成表达式的计算的思路
 * 1:通过一个index值(索引),来遍历我们的表达式
 * 2:如果我们发现是一个数字,就直接入数栈
 * 3:如果发现扫描到的是一个符号,就分如下情况
 * 3.1:如果发现当前的符号栈为空,就直接入栈
 * 3.2:如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或者等于
 * 栈中的操作符,就需要从数栈中pop出两个数,在从符号栈中pop出一个符号,进行运算
 * 将得到结果,入数栈,然后将当前符号入符号栈
 * 3.3:如果当前的操作符的优先级大于栈中的操作符,就直接入符号栈.
 * 4:当表达式扫描完毕后,就顺序的从数栈和符号栈中pop出响应的数和符号,并运行
 * 5:最后数栈只有一个数字,就是表达式的结果
 * <p>
 * 验证 3+2*6-2
 * 1:
 */
@Slf4j
public class Calculator {

    public static void main(String[] args) {

        String expression = "3+2*6-2";
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);

        //定义相关变量,index
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int operator = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        while (true) {
            //依次得到expression的每一个字段
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch,做响应的处理
            if (operatorStack.isOperator(ch)) {//如果是运算符
                if (!operatorStack.isEmpty()) {
                    //处理
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operator = operatorStack.pop();
                        res = numberStack.cal(num1, num2, operator);
                        numberStack.push(res);
                        operatorStack.push(operator);
                    } else {
                        operatorStack.push(ch);
                    }
                } else {
                    operatorStack.push(ch);
                }
            } else {//如果是数
                numberStack.push(ch - 48);// "1+3"==>'1' ==>1
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //
        while (true) {
            //符号栈为空,则计算到最后的结果,则数栈中只有一个数字[结果]
            boolean empty = operatorStack.isEmpty();
            if (empty) {
                break;
            }
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operator = operatorStack.pop();
            res = numberStack.cal(num1, num2, operator);
            numberStack.push(res);
        }
        //将数栈的最后一个数pop出,就是结果
        log.info("表达式{}={}", expression, numberStack.pop());
    }
}

//先创建一个栈
@Slf4j
class ArrayStack2 {

    int maxSize;//栈的大小
    int[] stack;//数组模拟栈,数据存放在该数组中
    int top = -1;//栈顶

    public ArrayStack2(int maxSize) {
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

    /**
     * 返回当前栈顶的值,但不是真正的出栈
     *
     * @return
     */
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级,优先级由程序员来定,优先级使用数字表示,数字越大则优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    public int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        return res;
    }
}
