package apoRelax.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.apogames.ApoConstants;

import apoRelax.ApoRelaxConstants;

public class ApoRelaxMenu extends ApoRelaxState {
	
	public static final String QUIT = "quit";
	public static final String START = "start";
	public static final String EDITOR = "editor";
	public static final String CREDITS = "credits";
	public static final String OPTIONS = "options";
	public static final String RANDOM = "random";
	
	private BufferedImage iBackground;
	
	public ApoRelaxMenu(ApoRelaxPanel game) {
		super(game);
	}
	
	@Override
	public void init() {
		this.getGame().setShouldRepaint(false);
		if (this.iBackground == null) {
			this.iBackground = new BufferedImage(ApoRelaxConstants.GAME_WIDTH, ApoRelaxConstants.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = this.iBackground.createGraphics();
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			this.getGame().renderBackground(g);
			
			Stroke stroke = g.getStroke();
			g.setStroke(new BasicStroke(5));
			
			g.setColor(Color.GRAY);
			g.drawRoundRect(5, 5, this.iBackground.getWidth() - 12, this.iBackground.getHeight() - 12, 20, 20);
			
			int change = 50;
			g.drawLine(7, this.iBackground.getHeight()/2 - change, this.iBackground.getWidth() - 2 * 5, this.iBackground.getHeight()/2 - change);
			g.drawLine(7, this.iBackground.getHeight()/2 + change, this.iBackground.getWidth() - 2 * 5, this.iBackground.getHeight()/2 + change);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
			String s = "ApoRelax";
			int w = g.getFontMetrics().stringWidth(s);
			int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
			g.drawString(s, this.iBackground.getWidth()/2 - w/2, ApoRelaxConstants.GAME_HEIGHT/2 + h/2);
			
			g.setStroke(stroke);
			
			g.dispose();
		}
	}

	@Override
	public void keyButtonReleased(int button, char character) {
		if (button == KeyEvent.VK_ESCAPE) {
			if (!ApoConstants.B_APPLET) {
				System.exit(0);
			}
		} else if (button == KeyEvent.VK_ENTER) {
			this.getGame().setLevelChooser();
		} else if (button == KeyEvent.VK_C) {
			this.getGame().setCredits();
		} else if (button == KeyEvent.VK_E) {
			this.getGame().setEditor();
		} else if (button == KeyEvent.VK_O) {
			this.getGame().setOptions();
		}
	}

	@Override
	public void mouseButtonFunction(String function) {
		if (function.equals(ApoRelaxMenu.QUIT)) {
			if (!ApoConstants.B_APPLET) {
				this.getGame().stopGame();
				System.exit(0);
			}
		} else if (function.equals(ApoRelaxMenu.START)) {
			this.getGame().setLevelChooser();
		} else if (function.equals(ApoRelaxMenu.EDITOR)) {
			this.getGame().setEditor();
		} else if (function.equals(ApoRelaxMenu.CREDITS)) {
			this.getGame().setCredits();
		} else if (function.equals(ApoRelaxMenu.RANDOM)) {
			this.getGame().setRandomGame();
		} else if (function.equals(ApoRelaxMenu.OPTIONS)) {
			this.getGame().setOptions();
		}
	}
	
	@Override
	public void mouseButtonReleased(int x, int y, boolean bRight) {
	}

	@Override
	public void think(int delta) {
		
	}
	
	@Override
	public void render(Graphics2D g) {
		if (this.iBackground != null) {
			g.drawImage(this.iBackground, 0, 0, null);
		}
	}

}
