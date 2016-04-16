package org.projpi.uberpilot;

public class Classify {

	public static String classifyWeapon(String type, String size, String variant, String version) {
		String Classification = "S-1";
			String o1 = "";
		if(type.equals("Standard")){
			o1 = "S";
		}else if(type.equals("Plasma")){
			o1 = "P";
		}else if(type.equals("High Speed")){
			o1 = "H";
		}else if(type.equals("Wave")){
			o1 = "W";
		}else if(type.equals("Energy")){
			o1 = "E";
		}else if(type.equals("Utility")){
			o1 = "U";
		}else if(type.equals("Rail")){
			o1 = "R";
		}else{
			o1 = "X";
		}
		String o2 = "";
		if(variant.equals("Standard")){
			o2 = "-";
		}else if(variant.equals("Efficient")){
			o2 = "E";
		}else if(variant.equals("Accelerated")){
			o2 = "A";
		}else if(variant.equals("Rapid")){
			o2 = "R";
		}else if(variant.equals("Specialized")){
			o2 = "S";
		}else if(variant.equals("Customized")){
			o2 = "C";
		}else if(variant.equals("Penetrating")){
			o2 = "P";
		}else if(variant.equals("Incendiary")){
			o2 = "I";
		}else if(variant.equals("Explosive")){
			o2 = "X";
		}else{
			o2 = "-";
		}
		String o3 = "";
		if(size.equals("Compacted")){
			o3 = "C";
		}else if(size.equals("Small")){
			o3 = "S";
		}else if(size.equals("Standard")){
			o3 = "";
		}else if(size.equals("Large")){
			o3 = "L";
		}else if(size.equals("Hardpoint")){
			o3 = "H";
		}else if(size.equals("Capital Primary")){
			o3 = "X";
		}else{
			o3 = "?";
		}
		Classification = o3 + o1 + o2 + version;
		return Classification;
	}
}