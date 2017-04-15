package com.antt.dsa.search;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by antt on 4/15/2017.
 */
public class epi128 {
    public static void main(String[] args) {
        int[] a = new int[]{1};
        System.out.println(kthLargest(a, 0, a.length-1, 1));

        a = new int[]{2,1};
        System.out.println(kthLargest(a, 0, a.length-1, 1));

        a = new int[]{2,1,3,5,6};
        System.out.println(kthLargest(a, 0, a.length-1, 1));

//        a = new int[]{};
//        kthLargest(a, 0, a.length-1, 1);
//        System.out.println(kthLargest(a, 0, a.length-1, 1));

        a = new int[]{2,1,3,5,6};
        System.out.println(kthLargest(a, 0, a.length-1, 2));

        a = new int[]{2,1,3,5,6};
        System.out.println(kthLargest(a, 0, a.length-1, 3));

        a = new int[]{2,1,3,5,6};
        System.out.println(kthLargest(a, 0, a.length-1, 4));

        a = new int[]{2,1,3,5,6};
        System.out.println(kthLargest(a, 0, a.length-1, 5));

    }

    public static int kthLargest(int[] arr, int start, int end, int k) {
//        if (start >= end) return???
        if (start >= end) {
            if (k == 1) return arr[end];
            return -1;
        }
        Random rand = new Random();
        int pv = rand.nextInt(end-start+1) + start;
        swap(arr, start, pv);
        int ib = start+1;
        int i = start+1;
        while(i <= end) {
            if (arr[i] >= arr[start]) {
                swap(arr, i, ib);
                ib++;
            }
            i++;
        }
        swap(arr, start, ib-1);
        int len = ib - start;
        if (len==k) {
            return arr[ib-1];
        } else if (len < k) {
            return kthLargest(arr, ib, end, k - len);
        } else {
            return kthLargest(arr, start, end, k);
        }
    }

    public static void swap(int[] arr, int ia, int ib) {
        int tmp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = tmp;
    }
}
