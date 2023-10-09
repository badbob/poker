package ru.vladimir.loshchin.casino;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ProcessorTest {

    Processor proc = new Processor();

    Pattern pattern = Pattern.compile("(?:[AKQJ2-9]|10)[hscd]");
    
    private File[] listImages() {
        File currentDir = new File(getClass().getResource("").getFile());
        return currentDir.listFiles(
                (dir, fileName) -> fileName.endsWith(".png") 
                && fileName.matches("(?:(?:[AKQJ2-9]|10)[hscd])+.png"));
    }
    @Test
    public void test() throws IOException {
        for (File f : listImages()) {
            String fileName  = f.getName();
            
            System.out.println(f.getAbsolutePath());

            List<String> expected = new ArrayList<>();
            final Matcher m = pattern.matcher(fileName);
            while (m.find()) {
            	expected.add(m.group());
            }

            System.out.print(expected);
            
            BufferedImage img = ImageIO.read(f);
            List<Card> actual = proc.parse(img);
            
            assertEquals(expected.size(), actual.size());
            for (int i = 0; i < actual.size(); i++) {
                assertEquals(expected.get(i), actual.get(i).toString());
            }
        }
    }
}
