public class CalcEngineer {
	private CalcBuilder calcBuilder;
	
	public CalcEngineer(){
		this.calcBuilder = new OldCalcBuilder();
	}
	
	public Calc getCalc(){
		return calcBuilder.getCalc();
	}
	
	public void makeCalc(){
		calcBuilder.buildJFrame();
		calcBuilder.buildMainPanel();
		calcBuilder.buildCalcScreen();
		calcBuilder.buildOperationLabel();
		calcBuilder.buildResultLabel();
		calcBuilder.buildHistoryScreen();
		calcBuilder.buildButtons();
	}
}
