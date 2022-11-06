package model;
import java.util.ArrayList;

public class ContentCreator extends ProducerUser{
	private ArrayList<Archive> podScasts;

	public ContentCreator(String nickName, String id, String imageUrl){
		super(nickName, id, imageUrl);
		podScasts = new ArrayList<Archive>(100);
	}

	@Override
	public void addAudio(Archive newArchive){
		podScasts.add(newArchive);
	}
}