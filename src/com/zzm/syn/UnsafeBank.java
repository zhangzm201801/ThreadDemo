package com.zzm.syn;

public class UnsafeBank {
	public static void main(String[] args) {
		Account account = new Account(100,"������"); 
		Drawing you = new Drawing(account, 50, "You");
		Drawing gf = new Drawing(account, 100, "GirlFriend");
		
		you.start();
		gf.start();
	}
}


class Account{
	
	int balance;//���
	String name;//����
	
	public Account(int balance, String name) {
		this.balance = balance;
		this.name = name;
	}
}

/*
 * ���У�ģ��ȡ��
 */
class Drawing extends Thread{
	Account account;//�˻�
	int withdrawal;//ȡ����
	int nowMoney;//���������ж���Ǯ
	
	public Drawing(Account account, int withdrawal, String name) {
		super(name);
		this.account = account;
		this.withdrawal = withdrawal;
	}
	
	public void run() {
		//�ж�����Ƿ�����ȡ����
		if (account.balance - withdrawal < 0) {
			System.out.println(Thread.currentThread().getName() + "Ǯ������ȡ����");
			return;
		}
		
		//Sleep�ܹ��Ŵ�����ķ�����
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		account.balance = account.balance - withdrawal;
		nowMoney = nowMoney + withdrawal;
		
		System.out.println(account.name + "���Ϊ��" + account.balance);
		System.out.println(this.getName() + "�����Ǯ��" + nowMoney);
	}
	
}