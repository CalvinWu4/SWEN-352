package edu.ncsu.csc326.coffeemaker1.exceptions;

public class InventoryException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InventoryException(String msg) {
		super(msg);
	}

}