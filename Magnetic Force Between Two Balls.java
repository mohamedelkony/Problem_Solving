/*
https://leetcode.com/problems/magnetic-force-between-two-balls/
*/
class Solution {
  
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 1;
        int r = (int) 1e9 + 1;
        int mid = l + (r - l+1) / 2;
        while (l < r) {
            if (feasible(mid, position, m))
                l = mid;
            else
                r = mid - 1;
            mid = l + (r - l+1) / 2;
        }
        return mid;
    }

    boolean feasible(int minDist, int[] arr, int minCount) {
        int count = 1;
        int pre = 0;
        while (count < minCount) {
            int l = pre + 1;
            int r = arr.length - 1;
            int mid = l + (r - l) / 2;
            while (l < r) {
                if (arr[mid] - arr[pre] >= minDist)
                    r = mid;
                else
                    l = mid + 1;
                mid=l+(r-l)/2;
            }
            if (mid<=arr.length-1 && mid>=pre+1 &&arr[mid] - arr[pre] >= minDist) {
                pre = mid;
                count++;
            } else
                break;
        }
        if (count >= minCount)
            return true;
        return false;
    }

}
