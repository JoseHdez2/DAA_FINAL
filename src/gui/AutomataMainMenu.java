package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import convenience.gui.CoolFrame;

public class AutomataMainMenu extends CoolFrame{

	static public final Dimension menuDimension = new Dimension(800,600); 
	
	public AutomataMainMenu() {
		super(menuDimension, CoolFrame.Type.MainCoolFrame);
	}
	
	// TODO: what
//	final static JButton btnDiversity = new JButton("Max Diversity");
//	final static JButton btnDispersion = new JButton("Max Mean Dispersion");

	@Override
	protected void addStuffIntoMe() {
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnDiversity = new JButton("Mean Diversity");
		btnDiversity.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("ping");
            	new SubMenuDiversity();
            }
        });
		this.add(btnDiversity);

		JButton btnDispersion = new JButton("Max Mean Dispersion");
		btnDispersion.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("pong");
            	
            }
        });
		this.add(btnDispersion);
	}
	
}
