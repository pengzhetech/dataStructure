package com.javaman.self.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        HeroNode first = new HeroNode(1, "第一个", null);
        HeroNode second = new HeroNode(2, "第二个", null);
        linkedList.add(first);
        linkedList.add(second);

        linkedList.showList();
    }

}


class LinkedList {

    HeroNode head = new HeroNode(0, "", null);

    public void add(HeroNode node) {
        HeroNode cur = head;
        while (true) {
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        cur.next = node;
    }

    public void showList() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.next;
        }
    }
}

class HeroNode {

    int no;
    String name;
    HeroNode next;

    public HeroNode(int no, String name, HeroNode next) {
        this.no = no;
        this.name = name;
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}