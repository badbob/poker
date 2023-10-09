package ru.vladimir.loshchin.casino;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public enum Value {

    ACE("A.png",   "A"),
    TWO("2.png",   "2"),
    THREE("3.png", "3"),
    FOUR("4.png",  "4"),
    FIVE("5.png",  "5"),
    SIX("6.png",   "6"),
    SEVEN("7.png", "7"),
    EIGHT("8.png", "8"),
    NINE("9.png",  "9"),
    TEN("10.png",  "10"),
    JACK("J.png",  "J"),
    QUEEN("Q.png", "Q"),
    KING("K.png",  "K");

    private final Matrix matrix;
    private final String code;

    private Value(String filename, String code) {
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
    public static Value findByMatrix(Matrix m) {
        int match = m.size();
        Value result = null;

        for (var v : values()) {
            var xorSum = v.matrix.xor(m).sum();

            if (xorSum < match) {
                match = xorSum;
                result = v;
            }
        }
        return result;
    }

    public String code() {
        return code;
    }
}
