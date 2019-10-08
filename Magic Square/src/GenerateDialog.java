import BreezySwing.*;
import javax.swing.*;
public class GenerateDialog extends GBDialog{

	//instance objects
	JLabel sizeLabel = addLabel("Size of square(odd numbers only):",1,1,1,1);
	JLabel startLabel = addLabel("Starting number:",2,1,1,1);
	
	IntegerField numField = addIntegerField(0,1,2,1,1);
	IntegerField startField = addIntegerField(0,2,2,1,1);
	JButton generateButton = addButton("Generate",3,1,2,1);
	JTextArea resultTA = addTextArea("",4,1,2,2);
	
	//instance variable
	int[][] square;
	
	public void buttonClicked(JButton button) {
		if(button == generateButton) {
			//error checks before continuing 
			if(!ReeveHelper.isValidNumber(numField)) {
				messageBox("Invalid number in size field");
				numField.requestFocusInWindow();
				numField.selectAll();
				return;
			}
			if(!ReeveHelper.isValidNumber(startField)) {
				messageBox("Invalid number in starting number field");
				startField.requestFocusInWindow();
				startField.selectAll();
				return;
			}
			int size = numField.getNumber();
			if(ReeveHelper.isNegative(numField)) {
				messageBox("Number must be positive");
				numField.requestFocusInWindow();
				numField.selectAll();
				return;
			}
			if(ReeveHelper.isEven(size)) {
				messageBox("Number must be odd");
				numField.requestFocusInWindow();
				numField.selectAll();
				return;
			}else {
				SquareGenerator g = new SquareGenerator(size,startField.getNumber());
				square = g.getSquareArray();
				resultTA.setText(g.getSquareString());
			}
		}
	}
	
	//constructor
	public GenerateDialog(JFrame parent) {
		super(parent);
		resultTA.setEditable(false);
		this.setTitle("Generate Button");
		this.setSize(400,400);
		this.setVisible(true);
	}

}