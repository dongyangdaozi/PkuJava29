package wuziqi2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import wuziqi2.Chessboard;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**
	 * 计算机随机下棋
	 */
public int[] computerDo() {
		
	
		
		String[][] board = chessboard.getBoard();
		String ico = Chessman.BLACK.getChessman();
		int X=posX;
		int Y=posY;
		int up=0, down=0,left=0, right=0, left_up=0,left_down=0,right_up=0, right_down=0;
		
		int i=1;
		while(posX-i>=0){  
			//判断左边是否为空
			if(board[posX-i][posY].equals(ico))
				left++;
			else
				break;
			i++;
		}
		i=1;
		while(posX-i>=0 && posY-i>=0){  
			//判断左上方向是否为空
			if(board[posX-i][posY-i].equals(ico))
				left_up++;
			else
				break;
			i++;
		}
		i=1;
		while(posY-i>=0){   
			//判断竖直向上方向是否为空
			if(board[posX][posY-i].equals(ico))
				up++;
			else
				break;
			i++;
		}
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY-i>=0){  
			//判断右上方向是否为空
			if(board[posX+i][posY-i].equals(ico))
				right_up++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE){   
			//判断右边是否为空
			if(board[posX+i][posY].equals(ico))
				right++;
			else
				break;
			i++;
		}
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY+i<Chessboard.BOARD_SIZE){  
			//判断右下方向是否为空
			if(board[posX+i][posY+i].equals(ico))
				right_down++;
			else
				break;
			i++;
		}

		i=1;
		while(posY+i<Chessboard.BOARD_SIZE){ 
			//判断竖直向下方向是否为空
			if(board[posX][posY+i].equals(ico))
				down++;
			else
				break;
			i++;
		}
		
		
		
		i=1;
		while(posX-i>=0 && posY+i<Chessboard.BOARD_SIZE){ 
			//判断左下方向是否为空
			if(board[posX-i][posY+i].equals(ico))
				left_down++;
			else
				break;
			i++;
		}
		
		//如果纵向最长则防守
				if((up+down)>=(left+right) && (up+down)>=(left_up+right_down) && (up+down)>=(left_down+right_up))
					if(posY-up-1>=0 && board[posX][posY-up-1].equals("十")){
						X=posX;
						Y=posY-up-1;
					}
					else if(posY+down+1<Chessboard.BOARD_SIZE && board[posX][posY+down+1].equals("十")){
						X=posX;
						Y=posY+down+1;
					}
					else
						;
		
		//如果行向最长则防守
		if((left+right)>=(up+down) && (left+right)>=(left_up+right_down) && (left+right)>=(left_down+right_up))
			if(posX-left-1>=0 && board[posX-left-1][posY].equals("十")){
				 X=posX-left-1;
				 Y=posY;
			}
			else if(posX+right+1<Chessboard.BOARD_SIZE && board[posX+right+1][posY].equals("十")){
				X=posX+right+1;
				Y=posY;
			}
			else ;

		//如果左下到右上最长则防守
				if((left_down+right_up)>=(left+right) && (left_down+right_up)>=(up+down) && (left_down+right_up)>=(left_up+right_down)){
					if(posX-left_down-1>=0 && posY+left_down+1<Chessboard.BOARD_SIZE && board[posX-left_down-1][posY+left_down+1].equals("十")){
						X=posX-left_down-1;
						Y=posY+left_down+1;
					}
					else if(posX+right_up+1<Chessboard.BOARD_SIZE && posY-right_up-1>=0 && board[posX+right_up+1][posY-right_up-1].equals("十")){
						X=posX+right_up+1;
						Y=posY-right_up-1;
					}
					else
					    ;
				}
				
		//如果左上到右下最长则防守
		if((left_up+right_down)>=(left+right) && (left_up+right_down)>=(up+down) && (left_up+right_down)>=(left_down+right_up)){
			if(posX-left_up-1>=0 && posY-left_up-1>=0 && board[posX-left_up-1][posY-left_up-1].equals("十")){
				X=posX-left_up-1;
				Y=posY-left_up-1;
			}
			else if(posX+right_down+1<Chessboard.BOARD_SIZE && posY+right_down+1<Chessboard.BOARD_SIZE && board[posX+right_down+1][posY+right_down+1].equals("十")){
				X=posX+right_down+1;
				Y=posY+right_down+1;
			}
			else
				;
		}
		
		if(X==posX && Y==posY){
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			while (board[posX][posY] != "十") {
				posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
				posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			}
		}
		else{
			posX=X;
			posY=Y;
			
		}
		int[] result={posX, posY };
		return result;
	}


	
	

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board=chessboard.getBoard();
		int count ;
		int i,j;
		{
		    count=1;
		    i=posX+1;
		    j=posY+1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i++;j++;
		    }
			i=posX-1;
		    j=posY-1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i--;j--;
		    }
	    }
		{
		    count=1;
		    i=posX+1;
		    j=posY-1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i++;j--;
		    }
			i=posX-1;
		    j=posY+1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i--;j++;
		    }
	    }
		{
		    count=1;
		    i=posX+1;
		    j=posY;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i++;
		    }
			i=posX-1;
		    j=posY;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    i--;
		    }
	    }
		{
		    count=1;
		    i=posX;
		    j=posY+1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    j++;
		    }
			i=posX;
		    j=posY-1;
		    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
		        count++;
			    if(count==WIN_COUNT)return true;
			    j--;
		    }
	    }
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
