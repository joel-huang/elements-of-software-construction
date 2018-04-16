public class Complex {
	public static void main(String[] args) {
		ComplexCalc.ComplexNumberRect = new ComplexCalc.ComplexNumberRect(1,2);
	}
}

class ComplexCalc {
	class ComplexNumberRect extends ComplexNumber {
		public double x;
		public double y;
		ComplexNumberRect(double x, double y) {
			x = x;
			y = y;
		}
		double getReal() {return x;}
		double getIm() {return y;}
		String getResult() {
			String s = x + " + " + y + "i";
			return s;
		}
	}

	class ComplexNumberPolar extends ComplexNumber {
		public double r;
		public double theta;
		double getR() {return r;}
		double getTheta() {return theta;}
	}

	ComplexNumberRect add(ComplexNumberRect num1, ComplexNumberRect num2) {
		double sumX = num1.x + num2.x;
		double sumY = num1.y + num2.y;
		return new ComplexNumberRect(sumX, sumY);
	}
}