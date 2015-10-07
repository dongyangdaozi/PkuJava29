public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum=0;
        int i=1;
        for(int j=0;j<32;j++){
            if((n&i)!=0)sum++;
            i=i<<1;
        }
        return sum;
    }
}