package com.javaman.training.linkedlist;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/18 18:44
 * @description 单向环形链表应用:约瑟夫问题
 * <p>
 * 构建单向的环形链表的思路:
 * 1:先创建第一个节点,让first指向该节点,并形成环形
 * 2:后面当我们每创建一个新的节点,加入到已有的环形链表中即可.
 * <p>
 * 遍历环形链表:
 * 1:先让一个辅助指针(变量),指向first节点
 * 2:然后通过一个while循环遍历该环形链表即可,curBoy.next=first结束
 */

public class JosepfuProblem {

    public static void main(String[] args) {
        //构建环形链表,和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);//加入5个小孩节点
        circleSingleLinkedList.showBoy();

        /**
         * 根据用户的输入,生成一个小孩出圈的顺序
         *
         * n=5;即有5个人
         *
         * 1:需要创建一个辅助指针(变量)helper,实现应该指向环形链表的最后这个节点
         * 补充:小孩报数前,先让first和helper移动k-1次
         * 2:当小孩报数时,让first和helper指针同时移动m-1次
         * 3:此时可以将first指向的小孩节点出圈,
         * first=first.next
         * helper.next=first
         * 原来first指向的节点就没有任何引用
         */

        //小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);


    }
}


//创建一个环形的单向链表
@Slf4j
class CircleSingleLinkedList {
    //创建一个first节点,当前还没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点,构建一个环形的链表
    public void add(int nums) {
        //nums做一个数据校验
        if (nums < 1) {
            //
            log.error("nums的值不正确");
            return;
        }
        //辅助指针,帮助构建环形链表
        Boy curBoy = null;

        //使用一个for循环,创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号,创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩,
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//当curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        if (first == null) {
            log.error("没有任何小孩");
            return;
        }
        //因为first不能动,因此我们仍然使用辅助指针,完成遍历
        Boy curBoy = first;
        while (true) {
            log.info("小孩的编号{}", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * @param startNo  表示从第几个小孩开始数据
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            log.info("参数有误");
            return;
        }
        //创建一个辅助指针,帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            //需要创建一个辅助指针(变量)helper,实现应该指向环形链表的最后这个节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前,先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时,让first和helper指针同时移动m-1次,然后出圈
        //这里是一个循环的操作,直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点,就是要出圈的小孩节点
            log.info("小孩{}出圈,", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        log.info("最后留在圈中的小孩编号是::{}", first.getNo());
    }
}

/**
 * 节点
 */
@Data
@ToString
class Boy {
    private int no;
    private Boy next;//指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

}