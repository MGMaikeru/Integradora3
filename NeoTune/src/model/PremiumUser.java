package model;
import java.util.ArrayList;

public class PremiumUser extends ConsumerUser{
	private ArrayList<PlayList> playLists;
	private ArrayList<Song> songs;

	public PremiumUser(String nickName, String id){
		super(nickName, id);
		playLists = new ArrayList<PlayList>();
		songs = new ArrayList<Song>();
	}

	@Override
	public void addAudioToPlayList(int playListPos, Archive audio){
		playLists.get(playListPos).addAudio(audio);
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

}