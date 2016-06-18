import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by jack on 16-6-16.
 */
public class WordCountTest {

    @org.junit.Test
    public void testShowLines() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream("/home/jack/IdeaProjects/wordcount/src/main/resources/inputtest.txt"));
        int count = WordCount.showLines(reader);
        assertEquals(count,7);
    }

    @org.junit.Test
    public void testShowChars() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream("/home/jack/IdeaProjects/wordcount/src/main/resources/inputtest.txt"));
        int count = WordCount.showChars(reader);
        assertEquals(count,90);

    }

    @Test
    public void testShowWords() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream("/home/jack/IdeaProjects/wordcount/src/main/resources/inputtest.txt"));
        int count = WordCount.showWords(reader);
        assertEquals(count,15);
    }
}