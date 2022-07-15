// https://leetcode.com/problems/jump-game-vi/
class Solution {
    class pair{
        int id;
        int value;
        public pair(int id,int value){
            this.id=id;
            this.value=value;
        }
    }
    public int maxResult(int[] arr, int k) {
        Queue<pair> pq=new PriorityQueue<>((a,b)->{
                                          if(a.id==b.id)
                                           return 0;
                                           return b.value-a.value;
            });
        int n=arr.length;
        int[] best=new int[n];
        best[n-1]=arr[n-1];
        pq.add(new pair(n-1,best[n-1]));
        
        for(int r=n-2;r>=0;r--){
           int x=pq.peek().id;
            while(x>k+r){
             pq.poll();    
             x=pq.peek().id;         
            }
            best[r]=arr[r]+pq.peek().value;
            pq.add(new pair(r,best[r]));
         
        }
        return best[0];
    }
}
