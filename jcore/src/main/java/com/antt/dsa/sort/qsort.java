package com.antt.dsa.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by antt on 4/15/2017.
 */
public class qsort {
    public static void main(String[] args) {
        int[] a = new int[]{1};
        quicksort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        a = new int[]{2,1};
        quicksort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
        a = new int[]{2,1,3,5,6};
        quicksort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
        a = new int[]{};
        quicksort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));

    }

    public static void quicksort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        Random r = new Random();
        int pv = r.nextInt((end - start) + 1) + start;
        swap(arr, start, pv);
        int c = start+1;
        int i = start+1;
        while(i <= end ) {
            if (arr[i] <= arr[start]) {
                swap(arr, c, i);
                c++;
            }
            i++;
        }
        swap(arr, start, c-1);
        quicksort(arr, start, c-1);
        quicksort(arr, c, end);
    }

    public static void swap(int[] arrn, int ia, int ib) {
        int tmp = arrn[ia];
        arrn[ia] =  arrn[ib];
        arrn[ib] = tmp;
    }
}
