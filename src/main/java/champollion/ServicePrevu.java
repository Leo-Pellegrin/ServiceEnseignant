package champollion;
import java.lang.Math;

public class ServicePrevu {
	private int volumeCM;
	private int volumeTD;
	private int volumeTP;

	public ServicePrevu(int volumeCM, int volumeTD, int volumeTP){
		this.volumeCM = volumeCM;
		this.volumeTD = volumeTD;
		this.volumeTP = volumeTP;
	}

	public long getHeureEquivalentTD(){
		return Math.round(this.volumeCM*1.5 + this.volumeTD + this.volumeTP*0.75);
	}

	public int getVolumeCM(){
		return volumeCM;
	}

	public int getVolumeTD(){
		return volumeTD;
	}

	public int getVolumeTP(){
		return volumeTP;
	}

	public void addHeureService(ServicePrevu sp){
		this.volumeCM += sp.getVolumeCM();
		this.volumeTD += sp.getVolumeTD();
		this.volumeTP += sp.getVolumeTP();
	}

}	
