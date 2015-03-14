
public class BigNumTest {

	public static void main(String[] args) {
		/**
		 * Main method for Test class
		 */
		// Test big-number initialization
		BigNum num1 = new BigNum(140);
		pr(num1);
		BigNum num2 = new BigNum(280);
		pr(num2);		
		// Test addition and subtraction
		num2.add(num1);
		pr(num2);
		num2.sub(num1);
		pr(num2);
		// Test multiplication and division
		num1 = new BigNum(203);
		BigNum num3 = new BigNum(600);
		num2.mul(num1);
		pr(num2);
		num2.add(num3);
		num2.div(num1);
		pr(num2);
		// Test factorial(100) calculation
		BigNum numFactorial = new BigNum(100);
		numFactorial = factorialBig(numFactorial);
		pr(numFactorial);
	}

	public static void pr(BigNum x){
		System.out.println(x.show());
	}

	public static BigNum factorialBig(BigNum x) {
		// TODO Factorial of the Number
		BigNum num1 = new BigNum(1);
		if (!(x.great(num1))) return num1;
		else {
			num1.copy(x);
			num1.sub(new BigNum(1));
			x.mul(factorialBig(num1));
			return x;
		}
	}
}
