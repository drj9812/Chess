package model;

import java.util.ArrayList;
import java.util.List;

//MVC���� MODEL�� ����ϴ� Ŭ����
public class ChessBoard {
	// field
	private static final int BOARD_SIZE = 8;
	public static ChessPiece[][] chessBoard = new ChessPiece[BOARD_SIZE][BOARD_SIZE]; // cv�� �ұ� iv�� �ұ�, ������ �ʵ忡�� �ϴ� ����
																						// ��� �����

	// Player ��ü�� static���� �����ϸ� ChessBoardŬ������ ������ �����Ǿ �ϳ��� Player���� ����Ű�� ��
	public static Player whitePlayer;
	public static Player blackPlayer;

//	 //true�� ���� üũ �������� �˸��ϴ�.
//	private static boolean isWhiteKingChecked;
//	private static boolean isBlackKingChecked;

	private static List<ChessPiece> pieces;
	// static���� �����ϸ� Ŭ���� ������ �Ǿ�, �ش� Ŭ������ ��� ��ü���� �����˴ϴ�.
	// ��, ChessBoard Ŭ������ ��� ��ü�� ���� Player ��ü�� ����ϰ� �Ǹ�, �̴� ��ü ���� ���α׷����� ĸ��ȭ�� ���ȭ ��Ģ��
	// �����ϰ� �˴ϴ�.
	// ����, ChessBoard Ŭ������ Player ��ü�� static���� �������� �ʰ�, �ν��Ͻ� ������ �����ϴ� ���� �ٶ����մϴ�.
	// �̷��� �ϸ� ChessBoard Ŭ������ �� ��ü�� ���������� Player ��ü�� ����� �� �ְ� �˴ϴ�.

//	//detectCheck�޼��忡�� getKing()�޼��带 ȣ���ϱ� ���� static���� ����
//	//TODO ���������� �� �� ������ ����
//	static King whiteKing = new King("white", 4, 7);
//	static King blackKing = new King("black", 4, 0);
	// constructor
	public ChessBoard() {
//		isWhiteKingChecked = false;
//	    isBlackKingChecked = false;
		initialize();
	}

//	������ �ʱ�ȭ�� å���� �𵨿� �ֽ��ϴ�. �� �⹰���� �ʱ� ��ġ�� ü�� ���ӿ��� �߿��� �������Դϴ�. 
//	�� ������ �ʱ�ȭ�� å���� �𵨿� ������, �̸� ��Ʈ�ѷ��� �信�� ó���ϸ� ������ ��Ģ�� ����˴ϴ�. 
//	���� chessBoard Ŭ�������� ���� �� �⹰���� ��ġ�� �������ִ� ���� ���� å������ ���� ���� �ùٸ��ϴ�.

	private void initialize() {

//		 White pieces
		chessBoard[7][0] = new Rook("white", 0, 7);
		chessBoard[7][1] = new Knight("white", 1, 7);
		chessBoard[7][2] = new Bishop("white", 2, 7);
		chessBoard[7][3] = new Queen("white", 3, 7);
		chessBoard[7][4] = new King("white", 4, 7);
		chessBoard[7][5] = new Bishop("white", 5, 7);
		chessBoard[7][6] = new Knight("white", 6, 7);
		chessBoard[7][7] = new Rook("white", 7, 7);
		for (int y = 0; y < BOARD_SIZE; y++) {
			chessBoard[6][y] = new Pawn("white", y, 6);
		}

		// Player ��ü�� �����ϴ� ���� �ſ� �����ϹǷ� ���� ������ �����ϴ� ���� �� ���� ���Դϴ�.
		// �̷��� �ϸ� �ٸ� �޼��忡�� ������ �� ���� ������ ĸ��ȭ�� �� ��ȭ�Ǹ� �ڵ��� �������� ���˴ϴ�.
		// ���� ������ ���� ���� ������ �����ϴ� ���� �����ϴ�.
//		Player whitePlayer = new Player("white");
		whitePlayer = new Player("white");
		whitePlayer.setTurn(true);

		// Black pieces
		chessBoard[0][0] = new Rook("black", 0, 0);
		chessBoard[0][1] = new Knight("black", 1, 0);
		chessBoard[0][2] = new Bishop("black", 2, 0);
		chessBoard[0][3] = new Queen("black", 3, 0);
		chessBoard[0][4] = new King("black", 4, 0);
		chessBoard[0][5] = new Bishop("black", 5, 0);
		chessBoard[0][6] = new Knight("black", 6, 0);
		chessBoard[0][7] = new Rook("black", 7, 0);
		for (int x = 0; x < BOARD_SIZE; x++) {
			chessBoard[1][x] = new Pawn("black", x, 1);
		}

//		Player blackPlayer = new Player("black");
		blackPlayer = new Player("black");
		blackPlayer.setTurn(false);

	}

