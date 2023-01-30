package metal.antenne.ENG;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MetalAntenneEnglish {

	private JFrame frmAntenne;
	private JLabel lbMusicTitel;

	private List<File> songs;
	private int songNumber;
	private File[] files;
	private File file;
	private Media media;
	private MediaPlayer player;
	private JFXPanel panel;

	// basic MP3 music player with javafx/swing components
	// javafx-sdk-19, jdk-17.0.2 required to start this application
	// standalone *.exe file can be created with Launch4j
	// please don't forget the VM arguments: --module-path
	// "classpath\javafx-sdk-19\lib" --add-modules
	// javafx.media,javafx.graphics,javafx.swing

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MetalAntenneEnglish window = new MetalAntenneEnglish();
					window.frmAntenne.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MetalAntenneEnglish() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAntenne = new JFrame();
		frmAntenne.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage(MetalAntenneEnglish.class.getResource("Simpsons-Garage-band-homer.jpg")));
				Object[] options = new Object[] { "Yes, i have to go", "No, i want stay yet" };

				if (JOptionPane.showOptionDialog(frmAntenne, "Do you really want to leave me with the good music?",
						"Finish?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options,
						options[1]) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}

		});
		frmAntenne.setIconImage(
				Toolkit.getDefaultToolkit().getImage(MetalAntenneEnglish.class.getResource("electric-guitar.png")));
		frmAntenne.setTitle("Metal Antenne");
		frmAntenne.setForeground(new Color(255, 0, 0));
		frmAntenne.setFont(new Font("DejaVu Serif", Font.BOLD, 13));
		frmAntenne.setBounds(100, 100, 700, 400);
		frmAntenne.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAntenne.getContentPane().setLayout(null);

		songs = new ArrayList<File>();
		songNumber = 0;
		panel = new JFXPanel();

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!songs.isEmpty()) {
					PlayMusic();
				} else {
					JOptionPane.showMessageDialog(frmAntenne, "No music file loaded!", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnPlay.setBackground(new Color(245, 222, 179));
		btnPlay.setForeground(new Color(160, 82, 45));
		btnPlay.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 15));
		btnPlay.setBounds(38, 284, 110, 30);
		frmAntenne.getContentPane().add(btnPlay);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!songs.isEmpty()) {
					NextMusic();
				} else {
					JOptionPane.showMessageDialog(frmAntenne, "No music file loaded!", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNext.setForeground(new Color(160, 82, 45));
		btnNext.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 15));
		btnNext.setBackground(new Color(245, 222, 179));
		btnNext.setBounds(158, 284, 110, 30);
		frmAntenne.getContentPane().add(btnNext);

		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!songs.isEmpty()) {
					PreviousMusic();
				} else {
					JOptionPane.showMessageDialog(frmAntenne, "No music file loaded!", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnPrevious.setForeground(new Color(160, 82, 45));
		btnPrevious.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 15));
		btnPrevious.setBackground(new Color(245, 222, 179));
		btnPrevious.setBounds(282, 284, 110, 30);
		frmAntenne.getContentPane().add(btnPrevious);

		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!songs.isEmpty()) {
					Pause();
				} else {
					JOptionPane.showMessageDialog(frmAntenne, "No music file loaded!", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnPause.setForeground(new Color(160, 82, 45));
		btnPause.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 15));
		btnPause.setBackground(new Color(245, 222, 179));
		btnPause.setBounds(402, 284, 110, 30);
		frmAntenne.getContentPane().add(btnPause);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!songs.isEmpty()) {
					Stop();
				} else {
					JOptionPane.showMessageDialog(frmAntenne, "No music file loaded!", "Attention",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnStop.setForeground(new Color(160, 82, 45));
		btnStop.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 15));
		btnStop.setBackground(new Color(245, 222, 179));
		btnStop.setBounds(522, 284, 110, 30);
		frmAntenne.getContentPane().add(btnStop);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				frmAntenne.dispatchEvent(new WindowEvent(frmAntenne, WindowEvent.WINDOW_CLOSING));

			}
		});
		btnExit.setForeground(new Color(165, 42, 42));
		btnExit.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		btnExit.setBackground(new Color(245, 245, 220));
		btnExit.setBounds(551, 155, 110, 35);
		frmAntenne.getContentPane().add(btnExit);

		lbMusicTitel = new JLabel("");
		lbMusicTitel.setForeground(new Color(255, 250, 250));
		lbMusicTitel.setFont(new Font("Segoe Script", Font.BOLD, 27));
		lbMusicTitel.setHorizontalAlignment(SwingConstants.CENTER);
		lbMusicTitel.setBounds(26, 51, 620, 60);
		frmAntenne.getContentPane().add(lbMusicTitel);

		JLabel lblBackGround = new JLabel("");
		lblBackGround.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(MetalAntenneEnglish.class.getResource("gitar-bolt-990x556.jpg"))));
		lblBackGround.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackGround.setBounds(0, 0, 686, 337);
		frmAntenne.getContentPane().add(lblBackGround);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(25, 25, 112));
		menuBar.setBackground(new Color(255, 248, 220));
		menuBar.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
		frmAntenne.setJMenuBar(menuBar);

		JMenu mnMusic = new JMenu("Load music");
		mnMusic.setBackground(new Color(255, 228, 181));
		mnMusic.setForeground(new Color(65, 105, 225));
		mnMusic.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 14));
		menuBar.add(mnMusic);

		JMenuItem mtmLoadMusicFromFiles = new JMenuItem("Load music from file(s)");
		mtmLoadMusicFromFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LoadMusicFromFiles();

			}
		});
		mtmLoadMusicFromFiles.setBackground(new Color(255, 255, 224));
		mtmLoadMusicFromFiles.setForeground(new Color(233, 150, 122));
		mtmLoadMusicFromFiles.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		mnMusic.add(mtmLoadMusicFromFiles);

		JSeparator separator = new JSeparator();
		mnMusic.add(separator);

		JMenuItem mtmLoadMusicFromDirectory = new JMenuItem("Load music folder");
		mtmLoadMusicFromDirectory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LoadMusicFromDirectory();

			}
		});
		mtmLoadMusicFromDirectory.setForeground(new Color(233, 150, 122));
		mtmLoadMusicFromDirectory.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		mtmLoadMusicFromDirectory.setBackground(new Color(255, 255, 224));
		mnMusic.add(mtmLoadMusicFromDirectory);
	}

	private void Stop() {

		player.stop();

	}

	private void PreviousMusic() {

		player.stop();
		if (songNumber > 0) {
			songNumber--;
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			PlayMusic();
		} else {
			songNumber = 0;
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			PlayMusic();
		}

	}

	private void NextMusic() {

		player.stop();
		if (songNumber < (songs.size() - 1)) {
			songNumber++;
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			PlayMusic();
		} else {
			songNumber = 0;
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			PlayMusic();
		}

	}

	private void LoadMusicFromDirectory() {

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Search music folder");
		chooser.setApproveButtonText("Load music");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = chooser.showOpenDialog(frmAntenne);

		if (returnValue == chooser.APPROVE_OPTION) {

			file = chooser.getSelectedFile();
			files = file.listFiles();

			for (File file : files) {
				if (file.toString().contains("mp3")) {
					songs.add(file);
				}
			}

			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);

		}

	}

	private void LoadMusicFromFiles() {

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Search music file(s)");
		chooser.setApproveButtonText("Load music");
		chooser.setMultiSelectionEnabled(true);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Music files", "mp3");
		chooser.setFileFilter(filter);

		int returnValue = chooser.showOpenDialog(frmAntenne);

		if (returnValue == chooser.APPROVE_OPTION) {

			files = chooser.getSelectedFiles();

			for (File file : files) {
				if (file.toString().contains("mp3")) {
					songs.add(file);
				}
			}

			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);

		}

	}

	private synchronized void PlayMusic() {

		player.play();
		lbMusicTitel.setText(songs.get(songNumber).getName());
		player.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {

				player.stop();

				if (songNumber < (songs.size() - 1)) {
					songNumber++;
					media = new Media(songs.get(songNumber).toURI().toString());
					player = new MediaPlayer(media);
					PlayMusic();
				} else {
					player.stop();
				}

			}
		});

	}

	private synchronized void Pause() {

		player.pause();

	}

}
