import java.util.Arrays;

public class Experiment {

    private final Sorter   sorter   = new Sorter();
    private final Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {

        int[] copy = Arrays.copyOf(arr, arr.length);

        long startTime = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);          // Bubble Sort
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);       // Merge Sort
        }

        long endTime = System.nanoTime();
        return endTime - startTime;          // duration in nanoseconds
    }

    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime   = System.nanoTime();
        return endTime - startTime;
    }


    private void printTableHeader() {
        System.out.println("+----------------+---------------+-------------------+--------------------+");
        System.out.printf( "| %-14s | %-13s | %-17s | %-18s |%n",
                "Array Size", "Input Type", "Bubble Sort (ns)", "Merge Sort (ns)");
        System.out.println("+----------------+---------------+-------------------+--------------------+");
    }

    private void printTableRow(int size, String type, long bubbleTime, long mergeTime) {
        System.out.printf("| %-14d | %-13s | %-17d | %-18d |%n",
                size, type, bubbleTime, mergeTime);
    }

    private void printSearchTableHeader() {
        System.out.println("+----------------+---------------+----------------------+----------+");
        System.out.printf( "| %-14s | %-13s | %-20s | %-8s |%n",
                "Array Size", "Input Type", "Binary Search (ns)", "Found?");
        System.out.println("+----------------+---------------+----------------------+----------+");
    }

    private void printSearchTableRow(int size, String type, long searchTime, boolean found) {
        System.out.printf("| %-14d | %-13s | %-20d | %-8s |%n",
                size, type, searchTime, found ? "YES" : "NO");
    }


    public void runAllExperiments() {

        int[] sizes = {10, 100, 1000};


        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           SORTING EXPERIMENT RESULTS                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        printTableHeader();

        for (int size : sizes) {


            int[] randomArr = sorter.generateRandomArray(size);
            long bubbleRandom = measureSortTime(randomArr, "basic");
            long mergeRandom  = measureSortTime(randomArr, "advanced");
            printTableRow(size, "Random", bubbleRandom, mergeRandom);


            int[] sortedArr = Arrays.copyOf(randomArr, size);
            Arrays.sort(sortedArr);                          // use Java's built-in to create sorted version
            long bubbleSorted = measureSortTime(sortedArr, "basic");
            long mergeSorted  = measureSortTime(sortedArr, "advanced");
            printTableRow(size, "Sorted", bubbleSorted, mergeSorted);


            System.out.println("+----------------+---------------+-------------------+--------------------+");
        }


        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           SEARCHING EXPERIMENT RESULTS                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("  Algorithm: Binary Search  |  Requires: Sorted Array");

        printSearchTableHeader();

        for (int size : sizes) {


            int[] randomArr = sorter.generateRandomArray(size);
            int[] sortedRandom = Arrays.copyOf(randomArr, size);
            Arrays.sort(sortedRandom);


            int targetRandom = sortedRandom[size - 1];
            long searchTimeRandom = measureSearchTime(sortedRandom, targetRandom);
            int  resultRandom     = searcher.search(sortedRandom, targetRandom);
            printSearchTableRow(size, "Random→Sorted", searchTimeRandom, resultRandom != -1);


            int[] sortedArr = new int[size];
            for (int i = 0; i < size; i++) sortedArr[i] = i * 2;  // even numbers: 0,2,4,...
            int targetMissing  = -1;                                // guaranteed not in array
            long searchTimeMiss = measureSearchTime(sortedArr, targetMissing);
            int  resultMissing  = searcher.search(sortedArr, targetMissing);
            printSearchTableRow(size, "Sorted", searchTimeMiss, resultMissing != -1);

            System.out.println("+----------------+---------------+----------------------+----------+");
        }


        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   ANALYSIS SUMMARY                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("  Bubble Sort  → O(n²)       — slower, simple comparison-based");
        System.out.println("  Merge Sort   → O(n log n)  — faster, divide-and-conquer");
        System.out.println("  Binary Search→ O(log n)    — very fast, requires sorted input");
        System.out.println();
        System.out.println("  KEY FINDINGS:");
        System.out.println("  • Merge Sort is significantly faster than Bubble Sort at large n");
        System.out.println("  • Bubble Sort on a sorted array can be slightly faster (fewer swaps)");
        System.out.println("  • Binary Search is extremely fast due to logarithmic growth");
        System.out.println("  • Performance gap widens as input size increases (n=1000 vs n=10)");
    }
}
