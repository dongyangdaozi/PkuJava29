package wuziqi2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import wuziqi2.Chessboard;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * ������������
	 */
public int[] computerDo() {
		
	
		
		String[][] board = chessboard.getBoard();
		String ico = Chessman.BLACK.getChessman();
		int X=posX;
		int Y=posY;
		int up=0, down=0,left=0, right=0, left_up=0,left_down=0,right_up=0, right_down=0;
		
		int i=1;
		while(posX-i>=0){  
			//�ж�����Ƿ�Ϊ��
			if(board[posX-i][posY].equals(ico))
				left++;
			else
				break;
			i++;
		}
		i=1;
		while(posX-i>=0 && posY-i>=0){  
			//�ж����Ϸ����Ƿ�Ϊ��
			if(board[posX-i][posY-i].equals(ico))
				left_up++;
			else
				break;
			i++;
		}
		i=1;
		while(posY-i>=0){   
			//�ж���ֱ���Ϸ����Ƿ�Ϊ��
			if(board[posX][posY-i].equals(ico))
				up++;
			else
				break;
			i++;
		}
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY-i>=0){  
			//�ж����Ϸ����Ƿ�Ϊ��
			if(board[posX+i][posY-i].equals(ico))
				right_up++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE){   
			//�ж��ұ��Ƿ�Ϊ��
			if(board[posX+i][posY].equals(ico))
				right++;
			else
				break;
			i++;
		}
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY+i<Chessboard.BOARD_SIZE){  
			//�ж����·����Ƿ�Ϊ��
			if(board[posX+i][posY+i].equals(ico))
				right_down++;
			else
				break;
			i++;
		}

		i=1;
		while(posY+i<Chessboard.BOARD_SIZE){ 
			//�ж���ֱ���·����Ƿ�Ϊ��
			if(board[posX][posY+i].equals(ico))
				down++;
			else
				break;
			i++;
		}
		
		
		
		i=1;
		while(posX-i>=0 && posY+i<Chessboard.BOARD_SIZE){ 
			//�ж����·����Ƿ�Ϊ��
			if(board[posX-i][posY+i].equals(ico))
				left_down++;
			else
				break;
			i++;
		}
		
		//�������������
				if((up+down)>=(left+right) && (up+down)>=(left_up+right_down) && (up+down)>=(left_down+right_up))
					if(posY-up-1>=0 && board[posX][posY-up-1].equals("ʮ")){
						X=posX;
						Y=posY-up-1;
					}
					else if(posY+down+1<Chessboard.BOARD_SIZE && board[posX][posY+down+1].equals("ʮ")){
						X=posX;
						Y=posY+down+1;
					}
					else
						;
		
		//�������������
		if((left+right)>=(up+down) && (left+right)>=(left_up+right_down) && (left+right)>=(left_down+right_up))
			if(posX-left-1>=0 && board[posX-left-1][posY].equals("ʮ")){
				 X=posX-left-1;
				 Y=posY;
			}
			else if(posX+right+1<Chessboard.BOARD_SIZE && board[posX+right+1][posY].equals("ʮ")){
				X=posX+right+1;
				Y=posY;
			}
			else ;

		//������µ�����������
				if((left_down+right_up)>=(left+right) && (left_down+right_up)>=(up+down) && (left_down+right_up)>=(left_up+right_down)){
					if(posX-left_down-1>=0 && posY+left_down+1<Chessboard.BOARD_SIZE && board[posX-left_down-1][posY+left_down+1].equals("ʮ")){
						X=posX-left_down-1;
						Y=posY+left_down+1;
					}
					else if(posX+right_up+1<Chessboard.BOARD_SIZE && posY-right_up-1>=0 && board[posX+right_up+1][posY-right_up-1].equals("ʮ")){
						X=posX+right_up+1;
						Y=posY-right_up-1;
					}
					else
					    ;
				}
				
		//������ϵ�����������
		if((left_up+right_down)>=(left+right) && (left_up+right_down)>=(up+down) && (left_up+right_down)>=(left_down+right_up)){
			if(posX-left_up-1>=0 && posY-left_up-1>=0 && board[posX-left_up-1][posY-left_up-1].equals("ʮ")){
				X=posX-left_up-1;
				Y=posY-left_up-1;
			}
			else if(posX+right_down+1<Chessboard.BOARD_SIZE && posY+right_down+1<Chessboard.BOARD_SIZE && board[posX+right_down+1][posY+right_down+1].equals("ʮ")){
				X=posX+right_down+1;
				Y=posY+right_down+1;
			}
			else
				;
		}
		
		if(X==posX && Y==posY){
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			while (board[posX][posY] != "ʮ") {
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
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
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
