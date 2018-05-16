package com.fufu.algorithm;

/**
 * Created by zhoujunfu on 2018/5/14.
 * 二叉搜索树BST
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {
    BinaryNode<T> root;

    public BinarySearchTree(){
        root =null;
    }

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
       return root == null;
    }

    /**
     * 计算大小
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        }
        int leftSize = size(p.left);
        int rightSize= size(p.right);
        return leftSize + rightSize + 1;
    }

    /**
     * 计算深度
     * @return
     */
    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        }
        int leftHeight = height(p.left);
        int rightHeight = height(p.right);

        return leftHeight>rightHeight?leftHeight+1:rightHeight+1;
    }

    /**
     * 查找最小值
     * @return
     */
    @Override
    public T findMin() {
        if (isEmpty()) {
            return null;
        }
        return findMin(root).data;
    }

    /**
     * 查找最小值节点
     * @param p
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> p) {
        if (p == null) {
            return null;
        }
        if (p.left == null) {
            return p;
        }
        return findMin(p.left);
    }

    /**
     * 查找最大值
     * @return
     */
    @Override
    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMin(root).data;
    }

    /**
     * 查找最大值节点
     * @param p
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> p) {
        if (p == null) {
            return null;
        }
        if (p.right == null) {
            return p;
        }
        return findMin(p.right);
    }

    /**
     * 插入数据
     * @param data 数据
     */
    @Override
    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("data is null!");
        }
        root = insert(data, root);
    }

    private BinaryNode<T> insert(T data, BinaryNode<T> p) {
        if (p == null) {
            return new BinaryNode<T>(data, null, null);
        }
        if (data.compareTo(p.data) < 0) {
            p.left = insert(data, p.left);
        } else if (data.compareTo(p.data) > 0) {
            p.right = insert(data, p.right);
        } else {

        }
        return p;
    }

    @Override
    public BinaryNode<T> findNode(T data) {
        return findNode(root, data);
    }

    private BinaryNode<T> findNode(BinaryNode<T> p, T data) {
        if (p == null || data == null) {
            return null;
        }
        if (data.compareTo(p.data) < 0) {
            return findNode(p.left, data);
        } else if (data.compareTo(p.data) > 0) {
            return findNode(p.right, data);
        } else {
            return p;
        }
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void remove(T data) {
        remove(root, data);
    }

    private BinaryNode<T> remove(BinaryNode<T> p, T data) {
        if (p == null) {
            return p;
        }
        int compare = data.compareTo(p.data);
        if (compare < 0) {
            p.left = remove(p.left, data);
        } else if (compare > 0) {
            p.right = remove(p.right, data);
        } else if (p.left != null && p.right != null) {
            p.data = findMin(p.right).data;
            p.right = remove(p.right, p.data);
        } else {
            p =(p.left==null)?p.left:p.right;
        }
        return p;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
