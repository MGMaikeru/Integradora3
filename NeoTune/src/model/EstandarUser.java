package model;
import java.util.ArrayList;
import java.util.Random;

public class EstandarUser extends ConsumerUser implements IPlayEstandar{
	private ArrayList<PlayList> playLists;
	private ArrayList<Archive> songs;
	private int reproductions;

	public EstandarUser(String nickName, String id){
		super(nickName, id);
		playLists = new ArrayList<PlayList>(20);
		songs = new ArrayList<Archive>(100);
		this.reproductions = 0;
	}

	@Override
	public String addSong(Archive newSong){
		String msj = "Limit of 100 audios reached.";
		boolean isAdded = false;
		for(int i = 0; i<100 && !isAdded; i++){
			msj = "New song bought and added.";
			songs.add(newSong);
			isAdded = true;
		}
		return msj;
	}

	@Override
	public String addPlayList(PlayList newPlayList){
		String msj = "Limit of 20 playlists reached.";
		boolean isAdded = false;
		for(int i = 0; i<20 && !isAdded; i++){
			msj = "New playlist bought and added.\n"+
				newPlayList.toString();
			
			playLists.add(newPlayList);
			isAdded = true;
		}
		
		return msj;
	}

	@Override
	public ArrayList<PlayList> getPlayLists(){
		return playLists;
	}

	@Override
	public String searchPlayList(String playListId){
		String msj = "N";
		boolean isFound = false;
		for (int i = 0; i < playLists.size() && !isFound; i++){
			if (playLists.get(i).getId().equals(playListId)){
				msj = playLists.get(i).toString() + "\n"+
					 playLists.get(i).printAudios();
				isFound = true;
			}
		}
		return msj;
	}

	@Override
	public String getPlayListSongs(int playListPos){
		String msj = playLists.get(playListPos).printAudios();
		return msj;
	}

	@Override
	public String addAudioToPlayList(int playListPos, Archive audio){
		String status = playLists.get(playListPos).addAudio(audio);
		return status;
	}

	@Override
	public void eliminateAudioToPlayList(int playListPos, String audioName){
		int auidoPos = playLists.get(playListPos).searchAudioByName(audioName);
		playLists.get(playListPos).eliminateAudio(auidoPos);
	}

	@Override
	public int searchPlayListByName(String name){
		int pos = -1;
		for(int i = 0; i<playLists.size(); i++){
			if((playLists.get(i).getName()).equals(name)){
				pos = i;
			}
		}
		return pos;
	}

	@Override
	public String playSong(){

		this.reproductions += 1;
		Random r = new Random();
		int adNumber = r.nextInt(3)+1;
		String reproduction = "";
		if (this.reproductions%2 != 0 && this.reproductions>2) {
			reproduction = chooseAd(adNumber);
			System.out.println(reproduction);
			try {
   				Thread.sleep(5*1000);
			}
			catch (Exception e) {
   				System.out.println(e);
			}

			reproduction = "Reproducing Song...";
			System.out.println(reproduction);
			try {
   				Thread.sleep(5*1000);
			}
			catch (Exception e) {
   				System.out.println(e);
			}

		}else{
			reproduction = "Reproducing Song...";
			System.out.println(reproduction);
			try {
   				Thread.sleep(5*1000);
			}
			catch (Exception e) {
   				System.out.println(e);
			}
		}
		return reproduction;
	}

	@Override
	public String playPodcast(){
		Random r = new Random();
		int adNumber = r.nextInt(3)+1;
		String reproduction = "";
		reproduction = chooseAd(adNumber);
		System.out.println(reproduction);
		try {
   			Thread.sleep(5*1000);
		}
		catch (Exception e) {
   			System.out.println(e);
		}

		reproduction = "Reproducing podcast...";
		System.out.println(reproduction);
		try {
   			Thread.sleep(5*1000);
		}
		catch (Exception e) {
   			System.out.println(e);
		}
		return reproduction;
	}

	@Override
	public String chooseAd(int adNumber){
		String ad = "";
		switch(adNumber){
		case 1: ad = "Nike - Just Do It.";
			break;

		case 2: ad = "Coca-Cola - Open Happiness.";
			break;

		case 3: ad = "M&Ms - Melts in Your Mouth, Not in Your Hands.";
			break;
		}

		return ad;
	}
}