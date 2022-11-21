package model;
import java.util.ArrayList;

public class PremiumUser extends ConsumerUser implements IPlayPremium{
	private ArrayList<PlayList> playLists;
	private ArrayList<Archive> songs;
	private int reproductions;

	public PremiumUser(String nickName, String id){
		super(nickName, id);
		playLists = new ArrayList<PlayList>();
		songs = new ArrayList<Archive>();
	}

	@Override
	public String addSong(Archive newSong){
		String msj = "New song bought and added.";
		songs.add(newSong);

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
	public String addAudioToPlayList(int playListPos, Archive audio){
		String status = playLists.get(playListPos).addAudio(audio);
		return status;
	}

	@Override
	public String getPlayListSongs(int playListPos){
		String msj = playLists.get(playListPos).printAudios();
		return msj;
	}

	@Override
	public void eliminateAudioToPlayList(int playListPos, String audioName){
		int auidoPos = playLists.get(playListPos).searchAudioByName(audioName);
		playLists.get(playListPos).eliminateAudio(auidoPos);
	}

	@Override
	public String addPlayList(PlayList newPlayList){
		playLists.add(newPlayList);
		return newPlayList.toString();
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
		String reproduction = "";

		reproduction = "Reproducing Song...";
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
	public String playPodcast(){
		String reproduction = "";

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

}