/*
用Java编写一个能对数组比较的程序，需要输出按行升序排序的结果 如输入
1 2 5 4 8
输出：
1 2 4 5 8
要求：数组是通过sort程序的参数输入。
*/
package software.homework;

public class Sort 
{
	public static void main (String[] args) 
	{
		int A[] = new int[args.length];
		for (int i = 0; i < args.length; i++)
			A[i] = Integer.parseInt(args[i]);
		
		for(int n = 1; n < args.length; n++) 
		{
			for (int m = 0; m < (args.length - n); m++) 
			{
				if(A[m] > A[m+1]) 
				{
					int b = A[m+1];
					A[m+1] = A[m];
					A[m] = b;
				}
			}
		}
		
		for (int k=0; k < args.length; k++)
			System.out.println(A[k]);
	}
}
