package mechanisms;



public class InterestedVariableMechnaism {
	static boolean IncInterested = false;
	static boolean DecInterested = false;
	public static void main(String[] args) throws InterruptedException{
		Inventory inv = new Inventory();
		IncrementThread inc = new IncrementThread(inv);
		DecrementThread dec = new DecrementThread(inv);
		inc.start();
		dec.start();
		inc.join();
		dec.join();
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
				DecInterested = true;
				while(IncInterested == true);
				inv.decrement();
				DecInterested=false;
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
				IncInterested = true;
				while(DecInterested == true);
				inv.increment();
				IncInterested = false;
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
