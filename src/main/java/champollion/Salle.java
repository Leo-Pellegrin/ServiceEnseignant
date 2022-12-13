package champollion;

import java.util.*;

public class Salle {
    public String intitule;
    public int capacite;
    public ArrayList<Intervention> occupations;

    public Salle(String intitule, int capacite){
        this.intitule = intitule;
        this.capacite = capacite;
        this.occupations = new ArrayList<Intervention>();
    }

    public void addOccupation(Intervention i){
        this.occupations.add(i);
    }
}
