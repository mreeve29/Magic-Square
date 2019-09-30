import BreezySwing.*;
import javax.swing.*;

public class MagicSquareGUI extends GBFrame{

	JButton generateSquareButton = addButton("Generate Square",1,1,1,1);
	JButton checkSquareButton = addButton("Check Square",2,1,1,1);
	
	public MagicSquareGUI() {
	}
	
	
	public void buttonClicked(JButton button) {
		if(button == generateSquareButton) {
			GenerateDialog gen = new GenerateDialog(this);
		}else if(button == checkSquareButton) {
			CheckSquareDialog checkDialog = new CheckSquareDialog(this);
		}
	}
	

	public static void main(String[] args) {
		MagicSquareGUI frm = new MagicSquareGUI();
		frm.setVisible(true);
		frm.setSize(300,300);
		frm.setTitle("Magic Squares");
	}

}
