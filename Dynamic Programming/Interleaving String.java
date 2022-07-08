class Solution {
    int dp(int i1,int i2,int i3){
        int n3=s3.length();
          int n2=s2.length();
          int n1=s1.length();
        if(i3>=n3 &&i2>=n2 && i1>=n1)
            return 1;
        if(i3>=n3)
            return 0;
        if(mem[i1][i2][i3]!=-1)
            return mem[i1][i2][i3];
        int c1=0;
        if(i1<n1&&s1.charAt(i1)==s3.charAt(i3))
            c1=dp(i1+1,i2,i3+1);
        int c2=0;
        if(i2<n2 && s2.charAt(i2)==s3.charAt(i3))
            c2=dp(i1,i2+1,i3+1);
        return mem[i1][i2][i3]=Math.max(c1,c2);
        
    }
    String s3,s2,s1;
    int[][][] mem;
    public boolean isInterleave(String ss1, String ss2, String ss3) {
        s1=ss1;
        s2=ss2;
        s3=ss3;
        mem=new int[101][][];
        for(int i=0;i<=100;i++){
            mem[i]=new int[101][];
            for(int j=0;j<=100;j++){
                mem[i][j]=new int[201];
                for(int k=0;k<=200;k++)
                    mem[i][j][k]=-1;
            }
        }
        return dp(0,0,0)==0?false:true;
    }
}
