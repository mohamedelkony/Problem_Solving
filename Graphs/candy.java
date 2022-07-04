//https://leetcode.com/problems/candy/submissions/
class Solution {
    List<List<Integer>> tree;
    int[] num;
    public int candy(int[] rate) {
        int n=rate.length;
        num=new int[n];
        for(int i=0;i<n;i++)
            num[i]=1;
        tree=new ArrayList<List<Integer>>();
        for(int i=0;i<n;i++)
            tree.add(new ArrayList<Integer>());
        for(int i=1;i<n;i++){
            if(rate[i]>rate[i-1])
                tree.get(i-1).add(i);
        }
        for(int i=n-2;i>=0;i--){
            if(rate[i]>rate[i+1])
                tree.get(i+1).add(i);
        }
        int[] in =new int[n];
        int[] out =new int[n];
        for(int i=0;i<n;i++){
            for(int x:tree.get(i)){
                out[i]++;
                in[x]++;
            }
        }
        for(int i=0;i<n;i++){
            if(out[i]>0 && in[i]==0){
                dfs(i);
            }
        }
        int ans=0;
        for(int i:num){
                ans+=i;
        System.out.print(i+",");
        }
        return ans;
    }
    void dfs(int root)
    {
        for(int to:tree.get(root)){
            num[to]=Math.max(num[root]+1,num[to]);
            dfs(to);
        }
    }
}
