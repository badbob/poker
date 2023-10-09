package ru.vladimir.loshchin.casino;

import static java.lang.System.out;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Processor {

	public static BufferedImage binary(BufferedImage src) {
        var binary = new BufferedImage(
            src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                int rgb = src.getRGB(x, y);
                int red   = (rgb >> 16) & 0xFF;
                int green = (rgb >>  8) & 0xFF;
                int blue  = (rgb)       & 0xFF;
                int gray  = red*3 + green*6 + blue;
                
                if (!(red == 255 && green == 255 && blue == 255) 
                		&& !(red == 120 && green == 120 && blue == 120)) {
                    binary.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                	binary.setRGB(x, y, -1);
                }
            }
        }

        return binary;
    }
    
    /**
     * Cropping the whitespaces from image
     * @param img
     * @return
     */
    public static BufferedImage crop(BufferedImage img) {
    	int minX = img.getWidth();
        int maxX = 0;
        int minY = img.getHeight();
        int maxY = 0;
        
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) != -1) {
                    if (x < minX) {
                        minX = x;
                    }
                    if (x > maxX) {
                        maxX = x;
                    }
                    if (y < minY) {
                        minY = y;
                    }
                    if (y > maxY) {
                        maxY = y;
                    }
                }
            }
        }

        return img.getSubimage(minX, minY, maxX-minX+1, maxY-minY+1);
    }

    public static boolean isLineEmpty(BufferedImage img, int line) {
        for (int x = 0; x < img.getWidth(); x++) {
            if (img.getRGB(x, line) != -1) {
                return false;
            }
        }
        return true;
    }

    public static BufferedImage splitTop(BufferedImage img) {
       for (int y = 0; y < img.getHeight(); y++) {
           if (isLineEmpty(img, y)) {
               return crop(img.getSubimage(0, 0, img.getWidth(), y));
           }
       }
       return null;
    }

    public static BufferedImage splitBottom(BufferedImage img) {
        for (int y = img.getHeight()-1; y > 0; y--) {
            if (isLineEmpty(img, y)) {
                return crop(img.getSubimage(0, y+1, img.getWidth(), img.getHeight() - y-1));
            }
        }
        return null;
    }

    public BufferedImage sliceCard(BufferedImage img, int i) {
        
        int width = 29;
        int height = 48;
        int shift = 43;
        var card = img.getSubimage(4 + ((width + shift) * i), 5, width, height);
        return card;
    }
    
    public Card imageToCard(BufferedImage img) throws IOException {
        ImageIO.write(img, "png", new File("first.png"));
        
        var wbfirst = binary(img);
        ImageIO.write(wbfirst, "png", new File("binary.png"));
        
        wbfirst = crop(wbfirst);
        ImageIO.write(wbfirst, "png", new File("wbfirst.png"));

        var top = splitTop(wbfirst);
        if (Objects.isNull(top)) {
        	return null;
        }
        
        ImageIO.write(top, "png", new File("top.png"));

        var bottom = splitBottom(wbfirst);
        ImageIO.write(bottom, "png", new File("bottom.png"));
        

        var bottomMatrix = ImgUtil.imgToMatrix(bottom);
        var s = Suit.findByMatrix(bottomMatrix);
        var v = Value.findByMatrix(ImgUtil.imgToMatrix(top));
        return new Card(s, v);
    }

    public List<Card> parse(BufferedImage img) throws IOException {
    	var cards = img.getSubimage(143, 585, 400, 90);
        
        ImageIO.write(cards, "png", new File("cards.png"));
        
        var first = sliceCard(cards, 0);
        
        List<Card> result = new ArrayList<>();
        result.add(imageToCard(first));
        
        var second = sliceCard(cards, 1);
        result.add(imageToCard(second));
        ImageIO.write(second, "png", new File("second.png"));

        var third = sliceCard(cards, 2);
        result.add(imageToCard(third));
        ImageIO.write(third, "png", new File("third.png"));
        
        var fourth = sliceCard(cards, 3);
        if (Objects.nonNull(fourth)) {
            ImageIO.write(fourth, "png", new File("fourth.png"));
            Card card = imageToCard(fourth);
            if (card != null) {
                result.add(card);
                
                var fifth = sliceCard(cards, 4);
                if (Objects.nonNull(fifth)) {
                    ImageIO.write(fifth, "png", new File("fifth.png"));
                    card = imageToCard(fifth);
                    if (card != null) {
                    	result.add(card);
                    }
                }
            }
        }
        
        return result;
    }
}
