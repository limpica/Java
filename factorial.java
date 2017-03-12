/*
编写一个程序，计算自然数n的阶乘n!。（提示：可用循环完成）
*/
package software.homework;

public class Factorial 
{
	public static void main(String[] args)
	{
        
		int n = Integer.parseInt(args[0]);
		for(int i=n-1; i>0; i--)
		{
			n = n * i;	
		}
		System.out.println(n);
	}
}
