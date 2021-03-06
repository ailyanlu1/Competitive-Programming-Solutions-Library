This is a relatively easy problem for stage 2; because the input is small, inefficient solutions can pass. For example, 

we can store all the words we have seen so far in one array, and their frequencies in another array; each time we read in a word, 

we first linear search through the first array to determine if the word has already been seen; if so, we update its frequency in the 

other array, otherwise we add it to the first array and add a 1 to the second array. After processing the test case we can directly use 

the definition of "kth most common"; for every word we can loop through the array and count how many words are more frequent, 

outputting the word if this equals k-1. This is an O(N<sup>2</sup>) solution (where N is the number of words in the test case and k is the 

maximum length of a word, since this many operations are required in the worst case to compare two words for equality).

A significantly faster solution can be obtained by storing the words and frequencies in an associative array, 

ike C++'s ```std::map```, which gives O(N log N) for the first step, or a trie, which gives O(N). 

Sorting can be accomplished quickly, in O(N log N) time. Finally, to identify which words to output, we can just walk through the 

sorted array in "blocks", where each "block" is a set of words with the same frequency; if the first word in the block is at

position k in the array (one-based), we print out all the words in that block. (This block-walking technique is not an algorithm in 

itself, but rather a step in the solutions to many problems.) This gives an O(N log N) algorithm overall
