package lines;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private graphicsPanel panel_1 = new graphicsPanel(this);
	int mode = -1;
	double steps = .01;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//"i think im cool but im not" - henry bulmer 
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.EAST);
		btnPanel.setLayout(new GridLayout(10, 10, 0, 0));
	
		JLabel lblMode = new JLabel("Step Size");
		btnPanel.add(lblMode);
		
		textField = new JTextField(".01");
		btnPanel.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent arg0){
				steps = Double.parseDouble(textField.getText());
		}
		});
		
		JLabel label = new JLabel("Press enter to input new steps");
		label.setFont(new Font("Serif", Font.PLAIN, 10));
		btnPanel.add(label);
		
		//creates a group of radio buttons for the three line types
		ButtonGroup group = new ButtonGroup();

		JRadioButton parametricBTN = new JRadioButton("Parametric");
		btnPanel.add(parametricBTN);
		group.add(parametricBTN);
		parametricBTN.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent arg0){
				mode = 0;
				panel_1.setMode(mode);
			}
		});
		JRadioButton bresenhamBTN = new JRadioButton("Bresenham");
		btnPanel.add(bresenhamBTN);
		group.add(bresenhamBTN);
		bresenhamBTN.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent arg0){
				mode = 1;
				panel_1.setMode(mode);
			}
		});
		JRadioButton apiBTN = new JRadioButton("API");
		btnPanel.add(apiBTN);
		group.add(apiBTN);
		apiBTN.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent arg0){
				mode = 2;
				panel_1.setMode(mode);
			}
		});
		
		JButton btnDot = new JButton("Erase");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_1.erase();
			}
				});

		btnPanel.add(btnDot);
		
		//my name is henry and i eat poop 
		panel_1.setBorder(new LineBorder(new Color(240, 240, 240), 10));
		panel_1.setBackground(Color.PINK);
		panel_1.setForeground(Color.BLACK);
		contentPane.add(panel_1, BorderLayout.CENTER);
	}
	
	public int getMode(){
		return mode;
	}
	
	public double getSteps(){
		return steps;
	}
}
