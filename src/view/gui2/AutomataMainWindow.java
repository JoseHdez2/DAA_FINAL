package view.gui2;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import convenience.gui.CoolFrame;
import view.gui.SubMenuDiversity;

public class AutomataMainWindow extends CoolFrame{

	static public final Dimension menuDimension = new Dimension(800,600); 
	
	public AutomataMainWindow() {
		super(menuDimension, CoolFrame.Type.MainCoolFrame);
	}
	
	// TODO: what
//	final static JButton btnDiversity = new JButton("Max Diversity");
//	final static JButton btnDispersion = new JButton("Max Mean Dispersion");

	@Override
	protected void addStuffIntoMe() {
		this.getContentPane().setLayout(new GridLayout(2, 1));
		
		Border probTypeBorder = BorderFactory.createTitledBorder("[1] Problem Type");
		JPanel probTypePanel = new JPanel();
		probTypePanel.setBorder(probTypeBorder);
		this.add(probTypePanel);
		
		JPanel probTypePanel2 = new JPanel(new GridLayout(1,2));
		this.add(probTypePanel2);
		
		Border border2a = BorderFactory.createTitledBorder("[2A] Problem Definition");
		JPanel panel2a = new JPanel();
		panel2a.setBorder(border2a);
		probTypePanel2.add(panel2a);
		
		Border border2b = BorderFactory.createTitledBorder("[2B] Test Execution");
		JPanel panel2b = new JPanel();
		panel2b.setBorder(border2b);
		probTypePanel2.add(panel2b);
	}
	
}
