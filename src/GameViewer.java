import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/* 
 * @author Alyssa Romero 2019 
 */

public class GameViewer {
	
	private static Box chosenBox;
	private static FlashCard chosenCard; 
	private static String chosenSide;
	
	private static final Color YELLOW = new Color(252, 247, 222);
	private static final Color RED = new Color(253, 223, 223);
	private static final Color BLUE = new Color(222, 243, 253);
	private static final Color GREEN = new Color(222, 253, 224);
	private static final Color PURPLE = new Color(240, 222, 253);
	private static final Color GRAY = new Color(192, 192, 192);
	
	private static AudioInputStream audioIn;
	private static Clip clip;
	
	public static void drawMainScreen(Game thisgame) {
		JFrame mainframe = new JFrame(thisgame.translate(thisgame.getLangauge(), "leitner flashcard application"));
		mainframe.setSize(500, 500);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor(), thisgame.getBackgroundPattern());
				
		JLabel welcome = new JLabel(thisgame.translate(thisgame.getLangauge(), "leitner flashcard application"));
		welcome.setSize(375, 50);
		welcome.setLocation((int) 62.5, 50);
		welcome.setLayout(new BorderLayout());
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setVerticalAlignment(JLabel.CENTER);
		welcome.setFont(new Font("Serif", Font.BOLD, 20));
		
		JButton playbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "play"));
		playbutton.setSize(200, 100);
		playbutton.setLocation(150, 150);
		playbutton.setLayout(null);
		playbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				drawGameScreen(thisgame);
				mainframe.dispose();
				//drawGameScreen(thisgame);
			}
		});
				
		JButton manageButton = new JButton(thisgame.translate(thisgame.getLangauge(), "manage"));
		manageButton.setSize(150, 50);
		manageButton.setLayout(null);
		manageButton.setLocation(75, 300);
		manageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				//mainframe.dispose();
				drawManagementScreen(thisgame);
				mainframe.dispose();
			}
		});
		
		JButton settingsbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "settings"));
		settingsbutton.setSize(150, 50);
		settingsbutton.setLayout(null);
		settingsbutton.setLocation(275, 300);
		settingsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				//mainframe.dispose();
				drawSettingsScreen2(thisgame);
				mainframe.dispose();
			}
			
		});
		
		background.add(welcome);
		welcome.setVisible(true);
		background.add(manageButton);
		manageButton.setVisible(true);
		background.add(playbutton);
		playbutton.setVisible(true);
		background.add(settingsbutton);
		settingsbutton.setVisible(true);
		
		mainframe.add(background);
		mainframe.setVisible(true);
	}
	
