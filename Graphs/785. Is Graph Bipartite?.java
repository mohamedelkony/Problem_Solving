/*
https://leetcode.com/problems/is-graph-bipartite/
*/
class Solution {
        int[][] g;
        int[] state;
        int[] color;

        boolean dfs(int source) {
            if (state[source] == 2)
                return true;
            state[source] = 1;
            if (color[source] == -1)
                color[source] = 0;
            for (int adj : g[source]) {
                if (state[adj] == 1)
                    continue;
                if (color[adj] == -1)
                    color[adj] = color[source] == 0 ? 1 : 0;
                else if (color[adj] == color[source])
                    return false;
                dfs(adj);
            }
            state[source] = 2;
            return true;
        }

        public boolean isBipartite(int[][] graph) {
            g=graph;
            int n = graph.length;
            color = new int[n];
            Arrays.fill(color, -1);
            color[0] = 0;
            state = new int[n];
            for (int i = 0; i < n; i++)
                if(!dfs(i))return false;
            return true;
        }
    }
