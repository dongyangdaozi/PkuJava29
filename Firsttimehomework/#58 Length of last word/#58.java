public class Solution {
    public int lengthOfLastWord(String s) {
        int i,carry=1,sum=0;
        for(i=0;i<s.length();i++){
            if(carry==1&&s.charAt(i)!=' '){
                sum++;
                carry=1;
            }
            if(s.charAt(i)==' ')carry=0;
            if((carry==0)&&s.charAt(i)!=' '){
                sum=1;
                carry=1;
            }
        }
        return sum;
    }
}