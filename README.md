# Coursework Brief - MSc Computer Science
## Module: CSM080, Objected Oriented Programming

## Coursework: January to April 2024 study session - End of Term Coursework Assessment

### Submission Deadline: Monday, 8 April 2024 by 13.00 Greenwich Mean Time

- Please note: When the due date is reached, the version you have submitted last, will be considered as your final submission and it will be the version that is marked.
- Once the due date has passed, it will not be possible for you to upload a different version of your coursework assessment. Therefore, you must ensure you have submitted the correct version of your coursework assessment which you wish to be marked, by the due date.

**Note**: Coursework is weighted at 75% of final mark for the module.

### Coursework Description: Saddle Points 

Consider the following very simple "game".  There is a two-dimensional array of numbers, and two players, *Maxie* and *Minnie*. At each turn, independently of each other, Max chooses a row and Min chooses a column. Where they intersect is the amount of money Min pays to Max (if it is *negative*, then 
Max pays Min).

For example, if Max chooses row 1 and Min chooses column 1, then Max wins £14 from Min. If Max chooses row 3 and Min chooses column 2, then Max pays Min £10. 
In general, Max wants larger numbers, and Min wants smaller numbers.

How should they play? 

![Game Example][tab1]

Your class should be named `SaddlePoints`; this class, and a `Main` class, should be placed inside a package called `saddlePoints`. 
**Note** see how these are capitalised and utilise *CamelCase*.

![Another game example][tab2]

For each player, we pick the *"best of the worst"*. In this example, the worst Max can do if he picks row 0 is -9; if row 1, then 5; if row 2, then -8; and if row 3, then -10. Of these possible worst outcomes, Max prefers the largest, which is 5 (row 1).

Similarly, Min wants smaller numbers. The worst she can do if she picks column 0 is 10; if column 1, then 17; and if column 2, then 5. Of these possible worst outcomes, 
Min prefers the smallest, which is 5 (column 2).

Since the "best of the worst" (the maximum of the minimums) for Max is 5, and the "best of the worst" (the minimum of the maximums) for Min is also 5, then Max 
should always choose row 1, and Min should always choose column 2. 

This entry of 5, at row 1 and column 2, is called a *saddle point*. A *saddle point* is defined to be an entry such that it is minimum in its row and maximum in its column. Indeed, if Min does choose column 2, then Max will do worse by choosing any other row; if Max does choose row 1, then Min will do worse by choosing any other column.

It is possible for there to be more than one saddle point, but in this case, it is known that they will have the same value.

## Specifications
Here are the computational methods you shall implement. You shall develop a unit test for each of these methods.

+ `int[][] createRandomArray(int numberOfRows, int numberOfColumns, int minValue, int maxValue)`
Creates and returns an array of the given size and fills it with random values in the specified range.

+ `int largest(int[] array)`
Finds the largest value in an array of integers.

+ `int smallest(int[] array)`
Finds the smallest value in an array of integers.

+ `int[] largestValues(int[][] array)`
Takes a two-dimensional array of integers and returns a one-dimensional array containing the largest values in each column (such as the array `[10, 17, 5]` in the above example).

+ `int[] smallestValues(int[][] array)`
Takes a two-dimensional array of integers and returns a one-dimensional array containing the smallest values in each row (such as the array `[-9, 5, -8, -10]` in the above example).

+ `boolean hasSaddlePoint(int[][] array)`
Takes a two-dimensional array of integers and returns `true` if it has a saddle point and `false` if it does not.

+ `int saddlePointRow(int[][] array)`
Takes a two-dimensional array of integers that is known to have a saddle point, and returns the row number of that saddle point. If there is more than one saddle point, returns the number of any row that contains a saddle point.

+ `int saddlePointColumn(int[][] array)`
Takes a two-dimensional array of integers that is known to have a saddle point, and returns the column number of that saddle point. If there is more than one saddle point, returns the number of any column that contains a saddle point.

