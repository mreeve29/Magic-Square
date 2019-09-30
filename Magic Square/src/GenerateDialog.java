import BreezySwing.*;
import javax.swing.*;
public class GenerateDialog extends GBDialog{

	IntegerField numField = addIntegerField(0,1,1,1,1);
	JButton generateButton = addButton("Generate",2,1,1,1);
	
	int[][] square;
	
	JTable table;
	
	public void buttonClicked(JButton button) {
		if(button == generateButton) {
			if(!ReeveHelper.isValidNumber(numField)) {
				messageBox("Invalid number");
				return;
			}
			int size = numField.getNumber();
			if(ReeveHelper.isEven(size)) {
				messageBox("Number must be odd");
				return;
			}else {
				SquareGenerator g = new SquareGenerator(size);
				square = g.getSquareArray();
				table = new JTable(size,size);
				table.setBounds(30, 30, 200, 300);
			}
		}
	}
	
	
	
	
	public GenerateDialog(JFrame parent) {
		super(parent);
		this.setTitle("Generate Button");
		this.setSize(400,400);
		this.setVisible(true);
	}

}
