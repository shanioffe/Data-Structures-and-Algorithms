/****************************************************************************
 * Title: Assignment 8
 * Author: Shannon Ioffe
 * Date: 5/5/2023
 * Description: This algorithm runs a BFS on two graphs in order find the 
 * shortest path (and number of them) from node 1 to each other node in the 
 * graph. Finally, it uses the Adj List Graph class to print the data for 
 * those paths.
 *
*/

import java.util.*;

public class Graph {
    
    private static final int INF = Integer.MAX_VALUE; // represents infinity
    
    public static void main(String[] args) {
        // creating graph G1
        Adj_List_Graph G1 = new Adj_List_Graph(7);
        G1.addEdge(0, 1);
        G1.addEdge(0, 2);
        G1.addEdge(0, 3);
        G1.addEdge(1, 4);
        G1.addEdge(2, 4);
        G1.addEdge(3, 5);
        G1.addEdge(4, 5);
        G1.addEdge(4, 6);
        G1.addEdge(5, 6);
        
        // creating graph G2
        Adj_List_Graph G2 = new Adj_List_Graph(9);
        G2.addEdge(0, 1);
        G2.addEdge(0, 2);
        G2.addEdge(0, 3);
        G2.addEdge(0, 4);
        G2.addEdge(0, 5);
        G2.addEdge(1, 6);
        G2.addEdge(2, 6);
        G2.addEdge(3, 6);
        G2.addEdge(4, 6);
        G2.addEdge(5, 6);
        G2.addEdge(6, 7);
        G2.addEdge(6, 8);
        
        int s = 0; // starting node
        
        System.out.println("For G1:");
        bfs(G1, s);
        
        System.out.println("\nFor G2:");
        bfs(G2, s);
    }
    
    public static void bfs(Adj_List_Graph G, int s) {
        int n = G.n;
        int[] dist = new int[n]; // stores the shortest path from s to each node
        int[] npath = new int[n]; // stores the number of shortest paths from s to each node
        Arrays.fill(dist, INF);
        dist[s] = 0;
        npath[s] = 1;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        
        while (!q.isEmpty()) {
            int u = q.remove();
            for (int v : G.adj.get(u)) {
                if (dist[v] == INF) { // node v hasn't been visited yet
                    dist[v] = dist[u] + 1;
                    npath[v] = npath[u];
                    q.add(v);
                } else if (dist[u] + 1 == dist[v]) { // another shortest path to v found
                    npath[v] += npath[u];
                }
            }
        }
        
        // printing the dist and npath arrays
        System.out.println("dist[] array");
        for (int i = 0; i < n; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println("\nnpath[] array");
        for (int i = 0; i < n; i++) {
            System.out.print(npath[i] + " ");
        }
        System.out.println();
    }
}
