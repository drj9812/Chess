package model;

public class Rook extends ChessPiece {
	// field
	private boolean hasMoved;// 캐슬링 가능 여부

	// constructor
	public Rook(String color, int posX, int posY) {
		super(color, posX, posY);
		this.hasMoved = false;

		if (color.equals("white")) {
			setEmoji("♖");
		} else {
			setEmoji("♜");
		}

	}

	// getter && setter
	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}

	@Override // 룩(Rook) 행마법
	public boolean isValidMove(int moveToX, int moveToY) {
		int posX = getXPosition();
		int posY = getYPosition();

		// 수평/수직으로 움직이지 않으면 잘못된 움직임
		if (moveToX != posX && moveToY != posY) { //네개가 서로 다르면
			return false;
		}

		// 수평/수직 방향으로 이동할 때 다른 기물이 있는지 확인
		int xDirection = Integer.compare(moveToX, posX);
		int yDirection = Integer.compare(moveToY, posY);
		int x = posX + xDirection;
		int y = posY + yDirection;

		while (x != moveToX || y != moveToY) {
			if (ChessBoard.getPiece(x, y) != null) {
				return false;
			}
			
			x += xDirection;
			y += yDirection;
		}

		// 모든 조건을 만족하면 이동 가능
		return true;

	}
}