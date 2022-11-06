package model;
import java.util.ArrayList;

public class Artist extends ProducerUser{
	private ArrayList<Archive> songs;

	public Artist(String nickName, String id, String imageUrl){
		super(nickName, id, imageUrl);
		songs = new ArrayList<Archive>(100);
	}

	@Override
	public void addAudio(Archive newArchive){
		songs.add(newArchive);
	}
}