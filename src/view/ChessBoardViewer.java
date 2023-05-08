package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ChessDaoImpl;
import model.ChessBoard;
import model.ChessPiece;
import model.Pawn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//AWT(abstract window toolkit)는 Java에서 GUI를 개발하는 데 사용되는 패키지 중 하나
//AWT는 Java 1.0 버전부터 제공되었으며, 기본적인 GUI 컴포넌트를 제공
public class ChessBoardViewer {

	private static JFrame frame; // 자주 쓰일 것 같아서 그냥 static으로 선언, JPannel을 반복문으로 간단하게 구현할 수 있으면 instance로 바꿔줘도 상관없을
									// 듯
	private static JPanel panel; // 이하 동문
	private static JPanel[][] panels;
	private static JLabel lbl; // 이하 동문 //JPanel에 넣을 JLabel (이모티콘(문자열) 넣을 거면 이거 사용)
	private JTextField txtWhitePosXY;
	private JTextField txtBlackPosXY;
	private JTextField txtMoveToWhiteXY;
	private JTextField txtMoveToBlackXY;
	private JLabel lblWhiteInput;
	private JLabel lblBlackInput;
	private JButton btnInput;

	ChessDaoImpl app = ChessDaoImpl.getInstance();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessBoardViewer window = new ChessBoardViewer();
					showPiece();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ChessBoardViewer() {
		initialize();
	}

	// 현재 체스보드 위에 있는 모든 기물들을 출력시켜주는 메서드
	private static void showPiece() {
		for (int i = 0; i < panels.length; i++) {
			for (int j = 0; j < panels[i].length; j++) {
				JLabel lblInPanel = (JLabel) panels[i][j].getComponent(0);
				if (ChessBoard.chessBoard[i][j] != null) {
					lblInPanel.setText(ChessBoard.chessBoard[i][j].getEmoji());
				} else {
					lblInPanel.setText("");
				}
			}
		}
	}

	// 좌표값(알파벳)을 나타내기 위해 필요한 JPanel을 생성해주는 메서드
	private static JPanel createJPanel(int x, int y, int width, int height, Color color) {
		panel = new JPanel();
		panel.setBounds(x, y, width, height);
		panel.setBackground(color);
		frame.getContentPane().add(panel);
		return panel;
	}

	// 좌표값(숫자)을 나타내기 위해 필요한 JLabel을 생성해주는 메서드
	private static JLabel createJLabel(int x, int y, int width, int height, String txt) {
		lbl = new JLabel(txt);
		lbl.setFont(new Font("D2Coding", Font.PLAIN, 30));
		lbl.setBounds(x, y, width, height);
		frame.getContentPane().add(lbl);
		return lbl;
	}

