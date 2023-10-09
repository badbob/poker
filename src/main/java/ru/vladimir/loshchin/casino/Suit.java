package ru.vladimir.loshchin.casino;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public enum Suit implements Figure {

    HEARTS("hearts.png", 'h'),
    DIAMONDS("diamonds.png", 'd'),
    CLUBS("clubs.png", 'c'),
    SPADES("spades.png", 's');

    private final Matrix matrix;
    private final char code;

    private Suit(String filename, char code) {
        try {
            InputStream is = Suit.class.getResourceAsStream(filename);
            var img = ImageIO.read(is);
            this.matrix = ImgUtil.imgToMatrix(img);
            this.code = code;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Search for best match
     */
    public static Suit findByMatrix(Matrix m) {
        int match = m.size();
        Suit result = null;

        for (var v : values()) {
            var xor = v.matrix.xor(m);
//            System.out.println(xor.toString());
            var xorSum = xor.sum();
//            System.out.println("Sum: " + xorSum);

            if (xorSum < match) {
                match = xorSum;
                result = v;
            }
        }
        return result;
    }

    public char code() {
        return code;
    }
}
