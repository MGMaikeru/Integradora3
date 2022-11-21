package model;
import java.util.ArrayList;

public class NeoTubeController{
	private ArrayList<User> users;
	private ArrayList<Archive> archives;
	private ArrayList<PlayList> playLists;
	private int[] reproductions;
	private SongGenre[] genres;
	private int[] podcastReproductions;
	private PodScastCategory[] categories;
	private int[] quanSold;
	private double[] soldValue;

	public NeoTubeController(){
		users = new ArrayList<User>(20);
		archives = new ArrayList<Archive>(100);
		playLists = new ArrayList<PlayList>(100);
		this.reproductions = new int[4];
		this.quanSold = new int[4];
		this.genres = new SongGenre[4];
		genres[0] = SongGenre.ROCK;
		genres[1] = SongGenre.POP;
		genres[2] = SongGenre.TRAP;
		genres[3] = SongGenre.HOUSE;
		
		this.podcastReproductions = new int[4];
		this.categories = new PodScastCategory[4];
		categories[0] = PodScastCategory.POLITICS;
		categories[1] = PodScastCategory.ENTERTAINMENT;
		categories[2] = PodScastCategory.VIDEOGAMES;
		categories[3] = PodScastCategory.FASHION;

	}

	public Archive getArchive(int archivePos){
		return archives.get(archivePos);
	}

	public int searchUserById(String id){
		int pos = -1;
		for(int i = 0; i<users.size(); i++){
			if (users.get(i).getId() != null){
				if((users.get(i).getId()).equals(id)){
					pos = i;
				}
			}
			
		}
		return pos;
	}

	public int searchArchiveByName(String archiveName){
		int pos = -1;
		for(int i = 0; i<archives.size(); i++){
			if((archives.get(i).getName()).equals(archiveName)){
				pos = i;
			}
		}
		return pos;
	}

	public String searchPlayList(String idPlayList){
		String msj = "Playlist not registered.";
		String result = "";
		int playListPos = 0;
		for (int i = 0; i<users.size(); i++){
			if (users.get(i) instanceof ConsumerUser && users.get(i) != null){
				
				result = (( ConsumerUser ) ( users.get(i) )) .searchPlayList(idPlayList);				
				if (!result.equals("N")){
					msj = result;
				}				
				
			}
		}
		return msj;
	}

	public int verifyInstance(int pos){
		int verify = 0;
		if(users.get(pos) instanceof ContentCreator){
			verify = 1;
		} else if(users.get(pos) instanceof Artist){
			verify = 2;
		}else{
			verify = 0;
		}
		return verify;
	}

	public int verifyAudioInstance(int pos){
		int verify = 0;
		if(archives.get(pos) instanceof Song){
			verify = 1;
		} else if(archives.get(pos) instanceof PodScast){
			verify = 2;
		}else{
			verify = 0;
		}
		return verify;
	}

	public String addConsumerUser(int option, String nickName, String id){
		String msj = "";
		User newUser = null;
		switch(option){
			case 1:
				newUser = new EstandarUser(nickName, id);
				msj = "New estandar user successful add.";
				break;
			case 2:
				newUser = new PremiumUser(nickName, id);
				msj = "New premium user successful add.";
				break;
			default:
				msj = "Invalid Option.";
				break;
		}
		users.add(newUser); 
		
		return msj;
	}

	public String addProducerUser(int option, String nickName, String id, String imageUrl){
		String msj = "";
		User newUser = null;
		switch(option){
			case 1:
				newUser = new Artist(nickName, id, imageUrl);
				msj = "New artist successful add.";
				break;
			case 2:
				newUser = new ContentCreator(nickName, id, imageUrl);
				msj = "New premium successful add.";
				break;
			default:
				msj = "Invalid Option.";
				break;
		}
		users.add(newUser); 
		
		return msj;
	}

	public String createAudio(String name, String imageUrl, double duration, String description, PodScastCategory podScastCategory, String albumName, SongGenre genre, double price, int pos, int status){
		String msj = "";
		Archive newArchive = null;
		switch(status){
			case 1:
				newArchive = new PodScast(name, imageUrl, duration, description, podScastCategory);
				msj = "New podscast successful add.";
				archives.add(newArchive);
				addAudio(newArchive, pos);
				break;
			case 2:
				newArchive = new Song(name, imageUrl, duration, albumName, genre, price);
				msj = "New song successful add.";
				archives.add(newArchive);
				addAudio(newArchive, pos);
				break;
			default:
				msj = "This is not producer user.";
				break;
		}
		return msj;
	}

