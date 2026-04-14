package graphe;

import java.util.*;

public class GrapheMatriceAdjacence implements IGraphe {
    private Map<String, Integer> sommetsIndex = new HashMap<>();
    private String[][] matrice = new String[100][100];
    private int nbS = 0;

    // Cette méthode règle ton échec sur "ajouterSommet"
    private void validerSommet(String s) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du sommet ne peut pas être null ou vide");
        }
    }

    @Override
    public void ajouterSommet(String e) {
        validerSommet(e); // Si e est vide, ça lance l'erreur attendue par le test
        if (!sommetsIndex.containsKey(e)) {
            sommetsIndex.put(e, nbS++);
        }
    }

    @Override
    public void ajouterArete(String src, String dest, String lib) {
        ajouterSommet(src);
        ajouterSommet(dest);
        // On récupère les indices et on remplit la matrice
        // (lib == null) ? "" : lib  permet de ne pas avoir de valeur null dans la matrice
        matrice[sommetsIndex.get(src)][sommetsIndex.get(dest)] = (lib == null) ? "" : lib;
    }

    @Override
    public boolean contientSommet(String e) {
        return sommetsIndex.containsKey(e);
    }

    @Override
    public boolean contientArete(String s, String d) {
        if (!contientSommet(s) || !contientSommet(d)) return false;
        return matrice[sommetsIndex.get(s)][sommetsIndex.get(d)] != null;
    }

    @Override
    public List<String> getSuccesseurs(String s) {
        List<String> res = new ArrayList<>();
        if (!contientSommet(s)) return res;
        int i = sommetsIndex.get(s);
        for (Map.Entry<String, Integer> entry : sommetsIndex.entrySet()) {
            if (matrice[i][entry.getValue()] != null) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    @Override
    public List<String> getPredecesseurs(String s) {
        List<String> res = new ArrayList<>();
        if (!contientSommet(s)) return res;
        int j = sommetsIndex.get(s);
        for (Map.Entry<String, Integer> entry : sommetsIndex.entrySet()) {
            if (matrice[entry.getValue()][j] != null) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    @Override
    public int getNombreSommets() { return sommetsIndex.size(); }

    @Override
    public int getNombreAretes() {
        int count = 0;
        for(int i=0; i<nbS; i++) {
            for(int j=0; j<nbS; j++) {
                if(matrice[i][j] != null) count++;
            }
        }
        return count;
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(sommetsIndex.keySet());
    }

    @Override
    public void supprimerSommet(String e) {
        if (!contientSommet(e)) return;
        int idx = sommetsIndex.get(e);
        for (int k = 0; k < 100; k++) {
            matrice[idx][k] = null;
            matrice[k][idx] = null;
        }
        sommetsIndex.remove(e);
    }

    @Override
    public void supprimerArete(String s, String d) {
        if(contientArete(s, d)) {
            matrice[sommetsIndex.get(s)][sommetsIndex.get(d)] = null;
        }
    }

    @Override
    public String getEtiquette(String s, String d) {
        if (!contientArete(s, d)) return null;
        return matrice[sommetsIndex.get(s)][sommetsIndex.get(d)];
    }
}