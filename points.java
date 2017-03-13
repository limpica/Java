package software.homework;

import java.util.Scanner;

public class Points{
	public static void main(String[] args)
	{
		Scanner input1 = new Scanner(System.in); 
		Scanner input2 = new Scanner(System.in); 
        String point1 = input1.nextLine();
        String point2 = input2.nextLine();
        String[] cor1 = point1.split(" ");
        String[] cor2 = point2.split(" ");
        
        double cor1_x = Double.parseDouble(cor1[0]);
        double cor1_y = Double.parseDouble(cor1[1]);
        double cor2_x = Double.parseDouble(cor2[0]);
        double cor2_y = Double.parseDouble(cor2[1]);
        


       MyPoint point_1 = new MyPoint(cor1_x, cor1_y);
       MyPoint point_2 = new MyPoint(cor2_x, cor2_y);
        
       System.out.print(point_1.getD(point_2));
        
	}
}

class MyPoint
{
	private double x;
	private double y;
	
	public MyPoint() 
	{
		x = 0.0;
		y = 0.0;
	}
	
	public MyPoint(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getD(MyPoint point)
	{
        double D = Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
        return(D);
	}
		
}
