public class Solution {
    public int[] twoSum(int[] nums, int target) {
       int length=nums.length;
	    Map<Integer,Integer> map= new HashMap<Integer,Integer>();
	    //twoSum�����洢��������λ��
	    int[] twoSum=new int[2];
	    //���������hash��
	    for(int i=0;i<length;i++){
	    	map.put(nums[i], i+1);
	    }
	    
	    for(int i=0;i<length;i++){
	    	if(map.get(target-nums[i])!=null && map.get(target-nums[i])!=i+1){
	    		twoSum[0]=i+1;
	    		twoSum[1]=map.get(target-nums[i]);
	    		return twoSum;
	    	}
	    }
		return twoSum;
    }
}