package model;

import java.util.ArrayList;
import java.util.List;

//MVC에서 MODEL을 담당하는 클래스
public class ChessBoard {
	// field
	private static final int BOARD_SIZE = 8;
	public static ChessPiece[][] chessBoard = new ChessPiece[BOARD_SIZE][BOARD_SIZE]; // cv로 할까 iv로 할까, 생성을 필드에서 하는 것은
																						// 어떤지 물어보기

	// Player 객체를 static으로 선언하면 ChessBoard클래스가 여러번 생성되어도 하나의 Player만을 가리키게 됨
	public static Player whitePlayer;
	public static Player blackPlayer;

//	 //true는 현재 체크 상태임을 알립니다.
//	private static boolean isWhiteKingChecked;
//	private static boolean isBlackKingChecked;

	private static List<ChessPiece> pieces;
	// static으로 선언하면 클래스 변수가 되어, 해당 클래스의 모든 객체에서 공유됩니다.
	// 즉, ChessBoard 클래스의 모든 객체가 같은 Player 객체를 사용하게 되며, 이는 객체 지향 프로그래밍의 캡슐화와 모듈화 원칙을
	// 위배하게 됩니다.
	// 따라서, ChessBoard 클래스의 Player 객체는 static으로 선언하지 않고, 인스턴스 변수로 선언하는 것이 바람직합니다.
	// 이렇게 하면 ChessBoard 클래스의 각 객체가 독립적으로 Player 객체를 사용할 수 있게 됩니다.

//	//detectCheck메서드에서 getKing()메서드를 호출하기 위해 static으로 선언
//	//TODO 지역변수로 뺄 수 있으면 빼기
//	static King whiteKing = new King("white", 4, 7);
//	static King blackKing = new King("black", 4, 0);
	// constructor
	public ChessBoard() {
//		isWhiteKingChecked = false;
//	    isBlackKingChecked = false;
		initialize();
	}

//	데이터 초기화의 책임은 모델에 있습니다. 각 기물들의 초기 위치는 체스 게임에서 중요한 데이터입니다. 
//	이 데이터 초기화의 책임은 모델에 있으며, 이를 컨트롤러나 뷰에서 처리하면 패턴의 원칙에 위배됩니다. 
//	따라서 chessBoard 클래스에서 최초 각 기물들의 위치를 배정해주는 것은 모델의 책임으로 보는 것이 올바릅니다.

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

		// Player 객체를 생성하는 것은 매우 간단하므로 지역 변수로 선언하는 것이 더 좋을 것입니다.
		// 이렇게 하면 다른 메서드에서 재사용할 수 없기 때문에 캡슐화가 더 강화되며 코드의 가독성이 향상됩니다.
		// 따라서 다음과 같이 지역 변수로 선언하는 것이 좋습니다.
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
//	 * 백색 킹의 체크상태를 반환합니다.
//	 * 
//	 * @return 백색 킹의 체크상태
//	 */
//    public boolean isWhiteKingChecked() {
//        return isWhiteKingChecked;
//    }
//    /**
//     * 흑색 킹의 체크상태를 반환합니다.
//     * 
//     * @return 흑색 킹의 체크상태
//     */
//    public boolean isBlackKingChecked() {
//        return isBlackKingChecked;
//    }

	/**
	 * 현재 체스보드에서 해당 위치에 있는 체스 기물을 반환합니다.
	 * 
	 * @param posX 체스 기물의 X 좌표
	 * @param posY 체스 기물의 Y 좌표
	 * @return 해당 위치에 있는 체스 기물 객체
	 */
	public static ChessPiece getPiece(int posX, int posY) { // DaoImpl, ChessPiece를 구현하는 클래스에서 사용되기 때문에 static으로 선언
		return chessBoard[posY][posX];
	}

	/**
	 * 현재 체스보드에 존재하는 플레이어의 모든 기물들을 리스트에 저장합니다.
	 * 
	 * @param player 플레이어
	 * @return 기물을 저장한 리스트
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
	 * 해당 플레이어의 킹 기물을 찾아 반환합니다.
	 * 
	 * @param player 플레이어
	 * @return 해당 플레이어의 킹 기물
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
	 * 현재 체스보드에서 해당 위치에 있는 체스 기물을 주어진 기물로 대체합니다.
	 * 
	 * @param piece   대체할 체스 기물 객체
	 * @param moveToX 기물이 이동할 X 좌표
	 * @param moveToY 기물이 이동할 Y 좌표
	 */
	public static void setPiece(ChessPiece piece, int moveToX, int moveToY) { // DaoImpl, ChessPiece를 구현하는 클래스에서 사용되기
																				// 때문에
		// static으로 선언
		chessBoard[moveToY][moveToX] = piece;
	}

