public class Subtraction implements Chain {
	private Chain nextChain;

	public void calculate(Operation op) {
		double result = op.getA() - op.getB();
		op.setResult(result);
	}

}
