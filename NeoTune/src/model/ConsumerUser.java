package model;
import java.util.ArrayList;

public abstract class ConsumerUser extends User{
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playLists;
	private double reproductionTime;
	private String mostStreamedArtist;
	private String mostStreamedCreator;
	private String mostStreamedMusicGenre;
	//private Date[] sellsDates;

	public ConsumerUser(String nickName, String id){
		super(nickName, id);
		playLists = new ArrayList<PlayList>(20);
	}

	public abstract String searchPlayList(String playListId);

	public abstract ArrayList<PlayList> getPlayLists();

	public abstract String addPlayList(PlayList newPlayList);

	public abstract String getPlayListSongs(int playListPos);

	public abstract int searchPlayListByName(String name);

	public abstract void addAudioToPlayList(int playListPos, Archive audio);

	public abstract void eliminateAudioToPlayList(int playListPos, String audioName);



}