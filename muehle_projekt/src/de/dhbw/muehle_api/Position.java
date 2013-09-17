/**
 * 
 */
package de.dhbw.muehle_api;

/**
 * Repr&auml;sentiert die Position eines Spielsteins auf einem Spielfeld.
 * 
 * @author DHBW
 */
public final class Position
{
	/**
	 * die Ebene
	 */
    private final EPositionIndex ebene;
    
	/**
	 * die Spalte
	 */
    private final EPositionIndex x;
    
    /**
     * die Zeile
     */
    private final EPositionIndex y;
    
    /**
     * Konstruktor
     *
     * @param ebene Ebene eines Feldes
     * @param x X-Position eines Feldes (Spalte)
     * @param y Y-Position eines Feldes (Zeile)
     */
    public Position( EPositionIndex ebene, EPositionIndex x, EPositionIndex y )
    {        
    	this.ebene = ebene;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Liefert die Ebene
     * 
     * @return Ebene
     */
    public EPositionIndex getEbene()
    {
        return ebene;
    }
    
    /**
     * Liefert die X-Position/Spalte in der Ebene
     * 
     * @return X-Position
     */
    public EPositionIndex getX()
    {
        return x;
    }
    
    /**
     * Liefert die Y-Position/Spalte in der Ebene
     * 
     * @return Y-Position
     */
    public EPositionIndex getY()
    {
        return y;
    }

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ebene.getValue();
		result = prime * result + x.getValue();
		result = prime * result + y.getValue();
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (ebene != other.ebene)
			return false;		
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "Position [ebene=" + ebene + ", x=" + x + ", y=" + y + "]";
	}

}