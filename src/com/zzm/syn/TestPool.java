package com.zzm.syn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestPool {

	public static void main(String[] args) {
		// �������񣬴����̳߳�
		ExecutorService service = Executors.newFixedThreadPool(10);

		// ִ�� execute �޷���ֵ
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());

		// ִ�� submit �з���ֵ
		Future<String> rtn = service.submit(new WithResultTask());
		try {
			System.out.println(rtn.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ر�����
		service.shutdown();
	}
}

class MyThread implements Runnable {

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName());

	}

}

class WithResultTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("call()�������Զ�����,�ɻ����             " + Thread.currentThread().getName());
		Thread.sleep(2000);
		return "����Ľ���ǣ�" + Thread.currentThread().getName();
	}

}
