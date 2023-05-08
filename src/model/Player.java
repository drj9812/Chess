package model;

public class Player {

	// field
	private String color;
	private Boolean turn;
	private int score;
	private boolean isChecked;
	

	// constructor
	public Player(String color) {
		this.color = color;
		this.isChecked = false;
	}

	// getter && setter
	/**
	 * 흰색 : 선공, 검은색 : 후공
	 */
	public String getColor() {
		return this.color;
	}

	public Boolean isTurn() {
		return this.turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public boolean isChecked() {
		return isChecked;
	}
	
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// method
	/**
	 * @param score 기물 별 점수
	 */
	public void updateScore(int score) {
		score += score;
	}
}