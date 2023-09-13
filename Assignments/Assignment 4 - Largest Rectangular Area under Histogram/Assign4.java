/**************
 * Author: Shannon Ioffe
 * March 16, 2023
 * COSC 336
 * Largest Rectangular Area Under Histogram Algorithm
 * 
 * This algorithm can read an input file of coordinates that plot a histogram,
 * then calculate the largest rectangular area inside the shape of the histogram.
 * It does so by tracking the left and right margins of each bar of the histogram,
 * along with their respective heights.
*/


import java.util.*;
import java.io.*;
public class Assign4 {

    //handles files to be scanned
    public static String[] files = {"input-4.3.txt", "input-4.4.txt"};
    public static void main(String[] args) throws FileNotFoundException {

        File input3_1 = new File(files[0]);
        File input3_2 = new File(files[0]);
        Scanner scan1 = new Scanner(input3_1);
        Scanner scan2 = new Scanner(input3_2);
        File input4_1 = new File(files[1]);
        File input4_2 = new File(files[1]);
        Scanner scan3 = new Scanner(input4_1);
        Scanner scan4 = new Scanner(input4_2);

        int[] widths;
        int[] heights;
        int size;

        size = (scan1.nextInt() / 2);
        widths = findRightArray(scan1, size);
        heights = findHeightArray(scan2, size);
        System.out.println("Max area of input-4.3: " + findLargestRectangle(widths, heights, size));

        size = (scan3.nextInt() / 2);
        widths = findRightArray(scan3, size);
        heights = findHeightArray(scan4, size);
        System.out.println("Max area of input-4.4: " + findLargestRectangle(widths, heights, size));

    }

    //populates right margin of each bar
    public static int[] findRightArray(Scanner sc, int size) {
        int[] rightArray = new int[size];
        for (int i = 0; i < size; i++) {
            sc.nextLine();
            sc.nextLine();
            rightArray[i] = (sc.nextInt());
        }
        sc.close();
        return rightArray;
    }

    //populates height values of each bar
    public static int[] findHeightArray(Scanner sc, int size) {
        int[] heightArray = new int[size];
        sc.nextLine();
        for (int i = 0; i < size; i++) {
            sc.nextInt();
            heightArray[i] = sc.nextInt();
            sc.nextLine();
            sc.nextLine();
        }
        sc.close();
        return heightArray;
    }

    //iterates through histogram and finds the largest rectangular area within the shape with linear runtime
    public static int findLargestRectangle(int[] widths, int[] heights, int size) {

        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int smallest;
        int areaWithTop;

        //run through all the bars of the histogram
        int index = 0;
        while (index <= size-1) {
            
            //if this bar is higher than or same as the bar on top of the stack, then push
            if (stack.empty() || heights[stack.peek()] <= heights[index]) {
                stack.push(index++);
            
            //if this bar is shorter than the top of the stack, pop it and calculate the area under it
            } else {
                smallest = stack.peek(); 
                stack.pop();

                areaWithTop = heights[smallest] * (stack.empty() ? widths[index] : widths[index-1] - widths[stack.peek()]);

                if (maxArea < areaWithTop)
                    maxArea = areaWithTop;
            }
        }

        //pop the remaining bars from the stack and find area with every popped bar as the smallest bar
        while (!stack.empty()) {
            smallest = stack.peek();
            stack.pop();

            if(smallest == 0 && !stack.empty()) {
                smallest = 1;
                areaWithTop = heights[smallest] * (widths[index-1] - widths[0]);
                smallest = 0;

            } else {
                areaWithTop = heights[smallest] * (stack.empty() ? widths[index-1] : widths[index-1] - widths[stack.peek()]);
            }

            if (maxArea < areaWithTop)
                maxArea = areaWithTop;
        }
        return maxArea;
    }
}