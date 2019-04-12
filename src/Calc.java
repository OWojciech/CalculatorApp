import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calc implements CalcPlan {
	private JPanel mainPanel;
	private JPanel calcScreen;
	private JPanel buttonPanel;
	private JPanel history;
	private JFrame frame;
	private JLabel operation;
	private JLabel result;
	private int maxHistory = 9;
	private Queue<JButton> historyQueue;
	
	public Calc(){
		historyQueue = new LinkedList<>();
	}
	@Override
	public void setMainPanel(JPanel panel) {
		mainPanel = panel;
	}

	@Override
	public void setCalcScreen(JPanel panel) {
		calcScreen = panel;
	}

	@Override
	public void setButtons(JPanel panel) {
		buttonPanel = panel;
	}

	@Override
	public void setHistoryScreen(JPanel panel) {
		history = panel;
	}

	@Override
	public void setJFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void setOperationLabel(JLabel label) {
		operation = label;
	}

	@Override
	public void setResultLabel(JLabel label) {
		result = label;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JPanel getCalcScreen() {
		return calcScreen;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JPanel getHistory() {
		return history;
	}

	public JFrame getJFrame() {
		return frame;
	}

	public JLabel getOperation() {
		return operation;
	}

	public JLabel getResult() {
		return result;
	}
	
	public void addToHistory(String historyText){
		JButton button = new JButton(historyText);
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.BOLD, 15));
		if(historyQueue.size()> maxHistory){
			historyQueue.remove();
		}
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(button.isEnabled()){
					String[] tmp = button.getText().split("=");
					getOperation().setText(getOperation().getText() + tmp[tmp.length-1]);
					getCalcScreen().add(getOperation(), BorderLayout.NORTH);
					getCalcScreen().revalidate();
					getCalcScreen().repaint();
				}
				
			}
			
		});
		historyQueue.add(button);
		history.removeAll();
		for(JButton q : historyQueue){
			history.add(q);
		}
	}
}
