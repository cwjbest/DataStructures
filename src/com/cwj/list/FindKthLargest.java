package com.cwj.list;

/**
 * Created by cwj on 18-8-6.
 *
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
         int[] heap = new int[k];
         System.arraycopy(nums, 0, heap, 0, k);

         //从第一个非叶子节点开始调整，完全二叉树的第一个非叶子节点，建立小根堆
         for(int i = k / 2 - 1; i >= 0; --i){
             adjust(heap, i);
         }

         //如果剩余元素小于堆顶，跳过
         //如果剩余元素大于堆顶，替换掉堆顶元素，然后调整
         //这样就可以保证K个元素的小根堆中，堆顶元素就是第K大个数
         for(int i = k; i<nums.length; i++){
             if(heap[0] < nums[i]){
                 heap[0] = nums[i];
                 adjust(heap, 0);
             }
         }
         return heap[0];
     }

     public static void adjust(int[] heap, int i){
         int temp = heap[i];
         int length = heap.length;
         //从左孩子开始，每次都找左孩子
         for(int k = 2*i+1; k<length; k = 2*k+1){
             //小根堆，如果右孩子比左孩子小，就先比右孩子
             if(k+1 < length && heap[k] > heap[k+1]){
                 k++;
             }
             if(heap[k] < temp){
                 heap[i] = heap[k];
                 i = k;
             }else{
                 break;
             }
         }
         //找到调整节点的最终位置
         heap[i] = temp;
     }

//    public int findKthLargest(int[] nums, int k) {
//        return quickSort(nums, 0, nums.length-1, k);
//    }

    public static int quickSort(int[] nums, int start, int end, int k){
        int temp = nums[start];
        int i = start, j = end;
        while(i < j){
            while(i < j && nums[j] > temp)
                j--;
            if(i < j){
                nums[i++] = nums[j];
            }
            while(i < j && nums[i] <= temp){
                i++;
            }
            if(i < j){
                nums[j--] = nums[i];
            }
        }
        nums[i] = temp;
        if(i == nums.length - k){
            return nums[i];
        }else if(i > nums.length - k){
            return quickSort(nums, start, i-1, k);
        }else{
            return quickSort(nums, i+1, end, k);
        }
    }

}
