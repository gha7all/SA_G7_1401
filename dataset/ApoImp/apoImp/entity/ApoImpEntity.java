package apoImp.entity;

import java.awt.Graphics2D;

public class ApoImpEntity {

	private int x, y, direction, color;
	
	private boolean bVisible;
	
	public ApoImpEntity(int x, int y, int direction, int color) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.color = color;
		this.bVisible = true;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getColor() {
		return this.color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isVisible() {
		return bVisible;
	}

	public void setVisible(final boolean bVisible) {
		this.bVisible = bVisible;
	}
	
	public void think(final int delta) {
	}
	
	public void render(final Graphics2D g) {
		this.render(g, 0, 0);
	}
	
	public void render(final Graphics2D g, final int changeX, final int changeY) {
		if (this.isVisible()) {
			
		}
	}
	
}
