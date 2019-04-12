public class Operation {
	private double a;
	private double b;
	private String znak;
	private double result;

	public Operation(double a, double b, String znak) {
		super();
		this.a = a;
		this.b = b;
		this.znak = znak;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public String getZnak() {
		return znak;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}
