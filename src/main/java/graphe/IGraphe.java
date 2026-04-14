package graphe;

import java.util.List;

public interface IGraphe {
    void ajouterSommet(String sommet);
    void supprimerSommet(String sommet);
    boolean contientSommet(String sommet);
    List<String> getSommets();
    void ajouterArete(String source, String destination, String libelle);
    void supprimerArete(String source, String destination);
    boolean contientArete(String source, String destination);
    String getEtiquette(String source, String destination);
    List<String> getSuccesseurs(String sommet); // <-- Doit être List
    List<String> getPredecesseurs(String sommet); // <-- Doit être List
    int getNombreSommets();
    int getNombreAretes();
}