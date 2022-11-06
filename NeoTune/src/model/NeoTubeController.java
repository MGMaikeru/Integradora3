package model;
import java.util.ArrayList;

public class NeoTubeController{
	private ArrayList<User> users;
	private ArrayList<Archive> archives;

	public NeoTubeController(){
		users = new ArrayList<User>(20);
		archives = new ArrayList<Archive>(100);
	}

	public int searchUserById(String id){
		int pos = -1;
		for(int i = 0; i<users.size(); i++){
			if((users.get(i).getId()).equals(id)){
				pos = i;
			}
		}
		return pos;
	}

	public int verifyInstance(int pos){
		int verify = 0;
		if(users.get(pos) instanceof ContentCreator){
			verify = 1;
		} else if(users.get(pos) instanceof Artist){
			verify = 2;
		}else{
			verify = 0;
		}
		return verify;
	}

	public String addConsumerUser(int option, String nickName, String id){
		String msj = "";
		User newUser = null;
		switch(option){
			case 1:
				newUser = new EstandarUser(nickName, id);
				msj = "New estandar user successful add.";
				break;
			case 2:
				newUser = new PremiumUser(nickName, id);
				msj = "New premium user successful add.";
				break;
			default:
				msj = "Invalid Option.";
				break;
		}
		users.add(newUser); 
		
		return msj;
	}

	public String addProducerUser(int option, String nickName, String id, String imageUrl){
		String msj = "";
		User newUser = null;
		switch(option){
			case 1:
				newUser = new Artist(nickName, id, imageUrl);
				msj = "New artist successful add.";
				break;
			case 2:
				newUser = new ContentCreator(nickName, id, imageUrl);
				msj = "New premium successful add.";
				break;
			default:
				msj = "Invalid Option.";
				break;
		}
		users.add(newUser); 
		
		return msj;
	}

	public String createAudio(String name, String imageUrl, double duration, String description, PodScastCategory podScastCategory, String albumName, SongGenre genre, double price, int pos, int status){
		String msj = "";
		Archive newArchive = null;
		switch(status){
			case 1:
				newArchive = new PodScast(name, imageUrl, duration, description, podScastCategory);
				msj = "New podscast successful add.";
				break;
			case 2:
				newArchive = new Song(name, imageUrl, duration, albumName, genre, price);
				msj = "New song successful add.";
				break;
			default:
				msj = "This is not producer user.";
				break;
		}
		archives.add(newArchive);
		
		return msj;
	}

	public void addAudio(Archive archive, int pos){
		(( ProducerUser ) ( users.get(pos) )).addAudio(archive);
	}

	//Tamaño de la matriz divido en dos menos 1.
}