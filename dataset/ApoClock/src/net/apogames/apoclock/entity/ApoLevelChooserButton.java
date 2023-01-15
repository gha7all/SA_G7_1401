package net.apogames.apoclock.entity;

import net.gliblybits.bitsengine.graphics.opengl.BitsGLImage;

public class ApoLevelChooserButton extends ApoButton {

	private boolean bSolved;
	
	public ApoLevelChooserButton(BitsGLImage iBackground, int x, int y, int width, int height, String function) {
		super(iBackground, x, y, width, height, function);
		
		this.bSolved = false;
	}

	public boolean isSolved() {
		return this.bSolved;
	}

	public void setSolved(boolean bSolved) {
		this.bSolved = bSolved;
	}

}
