package apoMario.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import apoMario.ApoMarioConstants;
import apoMario.level.ApoMarioLevel;

/**
 * Klasse die ein Partikel darstellt
 * Nachdem die Animation abgelaufen ist, wird sie unsichtbar
 * @author Dirk Aporius
 *
 */
public class ApoMarioParticle extends ApoMarioEntity {

	public ApoMarioParticle(BufferedImage animation, float x, float y, int tiles, long time) {
		super(animation, x, y, animation.getWidth()/tiles / ApoMarioConstants.APP_SIZE, animation.getHeight() / ApoMarioConstants.APP_SIZE, tiles, time, 1, 0);
		this.setBLoop(false);
	}

	public void think(int delta) {
		super.think(delta);
		if (!this.isBAnimation()) {
			super.setBVisible(false);
		}
	}

	@Override
	public void coinCheck(ApoMarioLevel level, int x, int y) {
	}

	@Override
	public void xCheckChange(ApoMarioLevel level, int x, int y) {
	}

	@Override
	public void yCheckUp(ApoMarioLevel level, int x, int y) {
	}

	@Override
	public void yDownCheck(ApoMarioLevel level, int x, int y, int delta) {	
	}
	
	public void render(Graphics2D g, int changeX, int changeY) {
		try {
			super.render(g, changeX, changeY);
		} catch (Exception ex) {
			
		}
	}
	
}