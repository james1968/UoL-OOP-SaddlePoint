package saddlePoints;
import java.util.Random;

/**
 * Creates a number of random arrays, and checks each array to see
 * if it contains a saddle point. Prints the arrays and the results.
 *
 * @author James Baker (jb362) - final version for upload 28/03/24.
 */
public class SaddlePoints {

    /**
     * Creates arrays various sizes (including some 2x2 arrays and some larger),
     * fills them with random values, and prints each array and information about
     * it. Keeps generating arrays until it has printed at least one with and
     * one without a saddle point.
     */

    // used to count number of arrays that have a saddle point
    private int countSaddlePoint = 0;
    // used to count number of arrays that have no saddle point
    private int countNoSaddlePoint = 0;
    // counter used to the increment the rows and columns by 1
    private int arrayCounter = 0;
    // rows and columns initiated as 2 so that a 2x2 array is generated first
    private int rows = 2;
    private int columns = 2;

    void run() {
        Random rand = new Random();
        // main loop to run the programme until at least one array with a saddle point and one without are generated
        while (true) {
            if (countSaddlePoint >= 1 && countNoSaddlePoint >= 1) {
                break;
            }
            // max value for random values added to array
            final int MAX_RANDOM_VALUE = 30;
            // min value for random values added to array
            final int MIN_RANDOM_VALUE = -30;
            int [][] newArray = createRandomArray(rows, columns, MIN_RANDOM_VALUE, MAX_RANDOM_VALUE);
            printArray(newArray);
            if (hasSaddlePoint(newArray)) {
                printArrayInfo(newArray);
                countSaddlePoint++;
            } else {
                System.out.print("Board has no saddle point.");
                countNoSaddlePoint++;
            }
            arrayCounter++;
            // increments the rows and columns so that the arrays sizes change as the programme runs until a saddle point is found
            // maximum number of arrays to be generated to stop infinite arrays being generated
            final int MAX_NUMBER_ARRAYS = 30;
            if (arrayCounter > 2 && arrayCounter % 2 == 0) {
                rows++;
            } else if (arrayCounter > 2 && arrayCounter % 3 == 0) {
                columns++;
            // sets a limit of 30 for the number of runs to stop programme running indefinitely
            } else if (arrayCounter > MAX_NUMBER_ARRAYS) {
                System.out.println("An array with and without a saddle point could not be found.  Programme terminates after 30 arrays");
                break;
            }
        }
    }

    /**
     * Prints the array.
     *
     * @param array The array to be printed.
     */
    void printArray(int[][] array) {
        // prints basic array details only
        for (int[] ints : array) {
            System.out.println();
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
        }
        System.out.println();
    }

    /**
     * Prints whether the given array has a saddle point, and if so, where it is (row and column)
     * and what its value is. (If there are multiple saddle points, just prints the first.)
     *
     * @param array The array to be checked.
     */
    void printArrayInfo(int[][] array) {
        System.out.println("Board has a saddle point.");
        System.out.println("Saddle Point Row: " + saddlePointRow(array));
        System.out.println("Saddle Point Column: " + saddlePointColumn(array));
        System.out.println("Saddle Point Value: " + smallest(largestValues(array)));

    }

    /**
     * Creates and returns an array of the given size and fills it with random
     * values in the specified range.
     *
     * @param numberOfRows    The number of rows desired.
     * @param numberOfColumns The number of columns desired.
     * @param minValue        The smallest number allowable in the array.
     * @param maxValue        The largest number allowable in the array.
     * @return
     */

    int[][] createRandomArray(int numberOfRows, int numberOfColumns, int minValue, int maxValue) {
        Random rand = new Random();
        int[][] newArray = new int[numberOfRows][numberOfColumns];
        try {
            // try statement to check that maximum value entered is greater than minimum value
            for (int i = 0; i < numberOfRows; i++){
                for(int j = 0; j < numberOfColumns; j++) {
                    newArray[i][j] = rand.nextInt(maxValue - minValue + 1) + minValue;
                }
            }
        } catch(IllegalArgumentException f) {
            System.out.println("Please enter a maximum greater than minimum");
            System.out.println(f);
        }
        return newArray;
    }

    /**
     * Finds the largest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The largest value in the array.
     */
    int largest(int[] array) {
        int largestRow = array[0];

        for (int n : array) {
            if (n > largestRow) {
                largestRow = n;
            }
        }
        return largestRow;
    }

    /**
     * Finds the smallest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The smallest value in the array.
     */
    int smallest(int[] array) {
        int smallestColumn = array[0];
        for (int m : array) {
            if (m < smallestColumn) {
                smallestColumn = m;
            }
        }
        return smallestColumn;
    }

    /**
     * Returns an array containing the largest values in each column of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the largest values in each column.
     */
    int[] largestValues(int[][] array) {
        // loops through generated arrays and selects the largest values in each column
        int[] maxColumnValue = new int[array[0].length];
        for (int j = 0; j < array[0].length; j++) {
            int max = array[0][j];
            for (int i = 0; i < array.length; i++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
                maxColumnValue[j] = max;
            }
        }
        return maxColumnValue;
    }

    /**
     * Returns an array containing the smallest values in each row of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the smallest values in each row.
     */
    int[] smallestValues(int[][] array) {
        // loops through generated array and creates new array with smallest values in
        int[] minRowValue = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int min = array[i][0];
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
                minRowValue[i] = min;
            }
        }
        return minRowValue;
    }


    /**
     * Returns true if the given array has a saddle point, and false if it does not.
     *
     * @param array The array to be checked.
     * @return True if the array has a saddle point, else false.
     */
    boolean hasSaddlePoint(int[][] array) {
        return smallest(largestValues(array)) == largest(smallestValues(array));
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * row containing a saddle point. If more than one row contains a saddle point,
     * the first such row will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered row containing a saddle point.
     */
    int saddlePointRow(int[][] array) {
        int saddlePointRow = 0;
        if (hasSaddlePoint(array)) {
            for (int i = 0; i < smallestValues(array).length; i++) {
                if (smallestValues(array)[i] == largest(smallestValues(array))) {
                    saddlePointRow = i + 1;
                    return saddlePointRow;
                }
            }
        }
        return saddlePointRow;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * column containing a saddle point. If more than one column contains a saddle point,
     * the first such column will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered column containing a saddle point.
     */

    int saddlePointColumn(int[][] array) {
        int saddlePointColumn = 0;
        if (hasSaddlePoint(array)) {
            for (int i = 0; i < largestValues(array).length; i++) {
                if (largestValues(array)[i] == smallest(largestValues(array))) {
                    saddlePointColumn = i + 1;
                    return saddlePointColumn;
                }
            }
        }
        return saddlePointColumn;
    }
}