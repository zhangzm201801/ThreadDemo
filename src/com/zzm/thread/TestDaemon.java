package com.zzm.thread;

/*
 * �̷߳�Ϊ�û��̺߳��ػ��߳�
 * ���������ȷ���û��߳�ִ�����
 * ��������õȴ��ػ��߳�ִ�����
 * �ػ��̣߳��������� ������ڴ棬��̨��־������
 * �����ػ��߳�setDaemon(true);
 */
public class TestDaemon {

	public static void main(String[] args) {

		God god = new God();
		You you = new You();

		Thread thread = new Thread(god);
		// Ĭ����false��ʾ���û��̣߳��������̶߳����û��߳�
		//�����ػ��߳�
		thread.setDaemon(true);
		thread.start();// �ϵ��ػ��߳�����
		
		new Thread(you).start();//�û��߳�����
	}
}

class God implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("�ϵ۱�������");
		}
	}

}

class You implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 365000; i++) {
			System.out.println("��һ�������ĵĻ���");
		}
		
		System.out.println("*********Goodbye world");
	}
}