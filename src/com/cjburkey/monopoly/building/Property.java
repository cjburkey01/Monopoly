package com.cjburkey.monopoly.building;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.object.GameObjectInst;
import com.cjburkey.monopoly.object.objects.GameObjectBoardSlot;

public class Property {
	
	public int id = 0;
	public Building build;
	public GameObjectInst inst;
	
	public Property(int id, Building build, GameObjectInst inst) {
		this.id = id;
		this.build = build;
		this.inst = inst;
	}
	
	public static final List<Property> properties = new ArrayList<Property>();
	
	public static final Property getFromID(int id) {
		for(Property p : properties) {
			if(p.id == id) {
				return p;
			}
		}
		return null;
	}
	
	public static final void loadProperties() {
		try {
			Scanner s = new Scanner(Property.class.getClassLoader().getResourceAsStream("res/board/layout.board"));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] info = line.split(":");
				if(info.length == 2 && info[0] != null && info[1] != null) {
					info[0] = info[0].trim();
					info[1] = info[1].trim();
					if(info[0] != "" && info[1] != "") {
						int id = -1;
						Building building = null;
						try {
							id = Integer.parseInt(info[0]);
							building = Building.valueOf(info[1]);
						} catch(Exception e) {  }
						
						if(id >= 0 && building != null) {
							GameObjectInst inst = GameObjectBoardSlot.getInstFromId(id);
							if(inst != null) {
								System.out.println(id + " as " + building);
								Property p = new Property(id, building, inst);
								properties.add(p);
							}
						} else {
							System.out.println(building + " not found or " + id + " is below 0");
						}
					}
				}
			}
			s.close();
		} catch(Exception e) { Monopoly.genericError(e); }
	}
	
}