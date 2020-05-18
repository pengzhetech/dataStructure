package com.javaman.learning.linkedlist;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/17 16:31
 * @description
 */
@Slf4j
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "智多星", "吴用");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(heroNode1);
        doubleLinkedList.addNode(heroNode2);
        doubleLinkedList.addNode(heroNode3);
        doubleLinkedList.addNode(heroNode4);
        doubleLinkedList.list();

        HeroNode2 heroNode5 = new HeroNode2(4, "公孙胜", "入云龙");

        doubleLinkedList.update(heroNode5);

        log.info("修改后的");
        doubleLinkedList.list();

        doubleLinkedList.delete(3);
        log.info("删除后的链表情况");
        doubleLinkedList.list();
    }


}

@Slf4j
class DoubleLinkedList {
    /**
     * 先初始化一个头节点,头节点不要动,仅表示链表的头,不存放具体数据
     */
    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }


    public void addNode(HeroNode2 heroNode) {
        /*
          思路:当不考虑编号的顺序时
          1:找到当前链表的最后节点
          2:将最后一个节点的next域指向next即可
         */
        //因为head节点不能动,需要一个临时变量
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    /**
     * @param newHeroNode 根据newHeroNode的no修改,双向链表的节点内容修改和单项链表一样
     *                    只是节点类型给成了HeroNode2
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            log.info("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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

    /**
     * 1:对于双向链表,我们可以直接找到要删除的节点,
     * 2:找到后,自我删除
     *
     * @param no
     */
    public void delete(int no) {

        //判断当前节点是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //找到
            // temp.next = temp.next.next[单项链表];
            temp.pre.next = temp.next;
            //这里代码有问题,
            //如果是最后一个节点,不需要执行下面这段,否则会出空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            log.info("无法删除,要删除的节点{}不存在", no);
        }
    }

    public void list() {
        //链表为空
        if (head.next == null) {
            log.info("链表为空");
            return;
        }
        //因为head节点不能动,需要一个临时变量
        HeroNode2 temp = head.next;
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

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

