package mechanisms;

public class strictAlternation {
	public static int turn = 0;
	/*
	 * turn = 1 implies DecrementThread is inside Critical Seaction.
	 * turn = 0 implies IncrementThread is inside Critical Seaction
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inventory inv = new Inventory();
		IncrementThread inc = new IncrementThread(inv);
		DecrementThread dec = new DecrementThread(inv);
		inc.start();
		dec.start();
		System.out.println("items in Inventory "+ inv.getItem());
	}
	public static class DecrementThread extends Thread{
		Inventory inv;
		public DecrementThread(Inventory inv) {
			this.inv = inv;
		}
		@Override
		public void run() {
			for(int i=0; i<500; i++) {
				while(turn == 0);
				inv.decrement();
				turn=1;
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
				while(turn == 1);
				inv.increment();
				turn = 1;
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
