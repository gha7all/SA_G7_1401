package org.apogames.help;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apogames.ApoConstants;
import org.apogames.entity.ApoEntity;

/**
 * Hilfsklasse, die einige Methoden zur Verf�gung stellt,
 * welche man immer mal wieder gebrauchen kann, aber in daf�r keine
 * extra Klasse geschrieben werden muss
 * @author Dirk Aporius
 *
 */
public class ApoHelp {

	/**
	 * Konstruktor
	 */
	public ApoHelp() {
	}
	
	/**
	 * erste Methode um ein Bild zu drehen, bei Angabe des Winkels
	 * funktioniert nur perfekt bei Bildern, deren Ma�e width = height ist
	 * @param src = Bilder welches gedreht werden soll
	 * @param degrees = Winkelangabe
	 * @return gedrehtes Bild
	 */
	public static BufferedImage rotateImage(BufferedImage src, double degrees) {
		AffineTransform affineTransform = AffineTransform.getRotateInstance( Math.toRadians(degrees), src.getWidth() / 2, src.getHeight() / 2);
		BufferedImage rotatedImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
		Graphics2D g = (Graphics2D)rotatedImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setTransform( affineTransform );
		g.drawImage( src, 0, 0, null );
		g.dispose();
		return rotatedImage;
	}
	
	/**
	 * erste Methode um ein Bild zu drehen, bei Angabe des Winkels
	 * funktioniert bei allen Bildern
	 * @param inputImage = Bilder welches gedreht werden soll
	 * @param degrees = Winkelangabe
	 * @return gedrehtes Bild
	 */
	public static BufferedImage rotateImageMethodTwo(BufferedImage inputImage, double degrees ) {
		int x,y = 0;
		
		if ((degrees == 0)||(degrees == 180)||(degrees == 360)){
			x = inputImage.getWidth(null);
			y = inputImage.getHeight(null);
		} else {
			x = inputImage.getHeight(null);
			y = inputImage.getWidth(null);
		}
		BufferedImage sourceBI = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(x, y, BufferedImage.TRANSLUCENT);
		AffineTransform at = new AffineTransform();

		at.rotate(Math.toRadians( degrees ), (sourceBI.getWidth() / 2), (sourceBI.getHeight() / 2) );

		AffineTransform translationTransform;
		translationTransform = findTranslation( at, sourceBI, degrees );
		at.preConcatenate( translationTransform );


		Graphics2D g = (Graphics2D) sourceBI.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setTransform(at);
		g.drawImage(inputImage, 0, 0, null);

		return sourceBI;
	}

	/**
	 * find proper translations to keep rotated image correctly displayed
	 * @param at = AffineTransformObjekt
	 * @param bi = BufferedImage
	 * @param degrees = Winkel
	 * @return korrekte AffineTransform
	 */
	private static AffineTransform findTranslation(AffineTransform at, BufferedImage bi, double degrees ) {
		Point2D p2din, p2dout;
		double ytrans, xtrans = 0.0;

		AffineTransform tat = new AffineTransform();

		if(degrees == 180) {
			p2din = new Point2D.Double(0, bi.getHeight());
		} else {
			p2din = new Point2D.Double(0.0, 0.0);
		}

		p2dout = at.transform(p2din, null);

		if(degrees == 270) {
			xtrans = p2dout.getX();
			ytrans = xtrans;
		} else {
			ytrans = p2dout.getY();
			xtrans = ytrans;
		}

		tat.translate(-xtrans, -ytrans);

		return tat;
	}
	
	/**
	 * rundet einen �bergebenen Wert auf die nachgefragte Stellen
	 * @param value = Der zu rundende float Wert
	 * @param adjust = wieviel Stellen nach dem Komma
	 * @return gerundete Zahl
	 */
	public static float round( float value, int adjust ) {
		BigDecimal bigDecimal = new BigDecimal( value );
		return ( bigDecimal.setScale( adjust, BigDecimal.ROUND_HALF_UP )).floatValue();
	}
	
	/**
	 * gibt den Winkel zwischen 2 Punkten zur�ck
	 * @param a = Punkt A
	 * @param b = Punkt B
	 * @return Winkel zwischen den 2 Punkten a und b
	 */
	public static double getAngleBetween2Points( Point a, Point b ) {
		return getAngleBetween2Points((float)a.getX(), (float)a.getY(), (float)b.getX(), (float)b.getY());
	}
		
