package com.cjburkey.monopoly.building;

public enum Building {
	
	// Normal Properties
	Mediterranean_Avenue("Mediterranean Avenue", BuildType.BROWN, 2, 4, 10, 30, 90, 160, 250, 60, 30, 50),					// Brown
	Baltic_Avenue("Baltic Avenue", BuildType.BROWN, 4, 8, 20, 60, 180, 320, 450, 60, 30, 50),								// Brown
	
	Oriental_Avenue("Oriental Avenue", BuildType.LBLUE, 6, 12, 30, 90, 270, 400, 550, 100, 50, 50),							// Light Blue
	Vermont_Avenue("Vermont Avenue", BuildType.LBLUE, 6, 12, 30, 90, 270, 400, 550, 100, 50, 50),							// Light Blue
	Connecticut_Avenue("Connecticut Avenue", BuildType.LBLUE, 8, 16, 40, 100, 300, 450, 600, 120, 60, 50),					// Light Blue
	
	St_Charles_Place("St. Charles Place", BuildType.PURPLE, 10, 20, 50, 150, 450, 625, 750, 140, 70, 100),					// Purple
	States_Avenue("States Avenue", BuildType.PURPLE, 10, 20, 50, 150, 450, 625, 750, 140, 70, 100),							// Purple
	Virginia_Avenue("Virginia Avenue", BuildType.PURPLE, 12, 24, 60, 180, 500, 700, 900, 160, 80, 100),						// Purple
	
	St_James_Place("St. James Place", BuildType.ORANGE, 14, 28, 70, 200, 550, 750, 950, 180, 90, 100),						// Orange
	Tennessee_Avenue("Tennessee Avenue", BuildType.ORANGE, 14, 28, 70, 200, 550, 750, 950, 180, 90, 100),					// Orange
	New_York_Avenue("New York Avenue", BuildType.ORANGE, 16, 32, 80, 220, 600, 800, 1000, 200, 100, 100),					// Orange
	
	Kentucky_Avenue("Kentucky Avenue", BuildType.RED, 18, 36, 90, 250, 700, 875, 1050, 220, 110, 150),						// Red
	Indiana_Avenue("Indiana Avenue", BuildType.RED, 18, 36, 90, 250, 700, 875, 1050, 220, 110, 150),						// Red
	Illinois_Avenue("Illinois Avenue", BuildType.RED, 20, 40, 100, 300, 750, 925, 1100, 240, 120, 150),						// Red
	
	Atlantic_Avenue("Atlantic Avenue", BuildType.YELLOW, 22, 44, 110, 330, 800, 975, 1150, 260, 130, 150),					// Yellow
	Ventnor_Avenue("Ventnor Avenue", BuildType.YELLOW, 22, 44, 110, 330, 800, 975, 1150, 260, 130, 150),					// Yellow
	Marvin_Gardens("Marvin Gardens", BuildType.YELLOW, 24, 48, 120, 360, 850, 1025, 1200, 280, 140, 150),					// Yellow
	
	Pacific_Avenue("Pacific Avenue", BuildType.GREEN, 26, 52, 130, 390, 900, 1100, 1275, 300, 150, 200),					// Green
	North_Carolina_Avenue("North Carolina Avenue", BuildType.GREEN, 26, 52, 130, 390, 900, 1100, 1275, 300, 150, 200),		// Green
	Pennsylvania_Avenue("Pennsylvania", BuildType.GREEN, 28, 56, 150, 450, 1000, 1200, 1400, 320, 160, 200),				// Green
	
	Park_Place("Park Place", BuildType.BLUE, 35, 70, 175, 500, 1100, 1300, 1500, 350, 175, 200),							// Blue
	Boardwalk("Boardwalk", BuildType.BLUE, 50, 100, 200, 600, 1400, 1700, 2000, 400, 200, 200),								// Blue
	
	// Station Properties
	Reading_Railroad("Reading Railroad", 200, 100, true),
	Pennsylvania_Railroad("Pennsylvania Railroad", 200, 100, true),
	BNO_Railroad("B. & O. Railroad", 200, 100, true),
	Short_Line("Short Line", 200, 100, true),
	
	// Utility Properties
	Electric_Company("Electric Company", 150, 75),
	Water_Works("Water Works", 150, 75);
	
	// Variables:
	// Global
	public BuildType type;					// Type of property.
	public String name;						// Displayed name of property.
	public int cost;						// Money payed to claim ownership of the property.
	public int mortgage;					// Mortgage
	
	// Normal Properties
	public int brent = 0;					// Rent cost without anything.
	public int bmonopolyRent = 0;			// Rent cost if all of this color are owned.
	public int brentOneHouse = 0;			// Rent cost with one house and a monopoly.
	public int brentTwoHouse = 0;			// Rent cost with two houses.
	public int brentThreeHouse = 0;			// Rent cost with three houses.
	public int brentFourHouse = 0;			// Rent cost with four houses.
	public int brentHotel = 0;				// Rent cost with four houses and a hotel.
	public int bcostBuilding = 0;			// Cost to add a building.
	
	// Utility Properties
	public int urentByDice = 4;				// Number on dice multiplied by this number to get rent.
	public int urentByDiceIfTwoOwned = 10;	// Number on dice multiplied by this number to get rent if player owns both utilit properties.
	
	// Station Properties
	public int srent1 = 25;					// Rent if one station is owned by player.
	public int srent2 = 50;					// Rent if two stations are owned by player.
	public int srent3 = 100;				// Rent if three stations are owned by player.
	public int srent4 = 200;				// Rent if four stations are owned by player.
	
	// Standard Property
	Building(String name, BuildType type, int rent, int monopolyRent, int rentOneHouse, int rentTwoHouse, int rentThreeHouse, int rentFourHouse,
			int rentHotel, int cost, int costMortgage, int costBuilding) {
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.mortgage = costMortgage;
		
		this.brent = rent;
		this.bmonopolyRent = monopolyRent;
		this.brentOneHouse = rentOneHouse;
		this.brentTwoHouse = rentTwoHouse;
		this.brentThreeHouse = rentThreeHouse;
		this.brentFourHouse = rentFourHouse;
		this.brentHotel = rentHotel;
		this.bcostBuilding = costBuilding;
	}
	
	// Utility
	Building(String name, int cost, int mortgage) {
		this.name = name;
		this.type = BuildType.UTILITIES;
		this.cost = cost;
		this.mortgage = mortgage;
	}
	
	// Station
	Building(String name, int cost, int mortgage, boolean setThisToAnythingYouWantItsValueIsIgnoredInTheCodeLol) {
		this.name = name;
		this.type = BuildType.STATION;
		this.cost = cost;
		this.mortgage = mortgage;
	}
	
}