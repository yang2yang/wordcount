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

        options.addOption("help", false, "help information");
        options.addOption("c", false, "show characters");
        options.addOption("l", false, "show lines");
        options.addOption("w", false, "show words");
        options.addOption("s", false, "recursion");

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = parser.parse(options, args);

        HelpFormatter formatter = new HelpFormatter();

        if (cmd.hasOption("help")) {
            formatter.printHelp("java wordcount [-help] [-c] [-l] [-w] filename", options);
            return;
        }

        String[] s;
        if ((s = cmd.getArgs()).length != 1) {
            formatter.printHelp("java wordcount [-help] [-c] [-l] [-w] filename", options);
            return;
        }

        File file = new File("/home/jack/IdeaProjects/wordcount/src/main/resources");

        if (cmd.hasOption("s")) {
            if (file.isDirectory()) {
                //递归的解决问题
                recurse(file,cmd);
            } else {
                //单文件正常操作
                judge(cmd, file);
            }
        } else {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    //每一个文件都这样操作
                    judge(cmd, f);
                }
            } else {
                //单文件正常操作
                judge(cmd, file);
            }
        }
    }

    public static void judge(CommandLine cmd, File file) throws IOException {
        System.out.println("Filename:"+file.getName());
        if (cmd.hasOption("c")) {
//          获得相应的选项的参数
            Reader reader = new InputStreamReader(new FileInputStream(file));
            showChars(reader);
        }

        if (cmd.hasOption("l")) {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            showLines(reader);
        }

        if (cmd.hasOption("w")) {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            showWords(reader);
        }

    }

    public static void recurse(File file,CommandLine cmd) throws IOException {
        for(File f : file.listFiles()){
            if(f.isDirectory()){
                recurse(f,cmd);
            }else{
               judge(cmd,f);
            }
        }
    }

    public static int showLines(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
//         空行是用空串表示的表示的
//         String对象的大小比较一定要使用equals
            if (!line.equals("")) {
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
            if ((char) chartemp != '\n' && (char) chartemp != '\t' && (char) chartemp != ' ') {
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
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                count++;
            }
        }
        System.out.println("单词总数是" + count);
        return count;
    }
}
