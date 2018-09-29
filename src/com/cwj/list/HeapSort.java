package com.cwj.list;

/**
 * Created by cwj on 18-8-13.
 *
 */
public class HeapSort {
    public int[] heapSort(int[] heap){
        int length = heap.length;
        for (int i = length/2 - 1; i>= 0; i--){
            adjust(heap, i, length);
        }
        //找出最大/最小的一个
        for (int i = length-1; i>=0; i--){
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            adjust(heap, 0, i);
        }
        //将堆顶的数交换到末尾，i每次减一，因为已排好序的末尾不需要再调整
        return heap;
    }
    private void adjust(int[] heap, int i, int length){
        int temp = heap[i];
        for (int k = 2*i+1; k < length; k= 2*k+1){
            if ((k+1 < length) && (heap[k] > heap[k+1])){
                k++;
            }
            if (heap[k] < temp){
                heap[i] = heap[k];
                i = k;
            }else {
                break;
            }
        }
        heap[i] = temp;
    }

    public static void main(String[] args) {
        int[] heap = {-4,-1,-4,0,2,-2,-4,-3,2,-3,2,3,3,-4};
        new HeapSort().heapSort(heap);
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + " ");
        }
    }
}
