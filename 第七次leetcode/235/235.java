 public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           if(root==null || q==null || p==null)
	        	return null;
	        
	        if(Math.min(p.val, q.val)>root.val)
	        	return lowestCommonAncestor(root.right, p, q);
	        else if(Math.max(p.val, q.val)<root.val)
	        	return lowestCommonAncestor(root.left,p,q);
	        else
	        	return root;
    }