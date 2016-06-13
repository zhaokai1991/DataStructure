import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zhaokai on 16-6-2.
 */


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val)
    {
        this.val=val;
        left=null;
        right=null;
    }
}

public class Tree {

    TreeNode root;

    public Tree()
    {
        root=null;
    }

    public Tree(int[] vals)
    {
        this();
        for(int val:vals)
            insert(val);
    }

    public void insert(int val)
    {
        if(root==null)
        {
            root=new TreeNode(val);
            return;
        }

        TreeNode p=root;
        TreeNode parent=null;

        while(p!=null)
        {
            if(p.val==val)
                return;
            else if(val>p.val)
            {
                parent=p;
                p=p.right;
            }
            else if(val<p.val)
            {
                parent=p;
                p=p.left;
            }
        }

        TreeNode node=new TreeNode(val);
        if(val>parent.val)
            parent.right=node;
        else
            parent.left=node;
    }

    public TreeNode find(int val)
    {
        TreeNode p=root;

        while(p!=null)
        {
            if(p.val==val)
                return p;
            else if(val>p.val)
                p=p.right;
            else if(val<p.val)
                p=p.left;
        }

        return null;
    }

    public int findMin()
    {
        if(root==null)
            throw new NullPointerException();

        TreeNode p=root;

        while(p.left!=null)
            p=p.left;

        return p.val;
    }

    public int findMax()
    {
        if(root==null)
            throw new NullPointerException();

        TreeNode p=root;

        while(p.right!=null)
            p=p.right;

        return p.val;
    }

    public List<Integer> inOrder()
    {

        if(root==null)
            throw new NullPointerException();

        List<Integer> result=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();

        TreeNode p=root;

        while(!stack.empty()||p!=null)
        {
            while(p!=null)
            {
                stack.push(p);
                p=p.left;
            }

            if(!stack.empty())
            {
                TreeNode node=stack.pop();
                result.add(node.val);
                p=node.right;
            }
        }

        return result;

    }

    public List<Integer> preOrder()
    {
        if(root==null)
            throw new NullPointerException();

        List<Integer> result=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();

        stack.push(root);

        while(!stack.empty())
        {
            TreeNode node=stack.pop();
            result.add(node.val);

            if(node.right!=null)
                stack.push(node.right);
            if(node.left!=null)
                stack.push(node.left);
        }

        return result;
    }
}
