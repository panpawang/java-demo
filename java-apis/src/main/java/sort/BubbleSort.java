package sort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BubbleSort {
//  static int[] arr = { 5, 52, 6, 3, 4, 10, 8, 100, 35, 78, 64, 31, 77, 90,
//          45, 53, 89, 78, 1 };
    static int[] arr = { 5, 52, 6, 3, 4};
    static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 奇偶换位排序
     * 
     * @param arr
     */
    public static void oddEvenSort(int[] arr) {
        int exchFlag = 1, start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            System.out.println(Arrays.toString(arr));
            if (start == 0)
                start = 1;
            else
                start = 0;
        }
    }

    static int exchFlag=1;
    static synchronized void setExchFlag(int v){
        exchFlag=v;
    }
    static synchronized int getExchFlag(){
        return exchFlag;
    }
    
    public static class OddEvenSortTask implements Runnable{
        int i;
        CountDownLatch latch;
        public OddEvenSortTask(int i,CountDownLatch latch){
            this.i=i;
            this.latch=latch;
        }
        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }
    public static void pOddEvenSort(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            //偶数的数组长度，当start为1时，只有len/2-1个线程
            CountDownLatch latch = new CountDownLatch(arr.length/2-(arr.length%2==0?start:0));
            for (int i = start; i < arr.length - 1; i += 2) {
                pool.submit(new OddEvenSortTask(i,latch));
            }
            //等待所有线程结束
            latch.await();
            if (start == 0)
                start = 1;
            else
                start = 0;
        }
    }
    public static void main(String[] args) throws InterruptedException {
//      pOddEvenSort(arr);
        oddEvenSort(arr);
        pool.shutdownNow();
        System.out.println(Arrays.toString(arr));
    }
}
