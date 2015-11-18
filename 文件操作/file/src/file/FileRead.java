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
	static int sumOfB=0;  //B文档的单词数，包含重复
	static int sumOfA=0;  //A文档的单词数，包含重复
	static int sumOfAll=0;
	static int sumOfAoxB=0;
	private static Set<String> setA=new HashSet<String>();
	private static Set<String> setB=new HashSet<String>();
//	private static Set<String> setAll=new HashSet<String>();
	private static Set<String> setAoxB=new HashSet<String>();
	
	static String pathOfA="A.txt";
	static String pathOfB="B.txt";
	static String pathOfAll="All.txt";  //AB两个文档总共词汇表
	static String pathOfAB="AB.txt";
	
	public static void combineAll() throws IOException{
		String str=null;
		FileReader inA=new FileReader(pathOfA);  
		FileReader inB=new FileReader(pathOfB);
		
		BufferedReader brA=new BufferedReader(inA); //把A文件写入到setA中
		while((str=brA.readLine())!=null){
			StringTokenizer stA=new StringTokenizer(str);
			while(stA.hasMoreElements()){
				String word=new String(stA.nextToken());
				setA.add(word);
			}
		}
		sumOfA=setA.size();
		
		BufferedReader brB=new BufferedReader(inB);  //把B文件提取到setB中
		while((str=brB.readLine())!=null){
			StringTokenizer stB=new StringTokenizer(str);
			while(stB.hasMoreElements()){
				String word=new String(stB.nextToken());
				setB.add(word);
			}
		}
		sumOfB=setB.size();
		
		Iterator<String> it=setB.iterator(); //把setB加入到setA中,setA中将存储A，B文件并集
		while(it.hasNext()){
			String AoxB=it.next();
			if(setA.add(AoxB)==false)
				setAoxB.add(AoxB);     //把交集部分存入setAoxB
		}
		sumOfAoxB=setAoxB.size();  //计算交集的数量
		
		sumOfAll=setA.size();     //并集的数量
		
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
