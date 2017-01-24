package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import convenience.gui.CoolFrame;

public class MainMenu extends CoolFrame{

	static final Dimension menuDimension = new Dimension(800,600); 
	
	public MainMenu() {
		super(menuDimension, CoolFrame.Type.MainCoolFrame);
	}
	
	ActionListener mDivMenu = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			System.out.println("SUP");
		};
	};
	
	// TODO: what
//	final static JButton btnDiversity = new JButton("Max Diversity");
//	final static JButton btnDispersion = new JButton("Max Mean Dispersion");

	@Override
	public void addStuffIntoMe() {
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton btnDiversity = new JButton("Mean Division");
		this.add(btnDiversity);
		
		btnDiversity.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("ping");
            }
        });
		
		JButton btnDispersion = new JButton("Max Mean Dispersion");
		btnDispersion.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("pong");
            }
        });
		this.add(btnDispersion);
		
		btnDispersion.addActionListener(mDivMenu);
	}
	
}
