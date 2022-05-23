/*
https://leetcode.com/problems/palindromic-substrings/

*/
   class Solution {
        int dp(int left, int right) {
            if (left < 0 || right >= n)
                return 0;
            if(left>right)
                return 0;
            if (left == right)
                return 1;
            if (mem[left][right] != -1)
                return mem[left][right];
            if (s.charAt(left) != s.charAt(right))
                return 0;
            if (right == left + 1)
                return 1;

            if (dp(left + 1, right - 1) == 1)
                return mem[left][right] = 1;
            return 0;
        }

        int[][] mem;
        int n;
        String s;

        public int countSubstrings(String s) {
            n = s.length();
            mem=new int[n][];
            for (int i = 0; i < n; i++) {
                mem[i] = new int[n];
                for (int j = 0; j < n; j++)
                    mem[i][j] = -1;
            }
            this.s = s;
            int ans = 0;
            for (int l = 0; l < n; l++)
                for (int r = 0; r < n; r++)
                    ans += dp(l, r);
            return ans;
        }
    }
