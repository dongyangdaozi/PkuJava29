public class Solution {
     public static String addBinary(String a, String b) {
	    	int i=a.length()-1;
	    	int j=b.length()-1;
	    	int k=0;
	    	char A[]=a.toCharArray();
	       char B[]=b.toCharArray();
	       char C[]=new char[Math.max(a.length(), b.length())+2];
	       int carry=0;
	       while(true){
	    	   if(i<0&&j<0&&carry==0)
	    		   {
	    		   break;
	    		   }
	    	  int am=0;
	    	  int bm=0;
	    		   if(i>=0){
	    			   
	    			  am=A[i]-'0';
	    			   
	    		   }
	    		   if(j>=0){
	    			   
	    			   
	    			   bm=B[j]-'0';
	    		   }
	    		   if(am+bm+carry>1){
	    			   
	    			   C[k]=(char)('0'+am+bm+carry-2);
	    			   carry=1;
	    			   
	    		   }else
	    		   {
	    			   C[k]=(char)(am+bm+carry+'0');
	    			   carry=0;
	    			   
	    		   }
	      i--;
	      j--;
	      k++;
	       
	       }
	       
	      return new StringBuffer(new String(C,0,k)).reverse().toString();
	   
	    
	    }
	 
	    }
