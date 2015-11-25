public class Solution {
    public int removeDuplicates(int[] A) {  
         if (A.length == 0) {  
             return 0;  
         }  
         int current = 1;  
         int index = 1;  
         int pre = A[0];  
         while (index < A.length) {  
             if (!(A[index] == pre)) {  
                 A[current++] = A[index];  
             }  
             pre = A[index];  
             index++;  
         }  
         return current;  
     }  
}