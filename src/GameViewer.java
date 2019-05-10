import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GameViewer {
	
	private static Box chosenBox; //does not need to be saved
	private static FlashCard chosenCard; //does not need to be saved
	private static String chosenSide; //does not need to be saved

	public static void drawMainScreen(Game thisgame) {
		JFrame mainframe = new JFrame("FLASHCARD APPLICATION");
		mainframe.setSize(500, 500);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(null);
		
		JPanel background = new JPanel();
		background.setBackground(thisgame.getAppColor());
		background.setLayout(null);
		background.setSize(500, 500);
		background.setVisible(true);
		
		JButton playbutton = new JButton("Play Game");
		playbutton.setSize(200, 100);
		playbutton.setLocation(150, 150);
		playbutton.setLayout(null);
		playbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.dispose();
				drawGameScreen(thisgame);
			}
		});
				
		JButton manageButton = new JButton("Manage");
		manageButton.setSize(100, 50);
		manageButton.setLayout(null);
		manageButton.setLocation(125, 300);
		manageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.dispose();
				drawManagementScreen(thisgame);
			}
		});
		
		JButton settingsbutton = new JButton("Settings");
		settingsbutton.setSize(100, 50);
		settingsbutton.setLayout(null);
		settingsbutton.setLocation(275, 300);
		settingsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.dispose();
				drawSettingsScreen(thisgame);
			}
			
		});
		
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
		
		JPanel background = new JPanel();
		background.setBackground(thisgame.getAppColor());
		background.setLayout(null);
		background.setSize(500, 500);
		background.setVisible(true);
		
		JPanel displaybackground = new JPanel();
		displaybackground.setBackground(Color.WHITE);
		displaybackground.setLayout(null);
		displaybackground.setSize(300, 300);
		displaybackground.setLocation(100, 50);
		displaybackground.setVisible(true);
		background.add(displaybackground);
		
		JTextField userinput = new JTextField();
		userinput.setSize(200, 50);
		userinput.setLayout(null);
		userinput.setLocation(150, 400);
		
		JLabel display = new JLabel();
		display.setHorizontalAlignment(JLabel.CENTER);
		display.setVerticalAlignment(JLabel.CENTER);
		display.setLayout(new BorderLayout());
		displaybackground.add(display, BorderLayout.CENTER);
		display.setSize(300, 300);
		display.setVisible(true);
		
		if (thisgame.getCardset().isEmpty()) {
			display.setText("ADD CARDS TO BOX TO STUDY!");
		}
		else {
			chosenBox = thisgame.chooseBox(thisgame.getBoxset());
			while (chosenBox.isEmpty()) {
				chosenBox = thisgame.chooseBox(thisgame.getBoxset());
			}
			chosenCard = chosenBox.chooseCard();
			chosenSide = chosenCard.chooseSide();
			display.setText(chosenSide);
			display.setHorizontalTextPosition(SwingConstants.CENTER);
			display.setVerticalTextPosition(SwingConstants.CENTER);
		}
		
		JButton exitbutton = new JButton("EXIT");
		exitbutton.setSize(50, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(0,  0);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameframe.dispose();
				drawMainScreen(thisgame);
			}
		});
		
		JButton flipbutton = new JButton("FLIP CARD");
		flipbutton.setSize(100, 50);
		flipbutton.setLayout(null);
		flipbutton.setLocation(50, 400);
		flipbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosenSide.compareTo(chosenCard.getFront()) == 0) {
					display.setText(chosenCard.getBack());
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            public void run() {
					            	userinput.setText("");
					            	userinput.requestFocus();
					                display.setBackground(Color.WHITE);
					                if (thisgame.getBoxset().get(2).getSize() == thisgame.getCardset().size()) {
					                	display.setText("YOU WIN!");
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					                }
					            }
					        }, 
					        1000 
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
					                	display.setText("YOU WIN!");
					                }
					                else {
					                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
					                	}
					                	chosenCard = chosenBox.chooseCard();
					                	chosenSide = chosenCard.chooseSide();
					                	display.setText(chosenSide);
					                }
					            }
					        }, 
					        1000 
					);
				}
			}
			
		});
		
		JButton submitbutton = new JButton("SUBMIT");
		submitbutton.setSize(100, 50);
		submitbutton.setLayout(null);
		submitbutton.setLocation(350, 400);
		
		submitbutton.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
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
						                	display.setText("YOU WIN!");
						                }
						                else {
						                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	}
						                	chosenCard = chosenBox.chooseCard();
						                	chosenSide = chosenCard.chooseSide();
						                	display.setText(chosenSide);
						                }
						            }
						        }, 
						        1000 
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
						                	display.setText("YOU WIN!");
						                }
						                else {
						                	chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = thisgame.chooseBox(thisgame.getBoxset());
						                	}
						                	chosenCard = chosenBox.chooseCard();
						                	chosenSide = chosenCard.chooseSide();
						                	display.setText(chosenSide);
						                }
						            }
						        }, 
						        1000 
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
		JFrame settingsmenu = new JFrame("Settings");
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
		JPanel background = new JPanel();
		background.setSize(500, 500);
		background.setLayout(null);
		background.setLocation(0, 0);
		background.setBackground(thisgame.getAppColor());
		settingsmenu.add(background);
		
		JButton exitbutton = new JButton("Exit");
		exitbutton.setSize(50, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(0, 0);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
				settingsmenu.dispose();
				drawMainScreen(thisgame);
			}
		});
		
		JLabel colorOptions = new JLabel("CHANGE APP COLOR:");
		colorOptions.setSize(250, 50);
		colorOptions.setBackground(Color.WHITE);
		colorOptions.setLocation(100, 100);
		colorOptions.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		colorOptions.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		
		String[] colorchoices = {"COLOR", "BLUE", "RED", "YELLOW", "GREEN", "PURPLE"};
		JComboBox<String> colors = new JComboBox<String>(colorchoices);
		colors.setSize(100, 50);
		colors.setLocation(250, 100);
		colors.getSelectedIndex();
		colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colors.getSelectedIndex() == 2) {
					thisgame.setAppColor(new Color (255, 153, 153));
					background.setBackground(thisgame.getAppColor());
				}
				else if (colors.getSelectedIndex() == 1) {
					thisgame.setAppColor(new Color(153, 204, 255));
					background.setBackground(thisgame.getAppColor());
				}
				else if (colors.getSelectedIndex() == 3) {
					thisgame.setAppColor(new Color(255, 255, 153));
					background.setBackground(thisgame.getAppColor());
				}
				else if (colors.getSelectedIndex() == 4){
					thisgame.setAppColor(new Color(204, 255, 153));	
					background.setBackground(thisgame.getAppColor());
				}
				else if (colors.getSelectedIndex() == 5) {
					thisgame.setAppColor(new Color(204, 153, 255));	
					background.setBackground(thisgame.getAppColor());
				}
			}			
		});
		
		background.add(colors);
		colors.setVisible(true);
		background.add(exitbutton);
		exitbutton.setVisible(true);
		background.add(colorOptions);
		colorOptions.setVisible(true);
		
		settingsmenu.add(background);
		settingsmenu.setResizable(false);
		settingsmenu.setVisible(true);
	}
	
	public static void drawManagementScreen(Game thisgame) {
		JFrame manframe = new JFrame();
		manframe.setSize(500, 500);
		manframe.setLayout(null);
		manframe.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		panel.setLayout(null);
		panel.setLocation(0, 0);
		panel.setBackground(thisgame.getAppColor());
		
		String[] colHeadings = {"Front", "Back"};
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		table.setSize(500, 300);
		table.setLocation(0, 200);
		
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
		
		JButton exitbutton = new JButton("EXIT");
		exitbutton.setSize(50, 50);
		exitbutton.setLocation(0, 0);
		exitbutton.setLayout(null);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
				manframe.dispose();
				drawMainScreen(thisgame);
			}
		});
		
		JButton removebutton = new JButton("REMOVE");
		removebutton.setSize(100,50);
		removebutton.setLayout(null);
		removebutton.setLocation(150, 125);
		removebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int row = table.getSelectedRow();
				String frontValue = table.getModel().getValueAt(row,  0).toString();
				String backValue = table.getModel().getValueAt(row, 1).toString();
				((DefaultTableModel)table.getModel()).removeRow(row);
				
				int cardindex = thisgame.findFlashCard(frontValue, backValue);
				thisgame.getCardset().remove(cardindex);
				System.out.println(thisgame.countCards());
				frontfield.requestFocus();
			}
		});
		
		removebutton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					int row = table.getSelectedRow();
					String frontValue = table.getModel().getValueAt(row,  0).toString();
					String backValue = table.getModel().getValueAt(row, 1).toString();
					((DefaultTableModel)table.getModel()).removeRow(row);
					
					int cardindex = thisgame.findFlashCard(frontValue, backValue);
					thisgame.getCardset().remove(cardindex);
					System.out.println(thisgame.countCards());
					frontfield.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		
		JButton addbutton = new JButton("ADD");
		addbutton.setSize(100, 50);
		addbutton.setLayout(null);
		addbutton.setLocation(250, 125);
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (frontfield.getText().compareTo("") == 0) {
					System.out.println("Front was Not Given!");
				}
				if (backfield.getText().compareTo("") == 0) {
					System.out.println("Back was Not Given!");
				}
				else {
					thisgame.getCardset().add(new FlashCard(frontfield.getText(), backfield.getText()));
					System.out.println(thisgame.countCards());
					
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
		
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (frontfield.getText().compareTo("") == 0) {
						System.out.println("Front was Not Given!");
					}
					if (backfield.getText().compareTo("") == 0) {
						System.out.println("Back was Not Given!");
					}
					else {
						thisgame.getCardset().add(new FlashCard(frontfield.getText(), backfield.getText()));
						System.out.println(thisgame.countCards());
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
		
		panel.add(table);
		table.setVisible(true);
		panel.add(removebutton);
		removebutton.setVisible(true);
		panel.add(addbutton);
		addbutton.setVisible(true);
		panel.add(exitbutton);
		exitbutton.setVisible(true);
		panel.add(frontfield);
		frontfield.setVisible(true);
		panel.add(backfield);
		backfield.setVisible(true);
		
		manframe.add(panel);
		manframe.setResizable(false);
		manframe.setVisible(true);
		frontfield.requestFocusInWindow();
	}

	public static void saveGame() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
			oos.writeObject(GameTester.getGame());
			System.out.println("Saved Game!");
			System.out.println(GameTester.getGame().getAppColor());
			System.out.println(GameTester.getGame().countCards());
			oos.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} 
	}
}