	// getter && setter
//	/**
//	 * ��� ŷ�� üũ���¸� ��ȯ�մϴ�.
//	 * 
//	 * @return ��� ŷ�� üũ����
//	 */
//    public boolean isWhiteKingChecked() {
//        return isWhiteKingChecked;
//    }
//    /**
//     * ��� ŷ�� üũ���¸� ��ȯ�մϴ�.
//     * 
//     * @return ��� ŷ�� üũ����
//     */
//    public boolean isBlackKingChecked() {
//        return isBlackKingChecked;
//    }

	/**
	 * ���� ü�����忡�� �ش� ��ġ�� �ִ� ü�� �⹰�� ��ȯ�մϴ�.
	 * 
	 * @param posX ü�� �⹰�� X ��ǥ
	 * @param posY ü�� �⹰�� Y ��ǥ
	 * @return �ش� ��ġ�� �ִ� ü�� �⹰ ��ü
	 */
	public static ChessPiece getPiece(int posX, int posY) { // DaoImpl, ChessPiece�� �����ϴ� Ŭ�������� ���Ǳ� ������ static���� ����
		return chessBoard[posY][posX];
	}

	/**
	 * ���� ü�����忡 �����ϴ� �÷��̾��� ��� �⹰���� ����Ʈ�� �����մϴ�.
	 * 
	 * @param player �÷��̾�
	 * @return �⹰�� ������ ����Ʈ
	 */
	public static List<ChessPiece> getPieces(Player player) {
		pieces = new ArrayList<>();

		for (int y = 0; y < ChessBoard.chessBoard.length; y++) {
			for (int x = 0; x < ChessBoard.chessBoard[y].length; x++) {
				ChessPiece piece = getPiece(x, y);

				if (piece != null && piece.getColor().equals(player.getColor())) {
					pieces.add(piece);
				}
			}
		}

		return pieces;
	}

	/**
	 * �ش� �÷��̾��� ŷ �⹰�� ã�� ��ȯ�մϴ�.
	 * 
	 * @param player �÷��̾�
	 * @return �ش� �÷��̾��� ŷ �⹰
	 */
	public static King getKing(Player player) {
		List<ChessPiece> pieces = getPieces(player);

		for (ChessPiece piece : pieces) {
			if (piece instanceof King) {
				return (King) piece;
			}
		}
		return null;
	}

	/**
	 * ���� ü�����忡�� �ش� ��ġ�� �ִ� ü�� �⹰�� �־��� �⹰�� ��ü�մϴ�.
	 * 
	 * @param piece   ��ü�� ü�� �⹰ ��ü
	 * @param moveToX �⹰�� �̵��� X ��ǥ
	 * @param moveToY �⹰�� �̵��� Y ��ǥ
	 */
	public static void setPiece(ChessPiece piece, int moveToX, int moveToY) { // DaoImpl, ChessPiece�� �����ϴ� Ŭ�������� ���Ǳ�
																				// ������
		// static���� ����
		chessBoard[moveToY][moveToX] = piece;
	}

	/**
	 * �ش� ��ġ�� ü������ ���� ���� �ִ��� Ȯ���ϴ� �޼����Դϴ�.
	 * 
	 * @param moveToX �̵��� X ��ǥ
	 * @param moveToY �̵��� Y ��ǥ
	 * @return ��ġ�� ü���� ���� ������ true, �׷��� ������ false�� ��ȯ�մϴ�.
	 */
	public static boolean isValidChessCoordinate(int moveToX, int moveToY) {
		if ((moveToX < 0 || moveToX > 7) || (moveToY < 0 || moveToY > 7)) {
			return false;
		}
		return true;
	}

	/**
	 * �ش� �÷��̾��� ŷ�� ������� ������ �ް� �ִ��� Ȯ���ϴ� �޼����Դϴ�. ���� ü���ǿ��� �ش� �÷��̾��� ��(King)�� ��ġ�� ã��,
	 * �ٸ� �÷��̾��� �� �� �ش� ���� ���� ������ ���� �ִ��� Ȯ���մϴ�.
	 * 
	 * @param player Ȯ���ϰ��� �ϴ� �÷��̾��� ���� (white �Ǵ� black)
	 * @return �ش� �÷��̾ üũ ������ ��� true, �ƴ� ��� false
	 */
	public static boolean detectCheck(Player player) {
		King king = getKing(player);

		// ��밡 ������� King�� ������ �� �ִ��� Ȯ��
		Player opponent = player.getColor().equals("white") ? blackPlayer : whitePlayer;

		List<ChessPiece> opponentPieces = getPieces(opponent);

		for (ChessPiece piece : opponentPieces) { // ��� = ��
			if (piece.isValidMove(king.getXPosition(), king.getYPosition())) {
				player.setChecked(true);
				return true;
			}
		}

		return false;
	}

