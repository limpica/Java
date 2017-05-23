package software.homework;
import java.io.*;

/*
   编写程序读取科目表文件,在内存中建立科目树, 并遍历科目树中每个科目对象,序列化到磁盘上。
   （1）从文件中读取科目表，包括两种编码格式（一种是固定编码格式，一种是动态编码格式）
   （2）建立Account类，为从文件中读取的每个科目构造Account对象，并在内存中建立科目树
   （3）遍历科目树，将科目对象序列化到磁盘上
   
   输入：固定科目.txt 动态科目.txt
   输出：科目树的序列化文件
*/

public class AccountTree 
{
	static Account[] accounts = new Account[10000]; //建立科目树
	
	public static void main(String[] args) 
	{
		//读入&序列化两个科目文件
		readAccount("固定科目.txt");
		serialize("Accounts.ser");
		readAccount("动态科目.txt");
		serialize("Accounts.ser");
	}
	
 
 //读数据到accounts[] 
	public static void readAccount(String filename) 
	{
		try 
		{
			accounts = new Account[10000];  //每次将account读入accounts[]前，都先将其清空
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/Miao Yi/Java/projects/src/software/experiment/"+filename));
			
			String line;  //循环存储读入的一行的内容
			int i = 0;  //用于循环
			while((line = br.readLine()) != null) 
			{
				String acc_number = line.split(" ")[0];  //科目代码（acc_number）和科目名称（acc_name）是用空格分开的
				String acc_name = new String(line.split(" ")[1]);
				accounts[i] = new Account(acc_number, acc_name);  //将读入的每个科目装入accounts的一个位置
				//打印一下看看对不对
				System.out.println(accounts[i].getAcc_name() + " " + accounts[i].getAcc_number());
				i = i + 1;
			}
			br.close();
			System.out.println("--------------------------------------");
		} catch (IOException ex) {
			System.out.println(ex);
		} 
	}
	
	public static void serialize(String filename)
	{
		try 
		{
			//序列化
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream("C:/Users/Miao Yi/Java/projects/src/software/experiment/" + filename));
			Account account;  //循环存储从accounts[i]中读取出来的内容
			int i = 0;
			while(accounts[i] != null)
			{
				account = accounts[i];
				output.writeObject(account);  //序列化
				i = i + 1;
			}
			output.close();

		} catch (IOException ex) {
			System.out.print(ex);
		}
	}
	
}


class Account implements Serializable {
	private String acc_number;
	private String acc_name;
	public Account(String acc_number, String acc_name) {
		this.setAcc_number(acc_number);
		this.setAcc_name(acc_name);
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
}
