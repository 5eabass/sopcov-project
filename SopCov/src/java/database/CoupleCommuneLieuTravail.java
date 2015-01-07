package database;

/**
 * CoupleCommuneLieuTravail permet d'associer à un couple commune,lieu de
 * travail un nombre d'utilisateurs. Si le trajet Toulouse 31100, Sopra
 * entreprise 1, intéresse 80 personnes alors on aura : nbrUtilisateurs = 80
 * commune = Toulouse codePostal = 31100 nomLieuTravail = Sopra entreprise 1
 *
 * @author gb
 */
public class CoupleCommuneLieuTravail {

    private int nbrUtilisateurs;
    private String commune;
    private String codePostal;
    private String nomLieuTravail;

    public CoupleCommuneLieuTravail(int nbrUtilisateurs, String commune, String codePostal, String nomLieuTravail) {
        this.nbrUtilisateurs = nbrUtilisateurs;
        this.commune = commune;
        this.codePostal = codePostal;
        this.nomLieuTravail = nomLieuTravail;
    }

    public int getNbrUtilisateurs() {
        return nbrUtilisateurs;
    }

    public String getCommune() {
        return commune;
    }

    public String getNomLieuTravail() {
        return nomLieuTravail;
    }

    public String getCodePostal() {
        return codePostal;
    }

    @Override
    public String toString() {
        return "database.CoupleCommuneLieuTravail[ nbrUtilisateurs =" + nbrUtilisateurs
                + " | commune =" + commune
                + " | codePostal = " + codePostal
                + " | nomLieuTravail= " + nomLieuTravail+ " ]";
    }

}
