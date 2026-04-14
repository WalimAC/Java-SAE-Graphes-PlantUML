# SAE 2.02 – Graphes de dépendances

## Membres de l'équipe

| Prénom Nom | Groupe TP |
|------------|-----------|
| *À compléter* | TP__ |
| *À compléter* | TP__ |
| *À compléter* | TP__ |
| *À compléter* | TP__ |

---

## Sprint 1 – Représentation des graphe

### Structure du projet

```
src/
├── main/java/graphe/
│   ├── graphe.IGraphe.java                  ← Interface commune
│   ├── graphe.GrapheListeAdjacence.java     ← Implémentation 1 : liste d'adjacence
│   └── graphe.GrapheMatriceAdjacence.java   ← Implémentation 2 : matrice d'adjacence
└── test/java/graphe/
    ├── GrapheListeAdjacenceTest.java  ← Tests JUnit de l'implémentation 1
    └── GrapheMatriceAdjacenceTest.java← Tests JUnit de l'implémentation 2
pom.xml                               ← Configuration Maven
```

### Deux structures de données

| Critère | `graphe.GrapheListeAdjacence` | `graphe.GrapheMatriceAdjacence` |
|---------|----------------------|------------------------|
| Structure | `Map<String, List<Arete>>` | `Map<String, Map<String, String>>` |
| Test arête | O(degré sortant) | O(1) moyen |
| Successeurs | O(1) | O(1) |
| Prédécesseurs | O(n + m) | O(n) |
| Espace | O(n + m) — efficace pour graphe creux | O(n²) — efficace pour graphe denses |

### Lancer les tests

```bash
mvn test
```

### Ce qui a été fait

- [x] Interface `graphe.IGraphe` avec toutes les opérations nécessaires
- [x] `graphe.GrapheListeAdjacence` : implémentation par liste d'adjacence
- [x] `graphe.GrapheMatriceAdjacence` : implémentation par matrice d'adjacence (HashMap 2D)
- [x] Tests JUnit 5 pour chaque implémentation (≥ 2 tests par méthode non triviale)
- [x] Sommets représentés par des `String` (ex : `"chenille.Chenille"`)
- [x] Arêtes orientées avec étiquette optionnelle (ex : `"create"`)
- [x] Pas de multiplicités sur les arêtes
