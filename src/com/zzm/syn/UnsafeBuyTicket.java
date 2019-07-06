package com.zzm.syn;

public class UnsafeBuyTicket {
	
	public static void main(String[] args) {
		BuyTicket station = new BuyTicket();
		
		new Thread(station,"苦逼的我").start();
		new Thread(station,"牛逼的你").start();
		new Thread(station,"可恶的黄牛党").start();
	}
}

class BuyTicket implements Runnable {
	//残余票的数量
	private int ticketNums = 10;
	//外部停止方式
	boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			buy();
		}
	}
	
	//synchronized 同步方法
	private synchronized void buy() {
		//判断是否还有票
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
		//买票
		System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
	}
}
