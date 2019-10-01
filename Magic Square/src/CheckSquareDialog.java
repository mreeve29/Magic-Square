import BreezySwing.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class CheckSquareDialog extends GBDialog{
	
	JComboBox sizeCombo = addComboBox(1,1,1,1);
	JButton buildButton = addButton("Build",2,1,1,1);
	JTextField[][] fieldArr;
	
	JLabel[] rowLabels;
	JLabel[] columnLabels;
	JLabel[] diagonalLabels;
	
	
	private int comboSize = 2;
	
	public void buttonClicked(JButton button) {
		if(button == buildButton) {
			String s = (String) sizeCombo.getSelectedItem();
			if(Character.getNumericValue(s.charAt(0)) == comboSize) {
				return;
			}
			resetTable();
			comboSize = Character.getNumericValue(s.charAt(0));
			initalizeArray(comboSize);
		}
	}
	
	private KeyListener textFieldKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField current = (JTextField)e.getSource();
			if(ReeveHelper.isValidNumber(current)) {
				if(Integer.parseInt(current.getText()) < 1) {
					messageBox("Can't have negative numbers or zeros");
				}else {
					checkSquare();
				}
			}
		}
		
	};
	

	
	public CheckSquareDialog(JFrame parent) {
		super(parent);	
		for(int i = 2; i <= 8; i++) {
			String size = i + "x" + i;
			sizeCombo.addItem(size);
		}	
	
		initalizeArray(2);
		this.setResizable(false);
		this.setTitle("Square Checker");
		this.setSize(323,195);
		this.setVisible(true);
	}
	private void initalizeArray(int size) {
		//TextFields
		fieldArr = new JTextField[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				//JTextField temp = addTextField(i + " " + j,j+2,i+2,1,1);
				JTextField temp = addTextField("",j+2,i+2,1,1);
				temp.addKeyListener(textFieldKL);
				fieldArr[i][j] = temp;
			}
		}
	
		
		//Labels
		rowLabels = new JLabel[size];
		columnLabels = new JLabel[size];
		diagonalLabels = new JLabel[2];	
		int bottom = 2;
		for(int i = 0; i < columnLabels.length; i++) {
			JLabel temp = null;
			temp = addLabel("column",size+2,bottom,1,1);
			bottom++;
			temp.setOpaque(true);
			temp.setBackground(Color.RED);
			columnLabels[i] = temp;
		}
		
		
		int top = 2;
		for(int i = 0; i < rowLabels.length; i++) {
			JLabel temp = null;
			temp = addLabel("row",top,size+2,1,1);
			top++;
			temp.setOpaque(true);
			temp.setBackground(Color.RED);
			rowLabels[i] = temp;
		}
		
		for(int i = 0; i < diagonalLabels.length; i++) {
			JLabel temp = null;
			if(i == 0) {
				temp = addLabel("total1",1,size+2,1,1);
			}else if(i == 1) {
				temp = addLabel("total2",size+2,size+2,1,1);
			}
			temp.setOpaque(true);
			temp.setBackground(Color.RED);
			diagonalLabels[i] = temp;
		}
		
		
		
		setWindowSize(size);
		
	}
	
	private void setWindowSize(int size) {
		switch(size) {
		case 2:
			this.setSize(323,195);
			break;
		case 3:
			this.setSize(331,217);
			break;
		case 4:
			this.setSize(341,235);
			break;
		case 5:
			this.setSize(382,281);
			break;
		case 6:
			this.setSize(408,314);
			break;
		case 7:
			this.setSize(434,342);
			break;
		case 8:
			this.setSize(468,368);
			break;
		default:
			this.setSize(323,195);
			break;
		}
		
	}
	private void resetTable() {
		for(int i = 0; i < fieldArr.length; i++) {
			for(int j = 0; j < fieldArr.length; j++) {
				if(fieldArr[i][j] != null) {
					this.remove(fieldArr[i][j]);
				}
			}
		}
		fieldArr = null;
		
		for(int i = 0; i < columnLabels.length; i++) {
			remove(columnLabels[i]);
		}
		columnLabels = null;
		for(int i = 0; i < rowLabels.length; i++) {
			remove(rowLabels[i]);
		}
		rowLabels = null;
		for(int i = 0; i < diagonalLabels.length; i++) {
			remove(diagonalLabels[i]);
		}
		diagonalLabels = null;
	}
	
	private void checkSquare() {
		int magicNum = getMagicNumber(comboSize);
		//row checks
		for(int i = 0; i < fieldArr.length; i++) {
			int total = 0;
			for(int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[j][i].getText();
				if(!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[j][i])) {
					total+=Integer.parseInt(fieldArr[j][i].getText());
				}
				updateTotalLabel(rowLabels[i],total,magicNum);
			}
		}
		
		
		//column checks
		for(int i = 0; i < fieldArr.length; i++) {
			int total = 0;
			for(int j = 0; j < fieldArr.length; j++) {
				String text = fieldArr[i][j].getText();
				if(!text.isEmpty() && ReeveHelper.isValidNumber(fieldArr[i][j])) {
					total+=Integer.parseInt(fieldArr[i][j].getText());
				}
				updateTotalLabel(columnLabels[i],total,magicNum);
			}
		}
		
		
		
		//diagonal checks
		int diagTopLeftBottomRightTotal = getTotalDiag(1);
		int diagBottomLeftTopRightTotal = getTotalDiag(2);
		
		updateTotalLabel(diagonalLabels[1],diagTopLeftBottomRightTotal,magicNum);
		updateTotalLabel(diagonalLabels[0],diagBottomLeftTopRightTotal,magicNum);
		
		
	}
	
	private int getTotalDiag(int type) {
		int total = 0;
		if(type == 1) {
			int x = 0;
			int y = 0;
			while(x < comboSize && y < comboSize) {
				String text = fieldArr[x][y].getText();
				if(!text.isEmpty()) {
					total+=Integer.parseInt(text);
				}
				x++;
				y++;
			}
		}else if(type == 2) {
			int x = comboSize-1;
			int y = 0;
			while(x >= 0 && y < comboSize) {
				String text = fieldArr[x][y].getText();
				if(!text.isEmpty()) {
					total+=Integer.parseInt(text);
				}
				x--;
				y++;
			}
		}else {
			return -1;
		}
		return total;
	}
	
	
	private void updateTotalLabel(JLabel label, int total, int magic) {
		label.setText(Format.justify('c', total, 10));
		if(total == magic) {
			label.setBackground(Color.GREEN);
		}else {
			label.setBackground(Color.RED);
		}
	}
	
	private int getMagicNumber(int x) {
		int high = x*x;
		int low = 1;
		int highx = 0;
		for(int i = 0; i <= x-1; i++) {
			highx += high - i;
		}
		int lowx = 1;
		for(int i = 0; i <= x-1; i++) {
			lowx += low + i;
		}
		int average = (highx + lowx)/2;

		return average;
	}
	
}
