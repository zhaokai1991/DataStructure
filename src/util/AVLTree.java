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

    private AVLNode rightRotate(AVLNode a)
    {
        AVLNode b=a.left;
        a.left=b.right;
        b.right=a;

        a.height=Math.max(a.left.height,a.right.height)+1;
        b.height=Math.max(b.left.height,b.right.height)+1;

        return b;
    }

}
