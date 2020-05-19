package com.javaman.learning.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author pengzhe
 * @date 2020/5/19 07:55
 * @description
 */
@Slf4j
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式 (3+4)*5-6==>3 4+5 *6 -
        //为了方便,数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        /**
         * 1:先将suffixExpression装入到ArrayList中
         * 2:将ArrayList传给一个方法,遍历ArrayList,配合栈完成计算
         * 3:
         */
        List<String> listString = getListString(suffixExpression);

        int res = calculate(listString);
        log.info("计算的结果是:{}", res);

    }


    //将一个逆波兰表达式,依次将表达式和运算符放入到ArrayList
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    public static int calculate(List<String> ls) {
        //创建一个栈,只需要一个即可
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //直接入栈
                stack.push(item);
            } else {
                //pop出两个数,并运算,在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equalsIgnoreCase("+")) {
                    res = num1 + num2;
                } else if (item.equalsIgnoreCase("-")) {
                    res = num1 - num2;
                } else if (item.equalsIgnoreCase("*")) {
                    res = num1 * num2;
                } else if (item.equalsIgnoreCase("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("不支持的操作,运算符有误");
                }
                stack.push(res + "");
            }
        }
        //最后留在栈中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
