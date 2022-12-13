package champollion;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.*;

public class Enseignant extends Personne {

    private ArrayList<UE> enseignements;
    private ArrayList<Intervention> interventionsPlanifiés;

    public Enseignant(String nom, String email) {
        super(nom, email);
        this.enseignements = new ArrayList<UE>();
        this.interventionsPlanifiés = new ArrayList<Intervention>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        long hPrev = 0;
        for(int i = 0; i < this.enseignements.size(); i++){
            hPrev += this.enseignements.get(i).getService(this).getHeureEquivalentTD();
        }
        return (int)hPrev;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        ServicePrevu tmp = ue.getService(this);
        long hTD = tmp.getHeureEquivalentTD();
        return (int)hTD;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     * @throws Exception
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) throws Exception {
        ServicePrevu tmp = new ServicePrevu(volumeCM, volumeTD, volumeTP);
        // System.out.println(ue.getHeureCM() + " " + ue.getHeureTD() + " " + ue.getHeureTP());
        // System.out.println(tmp.getVolumeCM() + " " + tmp.getVolumeTD() + " " + tmp.getVolumeTP());
        // System.out.println(ue.checkVolumeHoraire(tmp));
        if(!(ue.checkVolumeHoraire(tmp))){
            throw new Exception("Volume Horaire demandé par l'enseignant trop important");
        }
        if(!(this.enseignements.contains(ue))){
            this.enseignements.add(ue);
            ue.addIntervenant(this, tmp);
        }
        else{
            ue.addService(this, tmp);
        }
    }

    public void ajouteIntervention(Intervention i){
        this.interventionsPlanifiés.add(i);
        UE tmp = i.getMatiere();
        System.out.println(i.getMatiere());
        tmp.addSeance(i);
        i.getLieu().addOccupation(i);
    }

    public int getNbIntervention(){
        return this.interventionsPlanifiés.size();
    }

    public boolean enSousService(){
        return this.heuresPrevues() < 192;
    }

    public int resteAPlanifier(UE ue, TypeIntervention type){
        ServicePrevu sp = ue.getService(this);
        int nbHeurePlanif = 0;
        for(int i = 0; i < this.interventionsPlanifiés.size(); i++){
            if(this.interventionsPlanifiés.get(i).getType() == type){
                nbHeurePlanif += this.interventionsPlanifiés.get(i).getDuree();
            }
        }

        return (sp.getVolumeCM() - nbHeurePlanif);
    }

}
