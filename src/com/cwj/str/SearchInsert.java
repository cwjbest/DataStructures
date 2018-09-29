package com.cwj.str;

/**
 * Created by cwj on 18-4-3.
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int mid=0;
        while (start <= end){
            mid = (end+start) / 2;
            if(target == nums[mid]) return mid;
            else if (target > nums[mid]){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return target > nums[mid] ? mid+1:mid;
    }

    public static void main(String[] args) {
        int nums[] = {1, 3, 5, 6};
        int a = new SearchInsert().searchInsert(nums, 2);
        System.out.println(a);
    }
}
