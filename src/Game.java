import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* 
 * @author Alyssa Romero 2019 
 */

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Box> boxset = new ArrayList<Box>();  
	private ArrayList<FlashCard> cardset = new ArrayList<FlashCard>(); 
	private Color appColor = new Color(222, 243, 224); 
	private String musicfile;
	private String backgroundpattern;
	
	private HashMap<String, String[]> vocabulary = new HashMap<String, String[]>();
	private String language;
	
	private transient String[] english = {
			"leitner flashcard application", "play", "manage", "manage cards", "settings", "exit", "flip", "submit", "remove", "add",
			"Whoops! There are No Cards to Study!", "Congratulations! Session Complete!!", "music settings", "color settings", "pattern settings",
			"mute music", "sound", "theme", "language options", "color", "blue", "red", "yellow", "green", "purple", "gray"};
	
	private transient String[] deutsch = {
			"Leitner Flashcard Anwendung", "Begin Speil", "Verwalt Karten", "Verwalt Karten", "Einstellungen", "Verlass", "Umdrehen", "Antwort", "Löschen", "Hinzufüg", 
			"Whoops! Es gibt keine Karten zu studieren!", "Glückwunsch! Sitzung abgeschlossen!", "Musik", "Farben", "Themen",
			"Musik ausschalten", "Tonspur", "Theme", "Sprache", "Farbe", "Blau", "Rot", "Gelb", "Grun", "Lila", "Grau"};
	
	private transient String[] magyar = {
			"leitner flashcard alkalmazás", "játék", "kezelése", "kártyák kezelése", "beállítások", "kijárat", "átfordítja", "beküldése", "töröl", "hozzá",
			"Hoppá! Nincsenek kártyák tanulni!", "Gratula! Szekció befejeződött!", "zene", "színek", "témák",
			"némítsa a zenét","hangsávot","téma", "nyelv", "szín", "kék", "piros", "sárga", "zöld", "lila", "szürke"};
	
	public Game() {
		this.setLanguage("english"); 
		initializeLanguages(); 
		
		this.musicfile = "background_clip1.wav";
		this.backgroundpattern = "pattern1";
		System.out.println(this.getBackgroundPattern());
		
		Box b1 = new Box(0);
		Box b2 = new Box(1);
		Box b3 = new Box(2);
		boxset.add(b1);
		boxset.add(b2);
		boxset.add(b3);
	}
	
	public String getBackgroundPattern() {
		return this.backgroundpattern;
	}
	
	public void setBackgroundPattern(String background) {
		this.backgroundpattern = background;
	}
	
	public String getMusicFile() {
		return this.musicfile;
	}
	
	public void setMusicFile(String filename) {
		this.musicfile = filename;
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
	
	public String getLangauge() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void getVocab(String lang) {
		for (int i = 0; i <= this.vocabulary.size(); ++i) {
			if (this.vocabulary.get(lang) != null) {
				for (int j = 0; j < this.vocabulary.get(lang).length; ++j) {
					System.out.println(this.vocabulary.get(lang)[j]);
				}
			}
		}
	}	
	
	public ArrayList<FlashCard> getCardset() {
		return this.cardset;
	}
	
	public void initializeLanguages() {
		this.vocabulary.put("english", english);
		this.vocabulary.put("deutsch", deutsch);
		this.vocabulary.put("magyar", magyar);
	}
	
	public String translate(String lang, String word) {
		
		String translated ="";
		for (int i = 0; i < this.vocabulary.get("english").length; i++) {
			if (this.vocabulary.get("english")[i].compareTo(word) == 0) {
				translated = this.vocabulary.get(lang)[i];
			}
		}
		
		return translated.toUpperCase();
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
	
	public void restartGame() {
		for (int i = 0; i < this.cardset.size(); i++) {
			this.boxset.get(0).addCard(this.cardset.get(i));
		}
		System.out.println(this.boxset.get(0).getSize());
		for (int j = 0; j < this.cardset.size(); j++) {
			this.boxset.get(2).removeCard(this.cardset.get(j));
		}
		System.out.println(this.boxset.get(2).getSize());
	}
	
	public String toString() {
		return "Game [Cardset= " + cardset + ", AppColor=" + appColor + "]";
	}
}
