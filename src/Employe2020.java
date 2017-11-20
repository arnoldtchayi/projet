/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moussa flo
 */

    public abstract class Employe2020 {
    private String matricule;
    private String nom;
    private String prenom;
    private int age;
    private String date;
    private int salairebase;
    public Employe2020(String matricule,String nom, String prenom, int age, String date,int salairebase){
 this.matricule = matricule;
 this.nom = nom;
 this.prenom = prenom;
 this.age = age;
 this.date = date;
 this.salairebase = salairebase;
}
public abstract double calculerSalaire();
public String getTitre(){
    return "L'Employe";
}
public String getNom(){
    return getTitre()+prenom+" "+nom;
}

  }
 abstract class Commercial extends Employe2020{
     private double chiffreAffaire;
     public Commercial(String matricule, String nom, String prenom, int age, String date,double chiffreAffaire,int salairebase ){
     super (matricule, nom, prenom, age, date,salairebase);
     this.chiffreAffaire = chiffreAffaire;
     }
     public double getChiffreAffaire(){
         return chiffreAffaire;
     }
    
 }
class Vendeur extends Commercial{
    private final static double POURCENT_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
    public Vendeur (String matricule, String nom, String prenom, int age, String date,double chiffreAffaire,int salairebase){
        super (matricule, nom, prenom, age, date, chiffreAffaire,salairebase);
    }
    public double calculerSalaire(){
        return (POURCENT_VENDEUR * getChiffreAffaire())+BONUS_VENDEUR;
    }
    public String getTitre(){
        return "le vendeur : ";
    }
}
    class Representant extends Commercial{
        private final static double POURCENT_REPRESENTANT = 0.2;
        private final static int BONUS_REPRESENTANT = 200;
        public Representant(String matricule, String nom, String prenom, int age, String date,double chiffreAffaire,int salairebase){
            super (matricule, nom, prenom, age, date, chiffreAffaire,salairebase);}
            public double calculerSalaire(){
                return (POURCENT_REPRESENTANT * getChiffreAffaire())+BONUS_REPRESENTANT;     
        }
            public String getTitre(){
                return"Le representant : ";
            }
    }
         class Technicien extends Employe2020{
        private final static double FACTEUR_UNITE = 5.0;
        private int unites = 30;   
        public Technicien(String matricule, String nom, String prenom, int age, String date,double chiffreAffaire,int salairebase){
            super(matricule,nom,prenom,age,date,salairebase);
            this.unites = unites;
    }
        public double calculerSalaire(){
            return FACTEUR_UNITE * unites;
        }
        public String getTitre(){
            return "Le technicien : ";
        }
    }
    class Manutentionnaire extends Employe2020{
        private final static double SALAIRE_HORAIRE = 65.0;
        private int heures;
        public Manutentionnaire(String matricule, String nom, String prenom, int age, String date,double chiffreAffaire,int heures,int salairebase){
            super(matricule,nom,prenom,age,date,salairebase);
            this.heures = heures;}
        public double calculerSalaire(){
         return SALAIRE_HORAIRE * heures;}
        public String getTitre(){
            return "Le manutentionnaire : ";
        }
     }
interface ARisque {
    int PRIME = 25000;
}

class TechnARisque extends Technicien implements ARisque {

    public TechnARisque(String matricule, String prenom, String nom, int age, String date, int unites,int salairebase) {
        super (matricule, nom,prenom, age, date, unites,salairebase);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() +PRIME;
    }
     public String getTitre(){
            return "Le technicien a risque : ";
        }
}
class ManutARisque extends Manutentionnaire implements ARisque {
    
    public ManutARisque(String matricule, String nom, String prenom, int age, String date,double chiffreAffaire, int heures,int salairebase) {
        super(matricule,nom,prenom,age, date,chiffreAffaire, heures,salairebase);
    }

    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
     public String getTitre(){
            return "Le manutentionnaire a risque : ";
        }
}
class Personnel {
    private Employe2020[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 200;

    public Personnel() {
        staff = new Employe2020[MAXEMPLOYE];
        nbreEmploye = 0;
    }

    public void ajouterEmploye(Employe2020 e) {
        ++nbreEmploye;
        if (nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
        }
    }

    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nbreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nbreEmploye;
    }

    public void afficherSalaires() {
        for (int i = 0; i < nbreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne " +staff[i].calculerSalaire() + " francs.");
        }
    }
}
class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("15A", "nsabguien","tchayi", 45, "1995", 110000,110000));
        p.ajouterEmploye(new Representant("01C","anseryn", "Aht", 25, "2001", 110000,110000));
        p.ajouterEmploye(new Technicien("02A","baba", "jason", 28, "1998", 10,110000));
        p.ajouterEmploye(new Manutentionnaire("50A","nina", "stelle", 32, "1998",2.0, 45,110000));
        p.ajouterEmploye(new TechnARisque("04B","arnold", "julien", 28, "2000", 10,110000));
        p.ajouterEmploye(new ManutARisque("01A","boby", "b0ban", 30, "2001",2.0, 45,110000));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");

    }

}

    

