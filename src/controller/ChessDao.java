package controller;

import java.util.List;

import model.ChessPiece;
import model.Player;
import model.User;

//MVC(Model-View-Controller) : 아키텍쳐에서 Controller 역할을 수행할 인터페이스(구현할 클래스 있어야 함)
//DAO(Data Access Object) : 데이터를 사용해서 비즈니스 로직(기능)을 수행하는 객체 (저장, 수정, 검색, 삭제 등등)
public interface ChessDao {
	
	/**
	 주어진 위치(posX, posY)에 있는 기물을 선택하고, 선택된 기물을 주어진 위치(moveToX, moveToY)로 이동시키는 메서드입니다.
	 선택된 기물의 유효성 검사를 수행하고, 기물이 이동할 수 있는 위치인지 확인합니다.
	 기물이 선택되지 않았거나 기물의 좌표 또는 이동할 위치의 좌표가 체스판의 범위를 벗어나면 각각의 오류 메시지를 출력합니다.
	 이동할 위치에 기물이 이미 존재하면, 색깔이 다른 경우에만 이동을 수행합니다.
	 기물을 이동하면, 해당 기물의 좌표를 업데이트하고 체스판에서 해당 위치에 기물을 놓습니다.
	 기물이 이동할 때마다 King, Rook, Pawn 클래스에 구현된 setHasMoved() 메서드를 호출하여 각 기물이 이동했는지 여부를 업데이트합니다.
	 *
	 * @param posX 선택할 기물의 X 좌표
	 * @param posY 선택할 기물의 Y 좌표
	 * @param moveToX 선택된 기물이 이동할 X 좌표
	 * @param moveToY 선택된 기물이 이동할 Y 좌표
	 * @param color 차례인 유저의 색깔
	 * @return 기물을 성공적으로 이동시켰을 경우 3, 그렇지 않은 경우 0, 1, 2 중 하나의 값을 반환합니다.
	 */
	int selectAndMovePiece(int posX, int posY, int moveToX, int moveToY, String color);

	
	/**
	 선택된 기물을 주어진 위치로 이동시키는 메서드입니다.
	 선택된 기물의 좌표를 업데이트하고, 체스판에서 해당 위치에 기물을 놓습니다.
	 기물이 이동할 때마다 King, Rook, Pawn 클래스에 구현된 setHasMoved() 메서드를 호출하여 각 기물이 이동했는지 여부를 업데이트합니다.
	 *
	 * @param piece 이동시킬 기물 객체
	 * @param posX 선택된 기물의 X 좌표
	 * @param posY 선택된 기물의 Y 좌표
	 * @param moveToX 선택된 기물이 이동할 X 좌표
	 * @param moveToY 선택된 기물이 이동할 Y 좌표
	 */
	public void moveSelectedPiece(ChessPiece piece, int posX, int posY, int moveToX, int moveToY);
	
	//ID 확인
	public abstract int isIDExist(String userId);
	
	//ID 생성
	public abstract void create(String userId, String userPassword);
	
	//전적 업데이트
	public abstract void updateRecord(User user);
	
	//전적 공개
	public abstract void read(User user);
	
	//랭킹 공개
	public abstract List<User> read();
	
	/**
	 *턴 상태인 플레이어의 킹이 체크상태인지 아닌지 확인
	 *
	 * @return 체크상태이면 1을 return, 체크 상태가 아니라면 0을 return
	 */
	public abstract int isCheck();
}