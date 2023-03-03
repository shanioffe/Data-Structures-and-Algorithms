import java.util.*;

class LIS{
    public static void main(String[] args){
        
        int[] sequence = askUser();
        int sizeOfSequence = sequence.length;
        findLIS(sequence, sizeOfSequence);
    }

    public static void findLIS(int[] sequence, int sizeOfSequence){

        int sum = 0;
        int largest = 0;
        int[] sizes = new int[sizeOfSequence];

        for(int i = 0; i < sizeOfSequence; i++){
            sizes[i] = 1;
        }

        for(int i = 0; i<sizeOfSequence; i++){
            for(int j = i; j < sizeOfSequence-1; j++){
                sum = sequence[j+1] - sequence[j];
                if(sum > 0){
                    sizes[i]++;
                }
            }
        }

        for(int i = 0; i < sizeOfSequence; i++){
            if (largest < sizes[i]){
                largest = sizes[i];
            }
        }

        System.out.println("Length of largest increasing subsequence: " + largest);
    }

    public static int[] askUser(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a sequence of numbers (separated by a comma): ");
        String input = scan.nextLine();
        scan.close();

        //create an array of strings by splitting input by commas
        String[] strings = input.split(",");

        //create ints array with same length as strings array
        int[] ints = new int[strings.length];

        //converts all the strings to ints for the ints array
        for (int i = 0; i < strings.length; i++){
            ints[i] = Integer.valueOf(strings[i]);
        }

        return ints;
    }
}