	/**
	 * 해당 위치가 체스판의 범위 내에 있는지 확인하는 메서드입니다.
	 * 
	 * @param moveToX 이동할 X 좌표
	 * @param moveToY 이동할 Y 좌표
	 * @return 위치가 체스판 내에 있으면 true, 그렇지 않으면 false를 반환합니다.
	 */
	public static boolean isValidChessCoordinate(int moveToX, int moveToY) {
		if ((moveToX < 0 || moveToX > 7) || (moveToY < 0 || moveToY > 7)) {
			return false;
		}
		return true;
	}

	/**
	 * 해당 플레이어의 킹이 상대편의 공격을 받고 있는지 확인하는 메서드입니다. 현재 체스판에서 해당 플레이어의 왕(King)의 위치를 찾아,
	 * 다른 플레이어의 말 중 해당 왕을 공격 가능한 말이 있는지 확인합니다.
	 * 
	 * @param player 확인하고자 하는 플레이어의 색깔 (white 또는 black)
	 * @return 해당 플레이어가 체크 상태인 경우 true, 아닌 경우 false
	 */
	public static boolean detectCheck(Player player) {
		King king = getKing(player);

		// 상대가 사용자의 King을 공격할 수 있는지 확인
		Player opponent = player.getColor().equals("white") ? blackPlayer : whitePlayer;

		List<ChessPiece> opponentPieces = getPieces(opponent);

		for (ChessPiece piece : opponentPieces) { // 상대 = 블랙
			if (piece.isValidMove(king.getXPosition(), king.getYPosition())) {
				player.setChecked(true);
				return true;
			}
		}

		return false;
	}

	/**
	 * 해당 기물을 이동시켰을 때 체크 상태를 유발하는지 확인하는 메서드입니다.
	 * 
	 * @param player  기물을 이동시킬 플레이어
	 * @param posX    기물의 현재 x좌표
	 * @param posY    기물의 현재 y좌표
	 * @param moveToX 기물을 이동시킬 x좌표
	 * @param moveToY 기물을 이동시킬 y좌표
	 * @return 해당 기물을 이동시켰을 때 체크 상태를 유발하는지 여부
	 */
	public static boolean isMoveCausingCheck(Player player, int posX, int posY, int moveToX, int moveToY) {
		ChessPiece piece = getPiece(posX, posY);
		ChessPiece opponentPiece = getPiece(moveToX, moveToY);
//		System.out.println(piece);
//		System.out.println(opponentPiece);
		if (!piece.isValidMove(moveToX, moveToY)) {
			return false;
		}
		// 기물을 이동시키고, 체크 상태인지 확인합니다.
		setPiece(null, posX, posY);
		setPiece(piece, moveToX, moveToY);

		if (opponentPiece == null) {
			if (detectCheck(player)) { // 체크 상태인 경우, 기물을 이동시키지 않고 true를 반환합니다.
				setPiece(null, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return true;

			} else { // 체크 상태가 아닌 경우, 기물을 이동시키지 않고 false를 반환합니다.
				setPiece(null, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return false;
			}
		} else {
			if (detectCheck(player)) { // 체크 상태인 경우, 기물을 이동시키지 않고 true를 반환합니다.
				setPiece(null, moveToX, moveToY);
				setPiece(opponentPiece, moveToX, moveToY);
				setPiece(piece, posX, posY);
				
				return true;

			} else { // 체크 상태가 아닌 경우, 기물을 이동시키지 않고 false를 반환합니다.
				setPiece(null, moveToX, moveToY);
				setPiece(opponentPiece, moveToX, moveToY);
				setPiece(piece, posX, posY);
				return false;
			}
		}
	}

//	// 해당 경로에 나의 다른 기물이 있으면 true, 없으면 false를 반환
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

	// 체크메이트 판정 메서드
	public static boolean isCheckmate(Player player) {

		// 체크 상태가 아니면 체크메이트도 아님
		if (!detectCheck(player)) {
			return false;
		}

		// 체크 상태인 킹 찾기
		King king = getKing(player);

		// 체크 상태인 킹이 이동할 수 있는지 체크
		boolean checkmate = true;
		for (int x = king.getXPosition() - 1; x <= king.getXPosition() + 1; x++) {
			for (int y = king.getYPosition() - 1; y <= king.getYPosition() + 1; y++) {
				if ((isValidChessCoordinate(x, y)) && (x != king.getXPosition() || y != king.getYPosition())) {
					if (getPiece(x, y) == null || !getPiece(x, y).getColor().equals(king.getColor())) {

						// 이동 시뮬레이션
						setPiece(null, king.getXPosition(), king.getYPosition());
						setPiece(king, x, y);

						// 체크를 풀 수 있는 기물이 있다면 체크메이트 아님
						boolean isCheck = detectCheck(player);
						if (!isCheck) {
							checkmate = false;
						}

						// 다시 원래 자리로 이동시킴
						setPiece(null, x, y);
						setPiece(king, king.getXPosition(), king.getYPosition());

					}
				}
			}
		}
		// 주변에 기물들이 이동할 수 없다면 체크메이트
		return true;
	}

}
// TODO 스테일메이트
