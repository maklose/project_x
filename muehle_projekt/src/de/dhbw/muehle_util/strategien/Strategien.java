package de.dhbw.muehle_util.strategien;

import java.util.List;

import de.dhbw.muehle_util.plugin.MyServiceLoader;
import de.dhbw.muehle_api.strategy.IStrategieFactory;

/**
 * L&auml;dt registrierte Strategien.
 * Jede einzelnen Strategie wird in einem Unterverzeichnis im 'strategy'-Verzeichnis der Anwendung abgelegt.
 * Eine Strategie besteht hierbei aus mindestens einer jar-Datei.
 * Der Pfad zum 'strategy'-Verzeichnis kann &uuml;ber eine System-Property 'strategy.dir' gesetzt werden.
 * Sollte diese Variable nicht gesetzt sein, wird './strategy' als Basis-Pfad angenommen.
 * <br />
 * <br /> 
 * 
 * @author mschlegel
 *
 */
public class Strategien {

	private static List<IStrategieFactory> m_Factories = null;
	
	/**
	 * Verhindert das Instanziieren der Klasse.
	 */
	private Strategien(){}
	
	/**
	 * Listet alle registrierten Strategien auf.
	 * 
	 * @return Strategien
	 */
	public static List<IStrategieFactory> list()
	{
		if( m_Factories == null )
		{
			String l_StrategyDir = System.getProperty("strategy.dir", "./strategy");
			m_Factories = MyServiceLoader.load(l_StrategyDir, IStrategieFactory.class);
		}
		
		return m_Factories;
	}
}