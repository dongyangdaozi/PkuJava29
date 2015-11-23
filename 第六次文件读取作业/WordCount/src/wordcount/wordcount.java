package wordcount;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by ASUS on 2015/11/21.
 */
public class wordcount {
    public void word(String filepath1,String filepath2){ // word�����󵥴ʸ�����
       
        int count1=0;// ��������1����ͳ�Ƶ�һ���ļ��ĵ�������
        int count2=0; // ��������2����ͳ�Ƶڶ����ļ��еĵ�������
        Map<String,Integer> map=new HashMap<String, Integer>();
        File file1=new File(filepath1); // �ҵ���һ���ļ�
        File file2=new File(filepath2);// �ҵ��ڶ����ļ�
        try {
            BufferedReader reader1=new BufferedReader(new FileReader(file1));
            BufferedReader reader2=new BufferedReader(new FileReader(file2));
            String templineword=null; // ÿһ�н��д���ֱ�����н�����
            while ((templineword=reader1.readLine())!=null){  
                count1+=wordcount(map,templineword);
            }
            while ((templineword=reader2.readLine())!=null){ 
                count2+=wordcount(map, templineword);
            }
            reader1.close();// �ر��ļ���
            reader2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      // ���map�ĵ���������������map�е�ÿһ����ֵ��
        Iterator<Map.Entry<String,Integer>> iterator=map.entrySet().iterator();
        System.out.println("�ı�һ��������"+count1); // ����������
        System.out.println("�ı�����������"+count2); // ����������
        while (iterator.hasNext()){  
            Map.Entry<String,Integer> entry=iterator.next();
            System.out.println("�ʣ�"+entry.getKey()+" ���ָ�����"+entry.getValue());
        }
    }
    public void intersection_word(String filepath1,String filepath2){// �˺����󵥴ʽ�������
        Map<String,Integer> map=new HashMap<String, Integer>();
        Map<String,Integer> intersection_map=new HashMap<String, Integer>();
        File file1=new File(filepath1);
        File file2=new File(filepath2);
        try {
            BufferedReader reader1=new BufferedReader(new FileReader(file1));
            BufferedReader reader2=new BufferedReader(new FileReader(file2));
            String templineword=null;
            while ((templineword=reader1.readLine())!=null){
                wordcount(map,templineword);
            }
            while ((templineword=reader2.readLine())!=null){
                intersection_wordcount(map,intersection_map,templineword);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Map.Entry<String,Integer>> iterator=intersection_map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry=iterator.next();
            System.out.println("�����ĵ��ʣ�"+entry.getKey()+" �����ǣ�"+entry.getValue());
        }
    }
    public int wordcount(Map<String,Integer> map,String lineword){// wordcount����ͳ�Ƹ����ʸ���
        int count=0;
        StringTokenizer token=new StringTokenizer(lineword);//���ַ����ֽ�ɱ��
        while (token.hasMoreTokens()){
            count++;
            String word=token.nextToken(",?.!:\" \"''\n");// ����,�ո� ? . : "" '' \nȥ�ָ��ı������û����
            // ȷҪ�󣬼�������Ϊ�գ���Ĭ�ϰ����Ʊ�������з��ͻس����ָ
            if(map.containsKey(word)){
                int thisword_count=map.get(word);
                map.put(word,thisword_count+1);
            }
            else
                map.put(word,1);
        }
        return count;
    }
    public void intersection_wordcount(Map<String,Integer> map,Map<String,Integer> intersectionmap,String lineword){
        StringTokenizer token=new StringTokenizer(lineword);
        while (token.hasMoreTokens()){
            String word=token.nextToken(",?.!:\" \"''\n");
            if(map.containsKey(word)){
                if(intersectionmap.containsKey(word)){
                    int wordcount=intersectionmap.get(word);
                    intersectionmap.put(word,wordcount+1);
                }
                else{
                    int mapcount=map.get(word);
                    intersectionmap.put(word,mapcount+1);
                }
            }
        }
    }
    public static void main(String[] args){
        wordcount wordcount=new wordcount();
        wordcount.word("E:/������΢/java�γ�/text1.txt","E:/������΢/java�γ�/text2.txt");
        System.out.println("---------------------------");
        wordcount.intersection_word("E:/������΢/java�γ�/text1.txt", "E:/������΢/java�γ�/text2.txt");
    }
}
