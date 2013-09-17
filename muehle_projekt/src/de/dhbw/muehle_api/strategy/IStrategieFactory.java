package de.dhbw.muehle_api.strategy;

/**
 * Dies ist das Basisinterface f&uuml;r den Strategie-Service.
 * Eine Implementierung kann/sollte &uuml;ber den ServiceLoader geladen werden.
 * Siehe hierzu: {@linkplain http://www.javahowto.de/java6/service-loader/sl-basics.html}
 * 
 * @author DHBW
 * @see java.util.ServiceLoader
 */
public interface IStrategieFactory {

	/**
	 * Liefert eine Bezeichnung/Namen der Strategie.
	 * 
	 * @return Bezeichnung/Name
	 */
	public String getName();
	
	/**
	 * Liefert stets eine neue Instanz einer Strategie.
	 * 
	 * @return Strategie
	 * 
	 * @see IStrategie
	 */
	public IStrategie getStrategie();
}
