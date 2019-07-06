package com.zzm.syn;

public class UnsafeBank {
	public static void main(String[] args) {
		Account account = new Account(100,"结婚基金"); 
		Drawing you = new Drawing(account, 50, "You");
		Drawing gf = new Drawing(account, 100, "GirlFriend");
		
		you.start();
		gf.start();
	}
}


class Account{
	
	int balance;//余额
	String name;//卡名
	
	public Account(int balance, String name) {
		this.balance = balance;
		this.name = name;
	}
}

/*
 * 银行：模拟取款
 */
class Drawing extends Thread{
	Account account;//账户
	int withdrawal;//取款金额
	int nowMoney;//现在手里有多少钱
	
	public Drawing(Account account, int withdrawal, String name) {
		super(name);
		this.account = account;
		this.withdrawal = withdrawal;
	}
	
	public void run() {
		//判断余额是否满足取款金额
		if (account.balance - withdrawal < 0) {
			System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
			return;
		}
		
		//Sleep能够放大问题的发生性
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		account.balance = account.balance - withdrawal;
		nowMoney = nowMoney + withdrawal;
		
		System.out.println(account.name + "余额为：" + account.balance);
		System.out.println(this.getName() + "手里的钱：" + nowMoney);
	}
	
}