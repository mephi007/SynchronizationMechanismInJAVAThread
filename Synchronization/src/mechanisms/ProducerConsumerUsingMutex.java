package mechanisms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerConsumerUsingMutex {
	public static Semaphore con = new Semaphore(0);
	public static Semaphore pro = new Semaphore(1);
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
					pro.acquire();
				}catch(Exception e) {
					
				}
				num = random.nextInt(100);
				if (q.size() == cap) {
					try {
						wait();
					} catch (InterruptedException e) {

					}
				}
				q.add(num);
				System.out.println("produced " + num);
				System.out.println("Queue size " + q.size());
				
					con.release();
				
				
			}
		}
	}

	public static class Consumer extends Thread {
		@Override
		public void run() {
			int num;
			while (true) {
				try {
					con.acquire();
				}catch(Exception e) {
					
				}
				if (q.size() == 0) {
					try {
						wait();
					} catch (InterruptedException e) {

					}
				}
				num = q.poll();
				System.out.println("consumed " + num);
				System.out.println("Queue size " + q.size());
				
					pro.release();
				
				
			}
		}
	}

}