	public void addAudio(Archive archive, int pos){
		(( ProducerUser ) ( users.get(pos) )).addAudio(archive);
	}

	public void addGenreReproduction(SongGenre genre){
		switch(genre){
			case ROCK:
				this.reproductions[0] += 1;
			break;

			case POP:
				this.reproductions[1] += 1;
			break;

			case TRAP:
				this.reproductions[2] += 1;
			break;

			case HOUSE:
				this.reproductions[3] += 1;
			break;
		}
	}

	public void addCategoryReproduction(PodScastCategory category){
		switch(category){
			case POLITICS:
				this.podcastReproductions[0] += 1;
			break;

			case ENTERTAINMENT:
				this.podcastReproductions[1] += 1;
			break;

			case VIDEOGAMES:
				this.podcastReproductions[2] += 1;
			break;

			case FASHION:
				this.podcastReproductions[3] += 1;
			break;
		}
	}	

	public String createPlayList(String name, PlayListType playListType, int pos, int status){
		String msj = "";
		PlayList newPlayList = null;
		switch(status){
			case 0:
				newPlayList = new PlayList(name, playListType);
				msj = "New PlayList successful add.\n";
				msj += addPlayList(newPlayList, pos);
				break;

			default:
				msj = "This is not consumer user.";
				break;
		}
		return msj;
	}

	public String addPlayList(PlayList playList, int pos){
		String msj = (( ConsumerUser ) ( users.get(pos) )).addPlayList(playList);
		return msj;
	}

	public String addSongToUser(int userPos, int songPos){
		double price = ((Song) (archives.get(songPos))).getPrice();
		SongGenre genre =( (Song)(archives.get(audioPos)) ).getGenre();
		addSongSoldValue(genre, price);
		String msj = (( ConsumerUser ) ( users.get(userPos) )).addSong(archives.get(songPos));
		return msj;
	}

	public String executePlayListOption(int option, int userPos, int playListPos, int audioPos, String audioName){
		String msj = "";
		switch(option){
			case 1:
				msj = addAudioToPlayList(audioPos, playListPos, userPos);
				break;

			case 2:
				msj = eliminateAudioToPlayList(audioName, playListPos, userPos);
				break;

			default:
				msj = "Invalid Option!.";
				break;
		}
		return msj;
	}

	public int getPlayListPos(String playListName, int userPos){
		int playListPos = (( ConsumerUser ) ( users.get(userPos) )).searchPlayListByName(playListName);
		return playListPos;
	}

	public String addAudioToPlayList(int audioPos, int playListPos, int userPos){
		Archive archiveToAdd = getArchive(audioPos);
		String msj = (( ConsumerUser ) ( users.get(userPos) )).addAudioToPlayList(playListPos, archiveToAdd);
		return msj;
	}

	public String eliminateAudioToPlayList(String audioName, int playListPos, int userPos){
		(( ConsumerUser ) ( users.get(userPos) )).eliminateAudioToPlayList(playListPos, audioName);
		String msj = "Audio eliminated to the playlist of the user" + users.get(userPos).getName() + " .";
		return msj;
	}

	public String getPlayListAudios( int playListPos, int userPos){
		String msj = (( ConsumerUser ) ( users.get(userPos) )).getPlayListSongs(playListPos);
	
		return msj;
	}

	public String printConsumerUsers(){
		String msj = "<<< Consumer Users >>>\n";
		for(int i = 0; i<users.size(); i++){
			if(users.get(i) instanceof ConsumerUser){
				msj += users.get(i).toString() + "\n";
			}
		}
		return msj;
	}

	public String printProducerUsers(){
		String msj = "<<< Producer Users >>>\n";
		for(int i = 0; i<users.size(); i++){
			if(users.get(i) instanceof ProducerUser){
				msj += users.get(i).toString() + "\n";
			}
		}
		return msj;
	}

	public String printSong(){
		String msj = "<<< Songs >>>\n";
		for(int i = 0; i<archives.size(); i++){
			if(archives.get(i) instanceof Song){
				msj += archives.get(i).toString() + "\n";
			}
		}
		return msj;
	}

