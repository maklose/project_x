/**
 * 
 */
package de.dhbw.muehle_api.strategy;

import de.dhbw.muehle_api.ISpielstein;

/**
 * Implementierung eines Spielzuges f&uuml;r einen Stein.
 * 
 * @author DHBW
 *
 */
public interface ISpielzug
{
    /**
     * Liefert einen neuen Spielstein, der auf das Feld gesetzt werden soll.
     * <br />
     * (Anm.: Laut Regel wird diese Methode genau 9mal aufgerufen.)
     * 
     * @return Startposition eines Steines
     * 
     * @see ISpielstein
     */
    public ISpielstein getNeuenSpielstein();
    
    /**
     * Liefert die Bewegung eines Spielsteins.
     * 
     * @return Bewegung
     * 
     * @see IBewegung
     */
    public IBewegung bewegeSpielStein();
}
