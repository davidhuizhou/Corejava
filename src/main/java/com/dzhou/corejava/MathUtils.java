package com.dzhou.corejava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huizhou on 5/10/14.
 */
public class MathUtils {
    public static final String ABOVETOPHISTOGRAM = "          ";
    public static final String TOPHISTOGRAM = " ___      ";
    public static final String BELOWTOPHISTOGRAM = "|   |     ";


    public static double sqrt(double c) {
        if (c < 0)
            return Double.NaN;

        double err = 1e-15;
        double t = c;

        while (Math.abs(t - c / t) > err * t) {
            System.out.println("t=" + t);
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    public static boolean isPrime(int N) {
        if (N < 2)
            return false;

        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0)
                return false;

        }
        return true;
    }

    public static double harmonicNumber(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static String getBinaryString(int N) {
        String s = "";
        for (int i = N; i > 0; i = i / 2) {
            s = (i % 2) + s;
        }
        return s;
    }

    public static int lg(int N) {
        if (N <= 0)
            return Integer.MIN_VALUE;

        else if (N == 1)
            return 0;
        else if (N == 2)
            return 1;

        else {
//            int result = 1;
//            int i;
//            for (i = 1; i <= N; i++){
//                result *= 2;
//                if(result > N)
//                    break;
//            }
//            return i - 1;
            int n = N, i = 0;
            while (n > 1) {
                n = n / 2;
                i++;
            }
            return i;
        }

    }

    public static int[] histogram(int[] a, int M) {
        int[] result = new int[M];

        if (a.length == 0)
            return result;

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i)
                    ++result[i];
            }
        }

        return result;

    }

    public static String getHistogram(int number, int position) {

        if (number == 0 || number < position)
            return ABOVETOPHISTOGRAM;
        else if (number == position)
            return TOPHISTOGRAM;
        else
            return BELOWTOPHISTOGRAM;


    }

    public static void printHistogram(int[] a) {
        int max = a[0];

        for (int i = 0; i < 10; i++) {
            if (a[i] > max)
                max = a[i];
        }


        for (int i = max + 2; i >= -1; i--) {

            if (i == -1) {
                for (int j = 0; j < 120; j++)
                    System.out.print("-");
            } else {
                System.out.print("|         ");

                for (int j = 0; j < 10; j++) {
                    System.out.print(getHistogram(a[j], i));
                }

            }
            System.out.println("");


        }
        System.out.print("\n          ");
        System.out.print("zero      ");
        System.out.print("one       ");
        System.out.print("two       ");
        System.out.print("three     ");
        System.out.print("four      ");
        System.out.print("five      ");
        System.out.print("six       ");
        System.out.print("seven     ");
        System.out.print("eight     ");
        System.out.print("nine      ");


    }

    public static int[] twoSum(int[] a, int sum){
        int[] retVal = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap();

        for(int i = 0; i < a.length; i++){
            if(map.containsKey(sum - a[i])){
                retVal[0] = map.get(sum - a[i]);
                retVal[1] = i;
                return retVal;
            } else {
                map.put(a[i], i);
            }
        }
        return retVal;
    }

    public static int[] twoSum2(int[] a, int sum){
        int[] retVal = {-1, -1};
        Arrays.sort(a);

        int i = 0, j = a.length - 1;
        while(i < j){
            if(a[i] + a[j] == sum){
                retVal[0] = i;
                retVal[1] = j;
                return retVal;
            } else if(a[i] + a[j] < sum){
                i++;
            }else{
                j--;
            }
        }
        return retVal;

    }

    public Set<Result> threeSum(int[] a){
        Set<Result> results = new HashSet<Result>();
        Arrays.sort(a);

        for(int i = 0; i <= a.length - 2; i++){
            int k = i + 1;
            int l = a.length - 1;

            while(k < l){
                int sum = a[i] + a[k] + a[l];
                if(sum == 0){
                    results.add(new Result(a[i], a[k], a[l]));
                    k++;
                    l--;
                } else if (sum < 0){
                    k++;
                } else {
                    l--;
                }
            }

        }
        return results;
    }

    private class Result{
        int a;
        int b;
        int c;

        Result(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public boolean equals(Result r){
            return a == r.a  && b == r.b  && c ==r.c;
        }
        public String toString(){
            return "(" + a + "," + b + "," + c + ")";
        }
    }

    private class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    public ListNode addTowNumber(ListNode l1, ListNode l2){
        int carryOver = 0;
        ListNode sentinel = new ListNode(0);

        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode n3 = sentinel;

        while(n1 != null || n2 != null) {
            if(n1 != null) {
                carryOver += n1.val;
                n1 = n1.next;
            }
            if(n2 != null){
                carryOver += n2.val;
                n2 = n2.next;
            }
            n3.next = new ListNode(carryOver % 10);
            n3 = n3.next;
            carryOver = carryOver / 10;

        }

        if(carryOver == 1)
            n3.next = new ListNode(1);

        return sentinel.next;



    }

    public static int[] cloestPair(int[] A){
        Arrays.sort(A);

        int diff = A[1] - A[0];
        int[] retVal = {A[0], A[1]};
        for(int i = 1; i < A.length - 1; i++){
            if(A[i+1] - A[i] < retVal[1] - retVal[0]){
                retVal[0] = A[i];
                retVal[1] = A[i+1];
            }
        }
        return retVal;
    }

    public static int[] fartestPair(int[] A){
        int[] retVal = {Math.min(A[0], A[1]), Math.max(A[0], A[1])};

        for(int i = 2; i < A.length; i++){
            if(A[i] < retVal[0])
                retVal[0] = A[i];
            else if(A[i] > retVal[1])
                retVal[1] = A[i];
        }
        return retVal;

    }

    private static String toString(int[] A){
        StringBuilder sb = new StringBuilder();
        for(int e : A){
            sb.append(e).append(" ");
        }
        return sb.toString();
    }

    public static int findKth(int[] a, int[] b, int k, int aStart, int aEnd, int bStart, int bEnd){
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if(aLen == 0)
            return b[bStart + k];
        if(bLen == 0)
            return a[aStart + k];
        if(k == 0)
            return a[aStart] < b[bStart]?a[aStart] : b[bStart];

        int aMid = k * aLen/(aLen + bLen);
        int bMid = k - aMid - 1;

        aMid = aStart + aMid;
        bMid = bStart + bMid;

        if(a[aMid] > b[bMid]){
            k = k - (bMid - bStart + 1);
            bStart = bMid + 1;
            aEnd = aMid;

        } else {
            k = k - (aMid - aStart + 1);
            aStart = aMid + 1;
            bEnd = bMid;

        }
        return findKth(a, b, k, aStart, aEnd, bStart, bEnd);

    }

    public static void main(String[] args) {
//        System.out.println(sqrt(2.0));
//        System.out.println(sqrt(0.5));
//        System.out.println(isPrime(7));
//        System.out.println(isPrime(23));
//        System.out.println(isPrime(81));
//        System.out.println(harmonicNumber(81));
//        System.out.println("&&&&&&");
//        System.out.println(getBinaryString(7));
//        System.out.println(getBinaryString(16));
//
//        int[] a = new int[10];
//        for (int i = 0; i < 10; i++)
//            a[i] = 9 - i;
//        for (int i = 0; i < 10; i++)
//            System.out.print(a[i] + " ");
//
//        System.out.println("\n");
//
//        for (int i = 0; i < 10; i++)
//            a[i] = a[a[i]];
//
//        for (int i = 0; i < 10; i++)
//            System.out.print(a[i] + " ");
//
//        System.out.println();
//        System.out.println(lg(0));
//        System.out.println(lg(1));
//        System.out.println(lg(2));
//        System.out.println(lg(3));
//        System.out.println(lg(4));
//        System.out.println(lg(7));
//        System.out.println(lg(8));
//        System.out.println(lg(31));
//        System.out.println(lg(64));
//
//        int[] b = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 6, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 9, 9};
//        int[] c = histogram(b, 12);
//        System.out.println(Arrays.toString(c));
//
//        printHistogram(c);

        System.out.println("\n\n  towSum\n");
        int[] d = {1, 3, 5, 5, 7, 8, 9, 8, 4, 5, 3, 2, 1, 11};
        int[] e = twoSum(d, 4);
        System.out.println(e[0] + " " + e[1]);
        e = twoSum2(d, 4);
        System.out.println(e[0] + " " + e[1]);

        e = twoSum(d, 10);
        System.out.println(e[0] + " " + e[1]);
        e = twoSum2(d, 10);
        System.out.println(e[0] + " " + e[1]);

        e = twoSum(d, 16);
        System.out.println(e[0] + " " + e[1]);
        e = twoSum2(d, 16);
        System.out.println(e[0] + " " + e[1]);

        e = twoSum(d, 20);
        System.out.println(e[0] + " " + e[1]);
        e = twoSum2(d, 20);
        System.out.println(e[0] + " " + e[1]);

        e = twoSum(d, 100);
        System.out.println(e[0] + " " + e[1]);
        e = twoSum2(d, 100);
        System.out.println(e[0] + " " + e[1]);

        MathUtils util = new MathUtils();
        int[] f = { 4, 6, 9, 11, -25, -10, -7, -3, 2, 4, 8, 10, -20};
        Set<Result> results = util.threeSum(f);
        for(Result r : results){
            System.out.println(r);
        }


        int[] cloestPair = cloestPair(f);
        System.out.println("cloestPair is " + toString(cloestPair));

    }


}
