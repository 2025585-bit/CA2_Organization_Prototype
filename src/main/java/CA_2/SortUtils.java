package CA_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for recursive sorting.
 */
public class SortUtils {

    /**
     * Recursive Merge Sort algorithm.
     * Choice Justification: Merge Sort is selected because it is a stable, 
     * recursive sorting algorithm with a consistent O(n log n) time complexity. 
     * Stability is important when sorting objects (like Employees) to preserve 
     * the relative order of equal elements. It is more reliable than QuickSort 
     * in worst-case scenarios and handles linked data structures efficiently.
     */
    public static void mergeSort(List<String> list, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);

            merge(list, left, middle, right);
        }
    }

    private static void merge(List<String> list, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        List<String> L = new ArrayList<>(list.subList(left, middle + 1));
        List<String> R = new ArrayList<>(list.subList(middle + 1, right + 1));

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).compareToIgnoreCase(R.get(j)) <= 0) {
                list.set(k, L.get(i));
                i++;
            } else {
                list.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, R.get(j));
            j++;
            k++;
        }
    }
}
