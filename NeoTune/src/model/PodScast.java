package model;

public class PodScast extends Archive{
	private String description;
	private PodScastCategory category;
	private int reproductionsNum;

	public PodScast(String name, String imageUrl, double duration, String description, PodScastCategory category){
		super(name, imageUrl, duration);
		this.description = description;
		this.category = category;
		this.reproductionsNum = 0;
	}

	public PodScastCategory getPodScastCategory(){
		return this.category;
	}

	@Override
	public int getReproductions(){
		return this.reproductionsNum;
	}


	@Override
	public String toString(){
		return this.name + " Category: " + this.category + " Description: " + this.description + " Duration: " + this.duration;
	}

	@Override
	public void addReproduction(){
		this.reproductionsNum += 1;
	}
}