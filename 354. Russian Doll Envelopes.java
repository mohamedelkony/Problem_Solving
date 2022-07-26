/*
https://leetcode.com/problems/russian-doll-envelopes/
*/
class Solution {
    public int maxEnvelopes(int[][] mat) {
        Arrays.sort(mat,(a,b)->{
            if(a[0]==b[0])
                return b[1]-a[1];
            return a[0]-b[0];
        });
        int[] low=new int[mat.length];
        int sz=1;
        low[0]=mat[0][1];
        for(int at=1;at<mat.length;at++){
            int h=mat[at][1];
            int l=0;int r=sz-1;
            int mid=l+(r-l)/2;
            while(l<r){
                if(low[mid]>h)
                    r=mid;
                else
                    l=mid+1;
                mid=l+(r-l)/2;
            }
            if(low[mid]>h){
                if(mid==0||h>low[mid-1]) 
                    low[mid]=h;
            }
            else if(h>low[sz-1]){
                low[sz]=h;
                sz++;
            }
    
        }
        return sz;
    }
}
