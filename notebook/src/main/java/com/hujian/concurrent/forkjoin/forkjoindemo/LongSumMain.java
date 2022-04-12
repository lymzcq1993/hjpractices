package com.hujian.concurrent.forkjoin.forkjoindemo;


import com.hujian.concurrent.forkjoin.utils.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author hujian
 *
 * 利用ForkJoinPool计算1亿个整数的和
 */
public class LongSumMain {
	// 获取逻辑处理器数量 12
	static final int NCPU = Runtime.getRuntime().availableProcessors();

	static long calcSum;


	public static void main(String[] args) throws Exception {
		//准备数组
		int[] array = Utils.buildRandomIntArray(100000000);
		Instant now = Instant.now();
		//递归任务
		LongSum ls = new LongSum(array, 0, array.length);
		// 构建ForkJoinPool
  		ForkJoinPool fjp  = new ForkJoinPool(NCPU);

		now = Instant.now();
		//ForkJoin计算数组总和
		ForkJoinTask<Long> result = fjp.submit(ls);
		System.out.println("forkjoin sum=" + result.get());
		System.out.println("执行时间："+ Duration.between(now,Instant.now()).toMillis());

		fjp.shutdown();

	}

	/**
	 * RecursiveAction：用于递归执行但不需要返回结果的任务。
	 * RecursiveTask ：用于递归执行需要返回结果的任务。
	 * CountedCompleter<T> ：在任务完成执行后会触发执行一个自定义的回调函数，
	 */
	static class LongSum extends RecursiveTask<Long> {
		// 任务拆分最小粒度，1000万
		static final int NUM = 10000000;

		int low;
		int high;
		int[] array;

		LongSum(int[] arr, int lo, int hi) {
			array = arr;
			low = lo;
			high = hi;
		}

		/**
		 * 需要重写的方法，使用fork()来不停提交拆分的新任务
		 * 使用join()获取最终的结果
		 *
		 * @return
		 */
		@Override
		protected Long compute() {

			//当任务拆分到小于等于阀值时开始求和
			if (high - low <= NUM) {

				long sum = 0;
				for (int i = low; i < high; ++i) {
					sum += array[i];
				}
				return sum;
			} else {  // 任务过大继续拆分
				int mid = low + (high - low) / 2;
				LongSum left = new LongSum(array, low, mid);
				LongSum right = new LongSum(array, mid, high);
				// 提交任务
				left.fork();
				right.fork();
				//获取任务的执行结果,将阻塞当前线程直到对应的子任务完成运行并返回结果
				long rightAns = right.join();
				long leftAns = left.join();
				return leftAns + rightAns;
			}
		}
	}
}