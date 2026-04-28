import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Sorter     sorter     = new Sorter();
        Searcher   searcher   = new Searcher();
        Experiment experiment = new Experiment();

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   ALGORITHM DEMO — Small Array (10 elements)             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        int[] smallArray = sorter.generateRandomArray(10);
        System.out.print("  Original array:  ");
        sorter.printArray(smallArray);

        int[] bubbleDemo = Arrays.copyOf(smallArray, smallArray.length);
        sorter.basicSort(bubbleDemo);
        System.out.print("  After Bubble Sort: ");
        sorter.printArray(bubbleDemo);

        int[] mergeDemo = Arrays.copyOf(smallArray, smallArray.length);
        sorter.advancedSort(mergeDemo);
        System.out.print("  After Merge Sort:  ");
        sorter.printArray(mergeDemo);

        int target = mergeDemo[5];
        int foundIndex = searcher.search(mergeDemo, target);
        System.out.println();
        System.out.println("  Binary Search demo:");
        System.out.println("  Sorted array:  ");
        System.out.print("    ");
        sorter.printArray(mergeDemo);
        System.out.println("  Searching for: " + target);
        if (foundIndex != -1) {
            System.out.println("  Found at index: " + foundIndex + " ✓");
        } else {
            System.out.println("  Not found ✗");
        }

        int missingTarget = -999;
        int notFound = searcher.search(mergeDemo, missingTarget);
        System.out.println();
        System.out.println("  Searching for (missing): " + missingTarget);
        System.out.println("  Result: " + (notFound == -1 ? "Not found (-1) ✓" : "Found at " + notFound));

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   ALGORITHM DEMO — Medium Array (100 elements)           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        int[] mediumArray = sorter.generateRandomArray(100);

        int[] medBubble = Arrays.copyOf(mediumArray, mediumArray.length);
        long t1 = System.nanoTime();
        sorter.basicSort(medBubble);
        long t2 = System.nanoTime();
        System.out.println("  Bubble Sort on 100 elements: " + (t2 - t1) + " ns");

        int[] medMerge = Arrays.copyOf(mediumArray, mediumArray.length);
        long t3 = System.nanoTime();
        sorter.advancedSort(medMerge);
        long t4 = System.nanoTime();
        System.out.println("  Merge Sort on 100 elements:  " + (t4 - t3) + " ns");

        int medTarget = medMerge[50];
        long t5 = System.nanoTime();
        int medResult = searcher.search(medMerge, medTarget);
        long t6 = System.nanoTime();
        System.out.println("  Binary Search on 100 elements: " + (t6 - t5) + " ns"
                + "  →  index " + medResult);

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   ALGORITHM DEMO — Large Array (1000 elements)           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        int[] largeArray = sorter.generateRandomArray(1000);

        int[] largeBubble = Arrays.copyOf(largeArray, largeArray.length);
        long la1 = System.nanoTime();
        sorter.basicSort(largeBubble);
        long la2 = System.nanoTime();
        System.out.println("  Bubble Sort on 1000 elements: " + (la2 - la1) + " ns");

        int[] largeMerge = Arrays.copyOf(largeArray, largeArray.length);
        long la3 = System.nanoTime();
        sorter.advancedSort(largeMerge);
        long la4 = System.nanoTime();
        System.out.println("  Merge Sort on 1000 elements:  " + (la4 - la3) + " ns");

        int largeTarget = largeMerge[750];
        long la5 = System.nanoTime();
        int largeResult = searcher.search(largeMerge, largeTarget);
        long la6 = System.nanoTime();
        System.out.println("  Binary Search on 1000 elements: " + (la6 - la5) + " ns"
                + "  →  index " + largeResult);

        experiment.runAllExperiments();

        System.out.println("\n  Program completed successfully.");
    }
}