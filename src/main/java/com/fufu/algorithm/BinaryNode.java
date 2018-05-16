package com.fufu.algorithm;

import java.io.Serializable;

/**
 * Created by zhoujunfu on 2018/5/14.
 */
public class BinaryNode<T extends Comparable> implements Serializable {

    T data;

    BinaryNode<T> left;

    BinaryNode<T> right;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
