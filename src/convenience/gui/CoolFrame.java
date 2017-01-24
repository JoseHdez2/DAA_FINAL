package convenience.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Normal JFrames are not cool because they:
 * 1. Make you explicitly tell them obvious stuff like ... dude.
 * 2. Don't update anymore if you already called setVisible(true).
 * 
 * @author jose
 *
 */
public abstract class CoolFrame extends JFrame {
	
	protected enum Type {
		MainCoolFrame,	// Takes the program with it in a glorious bang.
		SubCoolFrame	// Dies with a whimper.
	}
	
	/**
	 * Create a CoolFrame of certain dimensions and type.
	 * @param dim The width and height of your CoolFrame.
	 */
	public CoolFrame(Dimension dim, CoolFrame.Type type){
		super();
		this.setSize(dim);
		if(type == Type.MainCoolFrame){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
		addStuffIntoMe();
		
		this.setVisible(true);
	}
	
	/**
	 * Add stuff into me, the CoolFrame, before I set myself visible
	 * and become a stupid immutable baby JFrame.
	 */
	abstract public void addStuffIntoMe();
}
