import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/* 
 * @author Alyssa Romero 2019 
 */

public class GameViewer {
	
	private static Box chosenBox;
	private static FlashCard chosenCard; 
	private static String chosenSide;
	private static Background background;
	
	public static void drawMainScreen(Game thisgame) {
		JFrame mainframe = new JFrame(thisgame.translate(thisgame.getLangauge(), "leitner flashcard application"));
		mainframe.setSize(500, 500);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor());
	
		JPanel compbackground = new JPanel();
		compbackground.setLayout(null);
		compbackground.setSize(500, 500);
		compbackground.setVisible(true);
				
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
				mainframe.dispose();
				drawGameScreen(thisgame);
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
				mainframe.dispose();
				drawManagementScreen(thisgame);
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
				mainframe.dispose();
				drawSettingsScreen(thisgame);
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
		
		Background background = new Background(thisgame.getAppColor());
		
		/*JPanel background = new JPanel();
		background.setBackground(thisgame.getAppColor());
		background.setLayout(null);
		background.setSize(500, 500);
		background.setVisible(true);*/
		
		JPanel displaybackground = new JPanel();
		displaybackground.setBackground(Color.WHITE);
		displaybackground.setLayout(null);
		displaybackground.setSize(400, 300);
		displaybackground.setLocation(50, 75);
		displaybackground.setVisible(true);
		background.add(displaybackground);
		
		JTextField userinput = new JTextField();
		userinput.setSize(250, 50);
		userinput.setLayout(null);
		userinput.setLocation(125, 400);
		
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
				gameframe.dispose();
				drawMainScreen(thisgame);
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
	
	public static void drawSettingsScreen(Game thisgame) {
		JFrame settingsmenu = new JFrame(thisgame.translate(thisgame.getLangauge(), "settings"));
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
		Background background = new Background(thisgame.getAppColor());
		
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
				settingsmenu.dispose();
				drawMainScreen(thisgame);
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
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				else if (languages.getSelectedIndex() == 1) {
					thisgame.setLanguage("deutsch");
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				else {
					thisgame.setLanguage("magyar");
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
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
					thisgame.setAppColor(new Color(153, 204, 255));
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				//Settings Background Color to Red
				else if (colors.getSelectedIndex() == 2) {
					thisgame.setAppColor(new Color (255, 153, 153));
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				//Setting Background Color to Yellow
				else if (colors.getSelectedIndex() == 3) {
					thisgame.setAppColor(new Color(255, 255, 153));
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				//Settings Background Color to Green
				else if (colors.getSelectedIndex() == 4){
					thisgame.setAppColor(new Color(204, 255, 153));	
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				//Settings Backgroun Color to Purple
				else if (colors.getSelectedIndex() == 5) {
					thisgame.setAppColor(new Color(204, 153, 255));	
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
				}
				//Setting Background Color to Gray
				else if (colors.getSelectedIndex() == 6) {
					thisgame.setAppColor(new Color(220, 220, 220));
					settingsmenu.dispose();
					drawSettingsScreen(thisgame);
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
		
		Background background = new Background(thisgame.getAppColor());
		
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
		
		table.setSize(500, 300);
		table.setLocation(0, 200);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		//table.setFillsViewportHeight(true);
		
		//JScrollPane jsp = new JScrollPane(table);
		//table.add(jsp);
		
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
				manframe.dispose();
				drawMainScreen(thisgame);
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
				//System.out.println(thisgame.countCards());
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
					//System.out.println(thisgame.countCards());
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
					//System.out.println(thisgame.countCards());
					
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
	
	public static void playBackgroundMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		String filename = "background_clip1.wav";
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