Here are the I/O methods for which you **do not** need unit tests:

+ `public static void main(String[] args)`
This is responsible for just calling `run()`.

+ `void run()`<br/>
Creates arrays various sizes (including some 2x2 arrays and some larger), fills them with random values, and prints each array and information about it. 
Keeps generating arrays until it has printed at least one with and one without a saddle point. (Smaller arrays are more likely to have a saddle point; about half of randomly generated 2 by 2 arrays will have one.)

+ `void printArray(int[][] array)`<br/>
Prints the array.

+ `void printArrayInfo(int[][] array)`<br/>
Prints whether the given array has a saddle point, and if so, where it is (row and column) and what its value is. (If there are multiple saddle points, just print any one.) 

To save you some typing, we have provided the file `SaddlePoints.java`, in which all the methods have been entered as stubs, and the comments have been mostly filled out. Start IntelliJ, create the project and the package, and away you go!

### JUnit tests in Java

We also provide the template for your tests in `SaddlePointsTest.java`.  Here are some of the more useful JUnit methods:

+ `assertEquals(expectedValue, actualValue);`
+ `assertArrayEquals(expectedArray, actualArray);`
+ `assertTrue(condition);`
+ `assertFalse(condition);`
+ `fail();`

The method 
```
createRandomArray(numberOfRows, numberOfColumns, minValue, maxValue) 
```
should return a different value every time, but you can still test it. Testing whether it's "really random" is difficult, but the following tests are pretty easy:

+ Test whether the array has the requested number of rows and columns.
+ Test if all the numbers in the array are in the specified range.
+ Test that the array isn't full of the same number, repeated over and over. 
 (This could happen even if your code is correct; but by choosing a larger array or a 
 larger range of values, you can make it extremely unlikely - say, once in a million times.)

### Random Numbers

You will need to import `java.util.Random`.  You will need to create a random number generator (an instance of the class `Random`); you will only need one of these.

To get numbers in a reasonable range, you will need to call one of the methods defined in `Random`. Please read  [this documentation](https://docs.oracle.com/javase/9/docs/api/java/util/Random.html) for more information.

### Arrays in Java

Arrays in Java are like lists in Python, and are indexed the same way. However, the size of an array is specified when you create the array, and you can't add or delete elements. (You can, however, assign a different array to the variable.) You can declare arrays like this:

+ `type [] name = new type[size];`
+ `type [][] name = new type[rows][columns];`

Declared in this way, all locations in the new array contain `null`, zero, or false, depending on the type of the array. You can put specific values in an array when you declare it (but not later) like this:

+ `type [] name = {value1, ..., valueN};`
+ `type [][] name = {{value1_1, ..., value1_N}, ... {valueN_1, ..., valueN_N}};`

To use a literal array in a statement, rather than in a declaration, the syntax is a little different:

+ `name = type [] {value1, ..., valueN};`
+ `name = type [][] {{value1_1, ..., value1_N}, ... {valueN_1, ..., valueN_N}};`

For testing purposes, you can start by using the following game,
```
     int[][] with = {{-9, 12, -6},
                     { 7, 14,  5}, 
                     {10, -8,  3}, 
                     { 6, 17,-10}};
```
which has a saddle point, and this further game
```
     int[][] without = {{ 1, -2,  3},
                        {-6,  5, -4}, 
                        { 7, -8,  9}};
```
which does not.

[tab1]: tab1.png "Example Game"
[tab2]: tab2.png "Another example game"

### Assessment Criteria:
Please refer to Appendix C of the Programme Regulations for detailed Assessment Criteria.

### Plagiarism:
This is cheating. Do not be tempted and certainly do not succumb to temptation. Plagiarised copies are invariably rooted out and severe penalties apply. All assignment submissions are electronically tested for plagiarism. More information may be accessed via: https://learn.london.ac.uk/course/view.php?id=3
