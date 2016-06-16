import java.io.*;

/**
 * Created by jack on 16-6-14.
 */
public class WordCount {

    public static void main(String[] args) throws IOException {
        int count = 0;
        if(args.length != 2){
            System.out.println("Please enter like : java WordCount <paramter> <filename>");
            return;
        }
        File file = new File("/home/jack/IdeaProjects/Example/src/input.txt");
        Reader reader = new InputStreamReader(new FileInputStream(file));

        if(args[0].equals("-c")){
            showChars(reader);
        }
        else if(args[0].equals("-l")){
            showLines(reader);
        }

    }

    public static int showLines(Reader reader) throws IOException {
       BufferedReader br = new BufferedReader(reader);
       String line;
       int count = 0;
       while((line = br.readLine()) != null){
//         空行是用空串表示的表示的
//         String对象的大小比较一定要使用equals
           if(!line.equals("")){
               count++;
           }
       }
       System.out.println("行数等于" + count);
       return count;
    }

    public static int showChars(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        int count = 0;
        int chartemp;
        while ((chartemp = br.read()) != -1) {
            if((char)chartemp != '\n' && (char)chartemp != '\t' && (char)chartemp != ' ') {
                count++;
            }
        }
        System.out.println("总字符数等于" + count);
        return count;
    }

//    public static int showWords(Reader reader){
//       int count = 0;
//       while (){
//
//       }
//    }
}