	// JPanel에 넣을 JLabel을 생성해주는 메서드
	private static JLabel createJLabelForJPanel(int x, int y, int width, int height) {
		lbl = new JLabel();
		lbl.setFont(new Font("D2Coding", Font.PLAIN, 60));
		lbl.setBounds(x, y, width, height);
		frame.getContentPane().add(lbl);
		return lbl;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Chess Online");
		frame.setBounds(100, 100, 1000, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// 체스보드 이미지를 구현하기 위한 코드
		int startPanelY = 100;
		int panelWidth = 70;
		int panelHeight = 70;
		panels = new JPanel[ChessBoard.chessBoard.length][ChessBoard.chessBoard.length];
		Color[] colors = { new Color(222, 184, 135), new Color(139, 69, 19) };

		for (int i = 0; i < ChessBoard.chessBoard.length; i++) {
			int startPanelX = 200;
			for (int j = 0; j < ChessBoard.chessBoard[i].length; j++) {
				if ((i + 1) % 2 != 0) {
					if ((j + 1) % 2 != 0) {
						panels[i][j] = createJPanel(startPanelX, startPanelY, panelWidth, panelHeight, colors[0]);
						panels[i][j].add(createJLabelForJPanel(startPanelX, startPanelY, panelWidth, panelHeight));
						startPanelX += panelWidth;
					} else {
						panels[i][j] = createJPanel(startPanelX, startPanelY, panelWidth, panelHeight, colors[1]);
						panels[i][j].add(createJLabelForJPanel(startPanelX, startPanelY, panelWidth, panelHeight));
						startPanelX += panelWidth;
					}
				} else {
					if ((j + 1) % 2 != 0) {
						panels[i][j] = createJPanel(startPanelX, startPanelY, panelWidth, panelHeight, colors[1]);
						panels[i][j].add(createJLabelForJPanel(startPanelX, startPanelY, panelWidth, panelHeight));
						startPanelX += panelWidth;
					} else {
						panels[i][j] = createJPanel(startPanelX, startPanelY, panelWidth, panelHeight, colors[0]);
						panels[i][j].add(createJLabelForJPanel(startPanelX, startPanelY, panelWidth, panelHeight));
						startPanelX += panelWidth;
					}
				}
			}
			startPanelY += panelHeight;
		}

		// 체스보드의 좌표값(알파벳)을 나타내기 위한 코드
		int startLabelY = 60;
		int labelWidth = 45;
		int labelHeight = 45;
		List<JLabel> alphabetLabels = new ArrayList<>();

		for (int i = 1; i <= 2; i++) {
			int startLabelX = 230;
			for (int j = 1; j <= ChessBoard.chessBoard.length; j++) {

				if (i == 1) {
					alphabetLabels
							.add(createJLabel(startLabelX, startLabelY, labelWidth, labelHeight, (char) (j + 64) + ""));
					startLabelX += 70;

				} else if (i == 2) {
					alphabetLabels
							.add(createJLabel(startLabelX, startLabelY, labelWidth, labelHeight, (char) (j + 64) + ""));
					startLabelX += 70;
				}
			}
			startLabelY += 600;
		}

		// 체스보드의 좌표값(숫자)을 나타내기 위한 코드
		int startLabelX = 170;
		labelWidth = 45;
		labelHeight = 45;
		List<JLabel> numberLabels = new ArrayList<>();

		for (int i = 1; i <= 2; i++) {
			startLabelY = 115;
			for (int j = 1; j <= ChessBoard.chessBoard.length; j++) {
				numberLabels.add(createJLabel(startLabelX, startLabelY, labelWidth, labelHeight, j + ""));
				startLabelY += 70;
			}
			startLabelX = 770;
		}

		lblWhiteInput = new JLabel("\uBC31 \uC785\uB825");
		lblWhiteInput.setBounds(25, 139, 62, 18);
		frame.getContentPane().add(lblWhiteInput);

		lblBlackInput = new JLabel("\uD751 \uC785\uB825");
		lblBlackInput.setBounds(807, 139, 62, 18);
		frame.getContentPane().add(lblBlackInput);

		btnInput = new JButton("\uC785\uB825");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtWhitePosXY.getText().isEmpty() && !txtMoveToWhiteXY.getText().isEmpty()) {
					try {
						int[] whitePosXY = convertToBoardIndex(txtWhitePosXY.getText());
						int[] moveToWhiteXY = convertToBoardIndex(txtMoveToWhiteXY.getText());

						boolean whiteTurn = ChessBoard.whitePlayer.isTurn();

						if (whiteTurn) {
					    if (ChessBoard.detectCheck(ChessBoard.whitePlayer)) {
					    	txtClear();
					    	JOptionPane.showMessageDialog(frame, "체크 상태입니다.", "체크", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
					    
					    // 체크가 아닌 경우에만 실행됩니다.
					    if (ChessBoard.isMoveCausingCheck(ChessBoard.whitePlayer,
					    		whitePosXY[0], whitePosXY[1], moveToWhiteXY[0], moveToWhiteXY[1])) {
					    	txtClear();
					    	JOptionPane.showMessageDialog(frame,
					    			"잘못된 움직임입니다.", "잘못된 움직임",
					    			JOptionPane.WARNING_MESSAGE);
					    	return;
					    }
					    
					    int result = app.selectAndMovePiece(
					            whitePosXY[0], whitePosXY[1],
					            moveToWhiteXY[0], moveToWhiteXY[1], "white");
					            
					    getMessageByIndex(result, "white");
					}  else {
							txtClear();
							JOptionPane.showMessageDialog(frame,
									"검은색 차례입니다.", "턴 오류", JOptionPane.WARNING_MESSAGE);

						}

					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(frame,
								"입력 형식(A0 or a0)을 지켜주세요.", "입력 오류",
								JOptionPane.WARNING_MESSAGE);
						txtClear();
					}

					if (ChessBoard.isCheckmate(ChessBoard.blackPlayer)) { // 체크메이트인 경우
			            JOptionPane.showMessageDialog(frame, "체크메이트!", "게임 종료", JOptionPane.INFORMATION_MESSAGE);
			            System.exit(0);
			        }
					
				} else if (!txtBlackPosXY.getText().isEmpty() && !txtMoveToBlackXY.getText().isEmpty()) {
					try {
						int[] blackPosXY = convertToBoardIndex(txtBlackPosXY.getText());
						int[] moveToBlackXY = convertToBoardIndex(txtMoveToBlackXY.getText());

						boolean blackTurn = ChessBoard.blackPlayer.isTurn();

						if (blackTurn) { // 검은색 플레이어의 턴이면
							if (ChessBoard.detectCheck(ChessBoard.blackPlayer)) {
								txtClear();
								JOptionPane.showMessageDialog(frame,
										"체크 상태입니다.", "체크",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (ChessBoard.isMoveCausingCheck(ChessBoard.blackPlayer,
						    		blackPosXY[0], blackPosXY[1], moveToBlackXY[0], moveToBlackXY[1])) {
						    	txtClear();
						    	JOptionPane.showMessageDialog(frame,
						    			"잘못된 움직임입니다.", "잘못된 움직임",
						    			JOptionPane.WARNING_MESSAGE);
						    	return;
						    }

							int result = app.selectAndMovePiece(blackPosXY[0], blackPosXY[1],
									moveToBlackXY[0], moveToBlackXY[1], "black");

							getMessageByIndex(result, "black");

						} else {
							txtClear();
							JOptionPane.showMessageDialog(frame,
									"흰색 차례입니다.",
									"턴 오류", JOptionPane.WARNING_MESSAGE);

						}
					} catch (IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(frame,
								"입력 형식(A0 or a0)을 지켜주세요.", "입력 오류",
								JOptionPane.WARNING_MESSAGE);
						txtClear();
					}
					if (ChessBoard.isCheckmate(ChessBoard.whitePlayer)) { // 체크메이트인 경우
			            JOptionPane.showMessageDialog(frame, "체크메이트!", "게임 종료", JOptionPane.INFORMATION_MESSAGE);
			            System.exit(0);
			        }
				}

			}
		});

		btnInput.setBounds(428, 13, 105, 27);
		frame.getContentPane().add(btnInput);

		txtWhitePosXY = new JTextField();
		txtWhitePosXY.setBounds(25, 170, 116, 24);
		frame.getContentPane().add(txtWhitePosXY);
		txtWhitePosXY.setColumns(10);

		txtBlackPosXY = new JTextField();
		txtBlackPosXY.setColumns(10);
		txtBlackPosXY.setBounds(807, 170, 116, 24);
		frame.getContentPane().add(txtBlackPosXY);

		txtMoveToWhiteXY = new JTextField();
		txtMoveToWhiteXY.setColumns(10);
		txtMoveToWhiteXY.setBounds(25, 207, 116, 24);
		frame.getContentPane().add(txtMoveToWhiteXY);

		txtMoveToBlackXY = new JTextField();
		txtMoveToBlackXY.setColumns(10);
		txtMoveToBlackXY.setBounds(807, 207, 116, 24);
		frame.getContentPane().add(txtMoveToBlackXY);
	}

	private int[] convertToBoardIndex(String xy) throws IllegalArgumentException {
		int x = 0;
		int y = 0;

		try {
			x = (Integer.parseInt((xy.toUpperCase().charAt(0) - 65) + ""));
			y = (Integer.parseInt(xy.charAt(1) + "")) - 1;

			if ((xy.length() != 2) && ((0 <= x && x <= 7) && (0 <= y && y <= 7))) {
				throw new IllegalArgumentException();
			}

		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}

		return new int[] { x, y };
	}

	private void txtClear() {
		txtWhitePosXY.setText("");
		txtMoveToWhiteXY.setText("");
		txtBlackPosXY.setText("");
		txtMoveToBlackXY.setText("");
	}

	private void getMessageByIndex(int result, String color) {
		if (result == 0) {// 입력한 좌표(움직이고자하는 기물의 좌표)에 기물이 없으면
			JOptionPane.showMessageDialog(frame, "입력한 좌표에 기물이 존재하지 않습니다.",
					"좌표값 오류", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 1) {// 입력한 좌표가 체스보드의 범위밖이라면
			JOptionPane.showMessageDialog(frame,
					"입력하신 좌표는 체스보드판의 범위를 넘어갑니다.",
					"좌표 범위 오류", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 2) {// 올바른 행마법이 아니라면
			JOptionPane.showMessageDialog(frame,
					"올바른 행마법이 아닙니다.",
					"행마법 오류", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;
		} else if (result == 3) {
			JOptionPane.showMessageDialog(frame,
					"플레이어의 색깔과 일치하는 기물만 이동시킬 수 있습니다.",
					"색깔 오류", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 4) {
			Object[] selectionPieces = { "Queen", "Rook", "Bishop", "Knight" };
			Object input = JOptionPane.showInputDialog(frame, // 부모 컴포넌트
					"교체할 기물 타입을 선택하세요.", // 메시지
					"프로모션", // 타이틀
					JOptionPane.QUESTION_MESSAGE, // 메시지 타입
					null, // 아이콘
					selectionPieces, // 입력값으로 사용할 수 있는 선택지들의 배열
					selectionPieces[1]); // 초기 선택값 , 포커스

			if (color.equals("white")) {
				Pawn pawn = (Pawn) ChessBoard.getPiece(
						Integer.parseInt((txtMoveToWhiteXY.getText().toUpperCase().charAt(0) - 65) + ""),
						Integer.parseInt(txtMoveToWhiteXY.getText().charAt(1) + "") - 1);

				pawn.promote(input + "");

			} else if (color.equals("black")) {
				Pawn pawn = (Pawn) ChessBoard.getPiece(
						Integer.parseInt((txtMoveToBlackXY.getText().toUpperCase().charAt(0) - 65) + ""),
						Integer.parseInt(txtMoveToBlackXY.getText().charAt(1) + "") - 1);

				pawn.promote(input + "");
			}
		}

		if (color.equals("white")) {
			ChessBoard.whitePlayer.setTurn(false);
			ChessBoard.blackPlayer.setTurn(true);

		} else {
			ChessBoard.whitePlayer.setTurn(true);
			ChessBoard.blackPlayer.setTurn(false);
		}

		txtClear();
		showPiece();
	}
}