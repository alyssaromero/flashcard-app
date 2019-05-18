import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Background extends JPanel {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;
	private final int DIST = 50;
	private final int NUM_SECTIONS = 500/50;
	private final Color defaultColor = new Color(245, 255, 250);
	private final Color selectedColor;
	private JPanel panel;
	private String patternname;
	
	public Background(Color color, String patternname) {
		this.patternname = patternname;
		this.panel = new JPanel();
		this.selectedColor = color;
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
	}	
	
	public String getPatternName() {
		return this.patternname;
	}
	
	public void drawLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//Diagonal Stripe Pattern//
		if (this.getPatternName().compareTo("pattern1") == 0) {
			for (int i = 0; i < NUM_SECTIONS*2; i++) {
				if (i == 0) {
					g2d.setColor(defaultColor);
					int[] firstx = {0, 0, 50};
					int[] firsty = {0, 50, 0};
					g2d.drawPolygon(firstx, firsty, 3);
				}
				
				if (i%2 == 0) {
					g2d.setColor(defaultColor);
					int[] xpoints = {0, 0, (i+1)*DIST, i*DIST};
					int[] ypoints = {i*DIST, (i+1)*DIST, 0, 0};
					g2d.fillPolygon(xpoints, ypoints, 4);
				}
				else {
					g2d.setColor(this.selectedColor);
					int[] xpoints = {0, 0, (i+1)*DIST, i*DIST};
					int[] ypoints = {i*DIST, (i+1)*DIST, 0, 0};
					g2d.fillPolygon(xpoints, ypoints, 4);
				}
			}
		}
		
		//Quadrant Square Pattern//
		else if (this.getPatternName().compareTo("pattern2") == 0) {
			g2d.setColor(this.selectedColor);
			int[] xpoints = {0, 0, HEIGHT/2, HEIGHT/2};
			int[] ypoints = {0, HEIGHT/2, HEIGHT/2, 0};
			g2d.fillPolygon(xpoints, ypoints, 4);
			int[] xpoints2 = {WIDTH/2, WIDTH, WIDTH, WIDTH/2};
			int[] ypoints2 = {HEIGHT/2, HEIGHT/2, HEIGHT, HEIGHT};
			g2d.fillPolygon(xpoints2, ypoints2, 4);
			
			g2d.setColor(defaultColor);
			int[] xpoints3 = {WIDTH/2, WIDTH/2, WIDTH, WIDTH};
			int[] ypoints3 = {0, HEIGHT/2, HEIGHT/2, 0};
			g2d.fillPolygon(xpoints3, ypoints3, 4);
			int[] xpoints4 = {0, 0, WIDTH/2, WIDTH/2};
			int[] ypoints4 = {HEIGHT/2, HEIGHT, HEIGHT, HEIGHT/2};
			g2d.fillPolygon(xpoints4, ypoints4, 4);
		}
		
		//Plain Color Background//
		else if (this.getPatternName().compareTo("pattern3") == 0) {
			this.setBackground(this.selectedColor);
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLine(g);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
}
