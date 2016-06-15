package util;

/**
 * Created by Zhaokai on 2016/6/15.
 */
public class AVLTree {

    static class AVLNode extends Tree.TreeNode
    {
        int height;

        public AVLNode(int val)
        {
            this(val,0);
        }

        public AVLNode(int val,int height)
        {
            super(val);
            this.height=height;
        }
    }

}
