package com.javaman.training.stack;

import com.google.common.collect.Lists;
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
         * [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]===>
         *
         */

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        log.info("中缀表达式对应的list:{}", strings);//[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        List<String> strings1 = parseSuffixExpressionList(strings);
        log.info("后缀表达式对应的list:{}", strings1);

        int calculate = calculate(strings1);
        log.info("结果:{}",calculate);
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

    //
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明:因为s2这个栈在整个转换的过程中没有pop的操作,而且后面我们还需要逆序输出,因此比较麻烦,这里我们就不用栈了
        //而直接使用list替代
        //  Stack<String> s2 = new Stack<>();//存储中间结果的栈s2
        List<String> s2 = Lists.newArrayList();
        for (String item : ls) {
            //如果是一个数,就入栈s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈,消除小括号
            } else {
                //当item的优先级小于等于栈顶的运算符的优先级,
                //将S1栈顶的运算符弹出并压入到S2中,再次转到4.1与S1中新的栈顶运算符比较
                // 我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符移到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
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

class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在");
                break;
        }
        return result;
    }
}
