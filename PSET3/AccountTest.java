package Week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest { 
   @Test
   public void Compare () { 
	   Account a = new Account(); 
	   Account b = new Account(); 
	   b.compare(a);
   }

   @Test
   public void testCalAmount() {
	   Account a = new Account(); 
	   a.setBalance(10);
	   int amount = a.calAmount();
	   assertTrue(amount == 30);
   }
   
   @Test
   public void testSetupAccount() {
	   Account a = new Account(); 
   }
   
   @Test
   public void testWithDraw() {
	   Account a = new Account(); 
	   a.setBalance(100);
	   boolean success = a.withdraw(1000);
	   assertFalse(success);
   }
}
