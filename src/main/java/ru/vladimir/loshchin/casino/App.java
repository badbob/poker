/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.vladimir.loshchin.casino;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import static java.lang.System.out;

public class App {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
	        BufferedImage img = ImageIO.read(new File(args[0]));
	
	        var proc = new Processor();
	        List<Card> result = proc.parse(img);
	        StringBuilder sb = new StringBuilder();
	        result.forEach(c -> sb.append(c.toString()));
	        out.println(args[0] + " - " + sb.toString());
        } else {
        	System.err.println("Image file should be specified");
        }
    }
}
