import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Background extends JPanel {
	
	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;
	private final int DIST = 50;
	private final int NUM_SECTIONS = 500/50;
	private final Color baseColor = Color.WHITE;
	private final Color offColor;
	private JPanel panel;
	
	public Background(Color color) {
		this.panel = new JPanel();
		this.offColor = color;
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
	}
	
	public void drawLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < NUM_SECTIONS*2; i++) {
			if (i == 0) {
				g2d.setColor(baseColor);
				int[] firstx = {0, 0, 50};
				int[] firsty = {0, 50, 0};
				g2d.drawPolygon(firstx, firsty, 3);
			}
			
			if (i%2 == 0) {
				g2d.setColor(baseColor);
				int[] xpoints = {0, 0, (i+1)*DIST, i*DIST};
				int[] ypoints = {i*DIST, (i+1)*DIST, 0, 0};
				g2d.fillPolygon(xpoints, ypoints, 4);
			}
			else {
				g2d.setColor(this.offColor);
				int[] xpoints = {0, 0, (i+1)*DIST, i*DIST};
				int[] ypoints = {i*DIST, (i+1)*DIST, 0, 0};
				g2d.fillPolygon(xpoints, ypoints, 4);
			}
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
