#include <stdio.h>
#include <stdlib.h>

// Implement the furthest-in-future paging algorithm and returns the number of page faults for each instance
int FurthestInFuture(int cacheSize, int numRequests, int* requests) {
    int numFaults = 0;
    int* cache = calloc(cacheSize, sizeof(int)); // Allocate memory for the cache array
    int* future = calloc(cacheSize, sizeof(int)); // Allocate memory for the future array
    
    // Loop through each page request
    for (int x = 0; x < numRequests; x++) {
        int page = requests[x];
        
        // Check if page is already in cache
        int inCache = 0;
        for (int j = 0; j < cacheSize; j++) {
            if (cache[j] == page) {
                inCache = 1;
                break;
            }
        }
        
        // If page is not in cache, add it and evict the page with the furthest future access
        if (!inCache) {
            numFaults++; // Increment the number of page faults
            
            // Find the page in cache with the furthest future access
            int maxFuture = -1;
            int evictIndex = -1;
            for (int y = 0; y < cacheSize; y++) {
                future[y] = numRequests; // Initialize to furthest future
                for (int z = x+1; z < numRequests; z++) {
                    if (requests[z] == cache[y]) {
                        future[y] = z;
                        break;
                    }
                }
                if (future[y] > maxFuture) {
                    maxFuture = future[y];
                    evictIndex = y;
                }
            }
            
            // Evict page with furthest future access and add new page to cache
            cache[evictIndex] = page;
        }
    }
    
    // Free the dynamically allocated memory and return the number of page faults
    free(cache);
    free(future);
    return numFaults;
}

int main() {
    int numInstances;
    scanf("%d", &numInstances); // User input for number of instances
    int* numFaults = malloc(numInstances * sizeof(int)); // Allocate memory for numFaults array to store the number of page faults for each instance
    
    // Loop through each instance
    for (int x = 0; x < numInstances; x++) {
        int cacheSize, numRequests;
        scanf("%d %d", &cacheSize, &numRequests); // User input for cache size and number of page requests
        int* requests = malloc(numRequests * sizeof(int)); // Allocate memory for the requests array

        for (int y = 0; y < numRequests; y++) {
            scanf("%d", &requests[y]); // Read each page request from standard input
        }
        
        numFaults[x] = FurthestInFuture(cacheSize, numRequests, requests); // Run the furthest-in-future paging algorithm and store the number of page faults
        
        free(requests); // Free the dynamically allocated memory
    }

    // Print the number of page faults for each instance
    for (int x = 0; x < numInstances; x++) {
        printf("%d\n", numFaults[x]);
    }

    free(numFaults); // Free the dynamically allocated memory
    
    return 0;
}
