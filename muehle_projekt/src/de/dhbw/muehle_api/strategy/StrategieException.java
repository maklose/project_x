/**
 * 
 */
package de.dhbw.muehle_api.strategy;

/**
 * Fehlerklklasse.
 * 
 * @author DHBW
 *
 */
public class StrategieException extends Exception
{
    private static final long serialVersionUID = -1L;

    /**
     * Konstruktor 
     * 
     * @param p_Message Fehlermeldung
     * @param p_Cause Fehlerursache
     * 
     * 
     */
    public StrategieException(String p_Message, Throwable p_Cause)
    {
        super(p_Message, p_Cause);
    }

    /**
     * Konstruktor
     * 
     * @param p_Message Fehlermeldung
     */
    public StrategieException(String p_Message)
    {
        super(p_Message);
    }

    /**
     * Konstruktor
     * 
     * @param p_Cause Fehlerursache
     */
    public StrategieException(Throwable p_Cause)
    {
        super(p_Cause);
    }
}