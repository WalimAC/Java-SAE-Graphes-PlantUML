<<<<<<< HEAD
<div align="center">
  <a href="https://iutparis-seine.u-paris.fr">
    <img src="https://medias.podcastics.com/podcastics/podcasts/artwork/universite-paris-cite.png.23d93fd89c820e5e702963c782b2214f.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Générateur de Graphes et Analyseur de Diagrammes PlantUML</h3>

  <p align="center">
    Application Java permettant de lire des diagrammes PlantUML, de les transformer en graphes et de calculer automatiquement leurs dépendances.
    <br>
    <i>⚠️ | Projet universitaire - BUT Informatique (Semestre 2) - Mai 2026</i>
        <br />
    <br />
    <a href="https://walimamor.fr/?i=2#contact">Signaler un bug</a>
  </p>
</div>

---

##  ℹ️ | Organisations fichier 
* Le projet complet et fonctionnel se situe entièrement dans le dossier `Sprint final`. Le dossier `Sprint 1` contient uniquement la première partie de la SAE (réalisée en avril). Pour tester l'application et lancer les tests de recette, veuillez utiliser les fichiers du dossier `Sprint Final`.

## 👥 | Équipe & Groupe de TP
* **Groupe de TP ( All ):** TP111
* **Membres de l'équipe :** Joseph COUSIN, Rafael BARGUIDJIAN, Walim AMOR-CHELIHI

---

## 📝 | État d'avancement du projet (Recette)

Voici le bilan des fonctionnalités implémentées :

### ✅ | Ce qui fonctionne parfaitement :
- **Sprint 1 :** Les structures de données initiales avec variables encapsulées en `private` et tests unitaires d'origine conformes sur GitHub.
- **Sprint Final - Exercice A (Graphe) :** Implémentation complète de l'interface `IGraphe` via la classe `Graphe`. Double table de hachage optimisée préservant l'intégrité des données (`Collections.unmodifiableSet`).
- **Sprint Final - Exercice B (Algorithmes) :** Implémentation des méthodes `dependantsDirects` et `dependantsElargis` en utilisant exclusivement l'interface `IGraphe`. La remontée récursive par contenance s'arrête strictement au premier paquetage rencontré.
- **Parseur PlantUML :** L'intégration avec le moteur d'import/export et la gestion des types (classes, interfaces, paquetages) passe 100 % des tests.

### ⛔ | Limitations du projet :
- Aucune anomalie constatée. Le projet valide **l'intégralité** de la suite de tests unitaires officiels fournis par le professeur (`test passed`).

---

## 🛠️ | Technologies & IDE utilisés

<img src="https://cdn-icons-png.flaticon.com/512/5968/5968282.png" alt="Logo Java" width="40" height="40"> &nbsp;
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/3840px-IntelliJ_IDEA_Icon.svg.png" alt="Logo IntelliJ" width="40" height="40">

* **Langage :** Java
* **IDE recommandé :** IntelliJ IDEA
* **Framework de tests :** JUnit 5 (Jupiter)

---

## 🎓 | Crédits

`Sujet et architecture de test par Mikal Ziane — Professeur à l'Université Paris Cité`
<br/>
=======
# SAE 2.02 – Graphes de dépendances

## Membres de l'équipe

| Prénom Nom | Groupe TP |
|------------|-----------|
| *À compléter* | TP 111 |
| *À compléter* | TP 111 |
| *À compléter* | TP 111|
| *À compléter* | TP 111|

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
>>>>>>> 1cf34e7a7f594143c0420dcb9d543a35416871d4
