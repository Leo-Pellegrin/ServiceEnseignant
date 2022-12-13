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
    private Salle lieu;

    public Intervention(Date debut, int duree, int heureDeb, UE matiere, TypeIntervention type, Salle lieu){
        this.debut = debut;
        this.duree = duree;
        this.heureDeb = heureDeb;
        this.matiere = matiere;
        this.type = type;
        this.lieu = lieu;
    }

    public Salle getLieu(){
        return this.lieu;
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
