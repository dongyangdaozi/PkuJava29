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
	/*public int[] computerDo() {
        
		
	int i=posX;
	int j=posY;
	int[] result2=new int[2];
	String ico = Chessman.BLACK.getChessman();
	String[][] board=chessboard.getBoard();
	int count ;
	int max=0;
	int flag=1;
	{
	    count=1;
	    i=posX+1;
	    j=posY+1;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
			if(max<count){max=count;flag=1;}
		    i++;j++;
	    }
		i=posX-1;
	    j=posY-1;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=5;}
		    i--;j--;
	    }
    }
	{
	    count=1;
	    i=posX+1;
	    j=posY-11;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=3;}
		    i++;j--;
	    }
		i=posX-1;
	    j=posY+1;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=7;}
		    i--;j++;
	    }
    }
	{
	    count=1;
	    i=posX+1;
	    j=posY;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=2;}
		    i++;
	    }
		i=posX-1;
	    j=posY;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=6;}
		    i--;
	    }
    }
	{
	    count=1;
	    i=posX;
	    j=posY+1;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=8;}
		    j++;
	    }
		i=posX;
	    j=posY-1;
	    while(i>=0&&i<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals(ico)){
	        count++;
		    if(max<count){max=count;flag=4;};
		    j--;
	    }
    }
	switch(flag){
	    case 1:
		    if(i-1>=0&&i-1<Chessboard.BOARD_SIZE&&j-1>=0&&j-1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i-1;
				posY=j-1;
				int []result = { posX, posY };result2=result;
	           // return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	           // String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           // return result;
			}
			break;
		case 2:
		    if(i-1>=0&&i-1<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i-1;
				int[] result = { posX, posY };result2=result;
	           // return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	           // String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           // return result;
			}
			break;
		case 3:
		    if(i-1>=0&&i-1<Chessboard.BOARD_SIZE&&j+1>=0&&j+1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i-1;
				posY=j+1;
				int[] result = { posX, posY };result2=result;
	          //  return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	         //   String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           // return result;
			}
			break;
		case 4:
		    if(i>=0&&i<Chessboard.BOARD_SIZE&&j+1>=0&&j-1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
				posY=j+1;
				int[] result = { posX, posY };result2=result;
	           // return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	        //    String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           // return result;
			}
			break;
		case 5:
		    if(i+1>=0&&i+1<Chessboard.BOARD_SIZE&&j+1>=0&&j+1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i+1;
				posY=j+1;
				int[] result = { posX, posY };result2=result;
	           // return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	         //   String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           // return result;
			}
			break;
		case 6:
		    if(i+1>=0&&i+1<Chessboard.BOARD_SIZE&&j>=0&&j<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i+1;
				int[] result = { posX, posY };result2=result;
	          //  return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	           // String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	          //  return result;
			}
			break;
		case 7:
		    if(i+1>=0&&i+1<Chessboard.BOARD_SIZE&&j-1>=0&&j-1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
			    posX=i+1;
				posY=j-1;
				int[] result = { posX, posY };result2=result;
	          //  return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	          //  String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	            //return result;
			}
			break;
		case 8:
		    if(i>=0&&i<Chessboard.BOARD_SIZE&&j-1>=0&&j-1<Chessboard.BOARD_SIZE&&board[i][j].equals("ʮ")){
				posY=j-1;
				int[] result = { posX, posY };result2=result;
	          //  return result;
			}
			else{
			    posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	          //  String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };
	            result2=result;
	           // return result;
			}
			break;
		default:
		        posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	         //   String[][] board = chessboard.getBoard();
	            while (board[posX][posY] != "ʮ") {
		            posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		            posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
	            }
	            int[] result = { posX, posY };result2=result;
	           //return result;
				break;
	}
	return result2;
}*/
	
	
	public int[] computerDo() {
		
		//int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		//String[][] board = chessboard.getBoard();
//		while (board[posX][posY] != "ʮ") {
//			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		}
//		int[] result = { posX, posY };
		
		String[][] board = chessboard.getBoard();
		String ico = Chessman.BLACK.getChessman();
		int X=posX;
		int Y=posY;
		int up=0;  //��
		int down=0; //��
		int left=0; //��
		int right=0;  //��
		int zuoshang=0;//����
		int zuoxia=0;//����
		int youshang=0;//����
		int youxia=0;//����
		
		int i=1;
		while(posX-i>=0){  //�ж����
			if(board[posX-i][posY].equals(ico))
				left++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE){   //�ж��ұ�
			if(board[posX+i][posY].equals(ico))
				right++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posY-i>=0){   //�ж���ֱ���Ϸ���
			if(board[posX][posY-i].equals(ico))
				up++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posY+i<Chessboard.BOARD_SIZE){  //�ж���ֱ���·���
			if(board[posX][posY+i].equals(ico))
				down++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX-i>=0 && posY-i>=0){  //�ж����Ϸ���
			if(board[posX-i][posY-i].equals(ico))
				zuoshang++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX-i>=0 && posY+i<Chessboard.BOARD_SIZE){  //�ж����·���
			if(board[posX-i][posY+i].equals(ico))
				zuoxia++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY-i>=0){  //�ж����Ϸ���
			if(board[posX+i][posY-i].equals(ico))
				youshang++;
			else
				break;
			i++;
		}
		
		i=1;
		while(posX+i<Chessboard.BOARD_SIZE && posY+i<Chessboard.BOARD_SIZE){  //�ж����·���
			if(board[posX+i][posY+i].equals(ico))
				youxia++;
			else
				break;
			i++;
		}
		//��������
		if((left+right)>=(up+down) && (left+right)>=(zuoshang+youxia) && (left+right)>=(zuoxia+youshang))
			if(posX-left-1>=0 && board[posX-left-1][posY].equals("ʮ")){
				 X=posX-left-1;
				 Y=posY;
			}
			else if(posX+right+1<Chessboard.BOARD_SIZE && board[posX+right+1][posY].equals("ʮ")){
				X=posX+right+1;
				Y=posY;
			}
			else ;
//				posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//				posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//				while (board[posX][posY] != "ʮ") {
//					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//				}
//				int[] result = { posX, posY };
				
		//��������
		if((up+down)>=(left+right) && (up+down)>=(zuoshang+youxia) && (up+down)>=(zuoxia+youshang))
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
		//������ϵ������
		if((zuoshang+youxia)>=(left+right) && (zuoshang+youxia)>=(up+down) && (zuoshang+youxia)>=(zuoxia+youshang)){
			if(posX-zuoshang-1>=0 && posY-zuoshang-1>=0 && board[posX-zuoshang-1][posY-zuoshang-1].equals("ʮ")){
				X=posX-zuoshang-1;
				Y=posY-zuoshang-1;
			}
			else if(posX+youxia+1<Chessboard.BOARD_SIZE && posY+youxia+1<Chessboard.BOARD_SIZE && board[posX+youxia+1][posY+youxia+1].equals("ʮ")){
				X=posX+youxia+1;
				Y=posY+youxia+1;
			}
			else
				;
		}
		//������µ������
		if((zuoxia+youshang)>=(left+right) && (zuoxia+youshang)>=(up+down) && (zuoxia+youshang)>=(zuoshang+youxia)){
			if(posX-zuoxia-1>=0 && posY+zuoxia+1<Chessboard.BOARD_SIZE && board[posX-zuoxia-1][posY+zuoxia+1].equals("ʮ")){
				X=posX-zuoxia-1;
				Y=posY+zuoxia+1;
			}
			else if(posX+youshang+1<Chessboard.BOARD_SIZE && posY-youshang-1>=0 && board[posX+youshang+1][posY-youshang-1].equals("ʮ")){
				X=posX+youshang+1;
				Y=posY-youshang-1;
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