	/**
	 * �ش� �⹰�� �̵������� �� üũ ���¸� �����ϴ��� Ȯ���ϴ� �޼����Դϴ�.
	 * 
	 * @param player  �⹰�� �̵���ų �÷��̾�
	 * @param posX    �⹰�� ���� x��ǥ
	 * @param posY    �⹰�� ���� y��ǥ
	 * @param moveToX �⹰�� �̵���ų x��ǥ
	 * @param moveToY �⹰�� �̵���ų y��ǥ
	 * @return �ش� �⹰�� �̵������� �� üũ ���¸� �����ϴ��� ����
	 */
	public static boolean isMoveCausingCheck(Player player, int posX, int posY, int moveToX, int moveToY) {
		ChessPiece piece = getPiece(posX, posY);
		ChessPiece opponentPiece = getPiece(moveToX, moveToY);
//		System.out.println(piece);
//		System.out.println(opponentPiece);
		if (!piece.isValidMove(moveToX, moveToY)) {
			return false;
		}
		// �⹰�� �̵���Ű��, üũ �������� Ȯ���մϴ�.
		setPiece(null, posX, posY);
		setPiece(piece, moveToX, moveToY);

		if (opponentPiece == null) {
			if (detectCheck(player)) { // üũ ������ ���, �⹰�� �̵���Ű�� �ʰ� true�� ��ȯ�մϴ�.
				setPiece(null, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return true;

			} else { // üũ ���°� �ƴ� ���, �⹰�� �̵���Ű�� �ʰ� false�� ��ȯ�մϴ�.
				setPiece(null, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return false;
			}
		} else {
			if (detectCheck(player)) { // üũ ������ ���, �⹰�� �̵���Ű�� �ʰ� true�� ��ȯ�մϴ�.
				setPiece(null, moveToX, moveToY);
				setPiece(opponentPiece, moveToX, moveToY);
				setPiece(piece, posX, posY);
				
				return true;

			} else { // üũ ���°� �ƴ� ���, �⹰�� �̵���Ű�� �ʰ� false�� ��ȯ�մϴ�.
				setPiece(null, moveToX, moveToY);
				setPiece(opponentPiece, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return false;
			}
		}
	}

//	// �ش� ��ο� ���� �ٸ� �⹰�� ������ true, ������ false�� ��ȯ
//	private static boolean isPathBlockedByOtherPiece(Player player, int posX, int posY, int moveToX, int moveToY) {
//	    int dx = Integer.compare(posX, moveToX);
//	    int dy = Integer.compare(posY, moveToY);
//	    int x = posX + dx;
//	    int y = posY + dy;
//	    while (x != moveToX || y != moveToY) {
//	        ChessPiece piece = getPiece(x, y);
//	        if (piece != null && !piece.getColor().equals(player.getColor())) {
//	            return true;
//	        }
//	        x += dx;
//	        y += dy;
//	    }
//	    return false;
//	}

	// üũ����Ʈ ���� �޼���
	public static boolean isCheckmate(Player player) {

		// üũ ���°� �ƴϸ� üũ����Ʈ�� �ƴ�
		if (!detectCheck(player)) {
			return false;
		}

		// üũ ������ ŷ ã��
		King king = getKing(player);

		// üũ ������ ŷ�� �̵��� �� �ִ��� üũ
		boolean checkmate = true;
		for (int x = king.getXPosition() - 1; x <= king.getXPosition() + 1; x++) {
			for (int y = king.getYPosition() - 1; y <= king.getYPosition() + 1; y++) {
				if ((isValidChessCoordinate(x, y)) && (x != king.getXPosition() || y != king.getYPosition())) {
					if (getPiece(x, y) == null || !getPiece(x, y).getColor().equals(king.getColor())) {

						// �̵� �ùķ��̼�
						setPiece(null, king.getXPosition(), king.getYPosition());
						setPiece(king, x, y);

						// üũ�� Ǯ �� �ִ� �⹰�� �ִٸ� üũ����Ʈ �ƴ�
						boolean isCheck = detectCheck(player);
						if (!isCheck) {
							checkmate = false;
						}

						// �ٽ� ���� �ڸ��� �̵���Ŵ
						setPiece(null, x, y);
						setPiece(king, king.getXPosition(), king.getYPosition());

					}
				}
			}
		}
		// �ֺ��� �⹰���� �̵��� �� ���ٸ� üũ����Ʈ
		return true;
	}

}
// TODO �����ϸ���Ʈ
