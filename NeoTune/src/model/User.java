package model;
import java.util.Date;
import java.util.ArrayList;
import java.time.*;

public abstract class User{
	protected String nickName;
	private String id;
	private LocalDate registerDate;


	public User(String nickName, String id){
		this.nickName = nickName;
		this.id = id;
		registerDate = LocalDate.now();

		System.out.println("Fecha: " + registerDate);
	}

	public String getId(){
		return this.id;
	}

	public String getName(){
		return this.nickName;
	}

	public String toString(){
		return "Name: " + this.nickName + "  Id: " + this.id;
	}

	//public abstract String addAudio();
}