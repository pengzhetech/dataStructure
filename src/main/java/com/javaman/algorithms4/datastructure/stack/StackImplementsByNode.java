package com.javaman.algorithms4.datastructure.stack;

import com.javaman.algorithms4.datastructure.node.Node;

import java.util.Iterator;

/**
 * @author pengzhe
 * @date 2020/5/4 09:59
 * @description
 */

public class StackImplementsByNode<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int N;


    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        //向栈顶添加元素
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        //从栈顶删除元素
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
        }
    }

}
