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

//AWT(abstract window toolkit)�� Java���� GUI�� �����ϴ� �� ���Ǵ� ��Ű�� �� �ϳ�
//AWT�� Java 1.0 �������� �����Ǿ�����, �⺻���� GUI ������Ʈ�� ����
public class ChessBoardViewer {

	private static JFrame frame; // ���� ���� �� ���Ƽ� �׳� static���� ����, JPannel�� �ݺ������� �����ϰ� ������ �� ������ instance�� �ٲ��൵ �������
									// ��
	private static JPanel panel; // ���� ����
	private static JPanel[][] panels;
	private static JLabel lbl; // ���� ���� //JPanel�� ���� JLabel (�̸�Ƽ��(���ڿ�) ���� �Ÿ� �̰� ���)
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

	// ���� ü������ ���� �ִ� ��� �⹰���� ��½����ִ� �޼���
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

	// ��ǥ��(���ĺ�)�� ��Ÿ���� ���� �ʿ��� JPanel�� �������ִ� �޼���
	private static JPanel createJPanel(int x, int y, int width, int height, Color color) {
		panel = new JPanel();
		panel.setBounds(x, y, width, height);
		panel.setBackground(color);
		frame.getContentPane().add(panel);
		return panel;
	}

	// ��ǥ��(����)�� ��Ÿ���� ���� �ʿ��� JLabel�� �������ִ� �޼���
	private static JLabel createJLabel(int x, int y, int width, int height, String txt) {
		lbl = new JLabel(txt);
		lbl.setFont(new Font("D2Coding", Font.PLAIN, 30));
		lbl.setBounds(x, y, width, height);
		frame.getContentPane().add(lbl);
		return lbl;
	}

	// JPanel�� ���� JLabel�� �������ִ� �޼���
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

		// ü������ �̹����� �����ϱ� ���� �ڵ�
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

		// ü�������� ��ǥ��(���ĺ�)�� ��Ÿ���� ���� �ڵ�
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

		// ü�������� ��ǥ��(����)�� ��Ÿ���� ���� �ڵ�
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
					    	JOptionPane.showMessageDialog(frame, "üũ �����Դϴ�.", "üũ", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
					    
					    // üũ�� �ƴ� ��쿡�� ����˴ϴ�.
					    if (ChessBoard.isMoveCausingCheck(ChessBoard.whitePlayer,
					    		whitePosXY[0], whitePosXY[1], moveToWhiteXY[0], moveToWhiteXY[1])) {
					    	txtClear();
					    	JOptionPane.showMessageDialog(frame,
					    			"�߸��� �������Դϴ�.", "�߸��� ������",
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
									"������ �����Դϴ�.", "�� ����", JOptionPane.WARNING_MESSAGE);

						}

					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(frame,
								"�Է� ����(A0 or a0)�� �����ּ���.", "�Է� ����",
								JOptionPane.WARNING_MESSAGE);
						txtClear();
					}

					if (ChessBoard.isCheckmate(ChessBoard.blackPlayer)) { // üũ����Ʈ�� ���
			            JOptionPane.showMessageDialog(frame, "üũ����Ʈ!", "���� ����", JOptionPane.INFORMATION_MESSAGE);
			            System.exit(0);
			        }
					
				} else if (!txtBlackPosXY.getText().isEmpty() && !txtMoveToBlackXY.getText().isEmpty()) {
					try {
						int[] blackPosXY = convertToBoardIndex(txtBlackPosXY.getText());
						int[] moveToBlackXY = convertToBoardIndex(txtMoveToBlackXY.getText());

						boolean blackTurn = ChessBoard.blackPlayer.isTurn();

						if (blackTurn) { // ������ �÷��̾��� ���̸�
							if (ChessBoard.detectCheck(ChessBoard.blackPlayer)) {
								txtClear();
								JOptionPane.showMessageDialog(frame,
										"üũ �����Դϴ�.", "üũ",
										JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							if (ChessBoard.isMoveCausingCheck(ChessBoard.blackPlayer,
						    		blackPosXY[0], blackPosXY[1], moveToBlackXY[0], moveToBlackXY[1])) {
						    	txtClear();
						    	JOptionPane.showMessageDialog(frame,
						    			"�߸��� �������Դϴ�.", "�߸��� ������",
						    			JOptionPane.WARNING_MESSAGE);
						    	return;
						    }

							int result = app.selectAndMovePiece(blackPosXY[0], blackPosXY[1],
									moveToBlackXY[0], moveToBlackXY[1], "black");

							getMessageByIndex(result, "black");

						} else {
							txtClear();
							JOptionPane.showMessageDialog(frame,
									"��� �����Դϴ�.",
									"�� ����", JOptionPane.WARNING_MESSAGE);

						}
					} catch (IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(frame,
								"�Է� ����(A0 or a0)�� �����ּ���.", "�Է� ����",
								JOptionPane.WARNING_MESSAGE);
						txtClear();
					}
					if (ChessBoard.isCheckmate(ChessBoard.whitePlayer)) { // üũ����Ʈ�� ���
			            JOptionPane.showMessageDialog(frame, "üũ����Ʈ!", "���� ����", JOptionPane.INFORMATION_MESSAGE);
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
		if (result == 0) {// �Է��� ��ǥ(�����̰����ϴ� �⹰�� ��ǥ)�� �⹰�� ������
			JOptionPane.showMessageDialog(frame, "�Է��� ��ǥ�� �⹰�� �������� �ʽ��ϴ�.",
					"��ǥ�� ����", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 1) {// �Է��� ��ǥ�� ü�������� �������̶��
			JOptionPane.showMessageDialog(frame,
					"�Է��Ͻ� ��ǥ�� ü���������� ������ �Ѿ�ϴ�.",
					"��ǥ ���� ����", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 2) {// �ùٸ� �ึ���� �ƴ϶��
			JOptionPane.showMessageDialog(frame,
					"�ùٸ� �ึ���� �ƴմϴ�.",
					"�ึ�� ����", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;
		} else if (result == 3) {
			JOptionPane.showMessageDialog(frame,
					"�÷��̾��� ����� ��ġ�ϴ� �⹰�� �̵���ų �� �ֽ��ϴ�.",
					"���� ����", JOptionPane.WARNING_MESSAGE);
			txtClear();
			return;

		} else if (result == 4) {
			Object[] selectionPieces = { "Queen", "Rook", "Bishop", "Knight" };
			Object input = JOptionPane.showInputDialog(frame, // �θ� ������Ʈ
					"��ü�� �⹰ Ÿ���� �����ϼ���.", // �޽���
					"���θ��", // Ÿ��Ʋ
					JOptionPane.QUESTION_MESSAGE, // �޽��� Ÿ��
					null, // ������
					selectionPieces, // �Է°����� ����� �� �ִ� ���������� �迭
					selectionPieces[1]); // �ʱ� ���ð� , ��Ŀ��

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