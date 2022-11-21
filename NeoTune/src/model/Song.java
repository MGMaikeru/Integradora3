package model;

public class Song extends Archive{
	private String album;
	private SongGenre genre;
	private double price;
	private int reproductionsNum;
	private int soldNum;

	public Song(String name, String imageUrl, double duration, String album, SongGenre genre, double price){
		super(name, imageUrl, duration);
		this.album = album;
		this.genre = genre;
		this.price = price;
		this.reproductionsNum = 0;
	}

	public SongGenre getGenre(){
		return this.genre;
	}

	public double getPrice(){
		return this.price;
	}

	@Override
	public int getReproductions(){
		return this.reproductionsNum;
	}

	@Override
	public String toString(){
		return name + "  Genre: " + this.genre + "  Album: " + this.album + "  Duration: " + duration + "  Price: " + price;
	}

	@Override
	public void addReproduction(){
		this.reproductionsNum += 1;
	}

}