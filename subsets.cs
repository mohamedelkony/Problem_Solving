//https://leetcode.com/problems/subsets/

public class Solution {
    public IList<IList<int>> Subsets(int[] nums) {
        int n=(int)Math.Pow(2,nums.Length);
        var ans=new List<IList<int>>();
        for(int i=0;i<n;i++)
        {
            ans.Add(new List<int>());
            for(int index=0;index<nums.Length;index++)
            {
                if(((i>>index) & 1)>0)
                {
                    ans[ans.Count-1].Add(nums[index]);
                }   
            }
        }
        return ans;
    }
}
