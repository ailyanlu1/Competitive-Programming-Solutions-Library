The classic algorithm for parsing infix expressions is Dijkstra's shunting yard algorithm. We use this algorithm to process the input 

string. We must remember that after two expressions are added or multiplied together, they become a single operand 

(which is pushed back onto the stack). At the completion of the algorithm, only one operand remains on the stack, which is the answer. 

There are only two tricky implementation details.

First, multiplication is not explicitly indicated in the input expressions. 

The key is to recognize that an implied multiplication sign is omitted between two characters when the first is 

either a letter or a close parenthesis and the second is either a letter or an open parenthesis. 

(Stop and think and convince yourself of this.) When an implied multiplication is discovered, we must treat it as though it were explicitly

indicated.

Second, how do we store the operands on the stack? One possibility is as an array-like structure 

```(like std::vector in C++)``` of strings. For example, the expression ab+cd would be stored as ab and cd encapsulated in one array.

When we add two operands, we simply concatenate their arrays. When we multiply, we want to expand immediately; 

this can be done using the schoolbook method, by considering all pairs of factors and concatenating them. At the completion of the 

algorithm, we are left with a single array of terms. We sort each term, then sort the array, and finally output its elements separated

by plus signs.
