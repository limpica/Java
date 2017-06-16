package software.homework;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;

/*
编写程序读取科目表文件,全部插入或更新到SQLite数据库中。
(1)建立SQLite数据库(Accounts.db),建立表Account（包括两个字段 id和name）
(2)从文件中读取科目表，将科目数据循环插入到SQLite数据库中
(3)建立Account类，为从数据库中读取的每个科目构造Account对象，并在内存中建立科目树


输入：固定科目.txt 动态科目.txt
输出：SQLite数据库
 */

public class AccountSQLite {
	static ArrayList<Account> accounts = new ArrayList<Account>(); // 建立科目树
	static Connection c = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		//readAccountFromTxt("动态科目.txt"); // 从txt中读取数据
		//createTable("Accounts.db"); // 创建数据库Accounts.db和表accounts，如果存在就清空数据不必新建
		//insertInto("Accounts.db"); // 将科目数据插入表accounts
		//readAccountFromDb("Accounts.db"); // 从Accounts.db文件中读取出数据
		
		readAccountFromTxt("固定科目.txt"); // 从txt中读取数据
		createTable("Accounts.db"); // 创建数据库Accounts.db和表accounts，如果存在就清空数据不必新建
		insertInto("Accounts.db"); // 将科目数据插入表accounts
		readAccountFromDb("Accounts.db"); // 从Accounts.db文件中读取出数据
	}

	public static void readAccountFromTxt(String filename) {
		try {
			
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/Miao Yi/Java/projects/src/software/homework/" + filename));

			String line; // 循环存储读入的一行的内容
			
			accounts.clear(); // 每次读入前先把之前的清空一遍
			//int i = 0;
			
			while ((line = br.readLine()) != null) {
				String acc_number = line.split(" ")[0]; // 科目代码（acc_number）和科目名称（acc_name）是用空格分开的
				String acc_name = new String(line.split(" ")[1]);
				String acc_entry;
				String acc_def;	
				
				if(line.split(" ").length >= 3)//只找了少部分科目的借贷方向，所以需要判断一下
					acc_entry = new String(line.split(" ")[2]);
				else acc_entry = null;
					
				if (line.split(" ").length >= 4) //只找了少部分科目的定义，所以需要判断一下
					acc_def = new String(line.split(" ")[3]);
				else acc_def = null;
				//不确定读入结果是不是正确，可以打印一下
				//System.out.println(acc_number + "|" + acc_name +"|" + acc_entry + "|" + acc_def);
				accounts.add(new Account(acc_number, acc_name, acc_entry, acc_def));
				//System.out.println(accounts.get(i).getAcc_number()+ "|" + accounts.get(i).getAcc_name() + "|" + accounts.get(i).getAcc_entry() + accounts.get(i).getAcc_def());
				//i = i + 1;
			}
			br.close();

		} catch (IOException ex) {
			System.out.println("1: " + ex);
		}
	}


	public static void createTable(String dbname) {
		establishment(dbname);
		try {
			// 检查Accounts表是不是存在。
			ResultSet rsTables = c.getMetaData().getTables(null, null, "Accounts", null);
			if (rsTables.next() != true) {
				// 不存在就新建。
				String sql = "CREATE TABLE ACCOUNTS (ID	CHAR(50) PRIMARY KEY, NAME TEXT NOT NULL, ENTRY CHAR(1), DEFINATION TEXT);";
				stmt.executeUpdate(sql);
				System.out.println("表格不存在，新创建成功！");

			} else {
				// 否则清空原表格即可
				String sql = "delete from Accounts;";
				stmt.executeUpdate(sql);
				System.out.println("表格已存在，清空成功！");
			}
			stmt.close();
			c.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void insertInto(String dbname) {
		establishment(dbname);
		int size = accounts.size();
		try {
			// 循环将数据插入accounts表格
			for (int i = 0; i < size; i++) {
				Account account = accounts.get(i);
				String id = account.getAcc_number();
				String name = account.getAcc_name();
				String entry = account.getAcc_entry();
				String def = account.getAcc_def();
				
				String sql = "INSERT INTO ACCOUNTS VALUES ('" + (String) id + "' , '" + name + "' , '" + entry + "' , '"+ def + "');";
				
				stmt.executeUpdate(sql);
			}
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void readAccountFromDb(String dbname) {
		establishment(dbname);
		// 从.db文件中读取数据
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS;");
			while (rs.next()) {
				Account account = new Account(rs.getString("ID"), rs.getString("NAME"), rs.getString("ENTRY"), rs.getString("DEFINATION"));
				System.out.println(account.getAcc_number() + " " + account.getAcc_name() + " " + account.getAcc_entry() + " "+ account.getAcc_def());
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException ex) {
			System.out.println("3" + ex);
		}
	}

	public static void establishment(String dbname) {
		// 用来和.db文件建立连接
		try {
			Class.forName("org.sqlite.JDBC");//注册driver
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Miao Yi/Java/projects/src/software/homework/" + dbname);
			//jdbc链接协议,SQLITE链接子协议。再后免就是地址。
			stmt = c.createStatement();
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println("1" + ex);
		}
	}
}
