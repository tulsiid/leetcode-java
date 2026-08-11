import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {

        int sum = nums[0];

        // Find sequential prefix sum
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Put all numbers in a HashSet
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find smallest missing number greater than sum
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}