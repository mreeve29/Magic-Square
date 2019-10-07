import BreezySwing.Format;

public class SquareGenerator {
	private int num;
	private int start;
	private int[][] square;
	
	public SquareGenerator(int size, int start) {
		num = size;
		square = new int[size][size];
		this.start = start;
		populateIntArr();
	}
	
	private void populateIntArr() {
		int x = 0;
		int y = num/2;
		for(int i = 0; i < num*num; i++) {
			if(i == 0) {
				square[x][y] = i+start;
				continue;
			}
			if(x == 0 && y == num-1) {
				x+=1;
			}else if(x==0) {
				y +=1;
				x = num-1;
			}else if(y==num-1) {
				x-=1;
				y=0;
			}else if(square[x-1][y+1] != 0) {
				x+=1;
			}else {
				x-=1;
				y+=1;
			}
			square[x][y] = i+start;
		}
	}
	
	public String getSquareString() {
		String str = "";
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				str += Format.justify('l',square[i][j], 4);
			}
			str += '\n';
		}
		return str;
	}
	
	public int[][] getSquareArray(){
		return square;
	}
	
	
}
//comment
