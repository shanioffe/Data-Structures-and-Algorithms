public class radixSort {
    
    public static void sort(int[] arr) {
        int n = arr.length;
        int max = n * n - 1;
        int k = (int) Math.ceil(Math.sqrt(max));
        int[][] buckets = new int[k][n];
        int[] count = new int[k];
        
        // Sort by the 1s place
        for (int i = 0; i < n; i++) {
            int digit = arr[i] % k;
            buckets[digit][count[digit]++] = arr[i];
        }

        // Concatenate buckets
        int index = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[index++] = buckets[i][j];
            }
            count[i] = 0;
        }

        // Sort by the 10s place
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / k) % k;
            buckets[digit][count[digit]++] = arr[i];
        }

        // Concatenate buckets
        index = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[index++] = buckets[i][j];
            }
            count[i] = 0;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {45, 98, 3, 82, 132, 71, 72, 143, 91, 28, 7, 45};
        System.out.println("Before sorting:");
        printArray(arr);
        sort(arr);
        System.out.println("After sorting:");
        printArray(arr);
    }
    
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
