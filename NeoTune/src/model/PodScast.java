package model;

public class PodScast extends Archive{
	private String description;
	private PodScastCategory category;
	private int reproductionsNum;

	public PodScast(String name, String imageUrl, double duration, String description, PodScastCategory category){
		super(name, imageUrl, duration);
		this.description = description;
		this.category = category;
	}
}