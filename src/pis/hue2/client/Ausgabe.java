package pis.hue2.client;

import javax.swing.SwingUtilities;

public class Ausgabe {

	private Gui window;
	
	public Ausgabe(Gui window) {
		super();
		this.window = window;
	}

	public void zeigeNachricht(String msg){
		SwingUtilities.invokeLater(() -> {
			window.getTextArea().setText(window.getTextArea().getText() + msg + "\n");
		});
	}
	
	public void zeigeListe(String[] users){
		SwingUtilities.invokeLater(() -> {
			window.getList().setListData(users);
		});
	}
	
}
