public class Solution {
    public String addBinary(String a, String b) {
       int lengthOfa=a.length();
		int lengthOfb=b.length();
		//int i=(lengthOfa>lengthOfb)?lengthOfa:lengthOfb;
		int carry=0; //��λ��־λ
		char mod;   //��2ȡ�������λ
		int charOfa,charOfb;
		int i=0;
		StringBuffer bufferOfa=new StringBuffer();
		
		StringBuffer bufferOfb=new StringBuffer();
		//bufferOfb.append(b);
		if(lengthOfa>lengthOfb){   //�ѳ����ַ�������bufferOfa����
			bufferOfa.append(a);
			bufferOfb.append(b);
		}
		else{
			bufferOfa.append(b);
			bufferOfb.append(a);
		}
		
		//lengthOfa=(lengthOfa>lengthOfb)?lengthOfa:lengthOfb;
		//lengthOfb=(lengthOfa<=lengthOfb)?lengthOfa:lengthOfb;
		if(lengthOfa<lengthOfb){
			int temp=lengthOfb;
			lengthOfb=lengthOfa;
			lengthOfa=temp;
		}
		for(i=0;i<lengthOfb;i++){
			charOfa=bufferOfa.charAt(lengthOfa-i-1)-'0';
			charOfb=bufferOfb.charAt(lengthOfb-i-1)-'0';
			mod=(char)((charOfa+charOfb+carry)%2+'0');
			carry=(charOfa+charOfb+carry)/2;
            bufferOfa.setCharAt(lengthOfa-i-1, mod);
		}
		while(carry>0 && lengthOfa-i-1>=0){
			charOfa=bufferOfa.charAt(lengthOfa-i-1)-'0';
			mod=(char)((charOfa+carry)%2+'0');
			carry=(charOfa+carry)/2;
			bufferOfa.setCharAt(lengthOfa-i-1, mod);
			i++;
		}
		if(carry>0){
			bufferOfa.insert(0, '1');
		}
		return bufferOfa.toString();
    }
}