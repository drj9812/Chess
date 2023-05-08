package controller;

import java.util.ArrayList;
import java.util.List;

import model.ChessBoard;
import model.ChessPiece;
import model.King;
import model.Pawn;
import model.Player;
import model.Rook;
import model.User;

//MVC(Model - View - Controller) 아키텍쳐에서 인터페이스로 설계한 컨트롤러(Dao)를 구현하는 클래스
//singleton 디자인 패턴을 적용.
public class ChessDaoImpl implements ChessDao {
//	private ChessBoard chessBoard;

	// singleton step 1
	private static ChessDaoImpl instance = null;

	// ->자신의 클래스에서밖에 접근할 수 없는 ChessDaoImpl 타입의 instance 변수 선언
	// singleton step 2
	// constructor
	private ChessDaoImpl() {
		ChessBoard chessBoard = new ChessBoard(); // <-모델, 싱글톤에서 객체 생성하는 게 나은지 main에서 생성하는 게 나은지 물어보기
	}
	// -> 기본 생성자를 private 으로 선언했기 때문에 외부에서는 객체를 생성할 수 없음

	// singleton step 3
	public static ChessDaoImpl getInstance() {
		if (instance == null) { // instance 객체가 생성되지 않았다면
			instance = new ChessDaoImpl(); // 기본 생성자를 이용해 instance 객체 생성
		}
		return instance; // instance 객체가 이미 생성되어 있다면, 이미 생성된 instance 객체를 반환
	}

	private List<User> playerList = new ArrayList<>(); // User의 정보가 담길 리스트

	public int selectAndMovePiece(int posX, int posY, int moveToX, int moveToY, String color) {
		
		ChessPiece piece = ChessBoard.getPiece(posX, posY);
		if (piece == null) {
			return 0; // '기물이 존재하지 않습니다.'

		}

		if (!ChessBoard.isValidChessCoordinate(posX, posY) || !ChessBoard.isValidChessCoordinate(moveToX, moveToY)) {
			return 1; // '기물의 좌표를 확인해주세요.' // 범위 밖

		}

		if (!piece.isValidMove(moveToX, moveToY)) {
			return 2; // '올바른 행마법이 아닙니다.'

		}
		
		if (!piece.getColor().equals(color)) {
			return 3; // '플레이어의 색깔과 일치하는 기물만 이동시킬 수 있습니다.'
			
		}

		if (ChessBoard.getPiece(moveToX, moveToY) != null) { // 기물을 이동시키고자 하는 좌표에 기물이 있을 경우
			if (piece.getColor().equals(ChessBoard.getPiece(moveToX, moveToY).getColor())) { // 색깔이 같으면
				return 1;

			} else {
				moveSelectedPiece(piece, posX, posY, moveToX, moveToY); // 색깔이 같지 않으면
				if (piece instanceof Pawn) {
					if (((Pawn) piece).isPromotable(moveToY) == true) { // 프로모션이 가능하면
						return 4;
					}
				}
			}

		} else {
			moveSelectedPiece(piece, posX, posY, moveToX, moveToY);
			if (piece instanceof Pawn) {
				if (((Pawn) piece).isPromotable(moveToY) == true) { // 프로모션이 가능하면
					return 4;
					
				}
			}
		}
	
		return 5;
	}

	public void moveSelectedPiece(ChessPiece piece, int posX, int posY, int moveToX, int moveToY) {
		ChessBoard.setPiece(piece, moveToX, moveToY);
		piece.setXPosition(moveToX);
		piece.setYPosition(moveToY);
		ChessBoard.setPiece(null, posX, posY);

		if (piece.getEmoji().equals("♔") || piece.getEmoji().equals("♚")) {
			((King) piece).setHasMoved();

		} else if (piece.getEmoji().equals("♖") || piece.getEmoji().equals("♜")) {
			((Rook) piece).setHasMoved();

		} else if (piece.getEmoji().equals("♙") || piece.getEmoji().equals("♟")) {
			((Pawn) piece).setHasMoved();
		}
	}

	@Override
	public void create(String playerId, String userPassword) {
		User user = new User(playerId, userPassword);
		playerList.add(user);
	}

	@Override
	public void updateRecord(User user) {

	}

	@Override
	public void read(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isIDExist(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isCheck() {
		return 0;
	}

}