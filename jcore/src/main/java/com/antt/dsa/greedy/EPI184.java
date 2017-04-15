package com.antt.dsa.greedy;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by antt on 4/10/2017.
 * Three-sum problem
 *
 */
public class EPI184 {
    public static void main(String[] args) {
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 21, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 21, 3));
        System.out.printf("false = %b\n", nSum(new int[]{2,3,7,5,11}, 22, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 15, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 9, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 6, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 7, 3));
        System.out.printf("true = %b\n", nSum(new int[]{2,3,7,5,11}, 33, 3));
        System.out.printf("false = %b\n", nSum(new int[]{2,3,7,5,11}, 34, 3));
        System.out.printf("false = %b\n", nSum(new int[]{2,3,7,5,11}, 1, 3));
    }

    private static boolean nSum(int[] arr,  int target, int n) {
        Arrays.sort(arr);
        return nSumRecurr(arr, arr.length, target, n);
    }

    private static boolean nSumRecurr(int[] arr, int length, int target, int n) {
        if (n==0 && target == 0) {
            return true;
        }
        if (length <= 0 || n < 0) {
            return false;
        }

        int max = arr[length-1];

        if (target > max*n) {
            return false;
        } else if (target == max*n) {
            return true;
        } else if (target <= max) {
            return nSumRecurr(arr, length -1, target, n);
        }

        {
            int newtarget = target%max;
            while(newtarget < target) {
                if (nSumRecurr(arr, length -1, newtarget, n-1)) {
                    return true;
                }
                newtarget += max;
            }
            return nSumRecurr(arr, length-1, target, n);

        }
    }
}