	public static double getAngleBetween2Points( float x1, float y1, float x2, float y2 ) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		double angle = 0.0d;
 
		if ( dx == 0.0 ) {
			if ( dy == 0.0 ) {
				angle = 0.0;
			} else if ( dy > 0.0 ) {
				angle = Math.PI / 2.0;
			} else {
				angle = (Math.PI * 3.0) / 2.0;
			}
		}
		else if( dy == 0.0 ) {
			if( dx > 0.0 ) {
				angle = 0.0;
			} else {
				angle = Math.PI;
			}
		} else {
			if ( dx < 0.0 ) {
				angle = Math.atan( dy/dx ) + Math.PI;
			} else if( dy < 0.0 ) {
				angle = Math.atan( dy/dx ) + ( 2*Math.PI );
			} else {
				angle = Math.atan( dy/dx );
			}
		}
		return ( angle * 180 ) / Math.PI;
    }
	
	public static ArrayList<ApoFloatPoint> getCircleCuts(float x1, float y1, float radius1, float x2, float y2, float radius2) {
		ArrayList<ApoFloatPoint> results = new ArrayList<ApoFloatPoint>();

		double resultX1 = -10000, resultX2 = -10000, resultY1 = -10000, resultY2 = -10000;

		if ((x2 == x1) && (y2 == y1)) {
			return results;
		} else {
			double c1 = ((radius1 * radius1) - (radius2 * radius2) - (x1 * x1) + (x2 * x2) - (y1 * y1) + (y2 * y2)) / (2.0 * x2 - 2.0 * x1);
			double c2 = (y1 - y2) / (x2 - x1);
			double k1 = 1.0 + (1.0 / Math.pow(c2, 2.0));
			double k2 = 2.0 * x1 + (2.0 * y1) / (c2) + (2.0 * c1) / Math.pow(c2, 2.0);
			double k3 = x1 * x1 + (c1 * c1) / (c2 * c2) + (2.0 * y1 * c1) / (c2) + (y1 * y1) - (radius1 * radius1);
			System.out.println("resultX1: "+resultX1);
			resultX1 = ((k2 / k1) / 2.0) + Math.sqrt((Math.pow((k2 / k1), 2.0) / 4.0) - (k3 / k1));
			System.out.println("resultX1: "+resultX1);
			resultX2 = (k2 / k1) / 2.0 - Math.sqrt((Math.pow((k2 / k1), 2.0) / 4.0) - (k3) / (k1));
			resultY1 = 1.0 / (c2) * resultX1 - (c1 / c2);
			resultY2 = 1.0 / (c2) * resultX2 - (c1 / c2);
		}
		
		if ((resultX1 != -10000) && (resultY1 != -10000)) {
			System.out.println("resultX1: "+resultX1);
			results.add(new ApoFloatPoint((float)(resultX1), (float)(resultY1)));
		}
		if ((resultX2 != -10000) && (resultY2 != -10000)) {
			results.add(new ApoFloatPoint((float)(resultX2), (float)(resultY2)));
		}
		return results;
	}
	
	/**
	 * gibt den String mit der Zeitangabe zur�ck
	 * @return gibt den String mit der Zeitangabe zur�ck
	 */
	public static String getTimeToDraw(int time) {
		if (time <= 0)
			return "";
		String min = String.valueOf(time/1000/60);
		String sec = ""+((time/1000)%60);
		String msec = ""+((time/10)%100);
		if (sec.length()<2) sec = "0"+sec;
		if (msec.length()<2) msec = "0"+msec;
		String timeString = min+":"+sec+":"+msec;
		
		return timeString;
	}

	/**
	 * gibt einen Zufallswert zur�ck
	 * @return gibt einen Zufallswert zur�ck
	 */
	public static int getRandomValue(int value, int plus) {
		int random = (int)(Math.random()*value + plus);
		if (random >= value + plus) {
			random = plus;
		}
		return random;
	}

	/**
	 * gibt ein kopiertes gleiches Feld zur�ck
	 * @param copyArray : das zu kopierende Array
	 * @return gibt ein kopiertes gleiches Feld zur�ck
	 */
	public static byte[][] getCopy(byte[][] copyArray) {
		byte[][] array = new byte[copyArray.length][copyArray[0].length];
		for (int y = 0; y < copyArray.length; y++) {
			for (int x = 0; x < copyArray[0].length; x++) {
				array[y][x] = copyArray[y][x];
			}
		}
		return array;
	}
	
	/**
	 * gibt ein kopiertes gleiches Feld zur�ck
	 * @param copyArray : das zu kopierende Array
	 * @return gibt ein kopiertes gleiches Feld zur�ck
	 */
	public static int[][] getCopy(int[][] copyArray) {
		int[][] array = new int[copyArray.length][copyArray[0].length];
		for (int y = 0; y < copyArray.length; y++) {
			for (int x = 0; x < copyArray[0].length; x++) {
				array[y][x] = copyArray[y][x];
			}
		}
		return array;
	}
	
	/**
	 * gibt den MD5 Hash eines Strings zur�ck
	 * @param pInput : Inputstring
	 * @return gibt den MD5 Hash eines Strings zur�ck
	 */
	public static String getMd5Digest(String pInput) {
		try {
			MessageDigest lDigest = MessageDigest.getInstance("MD5");
			lDigest.update(pInput.getBytes());
			BigInteger lHashInt = new BigInteger(1, lDigest.digest());
			return String.format("%1$032X", lHashInt);
		} catch(NoSuchAlgorithmException lException) {
			throw new RuntimeException(lException);
		}
	}
	
	/**
     * Place a String on the clipboard
	 * @param string
	 */
	public static void setClipboardContents(String string){
		if (!ApoConstants.B_APPLET) {
			StringSelection stringSelection = new StringSelection(string);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    	clipboard.setContents(stringSelection, null);
		}
	}
	
	/**
	  * Get the String residing on the clipboard.
	  *
	  * @return any text found on the Clipboard; if none found, return an empty String.
	  */
	public static String getClipboardContents() {
		String result = "";
		if (!ApoConstants.B_APPLET) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
			if (hasTransferableText) {
				try {
					result = (String)contents.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException ex){
					//highly unlikely since we are using a standard DataFlavor
				} catch (IOException ex) {
				}
			}
		}
		return result;
	}
	
	public static boolean rectangleIntersects(float rect1x, float rect1y, float rect1w, float rect1h, float rect2x, float rect2y, float rect2w, float rect2h) {
		return ((rect1x + rect1w >= rect2x) &&
				(rect1y + rect1h >= rect2y) &&
				(rect1x <= rect2x + rect2w) &&
				(rect1y <= rect2y + rect2h));
	}
	
	public static boolean rectangleContains(float rect1x, float rect1y, float rect1w, float rect1h, float rect2x, float rect2y) {
		return ((rect1x + rect1w >= rect2x) &&
				(rect1y + rect1h >= rect2y) &&
				(rect1x <= rect2x) &&
				(rect1y <= rect2y));
	}
	
	public static boolean rectangleIntersects(ApoEntity entity, float rect2x, float rect2y) {
		return ApoHelp.rectangleContains(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), rect2x, rect2y);
	}
	
	public static boolean rectangleIntersects(ApoEntity entity, float rect2x, float rect2y, float rect2w, float rect2h) {
		return ApoHelp.rectangleIntersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), rect2x, rect2y, rect2w, rect2h);
	}
	
	public static boolean rectangleIntersects(ApoEntity entity, ApoEntity secondEntity) {
		return ApoHelp.rectangleIntersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), secondEntity.getX(), secondEntity.getY(), secondEntity.getWidth(), secondEntity.getHeight());
	}
	
	/**
	 * draws a string
	 * @param g
	 * @param s
	 * @param x
	 * @param y
	 * @param width
	 */
	public static void drawSpeech(Graphics2D g, String s, int x, int y, int width) {
		int curHeight = 0;
		int[] maxLength = new int[] {width - 4, width - 4, width - 4, width - 4};
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(s);
		int cur = 0;
		int w = g.getFontMetrics().stringWidth(strings.get(cur));
		if (w > maxLength[curHeight]) {
			int curPos = strings.get(cur).indexOf(" ");
			while ((curPos > -1) && (g.getFontMetrics().stringWidth(strings.get(cur).substring(0, curPos)) < maxLength[curHeight])) {
				int nextPos = strings.get(cur).indexOf(" ", curPos + 1);
				if (nextPos != -1) {
					if (g.getFontMetrics().stringWidth(strings.get(cur).substring(0, nextPos)) >= maxLength[curHeight]) {
						String curString = strings.get(cur);
						strings.set(cur, curString.substring(0, curPos));
						cur++;
						if (curHeight == 0) {
							curHeight++;
						}
						strings.add(curString.substring(curPos + 1));
						curPos = strings.get(cur).indexOf(" ");
					} else {
						curPos = nextPos;
					}
				} else {
					String curString = strings.get(cur);
					if (g.getFontMetrics().stringWidth( curString ) > maxLength[curHeight]) {
						strings.set( cur, curString.substring(0, curPos) );
						cur++;
						strings.add( curString.substring(curPos + 1));
					}
					break;
				}
			}
		}
		int h = g.getFontMetrics().getHeight();
		for ( int i = 0; i < strings.size(); i++ ) {
			g.drawString(strings.get(i), x, y);
			y += h;
		}
	}
	
	public static ArrayList<String> drawSpeech(Graphics2D g, String s, int width) {
		int curHeight = 0;
		int[] maxLength = new int[] {width - 4, width - 4, width - 4, width - 4};
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(s);
		int cur = 0;
		int w = g.getFontMetrics().stringWidth(strings.get(cur));
		if (w > maxLength[curHeight]) {
			int curPos = strings.get(cur).indexOf(" ");
			while ((curPos > -1) && (g.getFontMetrics().stringWidth(strings.get(cur).substring(0, curPos)) < maxLength[curHeight])) {
				int nextPos = strings.get(cur).indexOf(" ", curPos + 1);
				if (nextPos != -1) {
					if (g.getFontMetrics().stringWidth(strings.get(cur).substring(0, nextPos)) >= maxLength[curHeight]) {
						String curString = strings.get(cur);
						strings.set(cur, curString.substring(0, curPos));
						cur++;
						if (curHeight == 0) {
							curHeight++;
						}
						strings.add(curString.substring(curPos + 1));
						curPos = strings.get(cur).indexOf(" ");
					} else {
						curPos = nextPos;
					}
				} else {
					String curString = strings.get(cur);
					if (g.getFontMetrics().stringWidth( curString ) > maxLength[curHeight]) {
						strings.set( cur, curString.substring(0, curPos) );
						cur++;
						strings.add( curString.substring(curPos + 1));
					}
					break;
				}
			}
		}
		return strings;
	}
	
	/**
	 * method to get the point where two lines intesect
	 * @param line1 : first line
	 * @param line2 : second line
	 * @return Point of intersection or null
	 */
	public static Point2D lineIntersection(Line2D line1, Line2D line2) {  
		if ((line1 == null) || (line2 == null)) {
			return null;
		}
		Point2D cp = null;
		double a1,b1,c1,a2,b2,c2,denom;
		a1 = line1.getY2()-line1.getY1();
		b1 = line1.getX1()-line1.getX2();
		c1 = line1.getX2()*line1.getY1()-line1.getX1()*line1.getY2();
		// a1x + b1y + c1 = 0 line1 eq
		a2 = line2.getY2()-line2.getY1();
		b2 = line2.getX1()-line2.getX2();
		c2 = line2.getX2()*line2.getY1()-line2.getX1()*line2.getY2();
		// a2x + b2y + c2 = 0 line2 eq
		denom = a1*b2 - a2*b1;
		if(denom != 0) {
			cp = new Point2D.Double((b1*c2 - b2*c1)/denom,(a2*c1 - a1*c2)/denom);
		} else {
			// lines are parallel
		}
		return cp;
	}
	
	public static void saveData(final URL codebase, final String name, final String value) {
		Thread t = new Thread() {
			public void run() {
				String parametersAsString = "SET=&name=" + name + "&value=" + value;
				byte[] parameterAsBytes = parametersAsString.getBytes();
				URL url;
				try {
					url = new URL(codebase + "cookies.php");
					URLConnection con = url.openConnection();
					((HttpURLConnection) con).setRequestMethod("GET");
					con.setDoOutput(true);
					con.setDoInput(true);
					con.setUseCaches(false);
			
					OutputStream oStream = con.getOutputStream();
					oStream.write(parameterAsBytes);
					oStream.flush();
					
					BufferedReader iStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
					iStream.readLine();
					iStream.close();
					oStream.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public static String loadData(URL codebase, String name) {
		String retVal = "";
		String parametersAsString = "GET=&name=" + name;
		byte[] parameterAsBytes = parametersAsString.getBytes();
		URL url;
		try {
			url = new URL(codebase + "cookies.php");

			URLConnection con = url.openConnection();
			((HttpURLConnection) con).setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
	
			OutputStream oStream = con.getOutputStream();
			oStream.write(parameterAsBytes);
			oStream.flush();
	
			BufferedReader iStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
			retVal = iStream.readLine();
			iStream.close();
			oStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
