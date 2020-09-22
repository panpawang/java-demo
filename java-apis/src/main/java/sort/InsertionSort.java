package sort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 插入排序
 * 
 * @author Administrator
 *
 */
public class InsertionSort {
//	static int[] arr = { 5, 52, 6, 3, 4, 10, 8, 100, 35, 78, 64, 31, 77, 90,
//			45, 53, 89, 78, 1 };
	static int[] arr = { 5, 52, 6, 3, 4};
	static ExecutorService pool = Executors.newFixedThreadPool(10);

	/**
	 *   j  i
	 *      key 
	 * 
	 */
	public static void insertSort(int[] arr) {
		int length = arr.length;
		int j, i, key;
		for (i = 1; i < length; i++) {
			//key为要准备插入的元素
			key = arr[i];
			j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			//找到合适的位置 插入key
			arr[j + 1] = key;
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void shellSort(int[] arr) {
		// 计算出最大的h值
		int h = 1;
		while (h <= arr.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			System.out.println("h=" + h);
			for (int i = h; i < arr.length; i++) {
				if (arr[i] < arr[i - h]) {
					int tmp = arr[i];
					int j = i - h;
					while (j >= 0 && arr[j] > tmp) {
						arr[j + h] = arr[j];
						j -= h;
					}
					arr[j + h] = tmp;
				}
				System.out.println(Arrays.toString(arr));
			}
			System.out.println(Arrays.toString(arr));
			// 计算出下一个h值
			h = (h - 1) / 3;
		}
	}

	public static class ShellSortTask implements Runnable {
		int i = 0;
		int h = 0;
		CountDownLatch l;

		public ShellSortTask(int i, int h, CountDownLatch latch) {
			this.i = i;
			this.h = h;
			this.l = latch;
		}

		@Override
		public void run() {
			if (arr[i] < arr[i - h]) {
				int tmp = arr[i];
				int j = i - h;
				while (j >= 0 && arr[j] > tmp) {
					arr[j + h] = arr[j];
					j -= h;
				}
				arr[j + h] = tmp;
			}
			l.countDown();
		}
	}

	public static void pShellSort(int[] arr) throws InterruptedException {
		// 计算出最大的h值
		int h = 1;
		CountDownLatch latch = null;
		while (h <= arr.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			System.out.println("h=" + h);
			if (h >= 4)
				latch = new CountDownLatch(arr.length - h);
			for (int i = h; i < arr.length; i++) {
				// 控制线程数量
				if (h >= 4) {
					pool.execute(new ShellSortTask(i, h, latch));
				} else {
					if (arr[i] < arr[i - h]) {
						int tmp = arr[i];
						int j = i - h;
						while (j >= 0 && arr[j] > tmp) {
							arr[j + h] = arr[j];
							j -= h;
						}
						arr[j + h] = tmp;
					}
					// System.out.println(Arrays.toString(arr));
				}
			}
			// 等待线程排序完成，进入下一次排序
			latch.await();
			// 计算出下一个h值
			h = (h - 1) / 3;
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		pShellSort(arr);
		insertSort(arr);
		pool.shutdownNow();
		System.out.println(Arrays.toString(arr));
	}
}
