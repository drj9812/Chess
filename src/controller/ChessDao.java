package controller;

import java.util.List;

import model.ChessPiece;
import model.Player;
import model.User;

//MVC(Model-View-Controller) : ��Ű���Ŀ��� Controller ������ ������ �������̽�(������ Ŭ���� �־�� ��)
//DAO(Data Access Object) : �����͸� ����ؼ� ����Ͻ� ����(���)�� �����ϴ� ��ü (����, ����, �˻�, ���� ���)
public interface ChessDao {
	
	/**
	 �־��� ��ġ(posX, posY)�� �ִ� �⹰�� �����ϰ�, ���õ� �⹰�� �־��� ��ġ(moveToX, moveToY)�� �̵���Ű�� �޼����Դϴ�.
	 ���õ� �⹰�� ��ȿ�� �˻縦 �����ϰ�, �⹰�� �̵��� �� �ִ� ��ġ���� Ȯ���մϴ�.
	 �⹰�� ���õ��� �ʾҰų� �⹰�� ��ǥ �Ǵ� �̵��� ��ġ�� ��ǥ�� ü������ ������ ����� ������ ���� �޽����� ����մϴ�.
	 �̵��� ��ġ�� �⹰�� �̹� �����ϸ�, ������ �ٸ� ��쿡�� �̵��� �����մϴ�.
	 �⹰�� �̵��ϸ�, �ش� �⹰�� ��ǥ�� ������Ʈ�ϰ� ü���ǿ��� �ش� ��ġ�� �⹰�� �����ϴ�.
	 �⹰�� �̵��� ������ King, Rook, Pawn Ŭ������ ������ setHasMoved() �޼��带 ȣ���Ͽ� �� �⹰�� �̵��ߴ��� ���θ� ������Ʈ�մϴ�.
	 *
	 * @param posX ������ �⹰�� X ��ǥ
	 * @param posY ������ �⹰�� Y ��ǥ
	 * @param moveToX ���õ� �⹰�� �̵��� X ��ǥ
	 * @param moveToY ���õ� �⹰�� �̵��� Y ��ǥ
	 * @param color ������ ������ ����
	 * @return �⹰�� ���������� �̵������� ��� 3, �׷��� ���� ��� 0, 1, 2 �� �ϳ��� ���� ��ȯ�մϴ�.
	 */
	int selectAndMovePiece(int posX, int posY, int moveToX, int moveToY, String color);

	
	/**
	 ���õ� �⹰�� �־��� ��ġ�� �̵���Ű�� �޼����Դϴ�.
	 ���õ� �⹰�� ��ǥ�� ������Ʈ�ϰ�, ü���ǿ��� �ش� ��ġ�� �⹰�� �����ϴ�.
	 �⹰�� �̵��� ������ King, Rook, Pawn Ŭ������ ������ setHasMoved() �޼��带 ȣ���Ͽ� �� �⹰�� �̵��ߴ��� ���θ� ������Ʈ�մϴ�.
	 *
	 * @param piece �̵���ų �⹰ ��ü
	 * @param posX ���õ� �⹰�� X ��ǥ
	 * @param posY ���õ� �⹰�� Y ��ǥ
	 * @param moveToX ���õ� �⹰�� �̵��� X ��ǥ
	 * @param moveToY ���õ� �⹰�� �̵��� Y ��ǥ
	 */
	public void moveSelectedPiece(ChessPiece piece, int posX, int posY, int moveToX, int moveToY);
	
	//ID Ȯ��
	public abstract int isIDExist(String userId);
	
	//ID ����
	public abstract void create(String userId, String userPassword);
	
	//���� ������Ʈ
	public abstract void updateRecord(User user);
	
	//���� ����
	public abstract void read(User user);
	
	//��ŷ ����
	public abstract List<User> read();
	
	/**
	 *�� ������ �÷��̾��� ŷ�� üũ�������� �ƴ��� Ȯ��
	 *
	 * @return üũ�����̸� 1�� return, üũ ���°� �ƴ϶�� 0�� return
	 */
	public abstract int isCheck();
}