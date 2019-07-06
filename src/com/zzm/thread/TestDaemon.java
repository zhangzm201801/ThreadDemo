package com.zzm.thread;

/*
 * 线程分为用户线程和守护线程
 * 虚拟机必须确保用户线程执行完毕
 * 虚拟机不用等待守护线程执行完毕
 * 守护线程：垃圾回收 ，监控内存，后台日志操作等
 * 设置守护线程setDaemon(true);
 */
public class TestDaemon {

	public static void main(String[] args) {

		God god = new God();
		You you = new You();

		Thread thread = new Thread(god);
		// 默认是false表示是用户线程，正常的线程都是用户线程
		//设置守护线程
		thread.setDaemon(true);
		thread.start();// 上帝守护线程启动
		
		new Thread(you).start();//用户线程启动
	}
}

class God implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("上帝保佑着你");
		}
	}

}

class You implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 365000; i++) {
			System.out.println("你一生都开心的活着");
		}
		
		System.out.println("*********Goodbye world");
	}
}