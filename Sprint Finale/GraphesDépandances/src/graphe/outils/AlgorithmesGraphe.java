package graphe.outils;

import graphe.modele.IGraphe;
import graphe.modele.IEntite;
import graphe.modele.NatureRelation;
import graphe.modele.RelationEntrante;
import graphe.impl.Entite;
import graphe.impl.TypeEntite;

import java.util.HashSet;
import java.util.Set;

public final class AlgorithmesGraphe {

    private AlgorithmesGraphe() {}

    /**
     * Retourne les entités qui dépendent directement de la cible
     * via une seule arête de dépendance statique.
     */
    public static Set<IEntite> dependantsDirects(IGraphe graphe, IEntite cible) {
        Set<IEntite> resultat = new HashSet<>();
        if (graphe == null || cible == null) return resultat;

        for (RelationEntrante rel : graphe.relationsEntrantes(cible)) {
            if (rel.nature().estDependanceStatique()) {
                resultat.add(rel.source());
            }
        }
        return resultat;
    }

    /**
     * Retourne les dépendants directs de la cible, puis remonte par contenance.
     */
    public static Set<IEntite> dependantsElargis(IGraphe graphe, IEntite cible) {
        Set<IEntite> resultat = new HashSet<>();
        if (graphe == null || cible == null) return resultat;

        Set<IEntite> directs = dependantsDirects(graphe, cible);

        for (IEntite entite : directs) {
            resultat.add(entite);
            remonterParContenance(graphe, entite, resultat);
        }
        return resultat;
    }

    /**
     * Méthode récursive pour remonter la chaîne des relations CONTIENT.
     * On explore TOUTES les relations de contenance entrantes.
     */
    private static void remonterParContenance(IGraphe graphe, IEntite entiteActuelle, Set<IEntite> resultat) {
        for (RelationEntrante rel : graphe.relationsEntrantes(entiteActuelle)) {
            if (rel.nature() == NatureRelation.CONTIENT) {
                IEntite conteneur = rel.source();

                if (resultat.contains(conteneur)) {
                    continue;
                }

                resultat.add(conteneur);

                if (conteneur instanceof Entite entiteConcrete) {
                    if (entiteConcrete.type() != TypeEntite.PACKAGE) {
                        remonterParContenance(graphe, conteneur, resultat);
                    }
                } else if (conteneur.estType()) {
                    remonterParContenance(graphe, conteneur, resultat);
                }
            }
        }
    }
}