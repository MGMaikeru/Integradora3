package ui;

import java.util.Scanner;
import model.NeoTubeController;
import model.PodScastCategory;
import model.SongGenre;


public class Main{


	private Scanner reader;
	private NeoTubeController controller;

	public Main() {
		reader = new Scanner(System.in); 
		controller = new NeoTubeController();

	}

	public static void main(String[] args) {
		Main main = new Main(); 

		int option = -1; 
		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

	}

	public Scanner getReader() {
		return this.reader;
	}

	public void setReader(Scanner reader) {
		this.reader = reader;
	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println(printMenu());

		option = validateIntegerOption(); 

		return option; 
	}

	public String printMenu(){
		return 
			"\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"<< -                                Welcome                            - >>\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"1. Register consumer user \n" +
			"2. Register producer user \n" + 
			"3. Register audio \n" +
			"4. Register tenant \n" +
			"5. Consult free apartments \n" +
			//"6. Consult total monthly value of the rented apartments of a building\n" +
			//"7. Consult status of a specific apartment \n" +
			//"8. Mostrar quantity of leased apartments of a specific owner \n" +
			//"9. Calculate and show monthly value of owner apartments \n" +
			"0. Exit.\n"; 
	}

	/*public void consumerUserRegister(){
		System.out.println("Type building name:");
		String nameId = getBuildingName();
		System.out.println("Type building adress:");
		String adress = reader.next();
		System.out.println("Type building number of aparments:");
		int aparmentsNumber = getIntegerData();
		String msj = controller.addBuilding(nameId, adress, aparmentsNumber);
		System.out.println(msj);
	}

	public String getBuildingName(){
		String msj = "This name is already use. Type another.";
		String nameId = "";
		int pos = 0;
		while(pos != -1){
			nameId = reader.next();
			pos = controller.searchBuildingByName(nameId);
			if (pos != -1){
				System.out.println(msj);
			}
		}
		return nameId;
	}

	public void registerAparmentToBuilding(){
		String msj = "The building is not exist.";
		System.out.println("Type building name to add aparment:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = "The owner number id is not registered.";
			System.out.println("Type owner id number of the aparment:");
			String ownerNumberId = reader.next();
			int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
			if (ownerPos != -1){
				System.out.println("Type apartment number id:");
				String numberId = getAparmentNumerId(buildingPos);
				System.out.println("Type apartment number of rooms:");
				int quantRooms = getIntegerData();
				System.out.println("Type apartment number of bathrooms:");
				int quantBaths = getIntegerData();
				System.out.println("Will The apartment have a balcony? S/N:");
				boolean balcony = getBalcony();
				System.out.println("Type apartment monthly value:");
				double monthlyValue = reader.nextDouble();
				msj = controller.addApartment(numberId, quantRooms, quantBaths, balcony, monthlyValue, buildingPos, ownerPos);
			}
		}

		System.out.println(msj);
	}

	public String getAparmentNumerId(int buildingPos){
		String msj = "This number id is already use. Type another.";
		String numberId = "";
		int pos = 0;
		while(pos != -1){
			numberId = reader.next();
			pos = controller.getBuildings()[buildingPos].searchAparmentsById(numberId);
			if (pos != -1){
				System.out.println(msj);
			}
		}
		return numberId;
	}

	public boolean getBalcony(){
		String option = "";
		boolean statusBalcony = false;
		while(!option.equals("S") && !option.equals("N")){
			option = reader.next();
			switch(option){
				case "S":
					statusBalcony = true;
					break;

				case "N":
					statusBalcony = false;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return statusBalcony;
	}*/

	public void consumerUserRegister(){
		String msj = "The number id is already on use.";
		System.out.println("Select a type consumer user: 1)ESTANDAR USER, 2)PREMIUM USER");
		int option = reader.nextInt();
		System.out.println("Type identification number:");
		String numberId = reader.next();
		int pos = controller.searchUserById(numberId);
		if (pos == -1){
			System.out.println("Type user nickname:");
			String nickName = reader.next();
			msj = controller.addConsumerUser(option, nickName, numberId);
		}
		System.out.println(msj);
	}

	public void producerUserRegister(){
		String msj = "The number id is already on use.";
		System.out.println("Select a type consumer user: 1)ARTIST, 2)CONTENT CREATOR");
		int option = reader.nextInt();
		System.out.println("Type identification number:");
		String numberId = reader.next();
		int pos = controller.searchUserById(numberId);
		if (pos == -1){
			System.out.println("Type user nickname:");
			String nickName = reader.next();
			System.out.println("Type A URL with the user image:");
			String imageUrl = reader.next();
			msj = controller.addProducerUser(option, nickName, numberId, imageUrl);
		}
		System.out.println(msj);
	}

