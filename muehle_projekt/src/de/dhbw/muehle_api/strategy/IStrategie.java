/**
 * 
 */
package de.dhbw.muehle_api.strategy;

import java.util.List;


import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.ISpielstein;


/**
 * Das Interface f&uur; eine Computer Strategie.
 * F&uuml;r jede Partie wird genau eine Instanz erzeugt.
 * 
 * @author DHBW
 *
 */
public interface IStrategie
{	
	/**
	 * Wird aufgerufen, wenn eine neue Partie gestartet wird.
	 * Diese Methode dient zum Initialisieren der Strategie.
	 * 
	 * @param p_SpielerFarbe Farbe des Spielers
	 * 
	 * @throws StrategieException Evtl. auftretende Fehler.
	 * 
	 * @see ESpielsteinFarbe
	 */
	public void startePartie(final ESpielsteinFarbe p_SpielerFarbe)throws StrategieException;
	
	/**
	 * Wird aufgerufen, wenn eine Partie beendet wurde.
	 * Diese Methode dient zum Aufr&auml;umen von Resourcen.
	 * 
	 * @throws StrategieException Evtl. auftretende Fehler.
	 */
	public void partieBeendet()throws StrategieException;
	
    /**
     * Berechnet einen Spielzug.
     * 
     * @param p_SpielFeld Aktualisierte Liste aller Spielsteine auf dem Spielfeld
     * @return Spielzug f&uuml;r einen Stein. Wenn <null> geliefert wird, hat der Spieler aufgegeben.
     *  
     * @throws StrategieException Evtl. auftretende Fehler. Das Spiel sollte beendet werden.
     * 
     * @see ISpielzug
     * @see ISpielstein
     */
    public ISpielzug bewegeStein( final List<ISpielstein> p_SpielFeld ) throws StrategieException;
    
    /**
     * Bestimmt, welcher gegnerische Spielstein entfernt werden soll, nachdem eine M&uuml;hle geschlossen wurde.
     * <br /><br />
     * Diese Methode wird aufgerufen, wenn eine M&uuml;hle geschlossen wurde, nachdem ein Spielzug ausgef&uuml;hrt wurde.
     * 
     * @param p_SpielFeld
     * @param p_LetzterSpielzug
     * @return zu entferndender Spielstein
     * @throws StrategieException
     */
    public ISpielstein entferneStein(final List<ISpielstein> p_SpielFeld, final ISpielzug p_LetzterSpielzug) throws StrategieException;
}
