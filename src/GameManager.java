import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GameManager {
	private static ArrayList<Box> boxset = new ArrayList<Box>();
	private static ArrayList<FlashCard> cardset = new ArrayList<FlashCard>();
	private static boolean LEITNER = true;
	private static boolean NORMAL = false;
	private static boolean ENGLISH = true;
	private static boolean DEUTSCH = false;
	
	public static void drawMainScreen() {
		JFrame mainframe = new JFrame("FlashCard Application");
		mainframe.setSize(500, 500);
		mainframe.setLocationRelativeTo(null);
		mainframe.setLayout(null);
		
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
		
		mainframe.add(manageButton);
		manageButton.setVisible(true);
		mainframe.add(playbutton);
		playbutton.setVisible(true);
		mainframe.add(settingsbutton);
		settingsbutton.setVisible(true);
		
		mainframe.setVisible(true);
	}
	
	public static void drawGameScreen() {

		JFrame gameframe = new JFrame();
		gameframe.setSize(500, 500);
		gameframe.setLocationRelativeTo(null);
		gameframe.setLayout(null);
		
		// ALLOWS USER INPUT FOR ANSWER //
		JTextField userinput = new JTextField();
		userinput.setSize(200, 100);
		userinput.setLayout(null);
		
		// DISPLAYS FRONT/BACK OF RANDOM CARD //
		JLabel display = new JLabel();
		display.setSize(300, 300);
		display.setLayout(null);
		if (cardset.isEmpty()) {
			display.setText("There are No Cards in Box!");
		}
		else {
			Box chosenBox = chooseBox(boxset);
			FlashCard chosenCard = chosenBox.chooseCard();
			String chosenSide = chosenCard.chooseSide();
			display.setText(chosenSide);
		}
		//display.setLocation(x, y);
	
		
		// BUTTON TO RETURN TO MAIN SCREEN //
		JButton exitbutton = new JButton("EXIT");
		exitbutton.setSize(50, 50);
		exitbutton.setLayout(null);
		exitbutton.setLocation(0,  0);
		
		// BUTTON TO DISPLAY ANSWER //
		JButton flipbutton = new JButton("FLIP CARD");
		flipbutton.setSize(50, 50);
		flipbutton.setLayout(null);
		//flipbutton.setLocation(x, y);
		flipbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		// BUTTON TO SUBMIT FOR CHECKING //
		JButton submitbutton = new JButton("SUBMIT");
		submitbutton.setSize(50, 50);
		submitbutton.setLayout(null);
		//submitbutton.setLocation(x, y);
		submitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if correct... display another card
				//change background green, CORRECT!, pause, display another card
				//if false... change background red, INCORRECT!, pause, display another card
			}
			
		});
		
		gameframe.add(exitbutton);
		exitbutton.setVisible(true);
		gameframe.add(flipbutton);
		flipbutton.setVisible(true);
		gameframe.add(submitbutton);
		submitbutton.setVisible(true);
		gameframe.add(display);
		display.setVisible(true);
		gameframe.add(userinput);
		userinput.setVisible(true);
		
		gameframe.setResizable(false);
		gameframe.setVisible(true);
		
	}
	
	public static void drawSettingsScreen() {
		JFrame settingsmenu = new JFrame("Settings");
		settingsmenu.setSize(500, 500);
		settingsmenu.setLocationRelativeTo(null);
		settingsmenu.setLayout(null);
		
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
	
		JButton englishButton = new JButton("English");
		englishButton.setSize(100, 50);
		englishButton.setLayout(null);
		englishButton.setLocation(200, 75);
		englishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ENGLISH = true;
				DEUTSCH = false;
				System.out.println("Language Switched to English");
			}
		});
		
		JButton germanButton = new JButton("Deutsch");
		germanButton.setSize(100, 50);
		germanButton.setLayout(null);
		germanButton.setLocation(200, 175);
		germanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ENGLISH = false;
				DEUTSCH = true;
				System.out.println("Language Switched to Deutsch");
			}
		});
		
		JButton leitnerMode = new JButton("Leitner");
		leitnerMode.setSize(100, 50);
		leitnerMode.setLayout(null);
		leitnerMode.setLocation(200, 275);
		leitnerMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEITNER = true;
				NORMAL = false;
				System.out.println("Game Mode was Switched to Leitner");
			}
		});
		
		JButton normalMode = new JButton("Normal");
		normalMode.setSize(100, 50);
		normalMode.setLayout(null);
		normalMode.setLocation(200, 375);
		normalMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LEITNER = false;
				NORMAL = true;
				System.out.println("Game Mode was Switched to Normal");
			}
		});
		
		settingsmenu.add(exitbutton);
		exitbutton.setVisible(true);
		settingsmenu.add(englishButton);
		englishButton.setVisible(true);
		settingsmenu.add(germanButton);
		germanButton.setVisible(true);
		settingsmenu.add(leitnerMode);
		leitnerMode.setVisible(true);
		settingsmenu.add(normalMode);
		normalMode.setVisible(true);
		
		settingsmenu.setResizable(false);
		settingsmenu.setVisible(true);
	}
	
	public static void drawManagementScreen() {
		JFrame manframe = new JFrame();
		manframe.setSize(500, 500);
		manframe.setLayout(null);
		manframe.setLocationRelativeTo(null);
		
		String[] colHeadings = {"Front", "Back"};
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length);
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		table.setSize(500, 300);
		table.setLocation(0, 200);
		
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
		
		JTextField frontfield = new JTextField();
		frontfield.setSize(150, 50);
		frontfield.setLayout(null);
		frontfield.setLocation(75, 75);		
		
		JTextField backfield = new JTextField();
		backfield.setSize(150, 50);
		backfield.setLayout(null);
		backfield.setLocation(275, 75);
		
		JButton removebutton = new JButton("REMOVE");
		removebutton.setSize(100,50);
		removebutton.setLayout(null);
		removebutton.setLocation(125, 125);
		removebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.remove(table.getSelectedRow());
				int row = table.getSelectedRow();
				String frontValue = table.getModel().getValueAt(row,  0).toString();
				String backValue = table.getModel().getValueAt(row, 1).toString();
				((DefaultTableModel)table.getModel()).removeRow(row);
				
				System.out.println(cardset.size());
				int cardindex = findFlashCard(frontValue, backValue);
				cardset.remove(cardindex);
				System.out.println(cardset.size());
				
				//findFlashCard(table)
			}
		});
		
		JButton addbutton = new JButton("ADD");
		addbutton.setSize(100, 50);
		addbutton.setLayout(null);
		addbutton.setLocation(275, 125);
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(frontfield.getText());
				if (frontfield.getText().compareTo("") == 0) {
					System.out.println("Front was Not Given!");
				}
				if (backfield.getText().compareTo("") == 0) {
					System.out.println("Back was Not Given!");
				}
				else {
					cardset.add(new FlashCard(frontfield.getText(), backfield.getText()));
					System.out.println("New Card Was Added!");
					System.out.println(cardset.size());
					model.addRow(new Object[] {frontfield.getText(), backfield.getText()});
					frontfield.setText("");
					backfield.setText("");
				}
			}
		});
		
		manframe.add(table);
		table.setVisible(true);
		manframe.add(removebutton);
		removebutton.setVisible(true);
		manframe.add(addbutton);
		addbutton.setVisible(true);
		manframe.add(exitbutton);
		exitbutton.setVisible(true);
		manframe.add(frontfield);
		frontfield.setVisible(true);
		manframe.add(backfield);
		backfield.setVisible(true);
		manframe.setResizable(false);
		manframe.setVisible(true);
	}
	
	public static Box chooseBox(ArrayList<Box> boxset) {
		Random random = new Random();
		int index = random.nextInt(boxset.size());
		System.out.println(index);
		return boxset.get(index);
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
			//card stays in box 2
		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation + 1).addCard(card);
		}
	}
	
	public static void moveBackward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 0) {
			//card stays in box zero
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
		
		drawMainScreen();
		/*
		Scanner scanner = new Scanner(System.in);
		
		Box b1 = new Box(0);
		Box b2 = new Box(1);
		Box b3 = new Box(2);
		boxset.add(b1);
		boxset.add(b2);
		boxset.add(b3);
		
		FlashCard f1 = new FlashCard("der Hund", "dog");
		FlashCard f2 = new FlashCard("die Katze", "cat");
		FlashCard f3 = new FlashCard("die Leute", "people");
		FlashCard f4 = new FlashCard("das Fenster", "window");
		FlashCard f5 = new FlashCard("der Junge", "boy");
		FlashCard f6 = new FlashCard("der Baum", "tree");
		
		b1.addCard(f1);
		b1.addCard(f2);
		b1.addCard(f3);
		b1.addCard(f4);
		b1.addCard(f5);
		b1.addCard(f6);
		
		f1.printCard();
		f2.printCard();
		f3.printCard();
		f4.printCard();
		
		b1.printCards();
		
		System.out.println(countCards()); //tested and works
		
		while (boxset.get(2).getSize() != countCards()) {
			Box chosenBox = chooseBox(boxset);
			while (chosenBox.isEmpty()) {
				chosenBox = chooseBox(boxset);
			}
			
			FlashCard chosenCard = chosenBox.chooseCard();
			System.out.println(chosenCard);
			String chosenSide = chosenCard.chooseSide();
			System.out.println(chosenSide);
			String answer = scanner.nextLine();
			if (chosenSide.compareTo(chosenCard.getFront()) == 0) {
				if (answer.compareTo(chosenCard.getBack()) == 0) {
					System.out.println("CORRECT");
					moveForward(chosenCard, chosenBox);
				}
				else {
					System.out.println("INCORRECT!");
					moveBackward(chosenCard, chosenBox);
				}
			}
			if (chosenSide.compareTo(chosenCard.getBack()) == 0) {
				if (answer.compareTo(chosenCard.getFront()) == 0) {
					System.out.println("CORRECT!");
					moveForward(chosenCard, chosenBox);
				}
				else {
					System.out.println("INCORRECT!");
					moveBackward(chosenCard, chosenBox);
				}
			}
			
			for (int i = 0; i < boxset.size(); i++) {
				System.out.println("Box " + i + " has " + boxset.get(i).getSize() + " cards");
			}
		}
		
		System.out.println("GAME OVER!! YOU WIN!!");
		
		scanner.close();*/
	}
}
