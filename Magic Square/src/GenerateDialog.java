import BreezySwing.*;
import javax.swing.*;
public class GenerateDialog extends GBDialog{

	JLabel sizeLabel = addLabel("Size of square:",1,1,1,1);
	JLabel startLabel = addLabel("Starting number:",2,1,1,1);
	
	IntegerField numField = addIntegerField(0,1,2,1,1);
	IntegerField startField = addIntegerField(0,2,2,1,1);
	JButton generateButton = addButton("Generate",3,1,2,1);
	JTextArea resultTA = addTextArea("",4,1,2,2);
	
	int[][] square;
	
	JTable table;
	
	public void buttonClicked(JButton button) {
		if(button == generateButton) {
			if(!ReeveHelper.isValidNumber(numField)) {
				messageBox("Invalid number");
				return;
			}
			int size = numField.getNumber();
			if(ReeveHelper.isNegative(numField)) {
				messageBox("Number must be positive");
				return;
			}
			if(ReeveHelper.isEven(size)) {
				messageBox("Number must be odd");
				return;
			}else {
				SquareGenerator g = new SquareGenerator(size,startField.getNumber());
				square = g.getSquareArray();
				resultTA.setText(g.getSquareString());
				table = new JTable(size,size);
				table.setBounds(30, 30, 200, 300);
			}
		}
	}
	
	
	
	
	public GenerateDialog(JFrame parent) {
		super(parent);
		resultTA.setEditable(false);
		this.setTitle("Generate Button");
		this.setSize(400,400);
		this.setVisible(true);
	}

}
