import BreezySwing.*;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class CheckSquareDialog extends GBDialog {

	//buttons
	JComboBox sizeCombo = addComboBox(1, 1, 1, 1);
	JButton buildButton = addButton("Build/Reset", 2, 1, 1, 1);
	
	//array of TextFields for input
	IntegerField[][] fieldArr;

	JLabel[] rowLabels;
	JLabel[] columnLabels;
	JLabel[] diagonalLabels;

	private int comboSize = 2;

	public void buttonClicked(JButton button) {
		if (button == buildButton) {
			boolean reset = true;
			String s = (String) sizeCombo.getSelectedItem();
			if (Character.getNumericValue(s.charAt(0)) == comboSize) {
				this.setSize(this.getSize().width+1,this.getSize().height+1);
			}
			resetTable();
			comboSize = Character.getNumericValue(s.charAt(0));
			initalizeArray(comboSize);
		}
	}
	
	

	private KeyListener intFieldKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			IntegerField current = (IntegerField) e.getSource();
			if(current.getText().isBlank())return;
			if(!validateFields())return;
			if (ReeveHelper.isValidNumber(current)) {
				if (current.getNumber() < 0) {
					messageBox("Can't have negative numbers");
					current.selectAll();
				} else {
					boolean validSquare = checkSquare();
					if(validSquare && getHighestTotal() == getMagicNumber(comboSize)) {
						messageBox("Congrats! You have made a valid magic square!\n"
								+ "Magic Number: " + getMagicNumber(comboSize));
					}else if (validSquare) {
						messageBox("Congrats! All of the rows, columns and diagonals add up to eachother!");
					}
				}
			}else {
				messageBox("Numbers only please");
			}
		}

	};
	
	
	private FocusListener intFieldFL = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() instanceof IntegerField) {
				IntegerField field = (IntegerField)e.getSource();
				field.selectAll();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {}
		
	};

	public CheckSquareDialog(JFrame parent) {
		super(parent);
		for (int i = 2; i <= 8; i++) {
			String size = i + "x" + i;
			sizeCombo.addItem(size);
		}

		initalizeArray(2);
		this.setResizable(false);
		this.setTitle("Square Checker");
		this.setSize(363, 235);
		this.setVisible(true);
	}

	private void initalizeArray(int size) {
		// TextFields
		fieldArr = new IntegerField[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				IntegerField temp = addIntegerField(0, j + 2, i + 2, 1, 1);
				temp.addKeyListener(intFieldKL);
				temp.addFocusListener(intFieldFL);
				fieldArr[i][j] = temp;
			}
		}

		String base = Format.justify('c', "0", 10);
		// Labels
		rowLabels = new JLabel[size];
		columnLabels = new JLabel[size];
		diagonalLabels = new JLabel[2];
		int bottom = 2;
		for (int i = 0; i < columnLabels.length; i++) {
			JLabel temp = null;
			temp = addLabel(base, size + 2, bottom, 1, 1);
			bottom++;
			temp.setOpaque(true);
			temp.setBackground(Color.GREEN);
			columnLabels[i] = temp;
		}

		int top = 2;
		for (int i = 0; i < rowLabels.length; i++) {
			JLabel temp = null;
			temp = addLabel(base, top, size + 2, 1, 1);
			top++;
			temp.setOpaque(true);
			temp.setBackground(Color.GREEN);
			rowLabels[i] = temp;
		}

		for (int i = 0; i < diagonalLabels.length; i++) {
			JLabel temp = null;
			if (i == 0) {
				temp = addLabel(base, 1, size + 2, 1, 1);
			} else if (i == 1) {
				temp = addLabel(base, size + 2, size + 2, 1, 1);
			}
			temp.setOpaque(true);
			temp.setBackground(Color.GREEN);
			diagonalLabels[i] = temp;
		}
		setWindowSize(size);
	}

	private void setWindowSize(int size) {
		switch (size) {
		case 2:
			this.setSize(363, 235);
			break;
		case 3:
			this.setSize(371, 257);
			break;
		case 4:
			this.setSize(381, 275);
			break;
		case 5:
			this.setSize(434, 344);
			break;
		case 6:
			this.setSize(486, 383);
			break;
		case 7:
			this.setSize(534, 414);
			break;
		case 8:
			this.setSize(593, 463);
			break;
		default:
			this.setSize(383, 225);
			break;
		}

	}

	private void resetTable() {
		// remove TextFields
		for (int i = 0; i < fieldArr.length; i++) {
			for (int j = 0; j < fieldArr.length; j++) {
				if (fieldArr[i][j] != null) {
					this.remove(fieldArr[i][j]);
				}
			}
		}
		fieldArr = null;

		// remove Labels
		for (int i = 0; i < columnLabels.length; i++) {
			remove(columnLabels[i]);
		}
		columnLabels = null;
		
		for (int i = 0; i < rowLabels.length; i++) {
			remove(rowLabels[i]);
		}
		rowLabels = null;
		
		for (int i = 0; i < diagonalLabels.length; i++) {
			remove(diagonalLabels[i]);
		}
		diagonalLabels = null;
	}

	
	private boolean checkSquare() {
		boolean correctSquare = true;
		// row checks
		for (int i = 0; i < fieldArr.length; i++) {
			int total = 0;
			for (int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[j][i].getText();
				if (!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[j][i])) {
					total += fieldArr[j][i].getNumber();
				}
			}
			if(!checkLabel(rowLabels[i],total)) {
				correctSquare = false;
			}
		}

		// column checks
		for (int i = 0; i < fieldArr.length; i++) {
			int total = 0;
			for (int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[i][j].getText();
				if (!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[i][j])) {
					total += fieldArr[i][j].getNumber();
				}
			}
			if(!checkLabel(columnLabels[i],total)) {
				correctSquare = false;
			}
		}

		// diagonal checks
		int diagTopLeftBottomRightTotal = getTotalDiag(1);
		int diagBottomLeftTopRightTotal = getTotalDiag(2);

		if(!checkLabel(diagonalLabels[1],diagTopLeftBottomRightTotal)) {
			correctSquare = false;
		}
		if(!checkLabel(diagonalLabels[0],diagBottomLeftTopRightTotal)) {
			correctSquare = false;
		}
		
		return correctSquare;
	}

	private boolean checkLabel(JLabel label, int total) {
		label.setText(Format.justify('c', total, 10));
		int high = getHighestTotal();
	
		if((total == high)) {
			//correct label
			label.setBackground(Color.GREEN);
			return true;
		}else {
			//incorrect label
			label.setBackground(Color.RED);
			return false;
		}
		
	}
	
	private boolean validateFields() {
		boolean valid = true;
		for(int i = 0; i < fieldArr.length; i++) {
			for(int j = 0; j < fieldArr.length; j++) {
				if(!ReeveHelper.isValidNumber(fieldArr[i][j])) {
					messageBox("invalid input in column " + (i+1) + " row " + (j+1));
					fieldArr[i][j].requestFocusInWindow();
					fieldArr[i][j].selectAll();
					valid = false;
					setAllLabelsError();
				}
			}
		}
		return valid;
	}
	
	
	private void setAllLabelsError() {
		for(int i = 0; i < rowLabels.length; i++) {
			rowLabels[i].setText("Error");
			rowLabels[i].setBackground(Color.RED);
		}
		
		for(int i = 0; i < columnLabels.length; i++) {
			columnLabels[i].setText("Error");
			columnLabels[i].setBackground(Color.RED);
		}
		
		diagonalLabels[0].setText("Error");
		diagonalLabels[0].setBackground(Color.RED);
		
		diagonalLabels[1].setText("Error");
		diagonalLabels[1].setBackground(Color.RED);
		
		
	}

	private int getTotalDiag(int type) {
		int total = 0;
		if (type == 1) {
			int x = 0;
			int y = 0;
			while (x < comboSize && y < comboSize) {
				String text = fieldArr[x][y].getText();
				if (!text.isEmpty()) {
					total += fieldArr[x][y].getNumber();
				}
				x++;
				y++;
			}
		} else if (type == 2) {
			int x = comboSize - 1;
			int y = 0;
			while (x >= 0 && y < comboSize) {
				String text = fieldArr[x][y].getText();
				if (!text.isEmpty()) {
					total += fieldArr[x][y].getNumber();
				}
				x--;
				y++;
			}
		} else {
			return -1;
		}
		return total;
	}
	
	private int getRowTotal() {
		int total = 0;
		int high = 0;
		for (int i = 0; i < fieldArr.length; i++) {
			for (int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[j][i].getText();
				if (!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[j][i])) {
					total += fieldArr[j][i].getNumber();
				}
			}
			if(total > high)high = total;
			total = 0;
		}
		return high;
	}
	
	private int getColumnTotal() {
		int total = 0;
		int high = 0;
		for (int i = 0; i < fieldArr.length; i++) {
			for (int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[i][j].getText();
				if (!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[i][j])) {
					total += fieldArr[i][j].getNumber();
				}
			}
			if(total > high)high = total;
			total = 0;
		}
		return high;
	}
	
	
	private int getHighestTotal() {
		int high = 0;
		int rowTotal, columnTotal, diagonalTotal;
		
		rowTotal = getRowTotal();
		columnTotal = getColumnTotal();
		
		if(getTotalDiag(1) > getTotalDiag(2)) {
			diagonalTotal = getTotalDiag(1);
		}else {
			diagonalTotal = getTotalDiag(2);
		}
		
		if(rowTotal > high)high = rowTotal;
		if(columnTotal > high)high = columnTotal;
		if(diagonalTotal > high)high = diagonalTotal;
		return high;
	}
	
	
	private int getMagicNumber(int x) {
		int high = x * x;
		int low = 1;
		int highx = 0;
		for (int i = 0; i <= x - 1; i++) {
			highx += high - i;
		}
		int lowx = 1;
		for (int i = 0; i <= x - 1; i++) {
			lowx += low + i;
		}
		int average = (highx + lowx) / 2;

		return average;
	}

}