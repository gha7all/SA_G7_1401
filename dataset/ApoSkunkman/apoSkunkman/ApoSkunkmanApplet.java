package apoSkunkman;

import javax.swing.JApplet;

import org.apogames.ApoConstants;
import org.apogames.ApoDisplayConfiguration;
import org.apogames.ApoLibraryGame;
import org.apogames.ApoScreen;
import org.apogames.ApoSubGame;

import apoSkunkman.game.ApoSkunkmanPanel;

/**
* Startklasse f�r das Applet
* @author Dirk Aporius
*
*/
public class ApoSkunkmanApplet extends JApplet {

	private static final long serialVersionUID = 4094382521849709508L;
	
	/**
	 * Konstruktor und sagt dem Programm das es ein Applet ist, damit es sp�ter ausgewertet werden kann
	 */
	public ApoSkunkmanApplet() {
		ApoConstants.B_APPLET = true;
	}
	
	public void stop() {
	}
	
	public void destroy() {
		super.destroy();
	}
	
	public void init() {
		super.init();
		// Gr��e des Displays, Bildwiederholungsrate, ob im Fenster oder nicht, ob Applet oder nicht
		ApoDisplayConfiguration displayConfiguration = new ApoDisplayConfiguration(ApoSkunkmanConstants.GAME_WIDTH, ApoSkunkmanConstants.GAME_HEIGHT, 16, true, true);

        final ApoSubGame subGame = new ApoSkunkmanPanel(new ApoScreen(ApoSkunkmanConstants.PROGRAM_NAME + " Version: "+ApoSkunkmanConstants.VERSION, displayConfiguration));
        final ApoLibraryGame game = new ApoLibraryGame(subGame);
        game.getScreen().setParent(this);
        game.getScreen().init();
        subGame.init();
        game.start();
	}

}
