package model;
import java.util.ArrayList;

public class NeoTubeController{
	private ArrayList<User> users;
	private ArrayList<Archive> archives;
	private ArrayList<PlayList> playLists;

	public NeoTubeController(){
		users = new ArrayList<User>(20);
		archives = new ArrayList<Archive>(100);
		playLists = new ArrayList<PlayList>(100);
	}

	public Archive getArchive(int archivePos){
		return archives.get(archivePos);
	}

	public int searchUserById(String id){
		int pos = -1;
		for(int i = 0; i<users.size(); i++){
			if (users.get(i).getId() != null){
				if((users.get(i).getId()).equals(id)){
					pos = i;
				}
			}
			
		}
		return pos;
	}

	public int searchArchiveByName(String archiveName){
		int pos = -1;
		for(int i = 0; i<archives.size(); i++){
			if((archives.get(i).getName()).equals(archiveName)){
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
				archives.add(newArchive);
				addAudio(newArchive, pos);
				break;
			case 2:
				newArchive = new Song(name, imageUrl, duration, albumName, genre, price);
				msj = "New song successful add.";
				archives.add(newArchive);
				addAudio(newArchive, pos);
				break;
			default:
				msj = "This is not producer user.";
				break;
		}
		return msj;
	}

	public void addAudio(Archive archive, int pos){
		(( ProducerUser ) ( users.get(pos) )).addAudio(archive);
	}

	public String createPlayList(String name, PlayListType playListType, int pos, int status){
		String msj = "";
		PlayList newPlayList = null;
		switch(status){
			case 0:
				newPlayList = new PlayList(name, playListType);
				msj = "New PlayList successful add.\n";
				msj += addPlayList(newPlayList, pos);
				break;

			default:
				msj = "This is not consumer user.";
				break;
		}
		return msj;
	}

	public String addPlayList(PlayList playList, int pos){
		String msj = (( ConsumerUser ) ( users.get(pos) )).addPlayList(playList);
		return msj;
	}

	public String executePlayListOption(int option, int userPos, int playListPos, int audioPos, String audioName){
		String msj = "";
		switch(option){
			case 1:
				msj = addAudioToPlayList(audioPos, playListPos, userPos);
				break;

			case 2:
				msj = eliminateAudioToPlayList(audioName, playListPos, userPos);
				break;

			default:
				msj = "Invalid Option!.";
				break;
		}
		return msj;
	}

	public int getPlayListPos(String playListName, int userPos){
		int playListPos = (( ConsumerUser ) ( users.get(userPos) )).searchPlayListByName(playListName);
		return playListPos;
	}

	public String addAudioToPlayList(int audioPos, int playListPos, int userPos){
		Archive archiveToAdd = getArchive(audioPos);
		(( ConsumerUser ) ( users.get(userPos) )).addAudioToPlayList(playListPos, archiveToAdd);
		String msj = "New audio added to the playlist of the user" + users.get(userPos).getName() + " .";
		return msj;
	}

	public String eliminateAudioToPlayList(String audioName, int playListPos, int userPos){
		(( ConsumerUser ) ( users.get(userPos) )).eliminateAudioToPlayList(playListPos, audioName);
		String msj = "Audio eliminated to the playlist of the user" + users.get(userPos).getName() + " .";
		return msj;
	}

	public String getPlayListAudios( int playListPos, int userPos){
		String msj = (( ConsumerUser ) ( users.get(userPos) )).getPlayListSongs(playListPos);
	
		return msj;
	}

	public String printConsumerUsers(){
		String msj = "<<< Consumer Users>>>\n";
		for(int i = 0; i<users.size(); i++){
			if(users.get(i) instanceof ConsumerUser){
				msj += users.get(i).toString();
			}
		}
		return msj;
	}

	public String printProducerUsers(){
		String msj = "<<< Producer Users>>>\n";
		for(int i = 0; i<users.size(); i++){
			if(users.get(i) instanceof ProducerUser){
				msj += users.get(i).toString();
			}
		}
		return msj;
	}

	public String searchPlayList(String idPlayList){
		String msj = "Playlist not registered.";
		String result = "";
		int playListPos = 0;
		for (int i = 0; i<users.size(); i++){
			if (users.get(i) instanceof ConsumerUser && users.get(i) != null){
				
				result = (( ConsumerUser ) ( users.get(i) )) .searchPlayList(idPlayList);				
				if (!result.equals("N")){
					msj = result;
				}				
				
			}
		}
		return msj;
	}
	//Tamaño de la matriz divido en dos menos 1.
}