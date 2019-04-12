public class Multiplication implements Chain {

	@Override
	public void calculate(Operation op) {
		double result = op.getA() * op.getB();
		op.setResult(result);
	}

}
