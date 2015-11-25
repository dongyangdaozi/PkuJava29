public class Solution {  
    public boolean containsDuplicate(int[] nums) {  
    Set<Integer> appearedNum = new HashSet<Integer>();  
    for(int i = 0; i < nums.length; i++){  
        if(!appearedNum.contains(nums[i])){  
            appearedNum.add(nums[i]);  
        } else return true;  
    }  
    return false;  
    }  
}  