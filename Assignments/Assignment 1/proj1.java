//import the scanner
import java.util.*;

class Assignment1 {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a sequence of numbers (separated by a comma): ");
        String sequenceIn = scan.nextLine();
        scan.close();
        int[] array = stringsToInts(sequenceIn);
        int n = array.length;

        maxContigSub(array, n); 
    }



    //Turns string ibers from input into integers to populate the array
    public static int[] stringsToInts(String sequenceIn){

        //create strings array
        String[] strings = sequenceIn.split(",");

        //create ints array
        int[] ints = new int[strings.length];

        //converts all the strings to ints for the ints array
        for (int i = 0; i < strings.length; i++){
            ints[i] = Integer.valueOf(strings[i]);
        }

        return ints;
    }

    //Finds the max contiguous subsequence (increasing)
    public static void maxContigSub(int[] array, int n){
        
        //largest is the size of the max contiguous subsequence
        //size is the array of subsequence sizes
        int largest = 0;
        int[] size = new int[n]; 

        //initializes the size array
        for (int i = 0; i < n; i++){
            size[i] = 1;
        }

        //algorithm for computing the size of subsequences
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (array[i] > array[j] && size[i] < size[j] + 1){
                    size[i] = size[i] + 1;
                }
            }
        }

        //parses size array to find the largest one
        for (int i = 0; i < n; i ++){
            if (largest < size[i]){
                largest = size[i];
            }
        }

        System.out.println("Largest subsequence has length " + largest);
        System.out.println("Here is the subsequence: ");
        for (int i = 1; i < n; i++){
            System.out.println(array[i]);
        }
    }
}