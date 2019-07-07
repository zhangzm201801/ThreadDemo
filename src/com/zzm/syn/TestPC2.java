package com.zzm.syn;

/*
 * ����������������ģ��2  �źŵƷ�����־λ���
 */
public class TestPC2 {

	public static void main(String[] args) {
		Film film = new Film();

		new Cinema(film).start();
		new Audience(film).start();
	}
}

/*
 * ������---����ӰԺ
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
				this.film.play("�ٶ��뼤��8");
			} else {
				this.film.play("Ҷ��");
			}
		}
	}

}

/*
 * ������---������
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
 * ��Ʒ----����Ӱ
 */
class Film {

	String programme;// ��Ӱ��Ŀ
	//
	boolean flag = true;

	// ����
	public synchronized void play(String programme) {
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("׼����ӳ��" + programme);
		// ֪ͨ���ڹۿ�
		this.notifyAll();// ֪ͨ����
		this.programme = programme;
		this.flag = !this.flag;
	}

	// �ۿ�
	public synchronized void watch() {

		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("�ۿ��ˣ�" + programme);
		// ֪ͨ��Ա����
		this.notifyAll();
		this.flag = !this.flag;
	}

}