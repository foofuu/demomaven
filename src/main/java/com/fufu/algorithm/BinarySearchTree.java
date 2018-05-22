package com.fufu.algorithm;

import java.util.LinkedList;

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
    public void remove(T data) {
        remove(root, data);
    }

    /**
     * 删除节点（递归）
     *  分3种情况
     * 1.删除叶子结点(也就是没有孩子结点)
     * 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子)
     * 3.删除拥有两个孩子结点的结点
     * @param p
     * @param data
     */
    private BinaryNode<T> remove(BinaryNode<T> p, T data) {
        //没有找到要删除的元素,递归结束
        if (p == null) {
            return p;
        }
        int compare = data.compareTo(p.data);
        if (compare < 0) { //左边查找删除结点
            p.left = remove(p.left, data);
        } else if (compare > 0) { //右边查找删除结点
            p.right = remove(p.right, data);
        } else if (p.left != null && p.right != null) { //已找到结点并判断是否有两个子结点(情况3)
            //中继替换,找到右子树中最小的元素并替换需要删除的元素值
            p.data = findMin(p.right).data;
            //移除用于替换的结点
            p.right = remove(p.right, p.data);
        } else {
            p =(p.left==null)?p.left:p.right;
        }
        return p;//返回该结点
    }

    private boolean removeNoRecure(T data) {
        if (data == null) {
            return false;
        }

        BinaryNode<T> current = this.root;
        BinaryNode<T> parent = this.root;
        boolean isLeft = true;

        while (data.compareTo(current.data) != 0) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                isLeft = true;
                current = current.left;
            } else if (data.compareTo(current.data) > 0) {
                isLeft = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }

        //----------到这里说明已找到要删除的结点

        //删除的是叶子结点
        if (current.left == null && current.right == null) {
            if (current == this.root) {
                this.root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {   //删除带有一个孩子结点的结点,当current的right不为null
            if (current == this.root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {   //删除带有一个孩子结点的结点,当current的right不为null
            if (current == this.root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right =  current.left;
            }
        } else {
            BinaryNode<T> successor = findSuccessor(current);
            if (current == root) {
                this.root = successor;
            } else if (isLeft) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    private BinaryNode<T> findSuccessor(BinaryNode<T> delNode) {
        BinaryNode<T> successor = delNode;
        BinaryNode<T> successorParent = successor;
        BinaryNode<T> current = delNode.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;

    }

    @Override
    public String preOrder() {
        String sb=preOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    private String preOrder(BinaryNode<T> p) {
        StringBuilder stringBuilder = new StringBuilder();
        if (p != null) {
            stringBuilder.append(p.data + ",");
            stringBuilder.append(preOrder(p.left));
            stringBuilder.append(preOrder(p.right));
        }
        return stringBuilder.toString();
    }

    private String preOrderTraverse() {
        StringBuilder stringBuilder = new StringBuilder();
        BinaryNode<T> p = this.root;
        LinkedList<BinaryNode<T>> stack = new LinkedList<>();
        while (p !=null || !stack.isEmpty()) {
            if (p != null) {
                stringBuilder.append(p.data + ",");
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        //去掉最后一个逗号
        if(stringBuilder.length()>0){
            return stringBuilder.toString().substring(0,stringBuilder.length()-1);
        }else {
            return stringBuilder.toString();
        }
    }

    @Override
    public String inOrder() {
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    private String inOrder(BinaryNode<T> p) {
        StringBuilder stringBuilder = new StringBuilder();
        if (p != null) {
            stringBuilder.append(inOrder(p.left));
            stringBuilder.append(p.data + ",");
            stringBuilder.append(inOrder(p.right));
        }
        return stringBuilder.toString();
    }

    private String inOrderTraverse() {
        StringBuilder stringBuilder = new StringBuilder();
        BinaryNode<T> p = this.root;
        LinkedList<BinaryNode<T>> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                stringBuilder.append(p.data + ",");
                p = p.right;

            }
        }
        if(stringBuilder.length()>0){
            return stringBuilder.toString().substring(0,stringBuilder.length()-1);
        }else {
            return stringBuilder.toString();
        }
    }

    @Override
    public String postOrder() {
        return postOrder(root);
    }

    private String postOrder(BinaryNode<T> p) {
        StringBuilder stringBuilder = new StringBuilder();
        if (p != null) {
            stringBuilder.append(postOrder(p.left));
            stringBuilder.append(postOrder(p.right));
            stringBuilder.append(p.data + ",");
        }
        return stringBuilder.toString();
    }

    private String postOrderTraverse()  {
        BinaryNode<T> current = this.root;
        BinaryNode<T> prev = this.root;
        LinkedList<BinaryNode<T>> stack = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (current.right != null && current.right != prev) {
                stack.push(current);
                current = current.right;
                while (current != null) {
                    stack.push(current);
                    current = current.right;
                }
            } else {
                stringBuilder.append(current.data  + ",");
                prev = current;
            }
        }
        if(stringBuilder.length()>0){
            return stringBuilder.toString().substring(0,stringBuilder.length()-1);
        }else {
            return stringBuilder.toString();
        }
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {
    }
}
