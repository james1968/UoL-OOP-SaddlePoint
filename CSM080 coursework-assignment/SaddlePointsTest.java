package saddlePoints;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class SaddlePointsTest {
    private SaddlePoints sp;

    @Before
    public void setUp() throws Exception {
         sp = new SaddlePoints(); // create an instance variable
    }

    @Test
    public void createRandomArray() {
        int [][] array1 = sp.createRandomArray(5, 5,1,  10);
        int [][] array2 = sp.createRandomArray(5, 5,1,  10);
        // test to check that generated random array has different values for each array
        int[] array1Row1 = array1[0];
        int[] array2Row2 = array2[0];
        assertFalse(Arrays.equals(array1Row1, array2Row2));
        assertEquals(5, sp.createRandomArray(5, 5,1,  10).length);
        assertEquals(3, sp.createRandomArray(5, 3,1,  10)[0].length);
        // test to ensure maximum and minimum values are less than and greater than the maxValue and minValue respectively
        final int MAX_VALUE = 10;
        final int MIN_VALUE = 1;
        int minimum = array1[0][0];
        int maximum = array1[0][0];
        for (int[] ints : array1) {
            Arrays.sort(ints);
            if (ints[0] < minimum) minimum = ints[0];
            if (ints[ints.length - 1] > maximum) maximum = ints[ints.length - 1];
        }
        assertTrue(maximum <= MAX_VALUE);
        assertTrue(minimum >= MIN_VALUE);
    }

    @Test
    public void largest() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        int[] arrayL = sp.smallestValues(with);
        assertEquals(5, sp.largest(arrayL));
        int[][] without = {{ 1, -2,  3}, {-6,  5, -4}, { 7, -8,  9}};
        int[] arrayM = sp.smallestValues(without);
        assertEquals(-2, sp.largest(arrayM));
        // second test for an array with a saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        int[] arrayN = sp.smallestValues(with2);
        assertEquals(2, sp.largest(arrayN));
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        int[] arrayO = sp.smallestValues(with3);
        assertEquals(3, sp.largest(arrayO));
        // second test for array without saddlePoint
        int[][] without2 = {{ 3, 2,  8}, {4, 6, 3}, {1, 1, 6}};
        int[] arrayP = sp.smallestValues(without2);
        assertEquals(3, sp.largest(arrayP));
    }

    @Test
    public void smallest() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        int[] arrayS = sp.largestValues(with);
        assertEquals(5, sp.smallest(arrayS));
        int[][] without = {{ 1, -2,  3}, {-6,  5, -4}, { 7, -8,  9}};
        int[] arrayT = sp.largestValues(without);
        assertEquals(5, sp.smallest(arrayT));
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        int[] arrayU = sp.largestValues(with2);
        assertEquals(2, sp.smallest(arrayU));
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        int[] arrayV = sp.largestValues(with3);
        assertEquals(3, sp.smallest(arrayV));
        // second test for array without saddlePoint
        int[][] without2 = {{ 3, 2,  8}, {4, 6, 3}, {1, 1, 6}};
        int[] arrayW = sp.largestValues(without2);
        assertEquals(4, sp.smallest(arrayW));


    }

    @Test
    public void largestValues() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        assertEquals(10, sp.largestValues(with)[0]);
        assertEquals(17, sp.largestValues(with)[1]);
        assertEquals(5, sp.largestValues(with)[2]);
        int[][] without = {{ 1, -2,  3}, {-6,  5, -4}, { 7, -8,  9}};
        assertEquals(7, sp.largestValues(without)[0]);
        assertEquals(5, sp.largestValues(without)[1]);
        assertEquals(9, sp.largestValues(without)[2]);
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        assertEquals(2, sp.largestValues(with2)[0]);
        assertEquals(5, sp.largestValues(with2)[1]);
        assertEquals(2, sp.largestValues(with2)[2]);
        assertEquals(5, sp.largestValues(with2)[3]);
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        assertEquals(9, sp.largestValues(with3)[0]);
        assertEquals(7, sp.largestValues(with3)[1]);
        assertEquals(3, sp.largestValues(with3)[2]);
        assertEquals(7, sp.largestValues(with3)[3]);
        // second test for array without saddlePoint
        int[][] without2 = {{ 3, 2,  8}, {4, 6, 3}, {1, 1, 6}};
        assertEquals(4, sp.largestValues(without2)[0]);
        assertEquals(6, sp.largestValues(without2)[1]);
        assertEquals(8, sp.largestValues(without2)[2]);
    }

    @Test
    public void smallestValues() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        assertEquals(-9, sp.smallestValues(with)[0]);
        assertEquals(5, sp.smallestValues(with)[1]);
        assertEquals(-8, sp.smallestValues(with)[2]);
        assertEquals(-10, sp.smallestValues(with)[3]);
        int[][] without = {{ 1, -2,  3}, {-6,  5, -4}, { 7, -8,  9}};
        assertEquals(-2, sp.smallestValues(without)[0]);
        assertEquals(-6, sp.smallestValues(without)[1]);
        assertEquals(-8, sp.smallestValues(without)[2]);
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        assertEquals(-2, sp.smallestValues(with2)[0]);
        assertEquals(2, sp.smallestValues(with2)[1]);
        assertEquals(-5, sp.smallestValues(with2)[2]);
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        assertEquals(1, sp.smallestValues(with3)[0]);
        assertEquals(3, sp.smallestValues(with3)[1]);
        assertEquals(3, sp.smallestValues(with3)[2]);
        assertEquals(3, sp.smallestValues(with3)[2]);
        // second test for array without saddlePoint
        int[][] without2 = {{ 3, 2,  8}, {4, 6, 3}, {1, 1, 6}};
        assertEquals(2, sp.smallestValues(without2)[0]);
        assertEquals(3, sp.smallestValues(without2)[1]);
        assertEquals(1, sp.smallestValues(without2)[2]);
    }

    @Test
    public void hasSaddlePoint() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        assertTrue(sp.hasSaddlePoint(with));
        int[][] without = {{ 1, -2,  3}, {-6,  5, -4}, { 7, -8,  9}};
        assertFalse(sp.hasSaddlePoint(without));
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        assertTrue(sp.hasSaddlePoint(with2));
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        assertTrue(sp.hasSaddlePoint(with3));
        // second test for array without saddlePoint
        int[][] without2 = {{ 3, 2,  8}, {4, 6, 3}, {1, 1, 6}};
        assertFalse(sp.hasSaddlePoint(without2));
        // second test for array without saddlePoint
    }

    @Test
    public void saddlePointRow() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        assertEquals(2, sp.saddlePointRow(with));
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        assertEquals(2, sp.saddlePointRow(with2));
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        assertEquals(2, sp.saddlePointRow(with3));
    }

    @Test
    public void saddlePointColumn() {
        int[][] with = {{-9, 12, -6}, { 7, 14,  5}, {10, -8,  3}, { 6, 17,-10}};
        assertEquals(3, sp.saddlePointColumn(with));
        // second test of array with saddlePoint
        int[][] with2 = {{ 1, -2,  1, 1}, {2,  5, 2, 5}, {2, -3, -1, -5}};
        assertEquals(1, sp.saddlePointColumn(with2));
        // third test for array with multiple saddlePoints
        int[][] with3 = {{ 5, 2,  1, 6}, {4, 7, 3, 4}, {9, 5, 3, 7}, {8, 3, 3, 7}};
        assertEquals(3, sp.saddlePointColumn(with3));
    }

    // As per email 15th March from Martyn Harris this test does not need to be implemented as it has been included in error
    //private int[] setupArray(int elements){
    //    return null;
    //}
}