package model;
import java.time.*;

public abstract class ProducerUser extends User{
	private String imageUrl;
	private double acomulateViews;
	private double acomulateReproductionTime;

	public ProducerUser(String nickName, String id, String imageUrl){
		super(nickName, id);
		this.imageUrl = imageUrl;
	}

	public abstract void addAudio(Archive newArchive);
}