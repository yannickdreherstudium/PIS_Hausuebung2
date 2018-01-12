package pis.hue2.client;

import javax.swing.SwingUtilities;

/**
 * Schnittstelle zwischen Gui und Programm
 * 
 * @author Johannes Mahn, Yannick Drher
 *
 */
public class Ausgabe {

	private Gui window;

	/**
	 * Kontruktor
	 * 
	 * @param window
	 */
	public Ausgabe(Gui window) {
		super();
		this.window = window;
	}

	/**
	 * Schreibt eine neue Zeile mit der nachricht msg in die GUI
	 * 
	 * @param msg
	 *            - String
	 */
	public void zeigeNachricht(String msg) {
		SwingUtilities.invokeLater(() -> {
			window.getTextArea().setText(window.getTextArea().getText() + msg + "\n");
		});
	}

	/**
	 * Setzt die Userlist
	 * 
	 * @param users
	 *            - String[]
	 */
	public void zeigeListe(String[] users) {
		SwingUtilities.invokeLater(() -> {
			window.getList().setListData(users);
		});
	}

}
