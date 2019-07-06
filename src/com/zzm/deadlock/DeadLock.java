package com.zzm.deadlock;

/*
 * ����������̻߳��౧�ŶԷ���Ҫ����Դ��Ȼ���γɽ���
 */
public class DeadLock {

	public static void main(String[] args) {

		Makeup g1 = new Makeup(0, "�ҹ���");
		Makeup g2 = new Makeup(1, "��ѩ����");

		g1.start();
		g2.start();
	}
}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {

	// ��Ҫ����Դֻ��һ�ݣ���static����ֻ֤��һ��
	static Lipstick lipstick = new Lipstick();
	static Mirror mirror = new Mirror();

	public Makeup(int choice, String girlName) {
		this.choice = choice;
		this.girlName = girlName;
	}

	int choice;// ѡ��
	String girlName;// ʹ�û�ױƷ����

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
			synchronized (lipstick) {// ��ÿں����
				System.out.println(this.girlName + ":��ÿں����");
				Thread.sleep(1000);
				synchronized (mirror) {// 1���Ӻ󣬻�þ���
					System.out.println(this.girlName + ":����˾��ӵ���");
				}
			}
		} else {
			synchronized (mirror) {// ��þ��ӵ���
				System.out.println(this.girlName + ":��þ��ӵ���");
				Thread.sleep(2000);
				synchronized (lipstick) {// 2���Ӻ󣬻�ÿں����
					System.out.println(this.girlName + ":����˿ں����");
				}
			}
		}
	}
}