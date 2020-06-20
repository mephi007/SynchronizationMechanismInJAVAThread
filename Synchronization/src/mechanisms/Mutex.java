package mechanisms;

import java.util.concurrent.Semaphore;

public class Mutex {
	private static Semaphore mutex = new Semaphore(1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceAllocation t1 = new ResourceAllocation("t1");
		ResourceAllocation t2 = new ResourceAllocation("t2");
		ResourceAllocation t3 = new ResourceAllocation("t3");
		t1.start();
		t2.start();
		t3.start();
	}
	
	private static class ResourceAllocation extends Thread{
		private String name = "";
		public ResourceAllocation(String name) {
			this.name = name;
		}
		@Override
		public void run() {
			try {
				System.out.println("available resources "+mutex.availablePermits());
				mutex.acquire();
				System.out.println(name+" acquired resource");
				try {
					System.out.println(name+ " working on resource");
					Thread.sleep(1000);
				}finally {
					mutex.release();
					System.out.println(name+ " released resource");
				}
			}catch(Exception e) {
				
			}
		}
	}

}
