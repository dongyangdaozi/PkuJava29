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
    public void word(String filepath1,String filepath2){ // word用来求单词个数。
       
        int count1=0;// 计数变量1用来统计第一个文件的单词数。
        int count2=0; // 计数变量2用来统计第二个文件中的单词数。
        Map<String,Integer> map=new HashMap<String, Integer>();
        File file1=new File(filepath1); // 找到第一个文件
        File file2=new File(filepath2);// 找到第二个文件
        try {
            BufferedReader reader1=new BufferedReader(new FileReader(file1));
            BufferedReader reader2=new BufferedReader(new FileReader(file2));
            String templineword=null; // 每一行进行处理。直到该行结束。
            while ((templineword=reader1.readLine())!=null){  
                count1+=wordcount(map,templineword);
            }
            while ((templineword=reader2.readLine())!=null){ 
                count2+=wordcount(map, templineword);
            }
            reader1.close();// 关闭文件读
            reader2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      // 获得map的迭代器，用作遍历map中的每一个键值对
        Iterator<Map.Entry<String,Integer>> iterator=map.entrySet().iterator();
        System.out.println("文本一单词数："+count1); // 输出计数结果
        System.out.println("文本二单词数："+count2); // 输出计数结果
        while (iterator.hasNext()){  
            Map.Entry<String,Integer> entry=iterator.next();
            System.out.println("词："+entry.getKey()+" 出现个数："+entry.getValue());
        }
    }
    public void intersection_word(String filepath1,String filepath2){// 此函数求单词交集个数
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
            System.out.println("交集的单词："+entry.getKey()+" 个数是："+entry.getValue());
        }
    }
    public int wordcount(Map<String,Integer> map,String lineword){// wordcount用来统计个单词个数
        int count=0;
        StringTokenizer token=new StringTokenizer(lineword);//将字符串分解成标记
        while (token.hasMoreTokens()){
            count++;
            String word=token.nextToken(",?.!:\" \"''\n");// 按照,空格 ? . : "" '' \n去分割文本，如果没有明
            // 确要求，即括号里为空，则默认按照制表符，新行符和回车符分割。
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
        wordcount.word("E:/北大软微/java课程/text1.txt","E:/北大软微/java课程/text2.txt");
        System.out.println("---------------------------");
        wordcount.intersection_word("E:/北大软微/java课程/text1.txt", "E:/北大软微/java课程/text2.txt");
    }
}
