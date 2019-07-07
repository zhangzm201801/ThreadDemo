package com.zzm.syn;

/*
 * 测试生产者消费者模型2  信号灯法，标志位解决
 */
public class TestPC2 {

	public static void main(String[] args) {
		Film film = new Film();

		new Cinema(film).start();
		new Audience(film).start();
	}
}

/*
 * 生产者---》电影院
 */
class Cinema extends Thread {

	Film film;

	public Cinema(Film film) {
		this.film = film;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 7; i++) {
			if (i == 6) {
				this.film.play("速度与激情8");
			} else {
				this.film.play("叶问");
			}
		}
	}

}

/*
 * 消费者---》观众
 */
class Audience extends Thread {

	Film film;

	public Audience(Film film) {
		this.film = film;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 7; i++) {
			this.film.watch();
		}
	}
}

/*
 * 产品----》电影
 */
class Film {

	String programme;// 电影题目
	//
	boolean flag = true;

	// 播放
	public synchronized void play(String programme) {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("准备上映：" + programme);
		// 通知观众观看
		this.notifyAll();// 通知唤醒
		this.programme = programme;
		this.flag = !this.flag;
	}

	// 观看
	public synchronized void watch() {

		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("观看了：" + programme);
		// 通知演员表演
		this.notifyAll();
		this.flag = !this.flag;
	}

}