package graphe;

import java.util.*;

public class GrapheListeAdjacence implements IGraphe {
    private Map<String, List<Arete>> adjacence = new HashMap<>();

    // Classe interne pour les arêtes
    private static class Arete {
        String destination;
        String etiquette;
        Arete(String d, String e) { this.destination = d; this.etiquette = e; }
    }

    private void validerSommet(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Le sommet ne peut pas être vide");
        }
    }

    @Override
    public void ajouterSommet(String e) {
        validerSommet(e); 
        if (!adjacence.containsKey(e)) {
            adjacence.put(e, new ArrayList<>());
        }
    }

    @Override
    public void ajouterArete(String src, String dest, String lib) {
        ajouterSommet(src);
        ajouterSommet(dest);

        for (Arete a : adjacence.get(src)) {
            if (a.destination.equals(dest)) {
                a.etiquette = lib; 
                return; 
            }
        }

        adjacence.get(src).add(new Arete(dest, lib));
    }

    @Override
    public boolean contientSommet(String e) {
        return adjacence.containsKey(e);
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(adjacence.keySet());
    }

    @Override
    public boolean contientArete(String src, String dest) {
        if (!adjacence.containsKey(src)) return false;
        for (Arete a : adjacence.get(src)) {
            if (a.destination.equals(dest)) return true;
        }
        return false;
    }

    @Override
    public String getEtiquette(String src, String dest) {
        if (!adjacence.containsKey(src)) return null;
        for (Arete a : adjacence.get(src)) {
            if (a.destination.equals(dest)) return a.etiquette;
        }
        return null;
    }

    @Override
    public List<String> getSuccesseurs(String s) {
        List<String> res = new ArrayList<>();
        if (adjacence.containsKey(s)) {
            for (Arete a : adjacence.get(s)) res.add(a.destination);
        }
        return res;
    }

    @Override
    public List<String> getPredecesseurs(String s) {
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<Arete>> entry : adjacence.entrySet()) {
            for (Arete a : entry.getValue()) {
                if (a.destination.equals(s)) res.add(entry.getKey());
            }
        }
        return res;
    }

    @Override
    public void supprimerSommet(String e) {
        if (!adjacence.containsKey(e)) return;
        adjacence.remove(e);
        for (List<Arete> aretes : adjacence.values()) {
            aretes.removeIf(a -> a.destination.equals(e));
        }
    }

    @Override
    public void supprimerArete(String src, String dest) {
        if (adjacence.containsKey(src)) {
            adjacence.get(src).removeIf(a -> a.destination.equals(dest));
        }
    }

    @Override public int getNombreSommets() { return adjacence.size(); }
    @Override public int getNombreAretes() {
        int total = 0;
        for (List<Arete> l : adjacence.values()) total += l.size();
        return total;
    }
}
