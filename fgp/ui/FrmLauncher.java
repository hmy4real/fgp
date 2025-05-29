package fgp.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import fgp.engine.GameEngine;

/**
 * @author Mr. Hapke
 */
public class FrmLauncher extends JFrame {

	private static final long serialVersionUID = 3811418719500055475L;
	private JPanel contentPane;
	private GameEngine game;
	private JTextArea txtReadme;

	/**
	 * Launch the application.
	 */
	public static void launch(GameEngine game) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrmLauncher frame = new FrmLauncher(game);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmLauncher(GameEngine game) {
		if (game == null) {
			System.err.println("You must create your game by passing it to FrmLauncher.launch(new YourGame())");
		}
		this.game = game;
		this.game.init();
		setTitle(game.getGameTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnStart = new JButton("Start Game!");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.newGame();
				FrmGame frame = new FrmGame(game);
				frame.setVisible(true);
				FrmLauncher.this.setVisible(false);
			}
		});
		btnStart.setFont(new Font("Candara", Font.BOLD, 20));
		btnStart.setBounds(10, 274, 239, 47);
		contentPane.add(btnStart);

		txtReadme = new JTextArea();
		txtReadme.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		txtReadme.setWrapStyleWord(true);
		txtReadme.setLineWrap(true);
		txtReadme.setText(
				"WASD - Move\r\nClick - Shoot");
		txtReadme.setBounds(10, 11, 239, 256);
		contentPane.add(txtReadme);
	}
}
