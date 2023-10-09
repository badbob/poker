package ru.vladimir.loshchin.casino;

public class Matrix {

    private boolean[][] value;

    private int cols;

    private int rows;

    public Matrix(int width, int height) {
        this.cols = width;
        this.rows = height;
	
        value = new boolean[height][width];
    }

    public int size() {
        return cols * rows;
    }

    public boolean get(int x, int y) {
        return y < rows && x < cols
            ? value[y][x] : false;
    }

    public void set(int x, int y) {
        value[y][x] = true;
    }

//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        for (int y = 0; y < rows; y++) {
//            sb.append('[');
//            for (int x = 0; x < cols; x++) {
//                sb.append(get(x, y) ? '█' : ' ');
//            }
//            sb.append(']');
//            sb.append('\n');
//        }
//        return sb.toString();
//    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < rows; y++) {
            sb.append("new boolean[] {");
            for (int x = 0; x < cols; x++) {
                sb.append(get(x, y) ? "true" : "false");
                if (x < cols - 1) {
                	sb.append(", ");
                }
            }
            sb.append("},");
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public Matrix xor(Matrix m) {
        var result = new Matrix(
            Math.max(cols, m.cols), 
            Math.max(rows, m.rows));

        for (int y = 0; y < result.rows; y++) {
            for (int x = 0; x < result.cols; x++) {
                if (get(x, y) != m.get(x, y)) {
                    result.set(x, y);
                }
            }
        }
        
        return result;
    }
    
    public int sum() {
        int result = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (get(x, y)) {
                    result++;
                }
            }
        }
        return result;
    }
}
