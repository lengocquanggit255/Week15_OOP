package Week15.Strategy;

import java.util.Arrays;

public class Context {
    private int[] arr;
    private Sort algorithm;

    public Context(int[] arr) {
        this.arr = arr;
    }

    public void setSortingAlgorithm(Sort algorithm) {
        this.algorithm = algorithm;
    }

    public void performSort(boolean ascending) {
        algorithm.sort(arr, ascending);
    }

    public int[] getSortedArray() {
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3};
        Context sorter = new Context(arr);
        

        System.out.println("Bubble sort in ascending order:");
        sorter.setSortingAlgorithm(new BubbleSort());
        sorter.performSort(true);
        System.out.println(Arrays.toString(sorter.getSortedArray()));

        System.out.println("Bubble sort in descending order:");
        sorter.performSort(false);
        System.out.println(Arrays.toString(sorter.getSortedArray()));



        System.out.println("Selection sort in ascending order:");
        sorter.setSortingAlgorithm(new SelectionSort());
        sorter.performSort(true);
        System.out.println(Arrays.toString(sorter.getSortedArray()));

        System.out.println("Selection sort in descending order:");
        sorter.performSort(false);
        System.out.println(Arrays.toString(sorter.getSortedArray()));
    }
}
