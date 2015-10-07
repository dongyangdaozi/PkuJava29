public class Solution {
    public void rotate(int[] nums, int k) {
        int len=nums.length;
        int []s=new int[len];
        for(int i=0;i<len;i++){
            s[(i+k)%len]=nums[i];
        }
        //nums=s;
        for(int i=0;i<len;i++){
        	nums[i]=s[i];
        }
    }
}