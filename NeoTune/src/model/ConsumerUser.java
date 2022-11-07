package model;
import java.util.ArrayList;

public abstract class ConsumerUser extends User{
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playlists;
	private double reproductionTime;
	private String mostStreamedArtist;
	private String mostStreamedCreator;
	private String mostStreamedMusicGenre;
	//private Date[] sellsDates;

	public ConsumerUser(String nickName, String id){
		super(nickName, id);
	}

	public abstract String addPlayList(PlayList newPlayList);

	public abstract int searchPlayListByName(String name);

	public abstract void addAudioToPlayList(int playListPos, Archive audio);

	public abstract void eliminateAudioToPlayList(int playListPos, String audioName);

}