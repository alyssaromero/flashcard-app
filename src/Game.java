import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Box> boxset = new ArrayList<Box>();  
	private ArrayList<FlashCard> cardset = new ArrayList<FlashCard>(); 
	private Color appColor = new Color(153, 204, 255); 
	
	public Game() {
		Box b1 = new Box(0);
		Box b2 = new Box(1);
		Box b3 = new Box(2);
		boxset.add(b1);
		boxset.add(b2);
		boxset.add(b3);
	}
	
	public Color getAppColor() {
		return this.appColor;
	}
	
	public void setAppColor(Color color) {
		this.appColor = color;
	}
	
	public ArrayList<Box> getBoxset() {
		return this.boxset;
	}
	
	public ArrayList<FlashCard> getCardset() {
		return this.cardset;
	}
	
	public Box chooseBox(ArrayList<Box> boxset) {
		Random random = new Random();
		float r = random.nextFloat();

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
	
	public boolean checkAnswer(FlashCard card, String display, String userinput) {
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
	
	public int findFlashCard(String front, String back) {
		int index = 0;
		for (int i = 0; i<cardset.size(); ++i) {
			if (cardset.get(i).getFront().compareTo(front) == 0 && cardset.get(i).getBack().compareTo(back) == 0) {
				index = i;
			}
		}
		return index;
	}
	
	public int findBoxNumber(Box box) {
		int index = 0;
		while (boxset.get(index) != box) {
			++index;
		}
		return index;
	}
	
	public void moveForward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 2) {
			
		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation + 1).addCard(card);
		}
	}
	
	public void moveBackward(FlashCard card, Box box) {
		int currentLocation = findBoxNumber(box);
		if (currentLocation == 0) {

		}
		else {
			box.removeCard(card);
			boxset.get(currentLocation - 1).addCard(card);
		}
	}
	
	public int countCards() {
		return cardset.size();
	}
	
	public void getCards() {
		System.out.println("Card Collection:");
		if (cardset.isEmpty()) {
			System.out.println("Empty Cardset");
		}
		else {
			for (int i = 0; i < cardset.size(); ++i) {
				System.out.println("\n" + cardset.get(i).getFront() + ":" + cardset.get(i).getBack());
			}
			System.out.println("Game Color: " + appColor);
		}
	}
	
	public String toString() {
		return "Game [Cardset= " + cardset + ", AppColor=" + appColor + "]";
	}
}
