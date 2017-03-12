/*
编写一个程序，输出字符串"abcdefgh"的全排列。（提示：可考虑递归）
*/
package software.homework;

public class Permutation
{
	public static void main(String[] args) 
	{
		permute(args, 0);
	}
	
	public static void swap(String[] ob, int m, int n) 
	{
		String temp = ob[m];
		ob[m] = ob[n];
		ob[n] = temp;
	}
	
	public static void print(String[] ob) 
	{
		for(int i = 0; i < ob.length; i++)
		System.out.print(ob[i]);
		System.out.print("\n");
	}
	
	public static void permute(String[] ob, int start)
	{
		if(start == ob.length)
		{
			print(ob);
		}
		else
		for(int i = start; i < ob.length; i++)
		{
	
			swap(ob, start, i);
			permute(ob, start+1);
			swap(ob, start, i);			
		}		
	}
}