	public void archiveAudioRegister(){
		String description = "";
		PodScastCategory podScastCategory = PodScastCategory.POLITICS;
		String albumName = "";
		SongGenre genre = SongGenre.ROCK;
		double price = 0;
		String name = "";
		String imageUrl = "";
		double duration = 0;
		String msj = "The number id isn't regitered.";
		System.out.println("Type identification number of the producer user:");
		String numberId = reader.next();
		int pos = controller.searchUserById(numberId);
		if (pos != -1){
			int status = controller.verifyInstance(pos);
			if (status == 1){
				System.out.println("Type podscat name:");
				name = reader.next();
				System.out.println("Type podscat image url:");
				imageUrl = reader.next();
				System.out.println("Type the podscat duration:");
				duration = reader.nextDouble();
				System.out.println("Type the podscat description:");
				description = reader.next();
				System.out.println("Select the podscat category: 1)POLITICS,  2)ENTERTAINMENT,  3)VIDEOGAMES,  4)FASHION");
				podScastCategory = getPodScastCategory();
			}else if (status == 2){
				System.out.println("Type song name:");
				name = reader.next();
				System.out.println("Type image url of the album:");
				imageUrl = reader.next();
				System.out.println("Type the song duration:");
				duration = reader.nextDouble();
				System.out.println("Type the album name:");
				albumName = reader.next();
				System.out.println("Select a song genre: 1)ROCK,  2)POP,  3)TRAP,  4)HOUSE");
				genre = getSongGenre();
				System.out.println("Type the song price:");
				price = reader.nextDouble();
			}
			
			msj = controller.createAudio(name, imageUrl, duration, description, podScastCategory, albumName, genre, price, pos, status);
		}
		System.out.println(msj);
	}

	public PodScastCategory getPodScastCategory(){
		int option = 0;
		PodScastCategory podScastCategory = PodScastCategory.POLITICS;
		while(option != 1 && option != 2 && option != 3 && option != 4){
			option = getIntegerData();
			switch(option){
				case 1:
					podScastCategory = PodScastCategory.POLITICS;
					break;

				case 2:
					podScastCategory = PodScastCategory.ENTERTAINMENT;
					break;

				case 3:
					podScastCategory = PodScastCategory.VIDEOGAMES;
					break;

				case 4:
					podScastCategory = PodScastCategory.FASHION;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return podScastCategory;
	}

	public SongGenre getSongGenre(){
		int option = 0;
		SongGenre songGenre = SongGenre.ROCK;
		while(option != 1 && option != 2 && option != 3 && option != 4){
			option = getIntegerData();
			switch(option){
				case 1:
					songGenre = SongGenre.ROCK;
					break;

				case 2:
					songGenre = SongGenre.POP;
					break;

				case 3:
					songGenre = SongGenre.TRAP;
					break;

				case 4:
					songGenre = SongGenre.HOUSE;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return songGenre;
	}
/*
	public void tenantRegister(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);

		if (buildingPos != -1){
			msj = "The apartment is not exist.";
			System.out.println("Type apartment number id to lease:");
			String apartmentId = reader.next();
			int apartmentPos = controller.getBuildings()[buildingPos].searchAparmentsById(apartmentId);

			if (apartmentPos != -1){
				System.out.println("Type identification type:");
				String typeId = reader.next();
				System.out.println("Type identification number:");
				String numberId = reader.next();
				System.out.println("Type tenant name:");
				String name = reader.next();
				System.out.println("Type tenant telephone number:");
				String telephoneNumber = reader.next();
				System.out.println("Select a type telephone option: 1)HOME, 2)OFFICE, 3)MOVIL, 4)FAMILY, 5)OTHER");
				TelephoneType telephoneType = getTelephoneType();
				msj = controller.addTenantToBuilding(typeId, numberId, name, telephoneNumber, telephoneType, buildingPos, apartmentPos);
			}			
		}
		System.out.println(msj);
	}

	public void consultFreeApartments(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = controller.getFreeApartmentsInformation(buildingPos);
		}
		System.out.println(msj);
	}

	public void consultTotalMonthlyValue(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			msj = controller.getTotalApartmentsValue(buildingPos);
		}
		System.out.println(msj);
	}

	public void consultEspecificApartment(){
		String msj = "The building is not exist.";
		System.out.println("Type building name where the apartment is located:");
		String buildingName = reader.next();
		int buildingPos = controller.searchBuildingByName(buildingName);
		if (buildingPos != -1){
			System.out.println("Type apartment number id:");
			String numberId = reader.next();
			msj = controller.consultApartmentStatus(buildingPos, numberId);
		}
		System.out.println(msj);
	}

	public void showOwnerLeasedApartments(){
		String msj = "The owner number id is not registered.";
		System.out.println("Type owner id number to consult information:");
		String ownerNumberId = reader.next();
		int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
		if (ownerPos != -1){
			msj = controller.getOwnerLeasedApartments(ownerPos);
		}
		System.out.println(msj);
	}

	public void consultOwnerMonthlyValue(){
		String msj = "The owner number id is not registered.";
		System.out.println("Type owner id number to consult information:");
		String ownerNumberId = reader.next();
		int ownerPos = controller.searchOwnerByNumberId(ownerNumberId);
		if (ownerPos != -1){
			msj = controller.calculateDistribution(ownerPos);
		}
		System.out.println(msj);
	}*/

	public int getIntegerData(){
		String msj = "Invalid character. Type another.";
		int data = -1;
		while(data == -1){
			data = validateIntegerOption();
			if (data == -1){
				System.out.println(msj);
			}
		}
		return data;
	}
	

	public void executeOption(int option){

			switch(option){
				case 1: 
					consumerUserRegister();
					break; 

				case 2: 
					producerUserRegister();
					break; 

				case 3: 
					archiveAudioRegister();
					break;

				case 4: 
					
					break;

				case 5: 
					
					break;

				case 6: 
					
					break;

				case 7: 
					
					break;

				case 8: 
					
					break;

				case 9: 
					
					break;

				case 0: 
					System.out.println("Exit program.");
					break; 

				default: 
					System.out.println("Invalid Option");
					break; 
			}
	}

	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}



}
