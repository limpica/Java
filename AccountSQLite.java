package software.homework;

import java.sql.*;
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
	static Account[] accounts = new Account[10000]; // 建立科目树,
													// Account类在同一个package里已经建过，直接使用。
	static Account account = null;
	static Connection c = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		// 读取数据
		// readAccount("固定科目.txt");
		readAccount("动态科目.txt");
		// 创建数据库和表
		createTable("Accounts.db");
		// 将读取的科目表插入数据库
		insert();
		// 从数据库中读取出数据
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS;");
			while (rs.next()) {
				account.setAcc_number(rs.getString("ID"));
				account.setAcc_name(rs.getString("NAME"));
				System.out.println(account.getAcc_number() + " " + account.getAcc_name());
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public static void readAccount(String filename) {
		// 读txt数据到accounts[]
		try {
			accounts = new Account[10000]; // 每次将account读入accounts[]前，都先将其清空
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/Miao Yi/Java/projects/src/software/homework/" + filename));

			String line; // 循环存储读入的一行的内容
			int i = 0; // 用于循环
			while ((line = br.readLine()) != null) {
				String acc_number = line.split(" ")[0]; // 科目代码（acc_number）和科目名称（acc_name）是用空格分开的
				String acc_name = new String(line.split(" ")[1]);
				accounts[i] = new Account(acc_number, acc_name); // 将读入的每个科目装入accounts的一个位置
				i = i + 1;
			}
			br.close();
			System.out.println(filename + "的数据读取成功！");
		} catch (IOException ex) {
			System.out.println("1: " + ex);
		}
	}

	public static void createTable(String filename) {
		// 创建Accounts.db文件和Accounts表
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager
					.getConnection("jdbc:sqlite:C:/Users/Miao Yi/Java/projects/src/software/homework/" + filename);
			stmt = c.createStatement();
			String sql = "CREATE TABLE ACCOUNTS " + "(ID 	CHAR(50) 	PRIMARY KEY	NOT NULL,"
					+ " NAME 		TEXT 				NOT NULL);";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public static void insert() {
		// 循环插入科目数据
		try {
			stmt = c.createStatement();
			int i = 0;

			while (accounts[i] != null) {
				account = accounts[i];
				String id = account.getAcc_number();
				String name = account.getAcc_name();
				String sql = "INSERT INTO ACCOUNTS VALUES ('" + (String) id + "' , '" + name + "');";
				stmt.executeUpdate(sql);
				i = i + 1;
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		System.out.println("科目创建成功！");
	}
}
