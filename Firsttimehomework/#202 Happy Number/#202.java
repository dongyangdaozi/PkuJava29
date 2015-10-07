import java.util.*;
public class Solution {
    public boolean isHappy(int n) {
        int sum;
        int carry=n;
        Vector v=new Vector(20,5);
        while(true){
            sum=0;
            sum=collect(carry);
            if(sum==1)return true;
            //v.addElenment(sum);
            if(v.contains(sum))return false;
            v.addElement(sum);
            
            carry=sum;
        }
    }
    public int collect(int n){
        int caculate=0;
        while(n>0){
            caculate+=(n%10)*(n%10);
            n/=10;
        }
        return caculate;
    }
}