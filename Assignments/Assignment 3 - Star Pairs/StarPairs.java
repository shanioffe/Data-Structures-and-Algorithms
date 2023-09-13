import java.util.*;
import java.io.*;

/**************************
 * 
 *  Merge-Sort algorithm that takes in unordered sequences of numbers, counts the amount of star pairs (pairs of increasing subsequences), and sorts the sequences into in increasing order.
 *  Time Complexity: Theta(n*log(n))
 * 
 * */

public class StarPairs{
    //global array to manage filenames
    public static String[] files = new String[]{"input3-4.txt", "input3-5.txt"};
    public static void main(String [] args) {
        //performs sort for short input
        input1();
        
        //handles sort for input files
        int i = 0;
        for(i = 0; i < 2; i++){
            
            System.out.println('\n'+"Loading INPUT 3-" + (i+4) + ": ...");
            String sequence = readFile(i);
            int[] seqArray = stringsToInts(sequence);

            System.out.println("Size: " + seqArray.length);
            System.out.println('\n'+"Sorting...");
            
            int[] sorted = mergeSort(seqArray);
            System.out.println("Sorted Sequence: ");
            System.out.println(Arrays.toString(sorted));
        }
    }

    public static String readFile(int i) {
        String data = " ";
        try {
            File input = new File(files[i]);
            Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
              data = scan.nextLine();
            }
        scan.close();

        } catch(FileNotFoundException e) {
            System.out.println("An error occurred reading in the file.");
            e.printStackTrace();
        }
        return data;
    }

    //Turns string input into int array
    public static int[] stringsToInts(String sequenceIn){
        String[] strings = sequenceIn.split(" ");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++){
            ints[i] = Integer.valueOf(strings[i]);
        }
        return ints;
    }
  
    private static int[] mergeSort(int[] unsorted){
        //recursive control if statement
        if(unsorted.length <= 1){
            return unsorted;
        }
      
        int midpoint = unsorted.length / 2;
        int [] left = new int [midpoint];
        int [] right;
      
        if(unsorted.length % 2 == 0){
            right = new int [midpoint];
        }else{
            right = new int [midpoint + 1];
        }
      
        //populate left sequence
        for(int i = 0; i < midpoint; i++){
            left[i] = unsorted[i];
        }
        
        //populate right sequence
        for(int j = 0; j < right.length; j++){
            right[j] = unsorted[midpoint + j];
        }
        
        int[] sorted = new int[unsorted.length];
        left = mergeSort(left);
        right = mergeSort(right);
        sorted = merge(left, right);
        
        return sorted;   
    }
    
    //Merges left and right sequences
    private static int [] merge(int [] left, int [] right){
        int [] result = new int[left.length + right.length]; 
        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;
      
        while(leftPointer < left.length || rightPointer < right.length){
            if(leftPointer < left.length && rightPointer < right.length){
                if(left[leftPointer] < right[rightPointer]){
                    result[resultPointer++] = left[leftPointer++];
                }else{
                    result[resultPointer++] = right[rightPointer++];
                }
            }else if(leftPointer < left.length){
                result[resultPointer++] = left[leftPointer++];
            }else if(rightPointer < right.length){
                result[resultPointer++] = right[rightPointer++];
            }
        }
      
        return result;
    }

    public static void input1(){
        System.out.println('\n'+"Loading INTPUT 1: ...");
        int[] input1 = new int[]{7, 3, 8, 1, 5};
        System.out.println('\n'+"Size: " + input1.length);
        
        System.out.println("Sorting...");
        int[] sorted = mergeSort(input1);
        System.out.println('\n'+"Sorted Sequence: ");
        System.out.println(Arrays.toString(sorted));
    }

}
    