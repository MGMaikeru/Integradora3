package model;
import java.util.ArrayList;
import java.util.Random;

public class PlayList{
	public static final int ROWS = 6; 
    public static final int COLUMNS = 6;

    private int[][] idMatrix;
	private String id;
	private String name;
	private PlayListType playListType;
	private ArrayList<Archive> audios;

	public PlayList(String name, PlayListType playListType){
		this.name = name;
		this.playListType = playListType;
		audios = new ArrayList<Archive>(30);
		this.idMatrix = new int[ROWS][COLUMNS];
		getRandomMatrix();
		this.id = generateId();
	}

	public String getName(){
		return this.name;
	}

	public String getId(){
		return this.id;
	}


	public String getRandomMatrix(){
		Random r = new Random();
		String msj = "";
		for (int i = 0; i < ROWS; i++){
			msj += "\n";
			for (int j = 0; j < COLUMNS; j++){
				this.idMatrix[i][j] = r.nextInt(9)+1;
				msj += this.idMatrix[i][j] + " ";
			}
		}
		return msj;
	}

	public String printMatrix(){
        String msj = ""; 

        for(int i = 0; i < ROWS; i++){
            msj += "\n"; 
            for(int j = 0; j < COLUMNS; j++){
                msj += idMatrix[i][j] + " ";
            }
        }
        return msj; 
    }

    public String printAudios(){
        String msj = "<<< AUDIOS >>>\n"; 

        for(int i = 0; i < audios.size(); i++){
            if (audios.get(i) != null){
            	msj += audios.get(i).toString()+ "\n"; 
            }
            
        }
        return msj; 
    }

	public int searchAudioByName(String audioName){
		int pos = -1;
		for(int i = 0; i<audios.size(); i++){
			if((audios.get(i).getName()).equals(audioName)){
				pos = i;
			}
		}
		return pos;
	}

	public String generateId(){
		String id = "";
		if(playListType == PlayListType.AUDIO){
			for (int i = ROWS-1; i > -1; i--){
				id += this.idMatrix[i][0];
			}
			for (int i = 1; i<ROWS; i++) {
				for (int j = 0; j<COLUMNS-1; j++) {
					if (i==j){
				 		id += this.idMatrix[i][j];
					}
				}
			}
			for (int i = ROWS-1; i > -1; i--){
				id += this.idMatrix[i][5];
			}

		}else if(playListType == PlayListType.PODSCAST){
			for (int j = 0; j < COLUMNS-3; j++){
				id += this.idMatrix[0][j];
			}
			for (int i = 1; i<ROWS; i++) {
				id += this.idMatrix[i][2];
			}
			for (int i = ROWS-1; i>0; i--) {
				id += this.idMatrix[i][3];
			}
			for (int j = 3; j < COLUMNS; j++){
				id += this.idMatrix[0][j];
			}

		}else if(playListType == PlayListType.MIXED){
			for (int i = ROWS-1; i>-1; i--) {
				for (int j = COLUMNS-1; j>-1; j--) {
					if ((((i+j)%2)!=0) /*&& (((i+j)%2)>1)*/){
				 		id += this.idMatrix[i][j];
					}
				}
			}
		}
		System.out.println(id);
		return id;
	}

	public String addAudio(Archive newAudio){
		String msj = "";
		if (this.playListType == PlayListType.AUDIO) {
			 msj = "This playlist only acepts songs";
			if (newAudio instanceof Song) {
				 msj = "Song successful added";
				audios.add(newAudio);
			}
		}else if (this.playListType == PlayListType.PODSCAST) {
			msj = "This playlist only acepts podcasts";
			if (newAudio instanceof PodScast) {
				msj = "Podcast successful added";
				audios.add(newAudio);
			}
		}else if (this.playListType == PlayListType.MIXED){
			msj = "Audio successful added";
			audios.add(newAudio);
		}
		return msj;
	}

	public void eliminateAudio(int audioPos){
		audios.remove(audioPos);
	}

	public String toString(){
		return "" + printMatrix() + " \n" +
		 "--------------------\n"+
		 "Playlist Id: " + this.id + "\n"+
		 "Playlist name: " + this.name + "\n"+
		 "--------------------";
	}
}