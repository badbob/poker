package ru.vladimir.loshchin.casino;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatrixTest {

	@Test
    public void test_2x2() {
        var m = new Matrix(3, 2);
        m.set(1, 1);

//        System.out.print(m.toString());
    }

    @Test
    public void test_xor() {
        var m1 = new Matrix(2, 2);
        m1.set(1, 1);
        assertEquals(1, m1.sum());
        
        var m2 = new Matrix(3, 2);
        m2.set(1, 0);
        assertEquals(1, m2.sum());
        
        var xor = m1.xor(m2);
//        System.out.println(xor.toString());
        
        assertEquals(2, xor.sum());
    }

    @Test
    public void test_xor_2() {
        var m1 = new Matrix(2, 2);
        m1.set(1, 1);
        assertEquals(1, m1.sum());
        
        var m2 = new Matrix(3, 2);
        m2.set(1, 1);
        assertEquals(1, m2.sum());
        
        var xor = m1.xor(m2);
//        System.out.println(xor.toString());
        
        assertEquals(0, xor.sum());
	}
}
