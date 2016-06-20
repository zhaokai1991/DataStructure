package util;

/**
 * Created by Zhaokai on 2016/6/15.
 */
public class AVLTree {

    static class AVLNode
    {
        int val;
        AVLNode left,right;
        int height;

        public AVLNode(int val)
        {
            this(val,0);
        }

        public AVLNode(int val,int height)
        {
            this.val=val;
            this.left=null;
            this.right=null;
            this.height=height;
        }
    }

    private AVLNode root;

    public AVLTree()
    {
        root=null;
    }

    private AVLNode insert(int val,AVLNode root)
    {
        if(root==null)
        {
            root=new AVLNode(val);
            return root;
        }
        else if(val>root.val)
        {
            root.right=insert(val,root.right);

            if(Math.abs(height(root.left)-height(root.right))>1)
            {
                if(val>root.right.val)
                    singleRotateWithRight(root);
                else
                    doubleRotateWithRight(root);
            }
        }
        else if(val<root.val)
        {
            root.left=insert(val,root.left);

            if(Math.abs(height(root.left)-height(root.right))>1)
            {
                if(val<root.left.val)
                    singleRotateWithLeft(root);
                else
                    doubleRotateWithLeft(root);
            }
        }

        root.height=Math.max(height(root.left),height(root.right))+1;

        return root;
    }

    public void insert(int val)
    {
        root=insert(val,root);
    }

    private int height(AVLNode node)
    {
        if(node==null)
            return -1;
        else
            return node.height;
    }

    private AVLNode singleRotateWithLeft(AVLNode a)
    {
        AVLNode b=a.left;
        a.left=b.right;
        b.right=a;

        a.height=Math.max(height(a.left),height(a.right))+1;
        b.height=Math.max(height(b.left),height(b.right))+1;

        return b;
    }

    private AVLNode singleRotateWithRight(AVLNode a)
    {
        AVLNode b=a.right;
        a.right=b.left;
        b.left=a;

        a.height=Math.max(height(a.left),height(a.right))+1;
        b.height=Math.max(height(b.left),height(b.right))+1;

        return b;
    }

    private AVLNode doubleRotateWithLeft(AVLNode a)
    {
        a.left=singleRotateWithRight(a.left);
        return singleRotateWithLeft(a);
    }

    private AVLNode doubleRotateWithRight(AVLNode a)
    {
        a.right=singleRotateWithLeft(a.right);
        return singleRotateWithRight(a);
    }

}
