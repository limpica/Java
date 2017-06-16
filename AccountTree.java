package software.homework;

import java.io.*;
import java.util.*;

/*
   编写程序读取科目表文件,在内存中建立科目树, 并遍历科目树中每个科目对象,序列化到磁盘上。
   （1）从文件中读取科目表，包括两种编码格式（一种是固定编码格式，一种是动态编码格式）
   （2）建立Account类，为从文件中读取的每个科目构造Account对象，并在内存中建立科目树
   （3）遍历科目树，将科目对象序列化到磁盘上

   输入：固定科目.txt 动态科目.txt
   输出：科目树的序列化文件
 */

//这个作业和AccountSerialize.java只差一步反序列化。因此，这个作业里的方法都来自于AccountSerialize.java
public class AccountTree {
	static ArrayList<Account> accounts; // 建立科目树

	public static void main(String[] args) {
		// 读入&序列化两个科目文件
		AccountSerialize.readAccountFromTxt("固定科目.txt");
		AccountSerialize.serialize("Accounts.ser");
		AccountSerialize.readAccountFromTxt("动态科目.txt");
		AccountSerialize.serialize("Accounts.ser");
	}
}	

class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String acc_number;
	private String acc_name;
	private String acc_entry;
	private String acc_def;
	

	public Account(String acc_number, String acc_name, String acc_def, String acc_entry) {
		this.setAcc_number(acc_number);
		this.setAcc_name(acc_name);
		this.setAcc_entry(acc_entry);
		this.setAcc_def(acc_def);
		
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
	
	public String getAcc_entry() {
		return acc_entry;
	}

	public void setAcc_entry(String acc_entry) {
		this.acc_entry = acc_entry;
	}

	public String getAcc_def() {
		return acc_def;
	}

	public void setAcc_def(String acc_def) {
		this.acc_def = acc_def;
	}
}
