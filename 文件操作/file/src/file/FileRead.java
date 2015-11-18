package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class FileRead {
	static int sumOfB=0;  //B�ĵ��ĵ������������ظ�
	static int sumOfA=0;  //A�ĵ��ĵ������������ظ�
	static int sumOfAll=0;
	static int sumOfAoxB=0;
	private static Set<String> setA=new HashSet<String>();
	private static Set<String> setB=new HashSet<String>();
//	private static Set<String> setAll=new HashSet<String>();
	private static Set<String> setAoxB=new HashSet<String>();
	
	static String pathOfA="A.txt";
	static String pathOfB="B.txt";
	static String pathOfAll="All.txt";  //AB�����ĵ��ܹ��ʻ��
	static String pathOfAB="AB.txt";
	
	public static void combineAll() throws IOException{
		String str=null;
		FileReader inA=new FileReader(pathOfA);  
		FileReader inB=new FileReader(pathOfB);
		
		BufferedReader brA=new BufferedReader(inA); //��A�ļ�д�뵽setA��
		while((str=brA.readLine())!=null){
			StringTokenizer stA=new StringTokenizer(str);
			while(stA.hasMoreElements()){
				String word=new String(stA.nextToken());
				setA.add(word);
			}
		}
		sumOfA=setA.size();
		
		BufferedReader brB=new BufferedReader(inB);  //��B�ļ���ȡ��setB��
		while((str=brB.readLine())!=null){
			StringTokenizer stB=new StringTokenizer(str);
			while(stB.hasMoreElements()){
				String word=new String(stB.nextToken());
				setB.add(word);
			}
		}
		sumOfB=setB.size();
		
		Iterator<String> it=setB.iterator(); //��setB���뵽setA��,setA�н��洢A��B�ļ�����
		while(it.hasNext()){
			String AoxB=it.next();
			if(setA.add(AoxB)==false)
				setAoxB.add(AoxB);     //�ѽ������ִ���setAoxB
		}
		sumOfAoxB=setAoxB.size();  //���㽻��������
		
		sumOfAll=setA.size();     //����������
		
		File fileAll=new File(pathOfAll);
		
		if(!fileAll.exists()){
			fileAll.createNewFile();
		}
		FileWriter fWAll=new FileWriter(pathOfAll);
		
		Iterator<String> all=setA.iterator();
		while(all.hasNext()){
			String word=all.next();
			//fWAll.write(" ");
			fWAll.write(word);
			fWAll.write("\r\n");
		}
		fWAll.flush();
	
        File fileAB=new File(pathOfAB);
		
		if(!fileAB.exists()){
			fileAB.createNewFile();
		}
		FileWriter fWAB=new FileWriter(pathOfAB);
		
		Iterator<String> AB=setAoxB.iterator();
		while(AB.hasNext()){
			String word=AB.next();
			//fWAB.write(" ");
			fWAB.write(word);
			fWAB.write("\r\n");
		}
		fWAB.flush();
		
		inA.close();
		inB.close();
		brA.close();
		brB.close();
		fWAll.close();	
		fWAB.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		combineAll();
		System.out.println("sumOfA="+sumOfA);
		System.out.println("sumOfB="+sumOfB);
		System.out.println("sumOfAll="+sumOfAll);
		System.out.println("sumOfAoxB="+sumOfAoxB);
		System.out.println();
	}
}
