package de.dhbw.muehle_api;

public enum EPositionIndex {
	/**
	 * die m&ouml;glichen Indexe einer Position;
	 */
	Eins(1), Zwei(2), Drei(3);
	
	private int value;
	private EPositionIndex(int value)
	{
		this.value = value;
	}
	
	/**
	 * Liefert den numerischen Wert eines Index
	 * @return Wert
	 */
	public int getValue()
	{
		return value;
	}
	
	//test1
}
