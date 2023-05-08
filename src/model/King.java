package model;

public class King extends ChessPiece {
	// field
	private boolean hasMoved; // 캐슬링 가능 여부

	// constructor
	public King(String color, int posX, int posY) {
		super(color, posX, posY);
		this.hasMoved = false;

		if (color.equals("white")) {
			setEmoji("♔");
		} else {
			setEmoji("♚");
		}
	}

	// getter && setter
	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}

	// method
	@Override
	public boolean isValidMove(int moveToX, int moveToY) {
		int posX = getXPosition();
		int posY = getYPosition();

		int xDirection = 1;
		int yDirection = 1;

		// y축 이동
		if ((moveToX == posX) && (moveToY == posY + yDirection || moveToY == posY + yDirection * -1) ) {
			if (!ChessBoard.getPiece(moveToX,moveToY).getColor().equals(getColor()) || //이동하려는 좌표에 같은 기물의 색상이 있거나
					ChessBoard.getPiece(moveToX, moveToY) == null) { //아예 기물이 없는 경우는 DaoImpl에서 처리해줬는데 ChessBoard의 canAvoidCheck메서드 때문에 급하게 추가함
																	//같은 작업을 두번하는 셈이니 해결할 수 있으면 해결하기
				return true;
				
			}
		}

		// x축 이동
		if ((moveToY == posY) && (moveToX == posX + xDirection || moveToX == posX + xDirection * -1)) {
			if (!ChessBoard.getPiece(moveToX,moveToY).getColor().equals(getColor()) || 
					ChessBoard.getPiece(moveToX, moveToY) == null) {
				return true;
				
			}
		}

		// 캐슬링(Castling)
		if (!hasMoved) {
			if ((moveToX == posX + xDirection * 2) && (moveToY == posY)) { // 오른쪽
				if (ChessBoard.getPiece(posX + 1, posY) == null && ChessBoard.getPiece(posX + 2, posY) == null) {
					Rook rook = (Rook) ChessBoard.getPiece(posX + 3, posY);

					if (rook != null && !rook.isHasMoved()) {
						if (!isAttacked(posX, posY, moveToX, moveToY)) {
							ChessBoard.setPiece(null, posX + 3, posY);
							ChessBoard.setPiece(rook, posX + 1, posY);
							rook.setHasMoved();
							return true;
							
						}
					}
				}

			} else if ((moveToX == posX + xDirection * -2) && (moveToY == posY)) { // 왼쪽
				if (ChessBoard.getPiece(posX - 1, posY) == null && ChessBoard.getPiece(posX - 2, posY) == null) {
					Rook rook = (Rook) ChessBoard.getPiece(posX - 4, posY);

					if (rook != null && !rook.isHasMoved()) {
						if (!isAttacked(posX, posY, moveToX, moveToY)) {
							ChessBoard.setPiece(null, posX - 4, posY);
							ChessBoard.setPiece(rook, posX - 1, posY);
							rook.setHasMoved();
							return true;
							
						}
					}
				}
			}
		}

		int rowDistance = Math.abs(moveToX - posX);
		int colDistance = Math.abs(moveToY - posY);

		if (rowDistance == colDistance && rowDistance == 1) {
			return true;
		}

		return false;
	}

	private static boolean isAttacked(int posX, int posY, int moveToX, int moveToY) {

		for (int y = 0; y < ChessBoard.chessBoard.length; y++) {
			for (int x = 0; x < ChessBoard.chessBoard[y].length; x++) {
				ChessPiece piece = ChessBoard.getPiece(x, y);

				if (piece != null && !piece.getColor().equals(ChessBoard.chessBoard[posY][posX].getColor())) {
					if (moveToX == posX + 2) {
						if (piece.isValidMove(posX + 1, moveToY) && piece.isValidMove(posX + 2, moveToY)) {
							return true;
						}

					} else if (moveToX == posX - 2) {
						if (piece.isValidMove(posX - 1, moveToY) && piece.isValidMove(posX - 2, moveToY)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}