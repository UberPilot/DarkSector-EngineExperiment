package org.projpi.uberpilot;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadItems {

	public static String[][] read(boolean debug){
		
		try {

			File selectables = new File("src/Data/Selectables.xml");
			//File locations = new File("src/Data/Locations");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document selectablesDoc = dBuilder.parse(selectables);
			selectablesDoc.getDocumentElement().normalize();


			NodeList weaponNodes = selectablesDoc.getElementsByTagName("weapon");
			NodeList armorNodes = selectablesDoc.getElementsByTagName("armor");
			NodeList componentNodes = selectablesDoc.getElementsByTagName("component");

			int arrayLength = 0;
			arrayLength = weaponNodes.getLength() + armorNodes.getLength() + componentNodes.getLength();
			String[][] items = new String[arrayLength][];
			
			int armorStart = weaponNodes.getLength();
			int compStart = armorStart + armorNodes.getLength();
			int compEnd = compStart + componentNodes.getLength();
			
			readWeapons(items, debug, selectablesDoc);
			readArmors(items, debug, selectablesDoc, armorStart, compStart);
			
			for(int loopv = 0; loopv < compEnd; loopv++){
				if(debug = true){
					System.out.println(items[loopv][0]);
				}
			}
			
			return items;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static String[][] readWeapons(String[][] items, boolean debug, Document selectablesDoc){

		NodeList weaponNodes = selectablesDoc.getElementsByTagName("weapon");
		
		for (int i = 0; i < weaponNodes.getLength(); i++) {
			Node weaponNode = weaponNodes.item(i);
			
			if (weaponNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) weaponNode;
				/*Weapon Reference
				 * 0: Debug ID
				 * 1: LoadID
				 * 2: Name
				 * 3: Version
				 * 4: Rarity
				 * 5: Damage
				 * 6: Shots per Second
				 * 7: Piercing
				 * 8: Mass
				 * 9: Price
				 * 10: Size
				 * 11: Variant
				 * 12: Type
				 * 13: Sub-type
				 * 14: Acceleration
				 * 15: Initial Velocity
				 * 16: Description
				 * 17: Classification
				 */
				String[] container = {getTextValue("debugid", element), String.valueOf(i), getTextValue("name", element), getTextValue("version",element), getTextValue("rarity", element), 
										getTextValue("damage", element), getTextValue("sps", element), getTextValue("piercing", element), getTextValue("mass", element),
										getTextValue("price", element), getTextValue("size", element),   getTextValue("variant", element), getTextValue("type", element), getTextValue("subtype", element),
										getTextValue("acceleration", element), getTextValue("ivelocity", element), getTextValue("description", element),
										Classify.classifyWeapon(getTextValue("type", element), getTextValue("size", element), getTextValue("variant", element),getTextValue("version", element))};
				if (debug == true){

				System.out.println("root of xml file " + selectablesDoc.getDocumentElement().getNodeName());	
				System.out.println("Loaded item " + container[0] + " with ID " + container[1]);
				System.out.println("- Name: " + container[2] + " " + container[17]);
				System.out.println("- Rarity: " + container[4]);
				System.out.println("- DPS: " + ((Integer.parseInt(container[5]))*(Integer.parseInt(container[6]))));
				System.out.println("- Piercing: " + container[7]);
				System.out.println("- Mass: " + container[8] + "kg");
				System.out.println("- Cost: " + container[9] + "p");
				System.out.println("=====-=====-=====-=====-=====");
				
				}
				items[i] = container;
			}
		}
		
		
		
		return items;
	}
	
	private static String[][] readArmors(String[][] items, boolean debug, Document selectablesDoc, int armorStart, int compStart){
		

		NodeList armorNodes = selectablesDoc.getElementsByTagName("armor");
		for (int i2 = armorStart; i2 < compStart; i2++) {
			Node armorNode = armorNodes.item(i2);
			
			if (armorNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) armorNode;
				/*Weapon Reference
				 * 0: Debug ID
				 * 1: LoadID
				 * 2: Name
				 * 3: Fit
				 * 4: Rarity
				 * 5: Mass
				 * 6: Base Durability
				 * 7: Resistance
				 * 8: Hardness
				 * 9: Type
				 * 10: Sub-type
				 * 11: Price
				 * 12: Description
				 * 13: Classification
				 */
				String[] container = {getTextValue2("debugid", element), String.valueOf(i2), getTextValue2("name", element), getTextValue2("fit",element), getTextValue2("rarity", element), 
										getTextValue2("mass", element), getTextValue2("bDurability", element), getTextValue2("resistance", element), getTextValue2("hardness", element),
										getTextValue2("type", element), getTextValue2("subtype", element),   getTextValue2("price", element), getTextValue2("description", element)};
				if (debug == true){

				System.out.println("root of xml file " + selectablesDoc.getDocumentElement().getNodeName());	
				System.out.println("Loaded item " + container[0] + " with ID " + container[1]);
				System.out.println("- Name: " + container[2]);
				System.out.println("- Rarity: " + container[4]);
				System.out.println("- Durability: " + container[6]);
				System.out.println("- Resistance: " + container[7]);
				System.out.println("- Hardness: " + container[8]);
				System.out.println("- Type/Subtype" + container[9] + "/" + container[10]);
				System.out.println("- Cost: " + container[11] + "p");
				System.out.println("=====-=====-=====-=====-=====");
				
				}
				items[i2] = container;
			}
		}
		
		
		
		return items;	
	}
	
	private static String getTextValue(String tag, Element element) {
		NodeList weaponNodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node weaponNode = (Node) weaponNodes.item(0);
		return weaponNode.getNodeValue();
	}	
	private static String getTextValue2(String tag, Element element) {
		NodeList armorNodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node armorNode = (Node) armorNodes.item(0);
		return armorNode.getNodeValue();
	}
}