	public String printPodcasts(){
		String msj = "<<< Podcasts >>>\n";
		for(int i = 0; i<archives.size(); i++){
			if(archives.get(i) instanceof PodScast){
				msj += archives.get(i).toString() + "\n";
			}
		}
		return msj;
	}

	public void reproduceAudio(int userPos, int audioPos){
		int audioType = verifyAudioInstance(audioPos);

		switch(audioType){
			case 1:
				SongGenre genre =( (Song)(archives.get(audioPos)) ).getGenre();
				if (users.get(userPos) instanceof EstandarUser){
					( ( EstandarUser ) ( users.get(userPos) ) ).addGenreReproduction(genre);
					( ( EstandarUser ) ( users.get(userPos) ) ).playSong();
					addGenreReproduction(genre);
					archives.get(audioPos).addReproduction();
				}else if (users.get(userPos) instanceof PremiumUser){
					( ( PremiumUser ) ( users.get(userPos) ) ).addGenreReproduction(genre);
					( ( PremiumUser ) ( users.get(userPos) ) ).playSong();
					addGenreReproduction(genre);
					archives.get(audioPos).addReproduction();
				}
				
				break;

			case 2:
				PodScastCategory category =( (PodScast)(archives.get(audioPos)) ).getPodScastCategory();
				if (users.get(userPos) instanceof EstandarUser){
					( ( EstandarUser ) ( users.get(userPos) ) ).addCategoryReproduction(category);
					( ( EstandarUser ) ( users.get(userPos) ) ).playPodcast();
					addCategoryReproduction(category);
					archives.get(audioPos).addReproduction();
				}else if (users.get(userPos) instanceof PremiumUser){
					( ( PremiumUser ) ( users.get(userPos) ) ).addCategoryReproduction(category);
					( ( PremiumUser ) ( users.get(userPos) ) ).playPodcast();
					addCategoryReproduction(category);
					archives.get(audioPos).addReproduction();
				}
				break;
		}
		
	}

	public String getTotalReproductions(){
		String msj = "";
		int songsReproductions= 0;
		int podcastReproductions= 0;
		for(int i = 0; i<archives.size(); i++){
			if (archives.get(i) != null && archives.get(i) instanceof Song){
				songsReproductions += archives.get(i).getReproductions();
			} else if(archives.get(i) != null && archives.get(i) instanceof PodScast){
				podcastReproductions += archives.get(i).getReproductions();
			}
		}
		int totalReproducitions = songsReproductions + podcastReproductions;
		msj = "----------------------------\n" +
				"Songs reproductions: " + songsReproductions + "\n" +
				"Podcasts reproductions: " + podcastReproductions + "\n" +
				"Total reproductions in NeoTube: " + totalReproducitions + "\n" +
				"----------------------------\n";

		return msj;
	}

	public String getMostplayedGenre(int userPos){
		String msj = ( (ConsumerUser) (users.get(userPos)) ).getMostplayedGenre();
		int mostPlayedGenre = reproductions[0];
		SongGenre genre = SongGenre.ROCK;
		for (int i = 1; i<4; i++){
			if (reproductions[i]>mostPlayedGenre){
				genre = genres[i];
				mostPlayedGenre = reproductions[i];
			}
		}
		msj += "\nIn the app.\n" +
				genre + ": " + mostPlayedGenre + "\n" +
				"---------------------------\n";
		return msj;
	}

	public String getMostplayedCategory(int userPos){
		String msj = ( (ConsumerUser) (users.get(userPos)) ).getMostplayedCategory();
		int mostPlayedCategory = podcastReproductions[0];
		PodScastCategory category = PodScastCategory.VIDEOGAMES;
		for (int i = 1; i<4; i++){
			if (podcastReproductions[i]>mostPlayedCategory){
				category = categories[i];
				mostPlayedCategory = podcastReproductions[i];
			}
		}
		msj += "\nIn the app.\n" +
				category + ": " + mostPlayedCategory + "\n" +
				"---------------------------\n";
		return msj;
	}

	public int[] getTop(int[] array) {

		int mayor[] = new int[2];

		for (int i = 0; i < array.length; i++) {

			if (array[i] > mayor[0]) {
				mayor[0] = array[i];
				mayor[1] = i;
			}

		}

		return mayor;
	}

