package com.javaman.learning.linkedlist;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.javaman.learning.linkedlist.SingleLinkedList.findLastIndexNode;
import static com.javaman.learning.linkedlist.SingleLinkedList.reverseList;

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

        singleLinkedList.list();
        log.info("修改后的");
        HeroNode newHeroNode = new HeroNode(2, "小路", "玉麒麟");
        singleLinkedList.update(newHeroNode);
/*
        singleLinkedList.list();
        log.info("删除");
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.delete(3);
        singleLinkedList.delete(2);
        singleLinkedList.list();*/
        log.info("有效的节点个数{}", SingleLinkedList.getLength(singleLinkedList.getHead()));

        log.info("倒数第k个");
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 8);
        log.info("res:{}", res);

        log.info("-------------------反转-------------------");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();


    }

}

@Slf4j
@Data
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

    /**
     * head节点不能动,因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 我们在比较时,是temp.next.no和需要删除的节点的no比较
     * 1:我们先找到需要删除的这个节点的前一个节点temp
     * 2:temp.next=temp.next.next
     * 3:被删除的节点将不会有其他引用指向,会被垃圾回收机制回收
     */

    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//标识是否找到待删除节点的前一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //找到
            temp.next = temp.next.next;
        } else {
            log.info("无法删除,要删除的节点{}不存在", no);
        }

    }

    /**
     * 查找单链表中的倒数第K个节点
     *
     * @param head
     * @return 1:编写一个方法,接收head节点,同时接收一个index
     * 2:index表示是倒数第index个节点
     * 3:先把链表从头到尾遍历,得到链表的总的长度
     * 4:得到size后从链表的第一个开始遍历,遍历size-index个,就可以得到
     */

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        //
        int size = getLength(head);
        //第二次遍历size-index位置,就是我们倒数的第k个节点
        //先做一个index校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量,使用for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    /**
     * 反转单链表
     * 1:先定义一个节点reverseHead=new HeroNode()
     * 2:从头到尾遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表的最前端
     * 3:原来的链表的head.next=reverseHead.next
     *
     * @param head
     * @return
     */

    public static void reverseList(HeroNode head) {
        //如果当前链表为空,或者只有一个节点,直接返回
        if (head == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针,帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点[cur]的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表的最前端
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点,因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next实现单链表的反转
        head.next = reverseHead.next;

    }

    //获取单链表的节点的个数(如果是带头节点的链表,需求不统计头节点)
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
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