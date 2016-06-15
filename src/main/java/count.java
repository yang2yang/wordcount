/**
 * Created by jack on 16-6-15.
 */
import java.io.*;
import java.util.*;

public class count {
    public static void main(String[] args)
    {
        try{
            FileReader fr1=new FileReader("input.txt");   //读取文本
            BufferedReader bf1=new BufferedReader(fr1);
            String str1 = null;String [] str=null;String [] str2=null;
            while((str1=bf1.readLine())!=null)            //按行读取
            {
                str=str1.split(" ");                      //按空格切分字符串
                for(int i=0;i<str.length;i++)
                {
                    str2=str[i].split("");                //把一个字符串分为一个一个的字符，主要目的是为了好把字母以外的符号去掉
                    String a=null;
                    for(int j=0;j<str2.length;j++)
                    {
                        if(str2[j].charAt(0)>='A'&&str2[j].charAt(0)<='Z'||str2[j].charAt(0)>='a'&&str2[j].charAt(0)<='z')
                        {
                            str2[j]=str2[j].toLowerCase();  //大写变小写
                            if(a==null)
                                a=str2[j];
                            else
                                a=a+str2[j];                //一个完整的只有字母的字符串即完整的单词
                        }
                    }
                    str[i]=a;
                }
            }
            fr1.close(); bf1.close();
            Map<String,Integer> map = new HashMap<String,Integer>(); //map接口统计字母出现频率
            for (int j = 0; j < str.length; j++)
            {
                Integer count = map.get(str[j]);       //一个单词出现的次数
                if(count==null)                        //如果没有出现过，置一次
                {
                    map.put(str[j],1);
                }
                else                                  //如果以前出现过，那么次数加一
                {
                    map.put(str[j],++count);
                }
            }
            String [] str3=null;
            for(Map.Entry<String,Integer> entry : map.entrySet())
            {
                if(entry.getKey()!=null)
                    str3=entry.getKey().split("");
                if( str3!=null&&str3.length>3)                     //str3可能为空，且这个控制只有含有3个字母以上的单词才打印
                    System.out.println(entry.getKey() +": "+"\t"+ entry.getValue());
            }
        }catch(Exception e){
            e.printStackTrace();}
    }
}
