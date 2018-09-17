package com.fufu.algorithm;

/**
 * Created by zhoujunfu on 2018/5/14.
 */
public interface Tree<T extends Comparable> {

    boolean isEmpty();

    int size();

    int height();

    String preOrder();

    String inOrder();

    String postOrder();

    String levelOrder();

    void insert(T data);

    void remove(T data);

    T findMin();

    T findMax();

    TreeNode findNode(T data);

    boolean contains(T data) throws Exception;

    void clear();
}
