package apoSimpleSudoku;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFileChooser;

import org.apogames.ApoConstants;
import org.apogames.ApoScreen;
import org.apogames.ApoSubGame;
import org.apogames.entity.ApoButton;
import org.apogames.help.ApoFileFilter;
import org.apogames.input.ApoMouse;
/**
 * Abstrakte Hilfsklasse, von der das eigentliche Spiel erbt und schon einige Funkitionen bereitstellt<br />
 * @author Dirk Aporius
 *
 */
public abstract class ApoSimpleSudokuGameComponent extends ApoSubGame {

	/** Objekt, mit dem mit bestimmten Werten Bilder erstellt und geladen werden k�nnen */
	private ApoSimpleSudokuImages images;
	/** Array der ganzen Buttons im Spiel */
	private ApoButton[] buttons;
	/** boolean Variable, ob gerade ein Handcursor angezeigt wird oder nicht */
	private boolean bHandCursor;
	
	/** A FileChooser to load an ai */
	private JFileChooser fileChooser;
	/** A Class file filter for the ai */
	private final ApoFileFilter	fileFilter = new ApoFileFilter("class");
	/** A FileChooser to load a replay */
	private JFileChooser fileChooserReplay;
	/** A Class file filter for the replay */
	private final ApoFileFilter	fileFilterReplay = new ApoFileFilter("rep");
	/** A FileChooser to load an editor level */
	private JFileChooser fileChooserEditor;
	/** A Class file filter for the editorlevel */
	private final ApoFileFilter	fileFilterEditor = new ApoFileFilter("skunk");
	
	/**
	 * Konstruktor
	 * @param screen : Screenobjekt
	 */
	public ApoSimpleSudokuGameComponent(ApoScreen screen) {
		super(screen);
	}
	
	@Override
	protected void load() {
		if (this.images == null) {
			this.images = new ApoSimpleSudokuImages();
		}
		super.setShouldRepaint(false);
	}

