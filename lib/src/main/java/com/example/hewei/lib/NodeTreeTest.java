package com.example.hewei.lib;

import jdk.nashorn.internal.ir.BinaryNode;

/**
 * 二叉树的题目普遍可以用递归和迭代的方式来解
 */
public class NodeTreeTest {

    public static void main(String[] args) {

    }

    class TreeNode {
        int val;
        //左孩子
        TreeNode left;
        //右孩子
        TreeNode right;
    }

    /**
     * 二叉树的最大深度
     *
     * @param node
     * @return
     */
    public int getMAxDepth(TreeNode node) {
        if (node == null) return 0;

        int left = getMAxDepth(node.left);
        int right = getMAxDepth(node.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int getMinDepth(TreeNode root) {
        if (root == null) return 0;

        return getMin(root);
    }

    public int getMin(TreeNode node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = getMin(node.left);
        int right = getMin(node.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 获取二叉树的节点数
     *
     * @param node
     * @return
     */
    public int getNumOfTreeNode(TreeNode node) {
        if (node == null) return 0;

        int left = getNumOfTreeNode(node.left);
        int right = getNumOfTreeNode(node.right);
        return left + right + 1;
    }

    /**
     * 求二叉树中叶子节点数
     *
     * @param node
     * @return
     */
    public int numsOfNoChildNode(TreeNode node) {
        if (node == null) return 0;

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = numsOfNoChildNode(node.left);
        int right = numsOfNoChildNode(node.right);
        return left + right;
    }

    /**
     * 求二叉树中第k层节点的个数
     *
     * @param root
     * @param k
     * @return
     */
    int numsOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        int left = numsOfkLevelTreeNode(root.left, k - 1);
        int right = numsOfkLevelTreeNode(root.right, k - 1);
        return left + right;
    }

    /**
     * 判断二叉树是否是平衡二叉树
     * <p>
     * 第一步先实现求解 二叉树中每个结点的高度的函数height(BinaryNode )；
     * 然后先序遍历二叉树中的每一个结点node，调用height()求出该结点的左子树高度height(node.left) 和 右子树高度 height(node.right)。根据左右子树的高度判断是否为平衡二叉树。
     *
     * @param node
     * @return
     */
    boolean isBalanced(TreeNode node) {
        if (node == null) return true;

        int left_height = height(node.left);
        int right_height = height(node.right);
        if (Math.abs(left_height - right_height) > 1) {
            return false;
        } else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_height = height(root.left);
        int right_height = height(root.right);
        return 1 + (left_height > right_height ? left_height : right_height);
    }


    /**
     * 判断二叉树是否是完全二叉树
     * <p>
     * 完全二叉树是由满二叉树而引出来的。对于深度为K的，有n个结点的二叉树，当且仅当其每一个结点都与深度为K的满二叉树中编号从1至n的结点一一对应时称之为完全二叉树。
     * 特点: 叶子结点只可能在最大的两层出现
     * <p>
     * https://baike.baidu.com/item/%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91/7773232?fr=aladdin
     *
     * @param root
     * @return
     */
    boolean isCompleteTreeNode(TreeNode root) {
        return true; //TODO
    }


    /**
     * 判断两个二叉树是否相同
     *
     * @param t1
     * @param t2
     * @return
     */
    boolean isSameTreeNode(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isSameTreeNode(t1.left, t2.left) && isSameTreeNode(t1.right, t2.right);
    }


    /**
     * 判断两个二叉树是否互为镜像
     *
     * @param t1
     * @param t2
     * @return
     */
    boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * 翻转二叉树or镜像二叉树
     *
     * @param node
     * @return
     */
    TreeNode mirrorTreeNode(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode left = mirrorTreeNode(node.left);
        TreeNode right = mirrorTreeNode(node.right);

        node.left = right;
        node.right = left;
        return node;
    }

    /**
     * 求两个节点的最低公共祖先节点
     *
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.left, t1)) {
            if (findNode(root.right, t2)) {
                return root;
            } else {
                return getLastCommonParent(root.left,t1,t2);
            }
        } else {
            if(findNode(root.left,t2)) {
                return root;
            } else {
                return getLastCommonParent(root.left,t1,t2);
            }
        }
    }

    //查找节点node是否在当前 二叉树中
    boolean findNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        return findNode(root.left, node) && findNode(root.right, node);
    }

}
