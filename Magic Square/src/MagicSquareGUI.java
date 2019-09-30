import BreezySwing.*;
import javax.swing.*;

public class MagicSquareGUI extends GBFrame{

	IntegerField numField = addIntegerField(0,1,1,1,1);
	JButton generateSquareButton = addButton("Generate Square",2,1,1,1);
	
	public MagicSquareGUI() {
	}
	
	public void buttonClicked(JButton button) {
		if(button == generateSquareButton) {
			GenerateDialog gen = new GenerateDialog(this);
		}else if(button == null) {
			
		}
	}
	

	public static void main(String[] args) {
		MagicSquareGUI frm = new MagicSquareGUI();
		frm.setVisible(true);
		frm.setSize(300,300);
		frm.setTitle("Magic Squares");
	}

}
