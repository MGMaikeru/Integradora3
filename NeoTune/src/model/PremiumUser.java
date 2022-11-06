package model;
import java.util.ArrayList;

public class PremiumUser extends ConsumerUser{
	private ArrayList<PlayList> playlists;
	private ArrayList<Song> songs;

	public PremiumUser(String nickName, String id){
		super(nickName, id);
		playlists = new ArrayList<PlayList>();
		songs = new ArrayList<Song>();
	}
}