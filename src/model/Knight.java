package model;

public class Knight extends ChessPiece {
	
	//constructor
	public Knight(String color, int posX, int posY) {
		super(color, posX, posY);
		if (color.equals("white")) {
			setEmoji("♘");
		} else {
			setEmoji("♞");
		}
	}

	@Override //나이트(Knight) 행마법
	public boolean isValidMove(int moveToX, int moveToY) {
		int posX = getXPosition();
		int posY = getYPosition();
		
		int xDirection = 1;
		int yDirection = 1;
		
		if ((moveToX == posX + xDirection || moveToX == posX + xDirection * - 1) &&
				(moveToY == posY + yDirection * 2 || moveToY == posY + yDirection * - 2)) {
			return true;
			
		} else if (((moveToX == posX + xDirection * 2 || moveToX == posX + xDirection * - 2)) &&
				(moveToY == posY + yDirection || moveToY == posY + yDirection * - 1)) {
			return true;
		}
		
		return false;
	}

}