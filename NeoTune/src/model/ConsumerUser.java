package model;
import java.util.ArrayList;

public abstract class ConsumerUser extends User{
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playLists;
	private String mostStreamedArtist;
	private String mostStreamedCreator;
	private int[] songReproductions;
	private SongGenre[] genres;
	private int[] podcastReproductions;
	private PodScastCategory[] categories;
	//private Date[] sellsDates;

	public ConsumerUser(String nickName, String id){
		super(nickName, id);
		playLists = new ArrayList<PlayList>(20);

		this.songReproductions = new int[4];
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

	public void addGenreReproduction(SongGenre genre){
		switch(genre){
			case ROCK:
				this.songReproductions[0] += 1;
			break;

			case POP:
				this.songReproductions[1] += 1;
			break;

			case TRAP:
				this.songReproductions[2] += 1;
			break;

			case HOUSE:
				this.songReproductions[3] += 1;
			break;
		}
	}

	public String getMostplayedGenre(){
		int mostPlayedGenre = songReproductions[0];
		SongGenre genre = SongGenre.ROCK;
		for (int i = 1; i<4; i++){
			if (songReproductions[i]>mostPlayedGenre){
				genre = genres[i];
				mostPlayedGenre = songReproductions[i];
			}
		}
		String msj = "---------------------------\n" +
				this.nickName + "\n" +
				genre + ": " + mostPlayedGenre + "\n" +
				"---------------------------\n";
		return msj;
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

	public String getMostplayedCategory(){
		int getMostplayedCategory = podcastReproductions[0];
		PodScastCategory category = PodScastCategory.FASHION;
		for (int i = 1; i<4; i++){
			if (podcastReproductions[i]>getMostplayedCategory){
				category = categories[i];
				getMostplayedCategory = podcastReproductions[i];
			}
		}
		String msj = "---------------------------\n" +
				this.nickName + "\n" +
				category + ": " + getMostplayedCategory + "\n" +
				"---------------------------\n";
		return msj;
	}

	public abstract String searchPlayList(String playListId);

	public abstract ArrayList<PlayList> getPlayLists();

	public abstract String addPlayList(PlayList newPlayList);

	public abstract String getPlayListSongs(int playListPos);

	public abstract int searchPlayListByName(String name);

	public abstract String addAudioToPlayList(int playListPos, Archive audio);

	public abstract void eliminateAudioToPlayList(int playListPos, String audioName);

	public abstract String addSong(Archive newSong);



}