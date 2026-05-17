package graphe.impl;

import graphe.modele.IGraphe;
import graphe.modele.IEntite;
import graphe.modele.NatureRelation;
import graphe.modele.RelationEntrante;
import graphe.modele.RelationSortante;

import java.util.*;

public class Graphe implements IGraphe {
    private final Map<IEntite, Set<RelationSortante>> relationsSortantesMap;
    private final Map<IEntite, Set<RelationEntrante>> relationsEntrantesMap;

    public Graphe() {
        this.relationsSortantesMap = new HashMap<>();
        this.relationsEntrantesMap = new HashMap<>();
    }

    @Override
    public boolean ajouterEntite(IEntite entite) {
        if (entite == null || relationsSortantesMap.containsKey(entite)) return false;
        relationsSortantesMap.put(entite, new HashSet<>());
        relationsEntrantesMap.put(entite, new HashSet<>());
        return true;
    }

    @Override
    public boolean ajouterRelation(IEntite source, IEntite cible, NatureRelation nature) {
        if (source == null || cible == null || nature == null) return false;

        ajouterEntite(source);
        ajouterEntite(cible);

        RelationSortante sortante = new RelationSortante(cible, nature);
        RelationEntrante entrante = new RelationEntrante(source, nature);

        if (relationsSortantesMap.get(source).contains(sortante)) {
            return false;
        }

        relationsSortantesMap.get(source).add(sortante);
        relationsEntrantesMap.get(cible).add(entrante);
        return true;
    }

    @Override
    public Set<IEntite> entites() {
        return Collections.unmodifiableSet(relationsSortantesMap.keySet());
    }

    @Override
    public Set<RelationSortante> relationsSortantes(IEntite source) {
        if (!relationsSortantesMap.containsKey(source)) return Collections.emptySet();
        return Collections.unmodifiableSet(relationsSortantesMap.get(source));
    }

    @Override
    public Set<RelationEntrante> relationsEntrantes(IEntite cible) {
        if (!relationsEntrantesMap.containsKey(cible)) return Collections.emptySet();
        return Collections.unmodifiableSet(relationsEntrantesMap.get(cible));
    }
}