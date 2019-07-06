package com.zzm.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeBuyTicket {

	public static void main(String[] args) {
		BuyTicket station = new BuyTicket();

		new Thread(station, "��Ƶ���").start();
		new Thread(station, "ţ�Ƶ���").start();
		new Thread(station, "�ɶ�Ļ�ţ��").start();
	}
}

class BuyTicket implements Runnable {
	// ����Ʊ������
	private int ticketNums = 10;
	// �ⲿֹͣ��ʽ
	boolean flag = true;

	private final ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		while (flag) {
			buy();
		}
	}

	private void buy() {
		try {
			lock.lock();
			// �ж��Ƿ���Ʊ
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ��Ʊ
			System.out.println(Thread.currentThread().getName() + "�õ�" + ticketNums--);
		} finally {
			lock.unlock();
		}

	}
}
