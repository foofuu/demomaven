package com.fufu.algorithm;

import java.util.LinkedList;

/**
 * Created by zhoujunfu on 2018/9/17.
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.setLeft(new TreeNode<Integer>(2));
        root.setRight(new TreeNode<Integer>(3));

        breadthFirst(root);
    }

    /**
     * 递归前序遍历
     * @param root
     */
    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.getData());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    /**
     * 非递归前序遍历
     * @param root
     */
    public static void preOrderNonRec(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> list = new LinkedList<>();
            list.push(root);
            while (!list.isEmpty()) {
                TreeNode current = list.pop();
                System.out.println(current.getData());
                if (current.getRight() != null) {
                    list.push(current.getRight());
                }
                if (current.getLeft() != null) {
                    list.push(current.getLeft());
                }
            }
        }
    }

    /**
     * 非递归中序遍历
     * @param root
     */
    public static void inOrderNonRec(TreeNode root) {
       if (root != null) {
           LinkedList<TreeNode> list = new LinkedList<>();
           TreeNode current = root;
           while (current != null || !list.isEmpty()) {
               if (current != null) {
                   list.push(current);
                   current = current.getLeft();
               } else {
                   current = list.pop();
                   System.out.println(current.getData());
                   current = current.getRight();
               }
           }
       }
    }

    /**
     * 非递归后续遍历
     * @param root
     */
    public static void postOrderNonRec(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> list = new LinkedList<>();
            TreeNode current = root;
            TreeNode lastVisit = null;
            while (current != null || !list.isEmpty()) {
                if (current != null) {
                    list.push(current);
                    current = current.getLeft();
                } else {
                    TreeNode top = list.peek();
                    if (top.getRight() != null && lastVisit != top.getRight()) {
                        current = top.getRight();
                    } else {
                        System.out.println(top.getData());
                        lastVisit = list.pop();
                    }
                }
            }
        }
    }

    /**
     * 广度优先遍历
     * @param root
     */
    public static void breadthFirst(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> list = new LinkedList<>();
            list.addLast(root);
            while (!list.isEmpty()) {
                TreeNode current = list.removeFirst();
                System.out.println(current.getData());
                if (current.getLeft() != null) {
                    list.addLast(current.getLeft());
                }
                if (current.getRight() != null) {
                    list.addLast(current.getRight());
                }
            }
        }
    }





}
