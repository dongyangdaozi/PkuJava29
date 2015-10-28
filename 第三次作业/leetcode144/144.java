public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<Integer>();
        if(root==null)
        return list;
        
        preorder(root,list);
        return list;
    }
    
    public void preorder(TreeNode root, List<Integer> list){
        if(root==null)
        return ;
        list.add(root.val);
        preorder(root.left,list);
        preorder(root.right,list);
    }
}