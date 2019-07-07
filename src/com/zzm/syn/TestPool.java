package com.zzm.syn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestPool {

	public static void main(String[] args) {
		// 创建服务，创建线程池
		ExecutorService service = Executors.newFixedThreadPool(10);

		// 执行 execute 无返回值
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.execute(new MyThread());

		// 执行 submit 有返回值
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

		// 关闭链接
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
		System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
		Thread.sleep(2000);
		return "任务的结果是：" + Thread.currentThread().getName();
	}

}
