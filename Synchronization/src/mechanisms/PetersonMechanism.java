package mechanisms;

import java.util.Arrays;

public class PetersonMechanism {
	static boolean[] interested = new boolean[2];
	static int turn;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Arrays.fill(interested, false);
		Inventory inv = new Inventory();
		IncrementThread inc = new IncrementThread(inv);
		DecrementThread dec = new DecrementThread(inv);
		inc.start();
		dec.start();
//		inc.join();
//		dec.join();
		System.out.println("Peterson Method items in Inventory "+ inv.getItem());
	}
	public static void entry(int process) {
		int other = 1 - process;
		interested[process] = true;
		turn = process;
		while(interested[other] == true && turn == process);
		
	}
	public static void exit(int process) {
		interested[process] = false;
	}
	
	public static class DecrementThread extends Thread{
		Inventory inv;
		public DecrementThread(Inventory inv) {
			this.inv = inv;
		}
		@Override
		public void run() {
			for(int i=0; i<500; i++) {
				entry(0);
				inv.decrement();
				exit(0);
			}
		}
	}
	public static class IncrementThread extends Thread{
		Inventory inv ;
		public IncrementThread(Inventory inv) {
			this.inv = inv;
		}
		@Override
		public void run() {
			for(int i=0; i<500; i++) {
				entry(1);
				inv.increment();
				exit(1);
			}
		}
	}
	private static class Inventory{
		private int item = 0;
		public void increment() {
			item++;
		}
		public void decrement() {
			item--;
		}
		public int getItem() {
			return item;
		}
	}
}
