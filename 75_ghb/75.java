public class Solution {
    public void sortColors(int[] nums) {
        int length=nums.length;
	        int zero=0,one=0,two=0;
	        for(int i=0;i<length;i++){
	        	if(nums[i]==0){
	        		zero++;
	        		continue;
	        	}
	        	if(nums[i]==1){
	        		one++;
	        		continue;
	        	}
	        		two++;
	        }
	        for(int i=0;i<length;i++){
	        	if(i<zero){
	        		nums[i]=0;
	        		continue;
	        	}
                if(i>=zero+one){
                	nums[i]=2;
                	continue;
                }
                nums[i]=1;
	        }
    }
}