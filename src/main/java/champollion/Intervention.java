package champollion;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Date;

public class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee = false;
    private int heureDeb;
    private UE matiere;
    private TypeIntervention type;

    public Intervention(Date debut, int duree, int heureDeb, UE matiere, TypeIntervention type){
        this.debut = debut;
        this.duree = duree;
        this.heureDeb = heureDeb;
        this.matiere = matiere;
        this.type = type;
    }

    public UE getMatiere(){
        return this.matiere;
    }

    public TypeIntervention getType(){
        return this.type;
    }

    public int getDuree(){
        return this.duree;
    }
}
