package org.nodeclipse.lab;

public class QuickSort {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void sort(Comparable[] data, int low, int high) {
        int i = low;
        int j = high;
        if (low < high) {
            Comparable pivotKey = data[low];
            while (i < j) {
                while (i < j && data[j].compareTo(pivotKey) > 0) {
                    j--;
                }// end while
                if (i < j) {
                    data[i] = data[j];
                    i++;
                }// end if
                while (i < j && data[i].compareTo(pivotKey) < 0) {
                    i++;
                }// end while
                if (i < j) {
                    data[j] = data[i];
                    j--;
                }// end if
            }// end while
            data[i] = pivotKey;
            //sort(data, low, i - 1);
            //sort(data, i + 1, high);
        }// end if
    }// end sort

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        Comparable[] c = {4, 9, 23, 1, 45, 27, 5, 2 };
        sort(c, 0, c.length - 1);
        for (Comparable data : c) {
            System.out.println(data);
        }
    }
}
