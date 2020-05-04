package com.javaman.algorithms4.datastructure.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * @author pengzhe
 * @date 2020/5/4 10:20
 * @description
 */

public class StackImplementsByNodeTest {

    @Test
    public void test() {
        StackImplementsByNode<String> stringStackImplementsByNode = new StackImplementsByNode<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stringStackImplementsByNode.push(item);
            } else if (!stringStackImplementsByNode.isEmpty()) {
                StdOut.print(stringStackImplementsByNode.pop() + "");
            }
            StdOut.println("(" + stringStackImplementsByNode.size() + "left on stack");
        }
    }
}