public static void drawGameScreen(Game thisgame) {
		
		for (int i = 0; i < thisgame.getCardset().size(); ++i) {
			thisgame.getBoxset().get(0).addCard(thisgame.getCardset().get(i));
		}

		JFrame gameframe = new JFrame();
		gameframe.setSize(500, 500);
		gameframe.setLocationRelativeTo(null);
		gameframe.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor(), thisgame.getBackgroundPattern());
		
		JPanel displaybackground = new JPanel();
		displaybackground.setBackground(Color.WHITE);
		displaybackground.setLayout(null);
		displaybackground.setSize(400, 300);
		displaybackground.setLocation(50, 75);
		displaybackground.setVisible(true);
		displaybackground.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		background.add(displaybackground);
		
		JTextField userinput = new JTextField();
		userinput.setSize(250, 50);
		userinput.setLayout(null);
		userinput.setLocation(125, 400);
		userinput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel display = new JLabel();
		display.setHorizontalAlignment(JLabel.CENTER);
		display.setVerticalAlignment(JLabel.CENTER);
		display.setLayout(new BorderLayout());
		displaybackground.add(display, BorderLayout.CENTER);
		display.setSize(400, 300);
		display.setVisible(true);
		
		if (thisgame.getCardset().isEmpty()) {
			display.setText(thisgame.translate(thisgame.getLangauge(), "Whoops! There are No Cards to Study!"));
			display.setFont(new Font("Serif", Font.BOLD, 15));
			userinput.setEditable(false);
		}
		else {
			userinput.setEditable(true);
			chosenBox = thisgame.chooseBox(thisgame.getBoxset());
			while (chosenBox.isEmpty()) {
				chosenBox = thisgame.chooseBox(thisgame.getBoxset());
			}
			chosenCard = chosenBox.chooseCard();
			chosenSide = chosenCard.chooseSide();
			display.setText(chosenSide);
			display.setFont(new Font("Serif", Font.BOLD, 25));
			display.setHorizontalTextPosition(SwingConstants.CENTER);
			display.setVerticalTextPosition(SwingConstants.CENTER);
		}
		
		JButton exitbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "exit"));
		exitbutton.setSize(100, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(10,  10);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				thisgame.restartGame();
				//gameframe.dispose();
				drawMainScreen(thisgame);
				gameframe.dispose();
			}
		});
		
		JButton flipbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "flip"));
		flipbutton.setSize(100, 50);
		flipbutton.setLayout(null);
		flipbutton.setLocation(10, 400);
		flipbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				if (chosenSide.compareTo(chosenCard.getFront()) == 0) {
					display.setText(chosenCard.getBack());
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            public void run() {
					            	userinput.setText("");
					            	userinput.requestFocus();
					                display.setBackground(Color.WHITE);
					                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
					                	System.out.println("Game Finished");
					                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
					        			display.setFont(new Font("Serif", Font.BOLD, 15));
					                	thisgame.restartGame();
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					        			display.setFont(new Font("Serif", Font.BOLD, 25));
					                }
					            }
					        }, 
					        1500 
					);
				}
				else {
					display.setText(chosenCard.getFront());
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            public void run() {
					            	userinput.setText("");
					            	userinput.requestFocus();
					                display.setBackground(Color.WHITE);
					                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
					                	System.out.println("Game Finished");
					                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
					        			display.setFont(new Font("Serif", Font.BOLD, 15));
					                	thisgame.restartGame();
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					        			display.setFont(new Font("Serif", Font.BOLD, 25));
					                }
					            }
					        }, 
					        1500 
					);
				}
			}
			
		});
		
		JButton submitbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "submit"));
		submitbutton.setSize(100, 50);
		submitbutton.setLayout(null);
		submitbutton.setLocation(390, 400);
		
		submitbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				
				if (thisgame.checkAnswer(chosenCard, chosenSide, userinput.getText())) {
					displaybackground.setBackground(Color.GREEN);
					thisgame.moveForward(chosenCard, chosenBox);
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            
					            public void run() {
					            	userinput.setText("");
					            	userinput.requestFocus();
					            	displaybackground.setBackground(Color.WHITE);
					                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
					                	System.out.println("Game Finished");
					                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
					        			display.setFont(new Font("Serif", Font.BOLD, 15));
					                	thisgame.restartGame();
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					        			display.setFont(new Font("Serif", Font.BOLD, 25));
					                }
					            }
					        }, 
					        1500 
					);
				}
				else {
					displaybackground.setBackground(Color.RED);
					thisgame.moveBackward(chosenCard, chosenBox);
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            
					            public void run() {
					            	userinput.setText("");
					            	userinput.requestFocus();
					                displaybackground.setBackground(Color.WHITE);
					                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
					                	System.out.println("Game Finished");
					                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
					        			display.setFont(new Font("Serif", Font.BOLD, 15));
					                	thisgame.restartGame();
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					        			display.setFont(new Font("Serif", Font.BOLD, 25));
					                }
					            }
					        }, 
					        1000 
					);
				}
			}
		});
		
		submitbutton.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER)  {
					if (thisgame.checkAnswer(chosenCard, chosenSide, userinput.getText())) {
						displaybackground.setBackground(Color.GREEN);
						thisgame.moveForward(chosenCard, chosenBox);
						new java.util.Timer().schedule( 
						        new java.util.TimerTask() {
						            public void run() {
						            	userinput.setText("");
						            	userinput.requestFocus();
						            	displaybackground.setBackground(Color.WHITE);
						                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
						                	System.out.println("Game Finished");
						                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
						        			display.setFont(new Font("Serif", Font.BOLD, 15));
						                	thisgame.restartGame();
						                }
						                else {
						                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	}
						                	chosenCard = chosenBox.chooseCard();
						                	chosenSide = chosenCard.chooseSide();
						                	display.setText(chosenSide);
						        			display.setFont(new Font("Serif", Font.BOLD, 25));
						                }
						            }
						        }, 
						        1500 
						);
					}
					else {
						displaybackground.setBackground(Color.RED);
						thisgame.moveBackward(chosenCard, chosenBox);
						new java.util.Timer().schedule( 
						        new java.util.TimerTask() {
						            public void run() {
						            	userinput.setText("");
						            	userinput.requestFocus();
						                displaybackground.setBackground(Color.WHITE);
						                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
						                	System.out.println("Game Finished");
						                	display.setText(thisgame.translate(thisgame.getLangauge(), "Congratulations! Session Complete!!"));
						        			display.setFont(new Font("Serif", Font.BOLD, 15));
						                	thisgame.restartGame();						                }
						                else {
						                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	}
						                	chosenCard = chosenBox.chooseCard();
						                	chosenSide = chosenCard.chooseSide();
						                	display.setText(chosenSide);
						        			display.setFont(new Font("Serif", Font.BOLD, 25));
						                }
						            }
						        }, 
						        1500 
						);
					};
				}
				
			}

			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
		});
				
		background.add(exitbutton);
		exitbutton.setVisible(true);
		background.add(flipbutton);
		flipbutton.setVisible(true);
		background.add(submitbutton);
		submitbutton.setVisible(true);
		background.add(userinput);
		userinput.setVisible(true);
		
		gameframe.add(background);
		gameframe.setResizable(false);
		gameframe.setVisible(true);
		userinput.requestFocusInWindow();
	}

	public static void drawSettingsScreen2(Game thisgame) {
		JFrame settingsmenu = new JFrame(thisgame.translate(thisgame.getLangauge(), "settings"));
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor(), thisgame.getBackgroundPattern());
		
		JLabel SettingsTitle = new JLabel(thisgame.translate(thisgame.getLangauge(), "settings"));
		SettingsTitle.setSize(375, 50);
		SettingsTitle.setLocation((int) 62.5, 10);
		SettingsTitle.setLayout(new BorderLayout());
		SettingsTitle.setHorizontalAlignment(JLabel.CENTER);
		SettingsTitle.setVerticalAlignment(JLabel.CENTER);
		SettingsTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		JButton exitbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "exit"));
		exitbutton.setSize(100, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(10, 10);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				saveGame();
				//settingsmenu.dispose();
				drawMainScreen(thisgame);
				settingsmenu.dispose();
			}
		});
		
		//Creating Separator for Music Settings//
		JPanel WhiteBackground = new JPanel();
		WhiteBackground.setSize(225, 400);
		WhiteBackground.setLocation(0, 100);
		WhiteBackground.setBackground(Color.WHITE);
		WhiteBackground.setLayout(null);
		WhiteBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel MusicSettingsTitle = new JLabel(thisgame.translate(thisgame.getLangauge(), "music settings"));
		MusicSettingsTitle.setSize(225, 25);
		MusicSettingsTitle.setLocation(0, 125);
		MusicSettingsTitle.setLayout(new BorderLayout());
		MusicSettingsTitle.setFont(new Font("Serif", Font.BOLD, 20));
		MusicSettingsTitle.setHorizontalAlignment(JLabel.CENTER);
		MusicSettingsTitle.setVerticalAlignment(JLabel.CENTER);
		
		JButton clip1 = new JButton(thisgame.translate(thisgame.getLangauge(), "sound") + " 1");
		clip1.setSize(175, 50);
		clip1.setLocation(25,  175);
		try {
			switchAudio(clip1, thisgame, "background_clip1.wav");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		JButton clip2 =  new JButton(thisgame.translate(thisgame.getLangauge(), "sound") + " 2");
		clip2.setSize(175, 50);
		clip2.setLocation(25,250);
		try {
			switchAudio(clip2, thisgame, "background_clip2.wav");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		JButton clip3 = new JButton(thisgame.translate(thisgame.getLangauge(), "sound") + " 3");
		clip3.setSize(175,  50);
		clip3.setLocation(25, 325);
		try {
			switchAudio(clip3, thisgame, "background_clip3.wav");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		JButton clip4 = new JButton(thisgame.translate(thisgame.getLangauge(), "mute music"));
		clip4.setSize(175, 50);
		clip4.setLocation(25, 400);
		try {
			switchAudio(clip4, thisgame, "Mute Music");
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		//BACKGROUND COLOR SETTINGS OPTIONS//
		JPanel WhiteBackground2 = new JPanel();
		WhiteBackground2.setSize(225, 400);
		WhiteBackground2.setLocation(275, 100);
		WhiteBackground2.setBackground(Color.WHITE);
		WhiteBackground2.setLayout(null);
		WhiteBackground2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel ThemeSettings = new JLabel(thisgame.translate(thisgame.getLangauge(), "color settings"));
		ThemeSettings.setSize(225, 25);
		ThemeSettings.setLocation(275, 125);
		ThemeSettings.setLayout(new BorderLayout());
		ThemeSettings.setFont(new Font("Serif", Font.BOLD, 20));
		ThemeSettings.setHorizontalAlignment(JLabel.CENTER);
		ThemeSettings.setVerticalAlignment(JLabel.CENTER);
		
		JButton redbutton = new JButton();
		redbutton.setSize(25, 25);
		redbutton.setLocation(300, 160);
		redbutton.setLayout(null);
		redbutton.setBorderPainted(false);
		redbutton.setOpaque(true);
		redbutton.setBackground(RED);
		colorButtonAction(settingsmenu, redbutton, RED, thisgame);
		
		JButton greenbutton = new JButton();
		greenbutton.setSize(25, 25);
		greenbutton.setLocation(400, 160);
		greenbutton.setLayout(null);
		greenbutton.setBackground(GREEN);
		greenbutton.setOpaque(true);
		greenbutton.setBorderPainted(false);
		colorButtonAction(settingsmenu, greenbutton, GREEN, thisgame);
		
		JButton yellowbutton = new JButton();
		yellowbutton.setSize(25, 25);
		yellowbutton.setLocation(350, 160);
		yellowbutton.setLayout(null);
		yellowbutton.setBorderPainted(false);
		yellowbutton.setOpaque(true);
		yellowbutton.setBackground(YELLOW);
		colorButtonAction(settingsmenu, yellowbutton, YELLOW, thisgame);
		
		JButton bluebutton = new JButton();
		bluebutton.setSize(25, 25);
		bluebutton.setLocation(450, 160);
		bluebutton.setLayout(null);
		bluebutton.setBorderPainted(false);
		bluebutton.setOpaque(true);
		bluebutton.setBackground(BLUE);
		colorButtonAction(settingsmenu, bluebutton, BLUE, thisgame);
		
		JButton purplebutton = new JButton();
		purplebutton.setSize(25, 25);
		purplebutton.setLocation(350, 160);
		purplebutton.setLayout(null);
		purplebutton.setBorderPainted(false);
		purplebutton.setOpaque(true);
		purplebutton.setBackground(PURPLE);
		colorButtonAction(settingsmenu, purplebutton, PURPLE, thisgame);
		
		//BACKGROUND PATTERN SETTINGS OPTIONS//
		JLabel patternsettings = new JLabel(thisgame.translate(thisgame.getLangauge(), "pattern settings"));
		patternsettings.setSize(225, 25);
		patternsettings.setLocation(275, 195);
		patternsettings.setLayout(null);
		patternsettings.setFont(new Font("Serif", Font.BOLD, 20));
		patternsettings.setHorizontalAlignment(JLabel.CENTER);
		patternsettings.setVerticalAlignment(JLabel.CENTER);
		
		JButton pattern1 = new JButton("1");
		pattern1.setSize(50, 50);
		pattern1.setLocation(290, 230);
		pattern1.setLayout(null);
		patternButtonAction(settingsmenu, pattern1, "pattern1", thisgame);
		
		JButton pattern2 = new JButton("2");
		pattern2.setSize(50, 50);
		pattern2.setLocation((int)362.5, 230);
		pattern2.setLayout(null);
		patternButtonAction(settingsmenu, pattern2, "pattern2", thisgame);
		
		JButton pattern3 = new JButton("3");
		pattern3.setSize(50, 50);
		pattern3.setLocation(435, 230);
		pattern3.setLayout(null);
		patternButtonAction(settingsmenu, pattern3, "pattern3", thisgame);
		
		JLabel languageOptions = new JLabel(thisgame.translate(thisgame.getLangauge(), "language options"));
		languageOptions.setSize(225, 25);
		languageOptions.setLocation(275, 290);
		languageOptions.setLayout(null);
		languageOptions.setFont(new Font("Serif", Font.BOLD, 20));
		languageOptions.setHorizontalAlignment(JLabel.CENTER);
		languageOptions.setVerticalAlignment(JLabel.CENTER);
		
		JButton englishbutton = new JButton("english".toUpperCase());
		englishbutton.setSize(175, 25);
		englishbutton.setLocation(300, 325);
		englishbutton.setLayout(null);
		languageButtonAction(settingsmenu, englishbutton, "english", thisgame);
		
		JButton germanbutton = new JButton("deutsch".toUpperCase());
		germanbutton.setSize(175,25);
		germanbutton.setLocation(300, 360);
		germanbutton.setLayout(null);
		languageButtonAction(settingsmenu, germanbutton, "deutsch", thisgame);
		
		JButton hungarianbutton = new JButton("magyar".toUpperCase());
		hungarianbutton.setSize(175, 25);
		hungarianbutton.setLocation(300, 395);
		hungarianbutton.setLayout(null);
		languageButtonAction(settingsmenu, hungarianbutton, "magyar", thisgame);
		
		JButton spanishbutton = new JButton("español".toUpperCase());
		spanishbutton.setSize(175, 25);
		spanishbutton.setLocation(300, 430);
		spanishbutton.setLayout(null);
		languageButtonAction(settingsmenu, spanishbutton, "español", thisgame);

		background.add(languageOptions);
		languageOptions.setVisible(true);
		background.add(englishbutton);
		englishbutton.setVisible(true);
		background.add(germanbutton);
		germanbutton.setVisible(true);
		background.add(hungarianbutton);
		hungarianbutton.setVisible(true);
		background.add(spanishbutton);
		spanishbutton.setVisible(true);
		
		background.add(patternsettings);
		patternsettings.setVisible(true);
		background.add(pattern1);
		pattern1.setVisible(true);
		background.add(pattern2);
		pattern2.setVisible(true);
		background.add(pattern3);
		pattern3.setVisible(true);
		
		background.add(redbutton);
		redbutton.setVisible(true);
		background.add(bluebutton);
		bluebutton.setVisible(true);
		background.add(greenbutton);
		greenbutton.setVisible(true);
		background.add(yellowbutton);
		yellowbutton.setVisible(true);
		background.add(MusicSettingsTitle);
		MusicSettingsTitle.setVisible(true);
		background.add(ThemeSettings);
		ThemeSettings.setVisible(true);
		
		background.add(clip1);
		clip1.setVisible(true);
		background.add(clip2);
		clip2.setVisible(true);
		background.add(clip3);
		clip3.setVisible(true);
		background.add(clip4);
		clip4.setVisible(true);
		
		background.add(SettingsTitle);
		SettingsTitle.setVisible(true);
		background.add(WhiteBackground);
		WhiteBackground.setVisible(true);
		background.add(WhiteBackground2);
		WhiteBackground2.setVisible(true);
		background.add(exitbutton);
		exitbutton.setVisible(true);
		
		settingsmenu.add(background);
		settingsmenu.setResizable(false);
		settingsmenu.setVisible(true);
	}
	
	public static void drawSettingsScreen(Game thisgame) {
		JFrame settingsmenu = new JFrame(thisgame.translate(thisgame.getLangauge(), "settings"));
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor(), thisgame.getBackgroundPattern());
		
		JLabel SettingsTitle = new JLabel(thisgame.translate(thisgame.getLangauge(), "settings"));
		SettingsTitle.setSize(375, 50);
		SettingsTitle.setLocation((int) 62.5, 10);
		SettingsTitle.setLayout(new BorderLayout());
		SettingsTitle.setHorizontalAlignment(JLabel.CENTER);
		SettingsTitle.setVerticalAlignment(JLabel.CENTER);
		SettingsTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		JButton exitbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "exit"));
		exitbutton.setSize(100, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(10, 10);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				saveGame();
				//settingsmenu.dispose();
				drawMainScreen(thisgame);
				settingsmenu.dispose();
			}
		});
		
		JLabel languageOptions = new JLabel(thisgame.translate(thisgame.getLangauge(), "language"));
		languageOptions.setSize(250, 50);
		languageOptions.setBackground(Color.WHITE);
		languageOptions.setLocation(100, 200);
		languageOptions.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		languageOptions.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		
		String[] languagechoices = {"English", "Deutsch", "Magyar"};
		JComboBox<String> languages = new JComboBox<String>(languagechoices);
		languages.setSize(100, 50);
		languages.setLocation(250, 200);
		languages.getSelectedIndex();
		languages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (languages.getSelectedIndex() == 0) {
					thisgame.setLanguage("english");
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				else if (languages.getSelectedIndex() == 1) {
					thisgame.setLanguage("deutsch");
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				else {
					thisgame.setLanguage("magyar");
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
			}
		});
		
		JLabel colorOptions = new JLabel(thisgame.translate(thisgame.getLangauge(), "theme"));
		colorOptions.setSize(250, 50);
		colorOptions.setBackground(Color.WHITE);
		colorOptions.setLocation(100, 100);
		colorOptions.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		colorOptions.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		
		String[] colorchoices = { 
				thisgame.translate(thisgame.getLangauge(), "color"), thisgame.translate(thisgame.getLangauge(), "blue"), 
				thisgame.translate(thisgame.getLangauge(), "red"), thisgame.translate(thisgame.getLangauge(), "yellow"), 
				thisgame.translate(thisgame.getLangauge(), "green"), thisgame.translate(thisgame.getLangauge(), "purple"), 
				thisgame.translate(thisgame.getLangauge(), "gray")
				};
		
		JComboBox<String> colors = new JComboBox<String>(colorchoices);
		colors.setSize(100, 50);
		colors.setLocation(250, 100);
		colors.getSelectedIndex();
		colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Settings Background Color to Blue
				if (colors.getSelectedIndex() == 1) {
					thisgame.setAppColor(BLUE);
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				//Settings Background Color to Red
				else if (colors.getSelectedIndex() == 2) {
					thisgame.setAppColor(RED);
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				//Setting Background Color to Yellow
				else if (colors.getSelectedIndex() == 3) {
					thisgame.setAppColor(YELLOW);
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				//Settings Background Color to Green
				else if (colors.getSelectedIndex() == 4){
					thisgame.setAppColor(GREEN);	
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				//Settings Background Color to Purple
				else if (colors.getSelectedIndex() == 5) {
					thisgame.setAppColor(PURPLE);	
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
				//Setting Background Color to Gray
				else if (colors.getSelectedIndex() == 6) {
					thisgame.setAppColor(GRAY);
					//settingsmenu.dispose();
					drawSettingsScreen(thisgame);
					settingsmenu.dispose();
				}
			}			
		});
		
		
		background.add(SettingsTitle);
		SettingsTitle.setVisible(true);
		background.add(colors);
		colors.setVisible(true);
		background.add(languages);
		languages.setVisible(true);
		background.add(exitbutton);
		exitbutton.setVisible(true);
		background.add(colorOptions);
		colorOptions.setVisible(true);
		background.add(languageOptions);
		languageOptions.setVisible(true);
		
		settingsmenu.add(background);
		settingsmenu.setResizable(false);
		settingsmenu.setVisible(true);
	}
	
	public static void drawManagementScreen(Game thisgame) {
		JFrame manframe = new JFrame();
		manframe.setSize(500, 500);
		manframe.setLayout(null);
		manframe.setLocationRelativeTo(null);
		
		Background background = new Background(thisgame.getAppColor(), thisgame.getBackgroundPattern());
		
		JLabel ManagementTitle = new JLabel(thisgame.translate(thisgame.getLangauge(), "manage cards"));
		ManagementTitle.setSize(375, 50);
		ManagementTitle.setLocation((int) 62.5, 10);
		ManagementTitle.setLayout(new BorderLayout());
		ManagementTitle.setHorizontalAlignment(JLabel.CENTER);
		ManagementTitle.setVerticalAlignment(JLabel.CENTER);
		ManagementTitle.setFont(new Font("Serif", Font.BOLD, 25));
		
		String[] colHeadings = {thisgame.translate(thisgame.getLangauge(), "front"), thisgame.translate(thisgame.getLangauge(), "back")};
		
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.setSize(500, 300);
		table.setLocation(0, 200);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
		//Automatically Add Any Pre-existing FlashCards to Table//
		for (int i = 0; i < thisgame.getCardset().size(); ++i) {
			model.addRow(new Object[] {thisgame.getCardset().get(i).getFront(), thisgame.getCardset().get(i).getBack()});			
		}
		
		JTextField frontfield = new JTextField();
		frontfield.setSize(150, 50);
		frontfield.setLayout(null);
		frontfield.setLocation(100, 75);		
		
		JTextField backfield = new JTextField();
		backfield.setSize(150, 50);
		backfield.setLayout(null);
		backfield.setLocation(250, 75);
		
		JButton exitbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "exit"));
		exitbutton.setSize(100, 50);
		exitbutton.setLocation(0, 0);
		exitbutton.setLayout(null);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				saveGame();
				//manframe.dispose();
				drawMainScreen(thisgame);
				manframe.dispose();
			}
		});
		
		JButton removebutton = new JButton(thisgame.translate(thisgame.getLangauge(), "remove"));
		removebutton.setSize(100,50);
		removebutton.setLayout(null);
		removebutton.setLocation(150, 125);
		removebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				
				int row = table.getSelectedRow();
				String frontValue = table.getModel().getValueAt(row,  0).toString();
				String backValue = table.getModel().getValueAt(row, 1).toString();
				((DefaultTableModel)table.getModel()).removeRow(row);
				
				int cardindex = thisgame.findFlashCard(frontValue, backValue);
				thisgame.getCardset().remove(cardindex);
				frontfield.requestFocus();
			}
		});
		
		removebutton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					try {
						buttonSound();
					} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
						System.out.println(e1.getMessage());
					}
					int row = table.getSelectedRow();
					String frontValue = table.getModel().getValueAt(row,  0).toString();
					String backValue = table.getModel().getValueAt(row, 1).toString();
					((DefaultTableModel)table.getModel()).removeRow(row);
					
					int cardindex = thisgame.findFlashCard(frontValue, backValue);
					thisgame.getCardset().remove(cardindex);
					frontfield.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		
		JButton addbutton = new JButton(thisgame.translate(thisgame.getLangauge(), "add"));
		addbutton.setSize(100, 50);
		addbutton.setLayout(null);
		addbutton.setLocation(250, 125);
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				
				if (frontfield.getText().compareTo("") == 0) {
					System.out.println("Front was Not Given!");
				}
				if (backfield.getText().compareTo("") == 0) {
					System.out.println("Back was Not Given!");
				}
				else {
					thisgame.getCardset().add(new FlashCard(frontfield.getText(), backfield.getText()));					
					model.addRow(new Object[] {frontfield.getText(), backfield.getText()});
					frontfield.setText("");
					backfield.setText("");
				}
				frontfield.requestFocus();
			}
		});
		
		addbutton.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
		
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (frontfield.getText().compareTo("") == 0) {
						System.out.println("Front was Not Given!");
					}
					if (backfield.getText().compareTo("") == 0) {
						System.out.println("Back was Not Given!");
					}
					else {
						thisgame.getCardset().add(new FlashCard(frontfield.getText(), backfield.getText()));
						//System.out.println(thisgame.countCards());
						model.addRow(new Object[] {frontfield.getText(), backfield.getText()});
						frontfield.setText("");
						backfield.setText("");
					}
					frontfield.requestFocus();
				}
				
			}

			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			
		});
		
		background.add(ManagementTitle);
		ManagementTitle.setVisible(true);
		background.add(table);
		table.setVisible(true);
		background.add(removebutton);
		removebutton.setVisible(true);
		background.add(addbutton);
		addbutton.setVisible(true);
		background.add(exitbutton);
		exitbutton.setVisible(true);
		background.add(frontfield);
		frontfield.setVisible(true);
		background.add(backfield);
		backfield.setVisible(true);
		
		manframe.add(background);
		manframe.setResizable(false);
		manframe.setVisible(true);
		frontfield.requestFocusInWindow();
	}

	public static void saveGame() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
			oos.writeObject(GameTester.getGame());
			oos.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public static void buttonSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
		String filename = "button_click.wav";
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void playBackgroundMusic(Game game) throws IOException, UnsupportedAudioFileException, LineUnavailableException {		
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(game.getMusicFile()).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void colorButtonAction(JFrame frame, JButton button, Color color, Game thisgame) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}

				thisgame.setAppColor(color);
				drawSettingsScreen2(thisgame);
				frame.dispose();
			}
		});
	}
	
	public static void patternButtonAction(JFrame frame, JButton button, String pattern, Game thisgame) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				thisgame.setBackgroundPattern(pattern);
				drawSettingsScreen2(thisgame);
				frame.dispose();
			}
		});
	}
	
	public static void languageButtonAction(JFrame frame, JButton button, String language, Game thisgame) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				thisgame.setLanguage(language);
				drawSettingsScreen2(thisgame);
				frame.dispose();
			}
		});
	}
	
	public static void switchAudio(JButton button, Game thisgame, String filename) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonSound();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					System.out.println(e1.getMessage());
				}
				if (filename.compareTo("Mute Music") == 0) {
					thisgame.setMusicFile("");
					clip.stop();
				}
				else if (filename.compareTo(thisgame.getMusicFile()) == 0) {}
				else {
					if (thisgame.getMusicFile().compareTo("") == 0) {
						thisgame.setMusicFile(filename);
						try {
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioIn);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();
						} catch(Exception e1) {
							System.out.println(e1.getMessage());
						}
					}
					else {
						clip.stop();
						thisgame.setMusicFile(filename);
						try {
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioIn);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();
						} catch(Exception e1) {
							System.out.println(e1.getMessage());
						}
					}
				}
			}
		});
	}
}