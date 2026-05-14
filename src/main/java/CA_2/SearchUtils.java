package CA_2;

import java.util.List;

/**
 * Utility class for recursive searching.
 */
public class SearchUtils {

    /**
     * Recursive Binary Search algorithm.
     * Choice Justification: Binary Search is selected because it provides 
     * exceptional performance with a logarithmic time complexity of O(log n). 
     * Since the requirement is to search in a sorted list, Binary Search is the 
     * most efficient choice compared to linear search, as it significantly 
     * reduces the number of comparisons by halving the search space in each recursive step.
     */
    public static int binarySearch(List<String> list, String target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        int comparison = target.compareToIgnoreCase(list.get(mid));

        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearch(list, target, low, mid - 1);
        } else {
            return binarySearch(list, target, mid + 1, high);
        }
    }
}
