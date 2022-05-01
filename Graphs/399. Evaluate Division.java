   /*
   https://leetcode.com/problems/evaluate-division/
   */
   class Solution {

        class edg {
            public int id;
            public double cost;

            public edg() {
            }

            public edg(int id, double cost) {
                this.id = id;
                this.cost = cost;
            }
        }

        ArrayList<ArrayList<edg>> g;
        int[] state;
        edg[] parents;

        boolean dfs(int source, int target) {
            if (state[source] == 2)
                return false;
            state[source] = 1;
            for (var adj : g.get(source)) {
                if (state[adj.id] == 1)
                    continue;
                parents[adj.id] = new edg(source, adj.cost);
                if (adj.id == target)
                    return true;
                if (dfs(adj.id, target))
                    return true;
            }
            state[source] = 2;
            return false;
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            var mp = new TreeMap<String, Integer>();
            for (var eq : equations) {
                if (!mp.containsKey(eq.get(0)))
                    mp.put(eq.get(0), mp.size());

                if (!mp.containsKey(eq.get(1)))
                    mp.put(eq.get(1), mp.size());
            }
            g = new ArrayList<ArrayList<edg>>();
            for (int i = 0; i < mp.size(); i++)
                g.add(new ArrayList<edg>());
            for (int i = 0; i < equations.size(); i++) {
                var eq = equations.get(i);
                int a = mp.get(eq.get(0));
                int b = mp.get(eq.get(1));
                g.get(a).add(new edg(b, values[i]));
                if (values[i] != 0)
                    g.get(b).add(new edg(a, 1 / values[i]));
            }
            int n = g.size();
            var ans = new double[queries.size()];
            state = new int[n];
            parents = new edg[n];
            Arrays.fill(parents, new edg(-1, -1));
            for (int i = 0; i < queries.size(); i++) {
                state = new int[n];
                parents = new edg[n];
                Arrays.fill(parents, new edg(-1, -1));
       
                var q = queries.get(i);
                int a = mp.containsKey(q.get(0)) ? mp.get(q.get(0)) : -1;
                int b = mp.containsKey(q.get(1)) ? mp.get(q.get(1)) : -1;
                if (-1 == b || a == -1)
                    ans[i] = -1;
                else if (a == b && a != -1)
                    ans[i] = 1;
                else {
                    if (dfs(a, b) == false)
                        ans[i] = -1;
                    else {
                        double res = 1;
                        while (parents[b].id != -1) {
                            res *= parents[b].cost;
                            b = parents[b].id;
                        }
                        ans[i] = res;
                    }
                }
            }
            return ans;
        }
    }

   
