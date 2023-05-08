package model;

public class Bishop extends ChessPiece {

	// constructor
	public Bishop(String color, int xPosition, int yPosition) {
		super(color, xPosition, yPosition);
		if (color.equals("white")) {
			setEmoji("♗");
		} else {
			setEmoji("♝");
		}
	}
	
	@Override //비숍(Bishop) 행마법
	public boolean isValidMove(int moveToX, int moveToY) {
	    int posX = getXPosition();
	    int posY = getYPosition();

	    // 대각선으로 움직이지 않으면 잘못된 움직임
	    if (Math.abs(moveToX - posX) != Math.abs(moveToY - posY)) {
	        return false;
	    }

	    // 대각선 방향으로 이동할 때 다른 기물이 있는지 확인
	    int xDirection = Integer.compare(moveToX, posX); //MoveToX = 3, posX = 2 -> 1
	    int yDirection = Integer.compare(moveToY, posY); //MoveToY = 6, posY = 7 - > -1
	    int x = posX + xDirection; //2 + 1 = 3
	    int y = posY + yDirection; //7 - 1 = 6
	    
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
	
}