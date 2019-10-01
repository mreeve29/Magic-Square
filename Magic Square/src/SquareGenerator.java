import BreezySwing.Format;

public class SquareGenerator {
	private int num;
	private int[][] square;
	private int[] highs;
	private int[] lows;
	private int[] between;
	
	public SquareGenerator(int x) {
		num = x;
		square = new int[x][x];
		highs = getHighs();
		lows = getLows();
		between = new int[num*num];
		for(int i = 0; i < num*num; i++) {
			between[i] = i+1;
		}
		populateTest2();
	}
	
	private void populate() {
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				int currentRand;
				do {
					currentRand =  ReeveHelper.random(1, num*num);
				}while(ReeveHelper.contains(square, currentRand));
				square[i][j] = currentRand;
			}
		}
	}
	
	
	private void populateTest() {
		for(int i = 0; i < num; i++) {
			boolean low = false ,high = false;
			for(int j = 0; j < num; j++) {
				if(Math.random() > 0.5 && !low) {
					System.out.println("Low i= " + i + " j= " + j);
					//pick from low
					low = true;
					square[i][j] = lows[i];
				}else if(!high){
					//pick from high
					System.out.println("High i= " + i + " j= " + j);
					high = true;
					square[i][j] = highs[i];
				}else if(high && low) {
					System.out.println("Random i= " + i + " j= " + j);
					//random num --> replace w/ 0 for now
					square[i][j] = 0;
				}
			}
		}
	}
	
	
	private void populateTest2() {
		int x = 0;
		int y = num/2;
		for(int i = 0; i < num*num; i++) {
			if(i == 0) {
				square[x][y] = between[i];
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
			System.out.println(i + " --> (" + x + "," + y + ")");
			square[x][y] = between[i];
			
		}
	}
	
	public String getSquareString() {
		String str = "";
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				str += Format.justify('c',square[i][j], 4);
			}
			str += '\n';
		}
		return str;
	}
	
	public int[][] getSquareArray(){
		return square;
	}
	
	
	private int[] getHighs() {
		int[] highs = new int[num];
		for(int i = 0; i < highs.length; i++) {
			highs[i] = (num*num) - i;
		}
		return highs;
	}
	
	private int[] getLows() {
		int[] lows = new int[num];
		for(int i = 0; i < lows.length; i++) {
			lows[i] = 1 + i;
		}
		return lows;
	}
	
	
}
