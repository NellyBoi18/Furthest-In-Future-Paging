# Furthest In Future Paging
 Implements a Furthest In The Future Paging algorithm using both C and Java

 # Input:
 The input will start with an positive integer, giving the number of instances that follow. For each instance, the first line will be a positive integer, giving the number of pages in the cache. The second line of the instance will be a positive integer giving the number of page requests. The third and final line of each instance will be space delimited positive integers which will be the request sequence.

 # Output:
 For each instance, the program will output the number of page faults achieved by furthest in the future paging assuming the cache is initially empty at the start of processing the page request sequence. One output/instance per line.

# Sample:
**Input:**\
3\
2\
7\
1 2 3 2 3 1 2\
4\
12\
12 3 33 14 12 20 12 3 14 33 12 20\
3\
20\
1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5\

**Output:**\
4\
6\
12

The sample input has three instances. The first has a cache which holds 2 pages. It then has a request sequence of 7 pages. The second has a cache which holds 4 pages and a request sequence of 12 pages. The third has a cache which holds 3 pages and a request sequence of 15 pages.