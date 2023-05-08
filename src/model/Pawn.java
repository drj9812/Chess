package model;

// Pawn 클래스
public class Pawn extends ChessPiece {
	// field
	private boolean hasMoved;// 두칸 움직일 수 있는지 확인하는 변수, true = 움직였음, false = 움직이지 않았음

	// constructor
	public Pawn(String color, int xPosition, int yPosition) {
		super(color, xPosition, yPosition);
		this.hasMoved = false;
		if (color.equals("white")) {
			setEmoji("♙");
		} else {
			setEmoji("♟");
		}
	}

	// getter && setter
	public boolean HasMoved() {
		return hasMoved;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}

	@Override // 폰(Pawn) 행마법
	public boolean isValidMove(int moveToX, int moveToY) {
		int posX = getXPosition();
		int posY = getYPosition();
		String color = getColor();

		int yDirection = 1; // 폰의 이동 방향
		if (color.equals("white")) {
			yDirection = -1;
		}

		// 움직이지 않은 폰인 경우
		if (!HasMoved()) {
			if ((moveToX == posX) && (moveToY == posY + 2 * yDirection)) {
				return true;
				// 위로(y축) 두 칸 이동 가능
			}
		}

		// 폰이 위로(y축) 한 칸 이동 가능한 경우
		if (((moveToX == posX) && (moveToY == posY + yDirection)) && ChessBoard.getPiece(moveToX, moveToY) == null) {
//			System.out.println("실행되나1");
			return true;
		}

		// 폰이 양 대각선으로 이동 가능한 경우
		if (((moveToX == posX + 1) || (moveToX == posX - 1)) && (moveToY == posY + yDirection)) {
			if (ChessBoard.getPiece(moveToX, moveToY) != null) { // 상대방 기물이 있는지 검사
				return true;
			}
		}
//		System.out.println("실행되나2");
		return false;

	}
	//프로모션 가능 여부
	public boolean isPromotable(int moveToY) {
		String color = getColor();
		

		if (color.equals("white")) {
			if (moveToY == 0) {
				return true;
			}
			
		} else if (color.equals("black")) {
			if (moveToY == 7) {
				return true;
			}
			
		}
		return false;
	}
	//프로모션
	public void promote(String pieceType) {
		switch (pieceType) {
		case "Queen" :
			ChessBoard.setPiece(new Queen(getColor(), getXPosition(), getYPosition()), getXPosition(), getYPosition());
			break;
		case "Rook" :
			ChessBoard.setPiece(new Rook(getColor(), getXPosition(), getYPosition()), getXPosition(), getYPosition());
			break;
		case "Bishop" :
			ChessBoard.setPiece(new Bishop(getColor(), getXPosition(), getYPosition()), getXPosition(), getYPosition());
			break;
		case "Knight" :
			ChessBoard.setPiece(new Knight(getColor(), getXPosition(), getYPosition()), getXPosition(), getYPosition());
			break;
		}
	}
	
	//TODO 앙파상
} 