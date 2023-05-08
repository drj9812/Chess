package model;

// 추상 클래스로 구현된 ChessPiece 클래스
public abstract class ChessPiece {

	// field
//	protected String name; //일단 폰은 name 변수 필요 나머지는 몰라 나머지 상당수가 name 변수를 필요로하면 생성
	private String color;
	private String emoji;

	private int posX;
	private int posY;

	// constructor
	public ChessPiece(String color, int posX, int posY) {
		this.color = color;
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * 해당 기물이 이동하려는 위치가 유효한 위치인지 확인하는 메서드.
	 * 
	 * @param moveToX 이동하려는 위치의 X 좌표
	 * @param moveToY 이동하려는 위치의 Y 좌표
	 * @return 이동이 가능하다면 true, 불가능하다면 false를 반환합니다.
	 */
	public abstract boolean isValidMove(int moveToX, int moveToY);
	
//     getter && setter
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public int getXPosition() {
		return posX;
	}
	//TODO X좌표와 Y좌표를 따로 움직이는 경우가 없다면 setXPostion 메서드와 setYPosition 메서드 합치기
	public void setXPosition(int posX) {
		this.posX = posX;
	}

	public int getYPosition() {
		return posY;
	}

	public void setYPosition(int posY) {
		this.posY = posY;
	}
	
}