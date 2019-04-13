import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Game {
	private static ArrayList<Box> boxset = new ArrayList<Box>();
	private static ArrayList<FlashCard> cardset = new ArrayList<FlashCard>();
	private static Color appColor = new Color(153, 204, 255);

	private static Box chosenBox;
	private static FlashCard chosenCard;
	private static String chosenSide;
	
	public static void drawMainScreen() {
		JFrame mainframe = new JFrame("FlashCard Application");
		mainframe.setSize(500, 500);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(null);
		
		JPanel background = new JPanel();
		background.setBackground(Game.appColor);
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
				drawGameScreen();
			}
		});
		
		JButton manageButton = new JButton("Manage");
		manageButton.setSize(100, 50);
		manageButton.setLayout(null);
		manageButton.setLocation(125, 300);
		manageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.dispose();
				drawManagementScreen();
			}
		});
		
		JButton settingsbutton = new JButton("Settings");
		settingsbutton.setSize(100, 50);
		settingsbutton.setLayout(null);
		settingsbutton.setLocation(275, 300);
		settingsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.dispose();
				drawSettingsScreen();
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
	
	public static void drawGameScreen() {
		
		for (int i = 0; i < cardset.size(); ++i) {
			boxset.get(0).addCard(cardset.get(i));
		}

		JFrame gameframe = new JFrame();
		gameframe.setSize(500, 500);
		gameframe.setLocationRelativeTo(null);
		gameframe.setLayout(null);
		
		JPanel background = new JPanel();
		background.setBackground(Game.appColor);
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
		
		if (cardset.isEmpty()) {
			display.setText("ADD CARDS TO BOX TO STUDY!");
		}
		else {
			chosenBox = chooseBox(boxset);
			while (chosenBox.isEmpty()) {
				chosenBox = chooseBox(boxset);
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
				drawMainScreen();
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
					                if (boxset.get(2).getSize() == cardset.size()) {
					                	display.setText("YOU WIN!");
					                }
					                else {
					                	chosenBox = chooseBox(boxset);
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = chooseBox(boxset);
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
					                if (boxset.get(2).getSize() == cardset.size()) {
					                	display.setText("YOU WIN!");
					                }
					                else {
					                	chosenBox = chooseBox(boxset);
					                	while (chosenBox.isEmpty()) {
					                		chosenBox = chooseBox(boxset);
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
					if (checkAnswer(chosenCard, chosenSide, userinput.getText())) {
						displaybackground.setBackground(Color.GREEN);
						moveForward(chosenCard, chosenBox);
						new java.util.Timer().schedule( 
						        new java.util.TimerTask() {
						            
						            public void run() {
						            	userinput.setText("");
						            	userinput.requestFocus();
						            	displaybackground.setBackground(Color.WHITE);
						                if (boxset.get(2).getSize() == cardset.size()) {
						                	display.setText("YOU WIN!");
						                }
						                else {
						                	chosenBox = chooseBox(boxset);
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = chooseBox(boxset);
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
						moveBackward(chosenCard, chosenBox);
						new java.util.Timer().schedule( 
						        new java.util.TimerTask() {
						            
						            public void run() {
						            	userinput.setText("");
						            	userinput.requestFocus();
						                displaybackground.setBackground(Color.WHITE);
						                if (boxset.get(2).getSize() == cardset.size()) {
						                	display.setText("YOU WIN!");
						                }
						                else {
						                	chosenBox = chooseBox(boxset);
						                	while (chosenBox.isEmpty()) {
						                		chosenBox = chooseBox(boxset);
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
	
	public static void drawSettingsScreen() {
		JFrame settingsmenu = new JFrame("Settings");
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
		JPanel background = new JPanel();
		background.setSize(500, 500);
		background.setLayout(null);
		background.setLocation(0, 0);
		background.setBackground(Game.appColor);
		settingsmenu.add(background);
		
		JButton exitbutton = new JButton("Exit");
		exitbutton.setSize(50, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(0, 0);
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsmenu.dispose();
				drawMainScreen();
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
					Game.appColor = new Color(255, 153, 153);
					background.setBackground(Game.appColor);
				}
				else if (colors.getSelectedIndex() == 1) {
					Game.appColor = new Color(153, 204, 255);
					background.setBackground(Game.appColor);
				}
				else if (colors.getSelectedIndex() == 3) {
					Game.appColor = new Color(255, 255, 153);
					background.setBackground(Game.appColor);
				}
				else if (colors.getSelectedIndex() == 4){
					Game.appColor = new Color(204, 255, 153);	
					background.setBackground(Game.appColor);
				}
				else if (colors.getSelectedIndex() == 5) {
					Game.appColor = new Color(204, 153, 255);	
					background.setBackground(Game.appColor);
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
	
	public static void drawManagementScreen() {
		JFrame manframe = new JFrame();
		manframe.setSize(500, 500);
		manframe.setLayout(null);
		manframe.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setSize(500, 500);
		panel.setLayout(null);
		panel.setLocation(0, 0);
		panel.setBackground(Game.appColor);
		
		String[] colHeadings = {"Front", "Back"};
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		table.setSize(500, 300);
		table.setLocation(0, 200);
		
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
				manframe.dispose();
				drawMainScreen();
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
				
				int cardindex = findFlashCard(frontValue, backValue);
				cardset.remove(cardindex);
				frontfield.requestFocus();
			}
		});
		
		removebutton.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					int row = table.getSelectedRow();
					String frontValue = table.getModel().getValueAt(row,  0).toString();
					String backValue = table.getModel().getValueAt(row, 1).toString();
					((DefaultTableModel)table.getModel()).removeRow(row);
					
					int cardindex = findFlashCard(frontValue, backValue);
					cardset.remove(cardindex);
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
					cardset.add(new FlashCard(frontfield.getText(), backfield.getText()));
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
						cardset.add(new FlashCard(frontfield.getText(), backfield.getText()));
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
	
	public static Box chooseBox(ArrayList<Box> boxset) {
		Random random = new Random();
		float r = random.nextFloat();
		// P(b1) > P(b2) > P(b3)
		int index = 0;
		if (r < .6) {
			index = 0;
		}
		else if (r >= .6 && r < .9) {
			index = 1;
		}
		else {
			index = 2;
		}
		return boxset.get(index);
	}
	
	public static boolean checkAnswer(FlashCard card, String display, String userinput) {
		if (card.getBack().compareTo(display) == 0 && userinput.compareTo(card.getFront()) == 0) {
			return true;
		}
		else if (card.getFront().compareTo(display) == 0 && userinput.compareTo(card.getBack()) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int findFlashCard(String front, String back) {
		int index = 0;
		for (int i = 0; i<cardset.size(); ++i) {
			if (cardset.get(i).getFront().compareTo(front) == 0 && cardset.get(i).getBack().compareTo(back) == 0) {
				index = i;
			}
		}
		return index;
	}
	
	public static int findBoxNumber(Box box) {
		int index = 0;
		while (boxset.get(index) != box) {
			++index;
		}
		return index;
	}
	
	public static void moveForward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 2) {
			
		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation + 1).addCard(card);
		}
	}
	
	public static void moveBackward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 0) {

		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation - 1).addCard(card);
		}
	}
	
	public static int countCards() {
		int count = 0;
		for (int i = 0; i < boxset.size(); i++) {
			Box currentBox = boxset.get(i);
			count += currentBox.getSize();
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		Box b1 = new Box(0);
		Box b2 = new Box(1);
		Box b3 = new Box(2);
		boxset.add(b1);
		boxset.add(b2);
		boxset.add(b3);
		
		drawMainScreen();
	}
}
