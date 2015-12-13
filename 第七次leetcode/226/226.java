 public TreeNode invertTree(TreeNode root) {
         if(root==null)
	    	  return root;
	      invertTree(root.left);
	      invertTree(root.right);
	      TreeNode temp=null;
	      temp=root.left;
	      root.left=root.right;
	      root.right=temp;
	      
	      return root;
    }