package model;

//MVC���� MODEL�� ����ϴ� Ŭ����
public class User {
	// field
	private String userId;
	private String userPassword;
	private int winRecord;
	private int defeatRecord;
	private double winRate;
	// TODO �⹰ �� ����ȹ�� ����

	// constructor
	private User() {
	}

	public User(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.winRecord = 0; // TODO ��Ⱑ ����Ǹ� ����� ������Ʈ���ִ� �޼��� ���� �־��ֱ�
		this.defeatRecord = 0; // TODO ��Ⱑ ����Ǹ� ����� ������Ʈ���ִ� �޼��� ���� �־��ֱ�
		this.winRate = ((double) winRecord + defeatRecord) / winRecord;
	}

	// getter && setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getWinRecord() {
		return winRecord;
	}

	public void setWinRecord(int winRecord) {
		this.winRecord = winRecord;
	}

	public int getDefeatRecord() {
		return defeatRecord;
	}

	public void setDefeatRecord(int defeatRecord) {
		this.defeatRecord = defeatRecord;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	// method
//	public String userInfo(User user) {
//		return String.format("User(userId=%s, winRecord=%d, defeatRecord=%d, winRate=%d)%n",
//				this.userId, this.winRecord, this.defeatRecord, this.winRate);
//	}
}