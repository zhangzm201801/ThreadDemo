package com.zzm.deadlock;

/*
 * 死锁：多个线程互相抱着对方需要的资源，然后形成僵持
 */
public class DeadLock {

	public static void main(String[] args) {

		Makeup g1 = new Makeup(0, "灰姑娘");
		Makeup g2 = new Makeup(1, "白雪公主");

		g1.start();
		g2.start();
	}
}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {

	// 需要的资源只有一份，用static来保证只有一份
	static Lipstick lipstick = new Lipstick();
	static Mirror mirror = new Mirror();

	public Makeup(int choice, String girlName) {
		this.choice = choice;
		this.girlName = girlName;
	}

	int choice;// 选择
	String girlName;// 使用化妆品的人

	@Override
	public void run() {
		try {
			makeup();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeup() throws InterruptedException {
		if (choice == 0) {
			synchronized (lipstick) {// 获得口红的锁
				System.out.println(this.girlName + ":获得口红的锁");
				Thread.sleep(1000);
				synchronized (mirror) {// 1秒钟后，获得镜子
					System.out.println(this.girlName + ":获得了镜子的锁");
				}
			}
		} else {
			synchronized (mirror) {// 获得镜子的锁
				System.out.println(this.girlName + ":获得镜子的锁");
				Thread.sleep(2000);
				synchronized (lipstick) {// 2秒钟后，获得口红的锁
					System.out.println(this.girlName + ":获得了口红的锁");
				}
			}
		}
	}
}