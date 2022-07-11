/*
https://leetcode.com/problems/number-of-people-aware-of-a-secret/
*/
class Solution {
    int[] mem;
    int n;
    int delay;
    int forget;
    int mod=(int)1e9+7;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        mem=new int[n+1];
        for(int i=0;i<n+1;i++)
            mem[i]=-1;
        this.delay=delay;
        this.forget=forget;
        this.n=n;
        return dp(1);
    }
    int dp(int at){
        if(at>n)
            return 0;
        if(mem[at]!=-1)return mem[at];
        int ans=1;
        for(int i=at+delay;i<at+forget;i++)
            ans=(ans+dp(i))%mod;
        if(at+forget<=n)
            ans--;
        return mem[at]=ans;
    }
}
