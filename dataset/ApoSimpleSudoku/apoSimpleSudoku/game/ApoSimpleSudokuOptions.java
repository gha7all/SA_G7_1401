package apoSimpleSudoku.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;

import apoSimpleSudoku.ApoSimpleSudokuConstants;
import apoSimpleSudoku.level.ApoSimpleSudokuLevel;

public class ApoSimpleSudokuOptions extends ApoSimpleSudokuModel {

	/** eindeutige Stringvariable f�r den QuitButton */
	public static String MENU = "menu";
	
	public ApoSimpleSudokuOptions(ApoSimpleSudokuPanel game) {
		super(game);
	}
	
	public void init() {
		if (super.getBackground() == null) {
			super.makeBackground(ApoSimpleSudokuConstants.GAME_WIDTH, ApoSimpleSudokuConstants.GAME_HEIGHT);
			Graphics2D g = super.getBackground().createGraphics();
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			Stroke stroke = g.getStroke();
			g.setStroke(new BasicStroke(5));
			
			int change = 50;
			
			g.setColor(ApoSimpleSudokuLevel.c);
			g.fillRect(7, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change, ApoSimpleSudokuConstants.GAME_WIDTH - 14, 2 * change);
			
			g.setColor(Color.DARK_GRAY);
			g.drawRoundRect(5, 5, ApoSimpleSudokuConstants.GAME_WIDTH - 12, ApoSimpleSudokuConstants.GAME_HEIGHT - 12, 20, 20);
			
			g.drawLine(7, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change, ApoSimpleSudokuConstants.GAME_WIDTH - 2 * 5, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change);
			g.drawLine(7, ApoSimpleSudokuConstants.GAME_HEIGHT/2 + change, ApoSimpleSudokuConstants.GAME_WIDTH - 2 * 5, ApoSimpleSudokuConstants.GAME_HEIGHT/2 + change);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
			String text = "Options";
			
			int width = ApoSimpleSudokuConstants.GAME_WIDTH - 12;
			int h = g.getFontMetrics().getHeight();
			int circleRadius = h + 2;
			Stroke basicStroke = new BasicStroke(3);
			int lineWidth = 5;
			int[] nextYSet = new int[text.length()];
			for (int i = 0; i < nextYSet.length; i++) {
				nextYSet[i] = (int)(Math.random() * (80 - circleRadius - 4)) + 2 + 10;
			}
			int startX = (int)(width/2 - (text.length()/2f)*(circleRadius) - ((text.length()-1)/2f)*(lineWidth)) + 5;
			int nextWidth = circleRadius + lineWidth;
			for (int t = 0; t < text.length(); t++) {
				String s = String.valueOf(text.charAt(t));
				int w = g.getFontMetrics().stringWidth(s);
				g.setStroke(basicStroke);
				g.setColor(ApoSimpleSudokuConstants.BROWN);
				g.fillOval(startX + 1, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change + nextYSet[t], circleRadius - 1, circleRadius - 1);
				g.setColor(Color.BLACK);
				g.drawOval(startX + 1, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change + nextYSet[t], circleRadius - 1, circleRadius - 1);
				if (t != text.length() - 1) {
					g.drawLine(startX + circleRadius + 1, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change + nextYSet[t] + circleRadius/2 - 1, startX + circleRadius + 1 + lineWidth, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change + nextYSet[t + 1] + circleRadius/2 - 1);
				}
				g.setStroke(stroke);
				int realHeight = h - 2*g.getFontMetrics().getDescent();
				g.setColor(Color.WHITE);
				g.drawString(s, startX + circleRadius/2 - w/2, ApoSimpleSudokuConstants.GAME_HEIGHT/2 - change + nextYSet[t] + circleRadius/2 + realHeight/2);
				startX += nextWidth;
			}
			
			g.setStroke(stroke);
			
			g.dispose();
		}
	}

	@Override
	public void keyButtonReleased(int button, char character) {
		if (button == KeyEvent.VK_ESCAPE) {
			this.getGame().setMenu();
		}
	}

	@Override
	public void mouseButtonFunction(String function) {
		if (ApoSimpleSudokuOptions.MENU.equals(function)) {
			this.getGame().setMenu();
		}
		if (!this.getGame().shouldRepaint()) {
			this.getGame().render();
		}
	}


	@Override
	public void think(long delta) {
	}
	
	@Override
	public void mouseReleased(int x, int y, boolean right) {
	}

	@Override
	public void render(Graphics2D g) {
		if (super.getBackground() != null) {
			g.drawImage(super.getBackground(), 0, 0, null);
		}
	}

}
