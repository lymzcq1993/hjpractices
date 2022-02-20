package com.hujian.concurrent;
/**
 * CPU读取内存的连续性..CPU读取内存的时候会默认读取连续内存的变量，提高速度尽量少的直接读取内存
 * @author 35918
 *
 */
public class CONCURR_01_CACHE {
	private static int CHANG = 1024*1024;
	private static Long[][] longs= new Long[CHANG][6];
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		for(int i =0;i<CHANG;i++) {
			for(int j = 0;j<6;j++) {
				longs[i][j] = 1l;
			}
		}
		System.out.println(System.currentTimeMillis() - s);
		
		s = System.currentTimeMillis();
		for(int i =0;i<6;i++) {
			for(int j = 0;j<CHANG;j++) {
				longs[j][i] = 1l;
			}
		}
		System.out.println(System.currentTimeMillis() - s);
	}
}
