
public class BigNum {
	/**
	 * Class that holds numbers larger than long as array
	 * base - base for calculations (10 - for decimal numbers)
	 * lenNum - size of the internal number
	 * num[] - the array of the internal number itself
	 */
	static byte base = 10;
	static int lenNum = 1024;
	private byte num[];

	// Constructors
	public BigNum() {
		// BigNum memory allocation constructor
		num = new byte[lenNum];
	}

	public BigNum(int x) {
		// Translate into base BigNum constructor
		this();
		int i = lenNum - 1;
		while (x > 0)
		{
			num[i] = (byte) (x % base);
			x /= base;
			i--;
		}
	}

	public BigNum(BigNum x) {
		// BigNum copy constructor
		this();
		int i = lenNum;
		while (i >= 0) {
			num[i] = x.num[i];
			i--;
		}
	}

	// Arithmetic operations
	void add(BigNum x) {
		// Addition method
		int overflow = 0;
		int i = lenNum - 1;
		for (; i>= 0; i--) {
			num[i] += x.num[i] + overflow;
			overflow = num[i] / base;
			num[i] %= base;
		}
	}

	void sub(BigNum x) {
		// Subtraction method
		int underflow = 0;
		for (int i = lenNum - 1; i >= 0; i--) {
			num[i] = (byte) (num[i] - x.num[i] + underflow);
			if (num[i] < 0) 
			{
				underflow = -1;
				num[i] += base;
			}
			else
				underflow = 0;
		}
	}

	void mul(BigNum x) {
		// Multiplication method
		BigNum rslt = new BigNum(); // Result
		int i, j = lenNum - 1;
		int lim_j = 0, lim_i = 0;
		int h; int overflow;
		while (x.num[j] <= 0) //Zeroes to skip in the number to multiply by
			j--;
		while (x.num[lim_j] == 0) // Beginning of the number to multiply by
			lim_j++;
		while (num[lim_i] == 0) // Beginning of internal number
			lim_i++;
		// From after zeroes to the beginning of the number to multiply by
		for (; j >= lim_j; j--) {
			h = j; overflow = 0;
			BigNum subRslt = new BigNum();
			// From the end of the internal number to its beginning
			for (i= lenNum - 1; i >= lim_i; i--) {
				subRslt.num[h] = (byte) (num[i] * x.num[j] + overflow);// Multiply elements of the numbers
				overflow = subRslt.num[h] / base;// If ost is more than base then shift it into the base
				subRslt.num[h] %= base;
				h--;
			}
			// Add ost to the next element (if it does exist)
			while (overflow > 0) {
				subRslt.num[h] = (byte) (overflow % base);
				overflow /= base;
				h--;
			}
			rslt.add(subRslt);
		}
		this.copy(rslt);
	}

	void div(BigNum x) {
		// Modular division
		BigNum rslt = new BigNum(); // Result
		BigNum one = new BigNum(1); //
		while (this.greater(x)) // Until fist number is lesser than the second
		{
			this.sub(x); // Subtract
			rslt.add(one); // Add to the Result
		}
		x.copy(this);
		this.copy(rslt); // Copy to the internal number
	}

	// Additional arithmetic
	private void mulBase() {
		// TODO Remove (not used)
		// Multiply the internal number by base - actually
		// shifts by position to the most digit
	}

	private void divBase() {
		// TODO Remove (not used)
		// Divide the internal number by base - actually
		// shifts by position to the less digit
	}

	private void mulDigit(byte x, BigNum y) {
		// TODO Remove (not used)
		// Multiply 1-digit number x by y
	}

	private int divDigit(BigNum x) {
		// TODO Remove (not used)
		// Returns 1 digit from division by the number x
		// The internal number must be greater than x not more than base times
		return 0;
	}

	// Support methods
	void copy(BigNum x) {
		// Copy method
		for (int i = 0; i < lenNum; i++)
			num[i] = x.num[i];
	}

	Boolean greater(BigNum x) {
		// Comparison method
		int i = 0;
		while (i < lenNum && num[i] == x.num[i])
			i++;
		if (i == lenNum || num[i] > x.num[i])
			return true;
		else
			return false;
	}

	String show() {
		// Show the internal number as String method
		int i = 0;
		String str = "";
		while (num[i] == 0)
			i++;
		for (; i < lenNum; i++){
			str += String.valueOf(num[i]);
		}
		return str;
	}

}
