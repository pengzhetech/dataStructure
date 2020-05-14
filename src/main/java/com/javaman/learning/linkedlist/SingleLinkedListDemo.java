package com.javaman.learning.linkedlist;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/13 23:29
 * @description
 */
@Slf4j
public class SingleLinkedListDemo {

    public static void main(String[] args) {


        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "智多星", "吴用");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        //
       /* SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(heroNode1);
        singleLinkedList.addNode(heroNode);
        singleLinkedList.addNode(heroNode3);
        singleLinkedList.addNode(heroNode4);
        singleLinkedList.list();*/


        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode3);

        singleLinkedList.list();
        log.info("修改后的");
        HeroNode newHeroNode = new HeroNode(2, "小路", "玉麒麟");
        singleLinkedList.update(newHeroNode);

        singleLinkedList.list();


    }

}

@Slf4j
class SingleLinkedList {
    /**
     * 先初始化一个头节点,头节点不要动,仅表示链表的头,不存放具体数据
     */
    HeroNode head = new HeroNode(0, "", "");

    public void addNode(HeroNode heroNode) {
        /*
          思路:当不考虑编号的顺序时
          1:找到当前链表的最后节点
          2:将最后一个节点的next域指向next即可
         */
        //因为head节点不能动,需要一个临时变量
        HeroNode temp = head;
        //遍历链表,找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后,就将temp后移
            temp = temp.next;
        }
        //当退出while循环时,temp指向链表的最后
        //将这个节点的next指向新节点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        //因为head节点不能动,需要一个临时指针来帮助找到添加的位置
        //因为是单链表,因为我们找的temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在,默认是false
        while (true) {
            //说明temp已经在链表的最后
            if (temp.next == null) {
                break;
            }
            //位置找到,就在temp的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {//希望添加的heroNode编号已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        //判断flag的值,如果flag=true
        if (flag) {
            log.info("准备插入的节点:{},已存在", heroNode.no);
        } else {
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息,根据no改,no编号不能改

    /**
     * @param newHeroNode 根据newHeroNode的no修改
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            log.info("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完整个链表
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            //没有找到
            log.info("没有找到编号{}的节点", newHeroNode.no);
        }

    }

    public void list() {
        //链表为空
        if (head.next == null) {
            log.info("链表为空");
            return;
        }
        //因为head节点不能动,需要一个临时变量
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            log.info(temp.toString());
            //将next后移,不然是死循环
            temp = temp.next;
        }
    }

}


@ToString
class HeroNode {
    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
}