package champollion;
import java.util.*;

public class UE {
    private final String myIntitule;
    private int heureCM;
    private int heureTP;
    private int heureTD;
    private HashMap<Enseignant, ServicePrevu> intervenants;
    private ArrayList<Intervention> seancesplanifies;

    public UE(String intitule, int heureCM, int heureTP, int heureTD) {
        myIntitule = intitule;
        this.heureCM = heureCM;
        this.heureTP = heureTP;
        this.heureTD = heureTD;
        this.intervenants = new HashMap<Enseignant, ServicePrevu>();
        this.seancesplanifies = new ArrayList<Intervention>();
    }

    public String getIntitule() {
        return myIntitule;
    }

    public int getHeureCM(){
        return this.heureCM;
    }

    public int getHeureTP(){
        return this.heureTP;
    }

    public int getHeureTD(){
        return this.heureTD;
    }
    public void addIntervenant(Enseignant e, ServicePrevu sp){
        this.intervenants.put(e, sp);
    }

    public ServicePrevu getService(Enseignant e){
        return this.intervenants.get(e);
    }

    public boolean checkVolumeHoraire(ServicePrevu sp){
        if(sp.getVolumeCM() > this.heureCM || sp.getVolumeTD() > this.heureTD || sp.getVolumeTP() > this.heureTP){
            return false;
        }
        else{
            return true;
        }
    }

    public void addService(Enseignant e, ServicePrevu sp1){
        ServicePrevu sp2 = this.intervenants.get(e);
        sp2.addHeureService(sp1);
        this.intervenants.put(e, sp2);
    }

    public void addSeance(Intervention i){
        this.seancesplanifies.add(i);
    }

    public int getNbIntervention(){
        return this.seancesplanifies.size();
    }
}
