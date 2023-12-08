package Week15.Strategy;

public class SelectionSort implements Sort {

    @Override
    public void sort(int[] arr, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                } else {
                    if (arr[j] > arr[minIndex]) {
                        minIndex = j;
                    }
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
