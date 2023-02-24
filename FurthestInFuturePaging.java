import java.util.*;

public class FurthestInFuturePaging {
    // Calculates the number of page faults using the Furthest-In-Future Paging algorithm
    public static int furthestInFuturePaging(int cacheSize, int[] requests) {
        // Initialize a hashset to store pages in the cache
        Set<Integer> cache = new HashSet<>();
        // Initialize a hashmap to store the next occurrence of each page
        Map<Integer, Integer> futureOccurrence = new HashMap<>();
        // Initialize a variable to keep track of the number of page faults
        int numFaults = 0;

        // Iterate through each request
        for (int x = 0; x < requests.length; x++) {
            int page = requests[x];

            // Check if the page is already in the cache
            if (!cache.contains(page)) {
                numFaults++;

                // If the cache is full, remove the page with the furthest future occurrence
                if (cache.size() == cacheSize) {
                    int pageToRemove = -1;
                    int maxFutureOccurrence = -1;

                    for (int cachedPage : cache) {
                        int futureIndex = futureOccurrence.get(cachedPage);
                        if (futureIndex > maxFutureOccurrence) {
                            maxFutureOccurrence = futureIndex;
                            pageToRemove = cachedPage;
                        }
                    }

                    cache.remove(pageToRemove);
                    futureOccurrence.remove(pageToRemove);
                }

                // Add the new page to the cache
                cache.add(page);
            }

            // Update the future occurrence of the current page
            futureOccurrence.put(page, findNextOccurrence(requests, x));
        }

        // Return the number of page faults
        return numFaults;
    }

    // Finds the next occurrence of a page
    public static int findNextOccurrence(int[] requests, int currentIndex) {
        int nextPage = requests[currentIndex];

        for (int x = currentIndex + 1; x < requests.length; x++) {
            if (requests[x] == nextPage) {
                return x;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        // Initialize a scanner to read input from the console
        Scanner sc = new Scanner(System.in);
        // Read user input for the number of test cases
        int numTests = sc.nextInt();
        // Initialize an array to store the number of page faults for each test case
        int numFaults[] = new int[numTests];

        // Iterate through each test case
        for (int x = 0; x < numTests; x++) {
            // Read user input for the cache size and the number of requests for the current test case
            int cacheSize = sc.nextInt();
            int numRequests = sc.nextInt();
            // Initialize an array to store the requests for the current test case
            int[] requests = new int[numRequests];

            // Read user input for the current test case
            for (int y = 0; y < numRequests; y++) {
                requests[y] = sc.nextInt();
            }

            // Calculate the number of page faults for the current test case
            numFaults[x] = furthestInFuturePaging(cacheSize, requests);
        }

        // Close the scanner
        sc.close();

        // Print the number of page faults for each test case
        for(int x = 0; x < numTests; x++) {
            System.out.println(numFaults[x]);
        }
    }

}
