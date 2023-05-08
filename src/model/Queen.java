package model;

public class Queen extends ChessPiece {

	// constructor
	public Queen(String color, int posX, int posY) {
		super(color, posX, posY);
		if (color.equals("white")) {
			setEmoji("♕");
		} else {
			setEmoji("♛");
		}
	}

	@Override //퀸(Queen) 행마법
	public boolean isValidMove(int moveToX, int moveToY) {
		int posX = getXPosition();
		int posY = getYPosition();

		// 대각선으로 움직이지 않으면 잘못된 움직임
		if (Math.abs(moveToX - posX) == Math.abs(moveToY - posY)) {
			// 대각선 방향으로 이동할 때 다른 기물이 있는지 확인
			int xDirection = Integer.compare(moveToX, posX);
			int yDirection = Integer.compare(moveToY, posY);
			int x = posX + xDirection;
			int y = posY + yDirection;

			while (x != moveToX && y != moveToY) {
				if (ChessBoard.getPiece(x, y) != null) {
					return false;
				}
				
				x += xDirection;
				y += yDirection;
			}

			// 모든 조건을 만족하면 이동 가능
			return true;
		}

		// 직선으로 움직이지 않으면 잘못된 움직임
		if (moveToX != posX && moveToY != posY) {
			return false;
		}

		// 직선 방향으로 이동할 때 다른 기물이 있는지 확인
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
