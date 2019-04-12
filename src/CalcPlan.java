import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface CalcPlan {
	public void setJFrame(JFrame frame);
	
	public void setMainPanel(JPanel panel);
	
	public void setCalcScreen(JPanel panel);
	
	public void setButtons(JPanel panel);
	
	public void setHistoryScreen(JPanel textArea);
	
	public void setOperationLabel(JLabel label);
	
	public void setResultLabel(JLabel label);
}
