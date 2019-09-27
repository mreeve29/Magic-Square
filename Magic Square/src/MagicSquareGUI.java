import BreezySwing.*;
import javax.swing.*;

public class MagicSquareGUI extends GBFrame{

	IntegerField numField = addIntegerField(0,1,1,1,1);
	JButton calculateButton = addButton("Go",2,1,1,1);
	
	public MagicSquareGUI() {
	}
	
	public void buttonClicked(JButton button) {
		if(button == calculateButton) {
			SquareGenerator g = new SquareGenerator(numField.getNumber());
			System.out.println(g.getSquare());
		}
	}
	

	public static void main(String[] args) {
		MagicSquareGUI frm = new MagicSquareGUI();
		frm.setVisible(true);
		frm.setSize(300,300);
		frm.setTitle("Magic Squares");
	}

}
