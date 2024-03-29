package apoPongBeat;

import javax.swing.JApplet;

import org.apogames.ApoConstants;
import org.apogames.ApoDisplayConfiguration;
import org.apogames.ApoLibraryGame;
import org.apogames.ApoScreen;
import org.apogames.ApoSubGame;

import apoPongBeat.game.ApoPongBeatPanel;

/**
* Startklasse f�r das Applet
* @author Dirk Aporius
*
*/
public class ApoPongBeatApplet extends JApplet {

	private static final long serialVersionUID = 4094382521849709508L;
	
	private ApoSubGame subGame;
	
	/**
	 * Konstruktor und sagt dem Programm das es ein Applet ist, damit es sp�ter ausgewertet werden kann
	 */
	public ApoPongBeatApplet() {
		ApoConstants.B_APPLET = true;
	}
	
	public void stop() {
		this.subGame.stopGame();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	public void init() {
		super.init();
		// Gr��e des Displays, Bildwiederholungsrate, ob im Fenster oder nicht, ob Applet oder nicht
		ApoDisplayConfiguration displayConfiguration = new ApoDisplayConfiguration(ApoConstants.GAME_WIDTH, ApoConstants.GAME_HEIGHT, 16, true, true);

        this.subGame = new ApoPongBeatPanel(new ApoScreen(ApoPongBeatConstants.PROGRAM_NAME + " Version: "+ApoPongBeatConstants.VERSION, displayConfiguration));
        final ApoLibraryGame game = new ApoLibraryGame(this.subGame);
        game.getScreen().setParent(this);
        game.getScreen().init();
        this.subGame.init();
        game.start();
	}

}
