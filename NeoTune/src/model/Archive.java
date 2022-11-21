package model;

public abstract class Archive{
	protected String name;
	private String imageUrl;
	protected double duration;
	
	public Archive(String name, String imageUrl, double duration){
		this.name = name;
		this.imageUrl = imageUrl;
		this.duration = duration;
	}

	public String getName(){
		return this.name;
	}
	public abstract int getReproductions();

	public abstract String toString();

	public abstract void addReproduction();

	
}