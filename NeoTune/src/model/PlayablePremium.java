package model; 

public interface IPlayPremium{

	public String play(int reproductions){
		String reproduction = "";
		if (reproductions%2 != 0) {
			reproduction = "Ad";
			try {
       //Ponemos a "Dormir" el programa durante los ms que queremos
   				Thread.sleep(5*1000);
			}
			catch (Exception e) {
   				System.out.println(e);
			}

		}else{
			reproduction = "Ad";
			try {
       //Ponemos a "Dormir" el programa durante los ms que queremos
   				Thread.sleep(5*1000);
			}
			catch (Exception e) {
   				System.out.println(e);
			}
		}
	}

	public default String run(double km){
		return "runnig in IMove interface"; 
	}

}