	/**
	 * gibt den Filechooser zum Laden einer KI zur�ck
	 * @return gibt den Filechooser zum Laden einer KI zur�ck
	 */
	public JFileChooser getFileChooser() {
		if ((!ApoConstants.B_APPLET) && (this.fileChooser == null)) {
			this.fileChooser = new JFileChooser();
			this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator));
			this.fileChooser.setFileFilter(this.fileFilter);
		}
		return this.fileChooser;
	}

	/**
	 * gibt den Filechooser zum Laden/Speichern eines Replays zur�ck
	 * @return gibt den Filechooser zum Laden/Speichern eines Replays zur�ck
	 */
	public JFileChooser getFileChooserReplay() {
		if ((!ApoConstants.B_APPLET) && (this.fileChooserReplay == null)) {
			this.fileChooserReplay = new JFileChooser();
			this.fileChooserReplay.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator + "replays" + File.separator));
			this.fileChooserReplay.setFileFilter(this.fileFilterReplay);
		}
		return this.fileChooserReplay;
	}

	/**
	 * gibt den Filechooser zum Laden/Speichern eines Editorlevel zur�ck
	 * @return gibt den Filechooser zum Laden/Speichern eines Editorlevels zur�ck
	 */
	public JFileChooser getFileChooserEditor() {
		if ((!ApoConstants.B_APPLET) && (this.fileChooserEditor == null)) {
			this.fileChooserEditor = new JFileChooser();
			this.fileChooserEditor.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator + "levels" + File.separator));
			this.fileChooserEditor.setFileFilter(this.fileFilterEditor);
		}
		return this.fileChooserEditor;
	}

	/**
	 * gibt den FileFilter f�r den Editor zur�ck
	 * @return gibt den FileFilter f�r den Editor zur�ck
	 */
	public ApoFileFilter getFileFilterEditor() {
		return this.fileFilterEditor;
	}

	/**
	 * gibt den FileFilter f�r das Laden einer KI zur�ck
	 * @return gibt den FileFilter f�r das Laden einer KI zur�ck
	 */
	public ApoFileFilter getFileFilter() {
		return this.fileFilter;
	}

	/**
	 * gibt den FileFilter f�r das Replay zur�ck
	 * @return gibt den FileFilter f�r das Replay zur�ck
	 */
	public ApoFileFilter getFileFilterReplay() {
		return this.fileFilterReplay;
	}

	/**
	 * boolean Variable, ob die FPS angezeigt werden soll oder nicht
	 * @return TRUE, FPS sollen angezeigt werden, FALSE FPS sollen nicht angezeigt werden
	 */
	public boolean isShowFPS() {
		return ApoSimpleSudokuConstants.FPS;
	}

	/**
	 * setzt den boolean-Wert, ob die FPS angezeigt werden soll oder nicht, auf den �bergebenen
	 * @param showFPS : TRUE, FPS sollen angezeigt werden, FALSE FPS sollen nicht angezeigt werden
	 */
	public void setShowFPS(boolean showFPS) {
		ApoSimpleSudokuConstants.FPS = showFPS;
	}

	/**
	 * gibt das Array mit den Buttons zur�ck
	 * @return gibt das Array mit den Buttons zur�ck
	 */
	public final ApoButton[] getButtons() {
		return this.buttons;
	}

	/**
	 * setzt das Array mit den Buttons auf den �bergebenen Wert
	 * @param buttons : neues Array mit Buttons
	 */
	public void setButtons(ApoButton[] buttons) {
		this.buttons = buttons;
	}

	/**
	 * gibt das Imagesobjekt zur�ck, zum Laden und Erstellen von Bildern
	 * @return gibt das Imagesobjekt zur�ck, zum Laden und Erstellen von Bildern
	 */
	public final ApoSimpleSudokuImages getImages() {
		return this.images;
	}
	
	/**
	 * gibt die Frames per Seconds zur�ck
	 * @return gibt die Frames per Seconds zur�ck
	 */
	public final int getFPS() {
		return this.screen.getFps();
	}

	/**
	 * rendert die Buttons
	 * @param g : das Graphics2D Object
	 */
	public void renderButtons(Graphics2D g) {
		if (this.buttons != null) {
			for (int i = 0; i < this.buttons.length; i++) {
				this.buttons[i].render(g, 0, 0);
			}
		}
	}
	
	// Methode, die alle delta-Millisekunden aufgerufen wird und sich um die Logik des Spiels k�mmert und um die Eingaben der Tastatur und Maus
	@Override
	protected void update(long delta) {
		int[] keyPressed = this.keyboard.getPressed();
		if (keyPressed != null) {
			for (int i = 0; i < keyPressed.length; i++) {
				this.keyPressed(keyPressed[i], (char) ((int) keyPressed[i]));
			}
		}

		int[] keyReleased = this.keyboard.getReleased();
		if (keyReleased != null) {
			for (int i = 0; i < keyReleased.length; i++) {
				this.keyReleased(keyReleased[i]);
			}
		}

		if (this.mouse.isDragged()) {
			this.mouseDragged(this.mouse.getX(), this.mouse.getY(), this.mouse.hasClicked(ApoMouse.LEFT));
		} else if (this.mouse.hasClicked(ApoMouse.LEFT)) {
			this.mousePressed(this.mouse.getX(), this.mouse.getY(), true);
		} else if (this.mouse.hasClicked(ApoMouse.RIGHT)) {
			this.mousePressed(this.mouse.getX(), this.mouse.getY(), false);
		} else if (this.mouse.isMoved()) {
			this.mouseMoved(this.mouse.getX(), this.mouse.getY());
		}
		boolean[] mouseReleased = this.mouse.getReleased();
		if (mouseReleased[ApoMouse.LEFT]) {
			this.mouseReleased(this.mouse.getX(), this.mouse.getY(), true);
		} else if (mouseReleased[ApoMouse.RIGHT]) {
			this.mouseReleased(this.mouse.getX(), this.mouse.getY(), false);
		}
		this.think(delta);
	}

	/**
	 * abstrakte Methode, die die Klasse zur Logikberechnung benutzen soll alle delta Millisekunden
	 * @param delta : Zeit die vergangen ist in Millieskunden seit dem letzten Aufruf
	 */
	public abstract void think(long delta);
	
	/**
	 * wird aufgerufen, wenn eine Tastaturtaste gedr�ckt wird<br />
	 * @param button : KeyEventKonstante f�r die Variable
	 * @param character : Character der KeyEventKostante
	 */
	public void keyPressed(int keyCode, char keyCharacter) {
	}

	/**
	 * wird aufgerufen, wenn eine Tastaturtaste losgelassen wird<br />
	 * @param button : KeyEventKonstante f�r die Variable
	 * @param character : Character der KeyEventKostante
	 */
	public void keyReleased(int keyCode) {
	}

	/**
	 * wird aufgerufen, wenn die Maus bewegt wurde und dabei eine Maustaste gedr�ckt gehalten wird
	 * @param x : X-Wert der Maus (im Frame gesehen)
	 * @param y : Y-Wert der Maus (im Frame gesehen)
	 * @param left : TRUE, linke Maustaste gedr�ckt, ansonsten FALSE
	 */
	public void mouseDragged(int x, int y, boolean left) {
	}

	/**
	 * wird aufgerufen, wenn die Maus bewegt wurde und wertet aus, ob die Maus �ber einem Button ist oder nicht
	 * @param x : X-Wert der Maus (im Frame gesehen)
	 * @param y : Y-Wert der Maus (im Frame gesehen)
	 */
	public void mouseMoved(int x, int y) {
		boolean bOver = false;
		if (this.buttons != null) {
			for (int i = 0; i < this.buttons.length; i++) {
				if (this.buttons[i].getMove(x, y)) {
					bOver = true;
					if (!super.shouldRepaint()) {
						this.render();
					}
					break;
				} else if (this.buttons[i].isBOver()) {
					bOver = true;
				}
			}
		}
		if (bOver) {
			if (!this.bHandCursor) {
				this.screen.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				this.bHandCursor = true;
			}
		} else {
			if (this.bHandCursor) {
				this.screen.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				this.bHandCursor = false;
			}
		}
	}

	/**
	 * wird aufgerufen, wenn eine Maustaste gedr�ckt wird und �berpr�ft, ob sie �ber einem Button ist
	 * @param x : X-Wert der Maus (im Frame gesehen)
	 * @param y : Y-Wert der Maus (im Frame gesehen)
	 * @param left : TRUE, linke Maustaste gedr�ckt, ansonsten FALSE
	 */
	public void mousePressed(int x, int y, boolean left) {
		if (this.buttons != null) {
			for (int i = 0; i < this.buttons.length; i++) {
				if (this.buttons[i].getPressed(x, y)) {
					if (!super.shouldRepaint()) {
						this.render();
					}
					break;
				}
			}
		}
	}

	/**
	 * wird aufgerufen, wenn eine Maustaste losgelassen wurde und �berpr�ft, ob sie �ber einem Button ist<br />
	 * und ruft in diesem Fall die setButtonFunction mit der eindeutigen Funktion des Buttons auf
	 * @param x : X-Wert der Maus (im Frame gesehen)
	 * @param y : Y-Wert der Maus (im Frame gesehen)
	 * @param left : TRUE, linke Maustaste gedr�ckt, ansonsten FALSE
	 */
	public void mouseReleased(int x, int y, boolean left) {
		if (this.buttons != null) {
			for (int i = 0; i < this.buttons.length; i++) {
				if (this.buttons[i].getReleased(x, y)) {
					String function = this.buttons[i].getFunction();
					this.setButtonFunction(function);
				}
			}
		}
		if (!super.shouldRepaint()) {
			this.render();
		}
	}

	/**
	 * wird aufgerufen, wenn ein Button gedr�ckt wurde
	 * @param function : Funktion, die der Button ausf�hren soll und ihn einzigartig macht
	 */
	public abstract void setButtonFunction(String function);

}
