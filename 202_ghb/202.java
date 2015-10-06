public class Solution {
    public boolean isHappy(int n) {
        if(n==0)
			return false;
		   Map<Integer, Boolean> map=new HashMap<Integer,Boolean>();
		   
	       int sum=0;
	       boolean flag=false;
	       while(n!=0){
	    	   sum=sum+(n%10)*(n%10);
	    	   n=n/10;
	    	   if(n==0 && sum!=1){
	    		   if(map.get(sum)!=null)
	    			   return false;
	    		   else
	    			   map.put(sum, true);
	    		   n=sum;
	    		   sum=0;
	    		   
	    	   }
	    	   if(n==0 && sum==1){
	    		   return true;
	    	   }
	       }
	       return true;
    }
}