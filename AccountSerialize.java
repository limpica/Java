package software.homework;
import java.io.*;
import java.util.ArrayList;

/*
编写程序读取科目表文件,在内存中建立科目树, 并遍历科目树中每个科目对象,序列化到磁盘上。
（1）从文件中读取科目表，包括两种编码格式（一种是固定编码格式，一种是动态编码格式）
（2）建立Account类,为从文件中读取的每个科目构造Account对象
（3）把Account对象序列化到磁盘上一个Accounts.ser文件中
（4）读取Accounts.ser文件，循环还原Account对象，形成Account对象数组

输入：固定科目.txt 动态科目.txt
输出：科目树的序列化文件
*/



public class AccountSerialize {
	static ArrayList<software.homework.Account> accounts = new ArrayList<Account>(); // 建立科目树

	public static void main(String[] args) {
		// 读入&序列化&反序列化两个科目文件
		readAccountFromTxt("固定科目.txt");
		serialize("Accounts.ser");
		deserialize("Accounts.ser");
		//readAccountFromTxt("动态科目.txt");
		//serialize("Accounts.ser");
		//deserialize("Accounts.ser");
	}

	// 读数据到arraylist accounts
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


	public static void serialize(String filename) {
		try {
			// 序列化
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream("C:/Users/Miao Yi/Java/projects/src/software/homework/" + filename));
			Account account;
			for (int i = 0; i < accounts.size(); i++) {
				account = (Account) accounts.get(i);
				output.writeObject(account);
			}
			output.close();
		} catch (IOException ex) {
			System.out.print("2: " + ex);
		}
	}

	public static void deserialize(String filename) {
		// 反序列化
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(new File("C:/Users/Miao Yi/Java/projects/src/software/homework/" + filename)));

			int size = accounts.size(); // 保存一下accounts的size，这是反序列化的长度
			accounts.clear(); // 重新读入accounts前清空一下
			for (int m = 0; m < size; m++) {
				accounts.add((Account) input.readObject());
				//不确定反序列化是不是正确，可以打印一下
				//System.out.println(((Account)accounts.get(m)).getAcc_number() + ((Account)accounts.get(m)).getAcc_name()+ ((Account)accounts.get(m)).getAcc_def() );
			}
			input.close();

		} catch (FileNotFoundException ex) {
			System.out.println("3: " + ex);
		} catch (IOException ex) {
			System.out.print("4: " + ex);
		} catch (ClassNotFoundException ex) {
			System.out.println("5: " + ex);
		}
	}
}

