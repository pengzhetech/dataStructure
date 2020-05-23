package com.javaman.learning.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author pengzhe
 * @date 2020/5/19 07:55
 * @description 后缀表达式求值
 */
@Slf4j
public class PolandNotation {
    public static void main(String[] args) {

        /**
         * 中缀表达式转后缀表达式
         * 1:1+((2+3)*4)-5
         * 2:因为直接对string操作不方便,因此现将1+((2+3)*4)-5转成list
         * 即1+((2+3)*4)-5==》ArrayList[1,+,(,(,........]
         *
         */

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println(strings);

        //先定义一个逆波兰表达式 (3+4)*5-6==>3 4+5 *6 -
        //(30+4)*5-6==>30 4+5 *6 -
        //4*5-8+60+8==>4 5 * 8 - 60 +8 2 / +
        //为了方便,数字和符号使用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        /**
         * 1:先将suffixExpression装入到ArrayList中
         * 2:将ArrayList传给一个方法,遍历ArrayList,配合栈完成计算
         */
        //  List<String> listString = getListString(suffixExpression);

        //  int res = calculate(listString);
        // log.info("计算的结果是:{}", res);

    }


    //将一个逆波兰表达式,依次将表达式和运算符放入到ArrayList
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针,用于遍历s
        String str;//对多位树的拼接
        char c;//每遍历到一个字符,就放入到c
        do {
            //如果c是一个非数字,我们就需要加入到ls中
            if ((c = s.charAt(i)) <= 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {
                //如果是一个数,需要考虑多位数的问题
                str = "";//先将string置成空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
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
