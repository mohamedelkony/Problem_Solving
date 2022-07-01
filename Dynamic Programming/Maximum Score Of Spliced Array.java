class Solution {
    public int maximumsSplicedArray(int[] a, int[] b) { 
        int n=a.length;
        if(n==1)
            return Math.max(a[0],b[0]);
        int[] diff=new int[n];
        for(int i=0;i<n;i++)
            diff[i]=a[i]-b[i];
        int[] sum=new int[n];
        sum[0]=diff[0]>=0?diff[0]:0;
        int mx=0;
        for(int i=1;i<n;i++){
            sum[i]=sum[i-1]+diff[i]>=0?sum[i-1]+diff[i]:0;
            mx=Math.max(sum[i],mx);
        }
        int c1=mx+Arrays.stream(b).sum();
        
        for(int i=0;i<n;i++)
            diff[i]=b[i]-a[i];
        sum=new int[n];
        sum[0]=diff[0]>=0?diff[0]:0;
        mx=0;
        for(int i=1;i<n;i++){
            sum[i]=sum[i-1]+diff[i]>=0?sum[i-1]+diff[i]:0;
            mx=Math.max(sum[i],mx);
        }
        int c2=mx+Arrays.stream(a).sum();
        
        return Math.max(c1,c2);
    }
}
