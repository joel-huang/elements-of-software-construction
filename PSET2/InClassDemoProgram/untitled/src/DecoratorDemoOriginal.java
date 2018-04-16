package Week3;

class iPizza {
	public String ingredients () {
		return "dough";
	}
	
	public double cost () {
		return 3.0;
	}
}

//pizza with cheese and no tomato and no ham
class iCheesePizza {	
	public String ingredients () {
		return "dough, cheese";
	}
	
	public double cost () {
		return 5.0;
	}
}

//pizza with ham and no cheese and no tomato
class HamPizza {	
	public String ingredients () {
		return "dough, ham";
	}
	
	public double cost () {
		return 5.0;
	}
}

//pizza with cheese and no tomato and no ham
class TomatoPizza {	
	public String ingredients () {
		return "dough, cheese";
	}
	
	public double cost () {
		return 4.0;
	}
}

class TomatoCheesePizza { }
class TomatoHamPizza { }
class HamCheesePizza { }
class HamTomatoCheesePizza {}
