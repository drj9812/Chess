package model;

// �߻� Ŭ������ ������ ChessPiece Ŭ����
public abstract class ChessPiece {

	// field
//	protected String name; //�ϴ� ���� name ���� �ʿ� �������� ���� ������ ������ name ������ �ʿ���ϸ� ����
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
	 * �ش� �⹰�� �̵��Ϸ��� ��ġ�� ��ȿ�� ��ġ���� Ȯ���ϴ� �޼���.
	 * 
	 * @param moveToX �̵��Ϸ��� ��ġ�� X ��ǥ
	 * @param moveToY �̵��Ϸ��� ��ġ�� Y ��ǥ
	 * @return �̵��� �����ϴٸ� true, �Ұ����ϴٸ� false�� ��ȯ�մϴ�.
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
	//TODO X��ǥ�� Y��ǥ�� ���� �����̴� ��찡 ���ٸ� setXPostion �޼���� setYPosition �޼��� ��ġ��
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