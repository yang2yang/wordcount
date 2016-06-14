import java.io.*;

/**
 * Created by jack on 16-6-14.
 */
public class WordCount {
    public static void main(String[] args){
        for(String e:args){
            System.out.println(e);
        }
        File file = new File(args[1]);
        int count = 0;
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            while(reader.read() != -1){
                count++;
            }
            System.out.println("这个文件的字符数是:"+count);
        } catch (IOException e) {
            System.out.println("没有找到此文件");
            e.printStackTrace();
        }

    }
}
