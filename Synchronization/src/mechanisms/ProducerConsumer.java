package mechanisms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
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
				synchronized (this) {
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
					if (q.size() == 1) {
						notify();
					}
				}
			}
		}
	}

	public static class Consumer extends Thread {
		@Override
		public void run() {
			int num;
			while (true) {
				synchronized (this) {
					if (q.size() == 0) {
						try {
							wait();
						} catch (InterruptedException e) {

						}
					}
					num = q.poll();
					System.out.println("consumed " + num);
					System.out.println("Queue size " + q.size());
					if (q.size() == cap - 1) {
						notify();
					}
				}
			}
		}
	}

}
