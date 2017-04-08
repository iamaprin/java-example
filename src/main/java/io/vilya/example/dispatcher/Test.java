package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月2日 下午10:01:11
 */
public class Test {
	
	public static int maxSubSum(int[] a) {
		int maxSum = 0;
		int thisSum = 0;
		
		for (int j = 0; j < a.length; j++) {
			thisSum += a[j];
			
			if (thisSum >= maxSum) {
				maxSum = thisSum;
			} else if (thisSum < 0) {
				thisSum = 0;
			}
		}
		
		return maxSum;
	}
	
	public static int maxSubSum2(int[] a) {
		int maxSum = 0;
		
		for (int i = 0; i < a.length; i++) {
			int thisSum = 0;
			for (int j = i; j < a.length; j++) {
				thisSum += a[j];
				if (thisSum > maxSum) {
					maxSum = thisSum;
				}
			}
		}
		
		return maxSum;
	}
	
	
	public static void main(String[] args) {
		int[] a = {4, 2, 21, -23, 12, 7, -9, -10};
		System.out.println(maxSubSum(a));
		System.out.println(maxSubSum2(a));
	}
	
	
	
	
}
