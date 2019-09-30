import BreezySwing.*;
import javax.swing.*;
public class CheckSquareDialog extends GBDialog{
	
	JComboBox sizeCombo = addComboBox(1,1,1,1);
	
	public CheckSquareDialog(JFrame parent) {
		super(parent);
		this.setTitle("Square Checker");
		this.setSize(300,300);
		this.setVisible(true);
		sizeCombo.addItem("test");
		for(int i = 2; i <= 8; i++) {
			String size = i + "x" + i;
			sizeCombo.addItem(size);
		}
	}

}
