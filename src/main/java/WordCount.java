import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.*;

/**
 * Created by jack on 16-6-14.
 */
public class WordCount {

    public static void main(String[] args) throws IOException, ParseException {

        Options options = new Options();

        Option c = Option.builder("c").required(false).hasArg().argName("filename").desc("return " +
                "sum of characters").build();

        Option l = Option.builder("l").required(false).hasArg().argName("filename").desc("return " +
                "sum of lines").build();

        Option w = Option.builder("w").required(false).hasArg().argName("filename").desc("return " +
                "sum of words").build();

//      这样的话，会有一个参数，那么这样的话和有参数的应该没与什么区别吧
        options.addOption("help",false,"help information");
        options.addOption(c);
        options.addOption(l);
        options.addOption(w);

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options,args);

        if(cmd.hasOption("help")) {
//            System.out.println(cmd.getOptionValue("help"));
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "java wordcount [OPTION] <FILENAME>", options );
            return;
        }

        if(cmd.hasOption("c")){
//          获得相应的选项的参数
            String filename = cmd.getOptionValue("c");
            System.out.println(filename);
            File file = new File("/home/jack/IdeaProjects/wordcount/src/main/resources/input.txt");
            Reader reader = new InputStreamReader(new FileInputStream(file));
            showChars(reader);
        }

        if(cmd.hasOption("l")){
            File file = new File("/home/jack/IdeaProjects/wordcount/src/main/resources/input.txt");
            Reader reader = new InputStreamReader(new FileInputStream(file));
             showLines(reader);
        }

        if(cmd.hasOption("w")){
            File file = new File("/home/jack/IdeaProjects/wordcount/src/main/resources/input.txt");
            Reader reader = new InputStreamReader(new FileInputStream(file));
            showWords(reader);
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

    public static int showWords(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line;
//      定义一个正则单词的正则表达式
        String regex = "\\w+";
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        while((line = br.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                count++;
            }
        }
        System.out.println("单词总数是"+count);
        return count;
    }
}
