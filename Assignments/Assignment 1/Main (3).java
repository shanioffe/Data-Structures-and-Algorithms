import java.util.*; //for scanner class

//inputting numbers: numbers must be immediately followed with a comma and a space (", ") for this program to work
//all inputs must be in a single line as well
class Main {
    public static void main(String[] args) {
        //scanner declaration
        Scanner input = new Scanner(System.in);

        //user prompt for integer list + input statement
        System.out.print("Enter list of integers to find subsequence: ");
        String intList = input.nextLine();

        //array/variable declarations and method calls
        int[] array = MakeIntArray(intList);//initialized integer array from string array using method call
        int n = array.length;//holds array size. Parameter
        FindLIS(array, n); //method call to find LIS. Void type, displays LIS size\
    }

    //turn list of list in integers (String type) into integer array
    public static int[] MakeIntArray(String intList){
        //split into substrings after every comma
        String[] stringArr = intList.split(", ");
        //initialize integer array with string array length
        int[] intArr = new int[stringArr.length];

        //loop to include elements in integer array from string array
        for (int i = 0; i < stringArr.length; i++){
            intArr[i] = Integer.valueOf(stringArr[i]);
        }
        return intArr;
    }

    //find largest increasing subsequence method
    public static void FindLIS(int[] array, int n){
        int max = 0;  //holds value to compare lis size
        int[] lis = new int[n]; //creates int array to hold lis sizes

        //initializes lis with 1 for every element
        for (int i = 0; i < n; i++)
            lis[i] = 1;

        //bottom up computation for lis
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (array[i] > array[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[i] + 1;

            }
        }

        //find max value of lis size from array
        for (int k = 0; k < n; k ++){
            if (max < lis[k])
                max = lis[k];
        }

        //display value
        System.out.println("Subsequence length is " + max);
    }
}