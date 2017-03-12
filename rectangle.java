/*
创建一个简单的表示矩形的Rectangle类，满足以下条件：

（1）定义两个成员变量height和width，表示矩形的长和宽，类型为整型 
（2）定义一个getArea方法，返回矩形的面积 
（3）定义一个getPerimeter方法，返回矩形的周长 
（4）在main函数中，利用输入的2个参数分别作为矩形的长和宽，调用getArea和getPermeter方法，计算并返回矩形的面积和周长

 输入：
 输入2个正整数，中间用空格隔开，分别作为矩形的长和宽，例如：5 8

 输出：
 输出2个正整数，中间用空格隔开，分别表示矩形的面积和周长，例如：40 26
*/
package software.homework;

import java.util.Scanner;

public class Rectangle 
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in); 
        String LW=input.nextLine();
        String[] ints = LW.split(" ");
        int L = Integer.parseInt(ints[0]);
        int W = Integer.parseInt(ints[1]);   
		getArea(L,W);
		getPermeter(L,W);  
	}
	
	public static void getArea(int L, int W) 
	{
		System.out.print("The area is: ");
		System.out.println(L*W);
	}
	
	public static void getPermeter(int L, int W)
	{
		System.out.print("The permeter is: ");
		System.out.println(2*(L+W));
	}
}
