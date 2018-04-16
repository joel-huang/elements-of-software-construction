package Week9;
class InterruptExample2 extends Thread{  
	private int id; 

	public InterruptExample2 (int id) {
		this.id = id; 
	}
	
	public void run(){  
		for(int i=1;i<=5;i++){  
			  
				System.out.println("thread " + id + " printing " + i);    
		}//end of for loop  
	}  
  
	public static void main(String args[]){  
  
		InterruptExample2 t1=new InterruptExample2(1);    
		InterruptExample2 t2=new InterruptExample2(2);    
		t1.start();  
		t2.start();
		t1.interrupt();  	
		System.out.println("thread 1 is interrupted? " + t1.isInterrupted());
		System.out.println("thread 2 is interrupted? " + t2.isInterrupted());	
		System.out.println("thread 1 is alive? " + t1.isAlive());
		System.out.println("thread 2 is alive? " + t2.isAlive());	
	}  
}  