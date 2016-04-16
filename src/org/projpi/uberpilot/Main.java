package org.projpi.uberpilot;

public class Main {

	public static void main(String args[]){
		boolean debug = true;
		String items[][] = ReadItems.read(debug);
		
		System.out.println(items[1][0]);
		System.out.println(items[3][0]);
	}

}