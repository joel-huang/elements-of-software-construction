package Week4;

public class Account {
	private int balance = 0;

	public Account () {
	}

	public Account (int balance) {
		this.balance = balance;
	}
	
	//Doubling the balance and then plus 10
	int calAmount () {
	     int ret = balance * 3;
	     ret = ret + 10;
	     //assert(ret == balance*2+10);
	     return ret;  
	}
	
	public void deposite (int amount) {
		balance += amount;
	}
	
	public boolean withdraw (int amount) {
		if (balance >= amount) {
			balance -= amount;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setBalance (int amount) {
		balance = amount;
	}
	
	public int getBalance () {
		return balance;
	}
	
	public int compare (Account yourAccount) {
		return balance/yourAccount.balance;
	}
}
