import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OldCalcBuilder implements CalcBuilder {
	private Calc calc;
	
	public OldCalcBuilder() {
		this.calc = new Calc();
	}

	@Override
	public void buildJFrame() {
		JFrame frame = new JFrame("Kalkulator");
		frame.setResizable(false);
		calc.setJFrame(frame);
	}

	@Override
	public void buildMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBackground(Color.BLACK);
		calc.setMainPanel(mainPanel);
		calc.getJFrame().setContentPane(mainPanel);
	}

	@Override
	public void buildCalcScreen() {
		JPanel calcScreen = new JPanel();
		calcScreen.setPreferredSize(new Dimension(400, 100));
		calcScreen.setBackground(Color.BLACK);
		calc.setCalcScreen(calcScreen);
		calc.getMainPanel().add(calcScreen, BorderLayout.NORTH);
	}

	@Override
	public void buildButtons() {
		JPanel buttons = new JPanel(new GridLayout(4, 4));
		buttons.setPreferredSize(new Dimension(400, 300));
		String[] buttonChar = { "1", "2", "3", " + ", "4", "5", "6", " - ", "7", "8", "9", " * ", "Clear", "0", " = ", " / " };
		for (int i = 0; i < buttonChar.length; i++) {
			JButton b = new JButton(buttonChar[i]);
			b.setFont(new Font("Dialog", Font.BOLD, 25));
			String bc = buttonChar[i];
			switch (bc) {
			case "Clear":
				b.setBackground(Color.LIGHT_GRAY);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (b.isEnabled()) {
							calc.getOperation().setText("");
							calc.getResult().setText("");
						}
					}
				});
				buttons.add(b);
				break;

			case " = ":
				b.setBackground(Color.ORANGE);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (b.isEnabled()) {
							calc.getOperation().setText(calc.getOperation().getText() + " = ");
							calc.getCalcScreen().add(calc.getOperation());
							calc.getCalcScreen().revalidate();
							calc.getCalcScreen().repaint();
							StringTokenizer st = new StringTokenizer(calc.getOperation().getText());
							double a = Double.parseDouble(st.nextToken());
							String znak = st.nextToken();
							double b = Double.parseDouble(st.nextToken());
							Operation operation = new Operation(a,b,znak);
							HashMap<String, Chain> operationMap = new HashMap<>();
							operationMap.put("+", new Addition());
							operationMap.put("-", new Subtraction());
							operationMap.put("*", new Multiplication());
							operationMap.put("/", new Division());
							operationMap.get(operation.getZnak()).calculate(operation);
							calc.getResult().setText("" + operation.getResult() + "\n");
							calc.getCalcScreen().add(calc.getResult(), BorderLayout.SOUTH);
							calc.getCalcScreen().revalidate();
							calc.getCalcScreen().repaint();
							calc.addToHistory(a +" "+ znak + " "+ b+ " = " + operation.getResult() + "\n");
						}

					}
				});
				buttons.add(b);
				break;

			default:
				try{
					Byte.parseByte(bc);
				}catch(NumberFormatException nfe){
					b.setBackground(Color.ORANGE);
				}
				
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(calc.getOperation().getText().length() > 0 && calc.getResult().getText().length() > 0){
							calc.getOperation().setText("");
							calc.getResult().setText("");
						}
						if (b.isEnabled()) {
							calc.getOperation().setText(calc.getOperation().getText() + b.getText());
							calc.getCalcScreen().add(calc.getOperation(), BorderLayout.NORTH);
							calc.getCalcScreen().revalidate();
							calc.getCalcScreen().repaint();
						}

					}
				});
				buttons.add(b);
				break;

			}
		}

		calc.getJFrame().add(buttons);
		calc.getJFrame().pack();
		calc.getJFrame().setVisible(true);
		calc.getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void buildHistoryScreen() {
		JPanel history = new JPanel();
		history.setPreferredSize(new Dimension(180, 400));
		history.setLayout(new GridLayout(10,1));
		history.add(new JScrollPane());
		calc.getMainPanel().add(history, BorderLayout.EAST);
		calc.setHistoryScreen(history);
	}

	@Override
	public Calc getCalc() {
		return this.calc;
	}

	@Override
	public void buildOperationLabel() {
		JLabel op = new JLabel();
		op.setToolTipText("Dzialanie");
		op.setOpaque(true);
		op.setBackground(Color.BLACK);
		op.setForeground(Color.WHITE);
		op.setFont(new Font("Dialog", Font.BOLD, 35));
		calc.setOperationLabel(op);

	}

	@Override
	public void buildResultLabel() {
		JLabel res = new JLabel();
		res.setToolTipText("Dzialanie");
		res.setOpaque(true);
		res.setBackground(Color.BLACK);
		res.setForeground(Color.WHITE);
		res.setFont(new Font("Dialog", Font.BOLD, 35));
		calc.setResultLabel(res);

	}

	public static void getResult(JPanel textPane, JLabel res, double operation) {
		res.setText("" + operation + "\n");
		textPane.add(res, BorderLayout.SOUTH);
		textPane.revalidate();
		textPane.repaint();
	}
}
