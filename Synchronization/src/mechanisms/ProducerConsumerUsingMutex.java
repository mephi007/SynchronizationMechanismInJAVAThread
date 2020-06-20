package mechanisms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerConsumerUsingMutex {
	public static Semaphore mutex = new Semaphore(1);
	public static Queue<Integer> q = new LinkedList();
	public static Random random = new Random();
	public static int cap = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Producer p = new Producer();
		Consumer c = new Consumer();
		p.start();
		c.start();
	}

	public static class Producer extends Thread {
		@Override
		public void run() {
			int num;
			while (true) {
				try {
					mutex.acquire();
					try {
						num = random.nextInt(100);
						q.add(num);
						System.out.println("produced " + num);
						System.out.println("Queue size " + q.size());
					}finally {
						mutex.release();
					}
					
				}catch(Exception e) {
					
				}
				
			}
		}
	}

	public static class Consumer extends Thread {
		@Override
		public void run() {
			int num;
			while (true) {
				try {
					mutex.acquire();
					try {
						
						num = q.poll();
						System.out.println("produced " + num);
						System.out.println("Queue size " + q.size());
					}finally {
						mutex.release();
					}
					
				}catch(Exception e) {
					
				}
				
			}
		}
	}

}
