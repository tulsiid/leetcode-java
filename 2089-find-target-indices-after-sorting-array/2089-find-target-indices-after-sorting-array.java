import java.util.*;

class Solution {
public List<Integer> targetIndices(int[] nums, int target) {
Arrays.sort(nums);


    List<Integer> result = new ArrayList<>();

    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else {
            right = mid - 1;
        }
    }

    while (left < nums.length && nums[left] == target) {
        result.add(left);
        left++;
    }

    return result;
}


}
