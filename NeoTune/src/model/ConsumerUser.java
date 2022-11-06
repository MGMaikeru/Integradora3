package model;
import java.util.ArrayList;

public abstract class ConsumerUser extends User{
	private ArrayList<Song> songs;
	private double reproductionTime;
	private String mostStreamedArtist;
	private String mostStreamedCreator;
	private String mostStreamedMusicGenre;
	//private Date[] sellsDates;

	public ConsumerUser(String nickName, String id){
		super(nickName, id);
	}

}