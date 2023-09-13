/**************
 * Author: Shannon Ioffe
 * April 11, 2023
 * COSC 336
 * Largest Increasing Subsequence Sum Algorithm
 * 
 * This algorithm works by reading in a file, transcribing the input into 
 * an array of integers, and scanning the array for permutations of increasing 
 * subsequences. Finally, it selects the subsequence with the largest sum.
*/

import java.util.*;
import java.io.*;

class LISsum{

    public static String[] files = {"input1.txt", "input2.txt", "input3.txt", "input4.txt",};
    public static void main(String[] args) throws IOException {
        
        //loop so algorithm processes all 4 input files
        for(int i = 0; i < files.length; i++){
            int[] sequence = readFile(files[i]);
            int sizeOfSequence = sequence.length;
            int result = findLIS(sequence, sizeOfSequence);
            System.out.println("\nInput #" + (i+1) + ":");
            System.out.println("Sum of elements in largest increasing subsequence: " + result);
        }
    }

    public static int findLIS(int[] sequence, int sizeOfSequence){

        int[] sizes = new int[sizeOfSequence];
        int[] sums = new int[sizeOfSequence];
        int sum = 0;

        //initializes sizes and sums arrays with 1
        for(int i = 0; i < sizeOfSequence; i++){
            sizes[i] = 1;
            sums[i] = sequence[i];
        }

        //finds each permutation of increasing subsequences and stores the sums
        for(int i = 0; i < sizeOfSequence; i++){
            for(int j = 0; j < i; j++){
                if (sequence[i] >= sequence[j] && sums[i] < sums[j] + sequence[i])
                    sums[i] = sums[j] + sequence[i];
            }
        }

        //find max sum
        for (int i = 0; i < sizeOfSequence; i++){
            if (sum < sums[i])
                sum = sums[i];
        }

        return sum;
    }

    public static int[] readFile(String fname) throws IOException {

        Scanner scan = new Scanner(new File(fname));

        int sizeOfSequence = scan.nextInt();
        int[] sequence = new int[sizeOfSequence];

        for(int i = 0; i < sizeOfSequence; i++){
            sequence[i] = scan.nextInt();
        }

        scan.close();

        return sequence;
    }
}