	/**
	* getAllScores: Get all players scores in the game.
	* @return allScores Represents the array with all players scores in the game.
	*/

	public int[] getAllSongReproduction(){

		int[] allSongsReproductions = new int[100];

		for (int i = 0; i < archives.size(); i++ ){

			if (archives.get(i) != null && archives.get(i) instanceof Song) {

				allSongsReproductions[i] = archives.get(i).getReproductions();

			}
		}

		return allSongsReproductions;
	}

	/**
	* getAllScores: Get all players scores in the game.
	* @return allScores Represents the array with all players scores in the game.
	*/

	public int[] getAllPodcastReproduction(){

		int[] allPodcastsReproductions = new int[100];

		for (int i = 0; i < archives.size(); i++ ){

			if (archives.get(i) != null && archives.get(i) instanceof PodScast) {

				allPodcastsReproductions[i] = archives.get(i).getReproductions();

			}
		}

		return allPodcastsReproductions;
	}

	/**
	* getNameCoincidence: Get player name if the score is him.
	* @param coincidence Represents the score to get the player name.
	* @return name Represents the player name.
	*/

	public String getSongNameCoincidence(int coincidence) {
		String name = "";
		boolean match = false;
		while (!match) {

			for (int i = 0; i < archives.size() ; i++) {
				if (archives.get(i) != null && archives.get(i) instanceof Song) {
					if (coincidence == archives.get(i).getReproductions()) {
						name = archives.get(i).getName();
						match = true;

					}
				}

			}

		}
		return name;
	}

	public String getSPodcastNameCoincidence(int coincidence) {
		String name = "";
		boolean match = false;
		while (!match) {

			for (int i = 0; i < archives.size() ; i++) {
				if (archives.get(i) != null && archives.get(i) instanceof PodScast) {
					if (coincidence == archives.get(i).getReproductions()) {
						name = archives.get(i).getName();
						match = true;

					}
				}

			}

		}
		return name;
	}

	/**
	* getOrderedScores: Get the list with the top players.
	* @return top5 Represents the list with the top.
	*/

	public String getOrderedSongsReproductions() {

		String top5 = "";

		int[] actuallyScore = getAllSongReproduction(); 

		int[] orderedScore = new int[actuallyScore.length];

		for (int i = 0; i < actuallyScore.length; i++) {

			int[] result = getTop(actuallyScore);
			orderedScore[i] = result[0];
			actuallyScore[(int)result[1]] = -1;

		}

		top5 = "<<< TOP SONGS >>>";

		for (int j = 0; j < 10; j++) {
			if (orderedScore[j] != 0) {
				top5 += "\nTOP " + (j + 1) + " : " + getSongNameCoincidence(orderedScore[j]) + " Reproductions: " + orderedScore[j];
			}

		}

		return top5;
	}

	public String getOrderedPodcastsReproductions() {

		String top5 = "";

		int[] actuallyScore = getAllPodcastReproduction(); 

		int[] orderedScore = new int[actuallyScore.length];

		for (int i = 0; i < actuallyScore.length; i++) {

			int[] result = getTop(actuallyScore);
			orderedScore[i] = result[0];
			actuallyScore[(int)result[1]] = -1;

		}

		top5 = "<<< TOP PODCASTS >>>";

		for (int j = 0; j < 10; j++) {
			if (orderedScore[j] != 0) {
				top5 += "\nTOP " + (j + 1) + " : " + getPodcastNameCoincidence(orderedScore[j]) + " Reproductions: " + orderedScore[j];
			}

		}

		return top5;
	}

	public String getSongsSolds(){
		String report = "";
		for (int i = 0; i<4; i++) {
			report = genres[i] + "  QuantitySold:" + quanSold[i] + "  Sale value:" + soldValue[i] + "\n";
		}
		return report;
	}

	public void addSongSoldValue(SongGenre genre, double price){
		switch(genre){
			case ROCK:
				this.quanSold[0] += 1;
				this.soldValue[0] += price;
			break;

			case POP:
				this.quanSold[1] += 1;
				this.soldValue[1] += price;
			break;

			case TRAP:
				this.quanSold[2] += 1;
				this.soldValue[2] += price;
			break;

			case HOUSE:
				this.quanSold[3] += 1;
				this.soldValue[3] += price;
			break;
		}
	}

}