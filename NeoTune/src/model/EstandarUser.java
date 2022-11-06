package model;
import java.util.ArrayList;

public class EstandarUser extends ConsumerUser{
	private ArrayList<PlayList> playlists;
	private ArrayList<Song> songs;

	public EstandarUser(String nickName, String id){
		super(nickName, id);
		playlists = new ArrayList<PlayList>(20);
		songs = new ArrayList<Song>(100);
	}

}