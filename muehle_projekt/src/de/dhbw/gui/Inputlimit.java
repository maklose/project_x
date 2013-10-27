package de.dhbw.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Inputlimit extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit=0;

	  public Inputlimit (int limit) {
	    this.limit = limit;
	  }

	  public void insertString(int offs, String str, AttributeSet a)
	      throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offs, str, a);
	    }
	  }
	}