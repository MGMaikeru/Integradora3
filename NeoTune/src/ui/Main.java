package ui;

import java.util.Scanner;
import model.NeoTubeController;
import model.PodScastCategory;
import model.SongGenre;
import model.PlayListType;


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
			"<< -                         Welcome To NeoTube                        - >>\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"1. Register consumer user \n" +
			"2. Register producer user \n" + 
			"3. Register audio \n" +
			"4. Create playlist \n" +
			"5. Configure playlist \n" +
			"6. Share playList\n" +
			"7. Reproduce Audio \n" +
			"8. Buy song \n" +
			"9. Show report \n" +
			"0. Exit.\n"; 
	}

	public String printReportMenu(){
		return 
			"\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"<< -                                 Report                            - >>\n" +
			"<< --------------------------------------------------------------------- >>\n" +
			"1. Show total reproduction in app \n" +
			"2. Show most played song genre of specific user and app \n" + 
			"3. Show most played podscat category of specific user and app \n" +
			"4. Show top of songs and podcats \n" +
			"5. Show sales \n" +
			"0. Back.\n"; 
	}

	public void consumerUserRegister(){
		String msj = "Invalid Option.";
		System.out.println("Select a type consumer user: 1)ESTANDAR USER, 2)PREMIUM USER");
		int option = getIntegerData();
		if (option == 1 || option == 2){
			msj = "The number id is already on use.";
			System.out.println("Type identification number:");
			String numberId = reader.next();
			int pos = controller.searchUserById(numberId);
			if (pos == -1){
				System.out.println("Type user nickname:");
				String nickName = reader.next();
				msj = controller.addConsumerUser(option, nickName, numberId);
			}
		}
		
		System.out.println(msj);
	}

	public void producerUserRegister(){
		String msj = "Invalid Option.";
		System.out.println("Select a type consumer user: 1)ARTIST, 2)CONTENT CREATOR");
		int option = getIntegerData();
		if (option == 1 || option == 2){
			msj = "The number id is already on use.";
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
		System.out.println(controller.printProducerUsers());
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

	public void playListRegister(){
		String msj = "The number id isn't regitered.";
		System.out.println(controller.printConsumerUsers());
		System.out.println("Type identification number of the consumer user:");
		String numberId = reader.next();
		int pos = controller.searchUserById(numberId);
		if (pos != -1){
			String name = null;
			PlayListType playListType = PlayListType.AUDIO;
			int status = controller.verifyInstance(pos);
			if (status == 0){
				System.out.println("Type playlist name:");
				name = reader.next();
				System.out.println("Select the playlist type: 1)AUDIO,  2)PODSCAST,  3)MIXED");
				playListType = getPlayListType();
			}
			
			msj = controller.createPlayList(name, playListType, pos, status);
		}
		System.out.println(msj);
	}

	public PlayListType getPlayListType(){
		int option = 0;
		PlayListType playListType = PlayListType.AUDIO;
		while(option != 1 && option != 2 && option != 3){
			option = getIntegerData();
			switch(option){
				case 1:
					playListType = PlayListType.AUDIO;
					break;

				case 2:
					playListType = PlayListType.PODSCAST;
					break;

				case 3:
					playListType = PlayListType.MIXED;
					break;

				default:
					System.out.println("Invalid Option!");
					break;
			}
		}
		return playListType;
	}

	public void configurePlayList(){
		String msj = "The number id isn't regitered.";
		System.out.println(controller.printConsumerUsers());
		System.out.println("Type identification number of the consumer user:");
		String numberId = reader.next();
		int userPos = controller.searchUserById(numberId);
		if (userPos != -1){
			msj = "This user isn't a consumer.";
			int status = controller.verifyInstance(userPos);
			if (status == 0){
				msj = "The playlist doesn't be of this user or doesn't exsit.";
				System.out.println("Type playlist name:");
				String playListName = reader.next();
				int playListPos = controller.getPlayListPos(playListName, userPos);
				if (playListPos != -1){
					msj = "The audio isn't exist.";
					System.out.println(controller.getPlayListAudios(playListPos, userPos)); 
					System.out.println("Type the audio name:");
					String audioName = reader.next();
					int audioPos = controller.searchArchiveByName(audioName);
					if (audioPos != -1){
						System.out.println("Select option: 1)Add audio,  2)Eliminate audio");
						int option = getIntegerData();
						msj = controller.executePlayListOption(option, userPos, playListPos, audioPos, audioName);
					}
				}
			}
		}
		System.out.println(msj);
	}

	public void sharePlayList(){
		String msj = "Play list isn't exist.";
		System.out.println("Type playlist id:");
		String playListId = reader.next();
		msj = controller.searchPlayList(playListId);
		System.out.println(msj);
	}

	public void playAudio(){
		String msj = "The number id isn't regitered.";
		System.out.println(controller.printConsumerUsers());
		System.out.println("Type identification number of the consumer user:");
		String numberId = reader.next();
		int userPos = controller.searchUserById(numberId);
		if (userPos != -1){
			msj = "This song don't exist.";
			System.out.println(controller.printSong());
			System.out.println(controller.printPodcasts());
			System.out.println("Type song name to play:");
			String songName = reader.next();
			int songPos = controller.searchArchiveByName(songName);
			if (songPos != -1) {
				controller.reproduceAudio(userPos, songPos);
				msj = "End of audio...";
			}
		}
		System.out.println(msj);
	}

	public void buySong(){
		String msj = "The number id isn't regitered.";
		System.out.println(controller.printConsumerUsers());
		System.out.println("Type identification number of the consumer user:");
		String numberId = reader.next();
		int userPos = controller.searchUserById(numberId);
		if (userPos != -1){
			msj = "This song don't exist.";
			System.out.println(controller.printSong());
			System.out.println("Type song name to buy:");
			String songName = reader.next();
			int songPos = controller.searchArchiveByName(songName);
			if (songPos != -1) {
				msj = "Please type a valid song name.";				
				int status = controller.verifyAudioInstance(songPos);
				if (status == 1){
					msj = controller.addSongToUser(userPos, songPos);
				}
			}
			
		}
		System.out.println(msj);
	}

	public void showReport(){
		int option = 1; 

		while(option != 0){
			System.out.println(printReportMenu());
			option = validateIntegerOption();
			switch(option){
				case 1:
					System.out.println(controller.getTotalReproductions());
				break;

				case 2:
					String msj = "The number id isn't regitered.";
					System.out.println(controller.printConsumerUsers());
					System.out.println("Type identification number of the consumer user:");
					String numberId = reader.next();
					int userPos = controller.searchUserById(numberId);
					if (userPos != -1){
						msj = controller.getMostplayedGenre(userPos);
					}
					System.out.println(msj);
				break;

				case 3:
					msj = "The number id isn't regitered.";
					System.out.println(controller.printConsumerUsers());
					System.out.println("Type identification number of the consumer user:");
					numberId = reader.next();
					userPos = controller.searchUserById(numberId);
					if (userPos != -1){
						msj = controller.getMostplayedCategory(userPos);
					}
					System.out.println(msj);
				break;

				case 4:
					String topSongs = controller.getOrderedSongsReproductions();
					System.out.println(topSongs);
					String topPodcasts = controller.getOrderedPodcastsReproductions();
					System.out.println(topPodcasts);
				break;

				case 5:
					System.out.println(controller.getSongsSolds()); 
				break;

				case 6:
				break;

				case 7:
				break;

				case 0:
					System.out.println("Back");
				break;

				default:
					System.out.println("Invalid Option");
				break;

			}
		}
		
	}

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
					playListRegister();
					break;

				case 5: 
					configurePlayList();
					break;

				case 6: 
					sharePlayList();
					break;

				case 7: 
					playAudio();
					break;

				case 8:
					buySong();
					break;

				case 9: 
					showReport();
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
