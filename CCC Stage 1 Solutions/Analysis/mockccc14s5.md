First note that two numbers a and b share a common factor greater than one if and only if GCD(a, b) > 1. 
This gives us a very fast way to check whether two cards can be eliminated, since the Euclidean GCD algorithm runs in O(log(a+b)). 
Otherwise, we would have to resort to the slow way computing all of their factors in O(sqrt(a)+sqrt(b)).

The greedy solution provided in the problem statement has a flaw: the cards must be originally in the optimal ordering for the greedy 
solution to produce the correct answer. There are two ways to get around this, and an entirely different solution.

1. Brute Force Permutations — O(N!×M!)
The first way to "fix" the greedy solution is to simply try all N!×M! permutations of the cards. This will be far too slow, even for
moderate values of N and M. This includes a lot of overlapping permutations, however, is sufficient to get 5/15 of the points.

2. Permuting Black Cards Only — O(max(N,M)!×min(N,M))
We can improve on the above method by generating the N! permutations of the black cards while keeping the white cards in place. 
This works because there is an optimal ordering of the black cards such that the greedy algorithm will eliminate the most cards. 
This leads to a running time of O(N!×N×M) = O(N!M).

A further improvement can be made which eliminates the greedy aspect of the solution entirely. 
Visualize the optimal pairing of the cards as a permutation of the black cards arranged in a row above the white cards, such that 
paired black cards are placed directly above their corresponding white cards. Without touching the white cards' original arrangement, 
there will always be a permutation of the black cards that satisfies this arrangement. So for each permutation, we go through pairs of 
cards at the same position (there are only min(N, M) positions where cards vertically line up). If we can pair them, then pair them. 
This gives a running time of O(max(N,M)!×min(N,M)), but is still not enough to score full marks.

3. Adjacency List Brute Force
Another approach is to brute force the valid pairings using recursion. First, we precompute an adjacency list, where adj[b] is a list 
of all the white cards that the black card b can be paired with. Then, we can use recursion to generate only valid pairings, 
ignoring cards that we cannot pair. The exact running time for this is difficult to calculate, 
but should be about the same as approach number 2.

4. Monte-Carlo + Greedy Algorithm — O(Trials×N×M)
The second way to "fix" the greedy solution gambles upon the fact that most permutations upon the black cards will result in a 
suboptimal solution. Therefore, we may risk being non-deterministic and construct a Monte-Carlo (i.e. randomized) algorithm that tries 
about 1000 random permutations of black cards to apply the greedy algorithm to, and takes the best answer out of those. 
This leads to a running time of O(1000×N×M), which is clearly fast enough for all cases but may produce wrong answers. 
Such an algorithm is given 10/15 points. This is because while it performs excellently on most inputs, a few carefully constructed 
inputs are able to defeat it. Namely, such an input will have the property that there exists only a few optimal matchings, 
yet the greedy solution will more often match a non-optimal black-white pair than not. This is the case when the the black cards are 
prime numbers, and the black cards are products of consecutive prime numbers.


5. Maximum Bipartite Matching with Max-Flow — O(N×M×min(N,M)).
Having come up with two solutions based on a greedy approach, we will try thinking in another direction. 
Observe that we can transform this problem into a graph theory problem by thinking of cards as vertices and matchability as edges. 
There is an edge between a white and a black card if they share a common factor, and the problem has now been reduced to finding a 
maximal matching on this bipartite graph. One way to do this is by finding the maximum flow from a source node which has outgoing edges 
to all of the black cards to a sink node which has incoming edges from all of the white cards (see figure on the right). 
All edges have the capacity of 1 in the flow network, and the maxflow×2 is the number of cards we can eliminate.

We can use the O(E*|f|) Ford-Fulkerson algorithm to compute the max-flow, where E is the number of edges and |f| is the maxflow. 
Here, the maximum number of edges is: N (source edges) + M (sink edges) + NM (pairing edges) = 100 + 100 + 100×100 = 10200. 
The max-flow is min(N, M), because that is the maximum pairs of cards we can eliminate. Thus, we have a total running time of 
O(NM×min(N,M)), and a maximum of 10 000×100 = 1 000 000 operations. In this case, we can a faster version of the Ford-Fulkerson 
algorithm, the Edmonds-Karp algorithm, although runs in O(VE<sup>2</sup>), is also bounded by O(E*|f|). Below is the official solution, 
implementing the Edmonds-Karp algorithm.

```cpp
#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int gcd(int a, int b) {
  if (b == 0) return a;
  return gcd(b, a % b);
}

const int INF = 1<<30;
const int MAX_N = 100;
const int MAX_V = 2*MAX_N + 2; //N + M + source + sink

int N, M, B[MAX_N], W[MAX_N];
vector<int> adj[MAX_V];
int cap[MAX_V][MAX_V];

int edmonds_karp(int nodes, int source, int sink) {
  int max_flow = 0, a, b, best[nodes], pred[nodes];
  bool visit[nodes];
  for (int i = 0; i < nodes; i++) best[i] = 0;
  while (true) {
    for (int i = 0; i < nodes; i++) visit[i] = false;
    visit[source] = true;
    best[source] = INF;
    pred[sink] = -1;
    queue<int> q;
    for (q.push(source); !q.empty(); q.pop()) {
      a = q.front();
      if (a == sink) break;
      for (int j = 0; j < adj[a].size(); j++) {
        b = adj[a][j];
        if (!visit[b] && cap[a][b] > 0) {
          visit[b] = true;
          pred[b] = a;
          best[b] = min(best[a], cap[a][b]);
          q.push(b);
        }
      }
    }
    if (pred[sink] == -1) break;
    for (int i = sink; i != source; i = pred[i]) {
      cap[pred[i]][i] -= best[sink];
      cap[i][pred[i]] += best[sink];
    }
    max_flow += best[sink];
  }
  return max_flow;
}


int main() {
  int source = 2*MAX_N, sink = 2*MAX_N + 1;
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> B[i];
    adj[source].push_back(i*2);
    cap[source][i*2] = 1;
  }
  for (int i = 0; i < M; i++) {
    cin >> W[i];
    adj[i*2+1].push_back(sink);
    cap[i*2+1][sink] = 1;
  }
  for (int i = 0; i < N; i++)
    for (int j = 0; j < M; j++)
      if (gcd(B[i], W[j]) > 1) {
        adj[i*2].push_back(j*2+1);
        adj[j*2+1].push_back(i*2);
        cap[i*2][j*2+1] = 1;
      }
  cout << 2*edmonds_karp(MAX_V, source, sink) << endl;
  return 0;
}
```

6. Maximum Bipartite Matching with Augmenting Paths — O(N×M×min(N,M))
The augmenting path method to find maximal matchings discussed here runs in O(V×(V+E)). Since V = N+M and E = NM, the 
final complexity will also end up being along the lines of O(N×M×min(N,M)). Upon examination, 
this algorithm and the Ford-Fulkerson are extremely similar.
