public class Solution {
    public int myAtoi(String str) {
       	//str=str.replaceAll(" ", "");
		int length=str.length();
		if(length==0)
			return 0;
		StringBuffer strBuffer=new StringBuffer();
		//forѭ��ɾ��str�ж�����ַ�
		for(int i=0;i<length;i++){ 
			//if(str.charAt(i)=='-' || str.charAt(i)=='+'||(str.charAt(i)>='0' && str.charAt(i)<='9'))
			//	strBuffer.append(str.charAt(i));
			if(str.charAt(i)>'0' && str.charAt(i)<='9')
				strBuffer.append(str.charAt(i));
			
			else if((str.charAt(i)=='-' || str.charAt(i)=='+') && strBuffer.length()==0)
				strBuffer.append(str.charAt(i));
			else if(str.charAt(i)=='0' && strBuffer.length()>1)
				strBuffer.append(str.charAt(i));
			else if(str.charAt(i)=='0' && strBuffer.length()==1){
				if(strBuffer.charAt(0)!='-' && strBuffer.charAt(0)!='+')
				strBuffer.append(str.charAt(i));
			}
			//else if(strBuffer.length()>0)
			else if(str.charAt(i)==' ' && strBuffer.length()>0)	
			    break;
			else if(str.charAt(i)!='0' && str.charAt(i)!=' ')
				break;
		}
		if(strBuffer.length()==0)
			return 0;
		if(strBuffer.charAt(0)=='+' && strBuffer.length()>=11){
			if(strBuffer.length()>11)
			    return 2147483647;
			if(strBuffer.length()==11 && strBuffer.substring(1).compareTo("2147483647")>0)
				return 2147483647;
			
		}
		else if(strBuffer.charAt(0)=='-' && strBuffer.length()>=11){
			if(strBuffer.length()>11)
			    return -2147483648;
			if(strBuffer.length()==11 && strBuffer.substring(1).compareTo("2147483648")>0)
				return -2147483648;
			
		}
		else if(strBuffer.length()>=10){
			if(strBuffer.length()>10)
			    return 2147483647;
			if(strBuffer.toString().compareTo("2147483647")>0)
				return 2147483647;
		}
		
		
		//sumΪ���Ҫ���ص�str���������
		int sum=0; 
		int weight=1;  //weightΪÿ���ַ���Ȩ��
		int i=1;
		length=strBuffer.length();
		while(length-i>=0){
		if(strBuffer.charAt(length-i)>='0' && strBuffer.charAt(length-i)<='9' && length-i!=0){
			sum=sum+(strBuffer.charAt(length-i)-'0')*weight;
			weight=weight*10;
		}
		if((strBuffer.charAt(length-i)<'0' || strBuffer.charAt(length-i)>'9') && length-i!=0){
			return 0;
		}
		if(length-i==0 && strBuffer.charAt(0)=='+')
			return sum;
		if(length-i==0 && strBuffer.charAt(0)=='-')
			return -sum;
		else if(length-i==0)
			return sum=sum+(strBuffer.charAt(length-i)-'0')*weight;
		i++;
	    }
		return 0;
    }
}