package mechanisms;

public class lockVariable {
	public static int lock = 0;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Inventory inv = new Inventory();
		IncrementThread inc = new IncrementThread(inv);
		DecrementThread dec = new DecrementThread(inv);
		inc.start();
		dec.start();
//		inc.join();
//		dec.join();
		System.out.println("Item is inventory should be zero"+ inv.getItems());
	}
	public static class IncrementThread extends Thread{
		private Inventory inv;
		public IncrementThread(Inventory inv) {
			this.inv = inv;
		}
		@Override
		public void run() {
			for(int i=0; i<500; i++) {
			while(lock != 0);
			lock = 1;
			inv.increment();
			lock = 0;
			}
		}
	}
	
	public static class DecrementThread extends Thread{
		private Inventory inv;
		public DecrementThread(Inventory inv) {
			this.inv = inv;
		}
		@Override
		public void run(){
			for(int i=0; i<500; i++) {
				while(lock != 0);
				lock = 1;
				inv.decrement();
				lock = 0;
				}
		}
	}
	private static class Inventory{
		private int items = 0;
		public void increment() {
			items++;
		}
		public void decrement() {
			items--;
		}
		public int getItems() {
			return items;
		}
	}
}
