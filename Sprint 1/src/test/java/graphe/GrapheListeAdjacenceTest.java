package graphe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests – graphes.GrapheListeAdjacence")
class GrapheListeAdjacenceTest {

    private GrapheListeAdjacence graphe;

    @BeforeEach
    void setUp() {
        graphe = new GrapheListeAdjacence();
    }

    @Test
    @DisplayName("ajouterSommet : un nouveau sommet est bien présent")
    void ajouterSommet_nouveauSommet_presentDansLeGraphe() {
        graphe.ajouterSommet("chenille.Chenille");
        assertTrue(graphe.contientSommet("chenille.Chenille"));
    }

    @Test
    @DisplayName("ajouterSommet : ajouter deux fois le même sommet ne crée pas de doublon")
    void ajouterSommet_doublon_pasDeDuplication() {
        graphe.ajouterSommet("chenille.Anneau");
        graphe.ajouterSommet("chenille.Anneau");
        assertEquals(1, graphe.getNombreSommets());
    }

    @Test
    @DisplayName("ajouterSommet : sommet null lève IllegalArgumentException")
    void ajouterSommet_null_leveException() {
        assertThrows(IllegalArgumentException.class, () -> graphe.ajouterSommet(null));
    }

    @Test
    @DisplayName("ajouterSommet : sommet vide lève IllegalArgumentException")
    void ajouterSommet_vide_leveException() {
        assertThrows(IllegalArgumentException.class, () -> graphe.ajouterSommet("   "));
    }


    @Test
    @DisplayName("supprimerSommet : le sommet n'est plus présent après suppression")
    void supprimerSommet_sommetExistant_plusPresent() {
        graphe.ajouterSommet("chenille.Tete");
        graphe.supprimerSommet("chenille.Tete");
        assertFalse(graphe.contientSommet("chenille.Tete"));
    }

    @Test
    @DisplayName("supprimerSommet : les arêtes incidentes sont aussi supprimées")
    void supprimerSommet_supprimeAretesIncidentes() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");

        graphe.supprimerSommet("chenille.Chenille");

        assertFalse(graphe.contientArete("chenille.Chenille", "chenille.Anneau"));
        assertFalse(graphe.contientArete("appli.AppliChenille", "chenille.Chenille"));
    }

    @Test
    @DisplayName("supprimerSommet : sans effet si le sommet est inconnu")
    void supprimerSommet_sommetInconnu_sansEffet() {
        graphe.ajouterSommet("chenille.Anneau");
        graphe.supprimerSommet("sommet.Inexistant"); 
        assertEquals(1, graphe.getNombreSommets());
    }

    @Test
    @DisplayName("getSommets : retourne tous les sommets ajoutés")
    void getSommets_retourneTousLesSommets() {
        graphe.ajouterSommet("chenille.Chenille");
        graphe.ajouterSommet("chenille.Anneau");
        graphe.ajouterSommet("chenille.Tete");

        List<String> sommets = graphe.getSommets();
        assertEquals(3, sommets.size());
        assertTrue(sommets.containsAll(List.of("chenille.Chenille", "chenille.Anneau", "chenille.Tete")));
    }

    @Test
    @DisplayName("getSommets : retourne une liste vide pour un graphe vide")
    void getSommets_grapheVide_listeVide() {
        assertTrue(graphe.getSommets().isEmpty());
    }


    @Test
    @DisplayName("ajouterArete : arête avec étiquette correctement ajoutée")
    void ajouterArete_avecEtiquette_aretePresente() {
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");
        assertTrue(graphe.contientArete("appli.AppliChenille", "chenille.Chenille"));
    }

    @Test
    @DisplayName("ajouterArete : arête sans étiquette correctement ajoutée")
    void ajouterArete_sansEtiquette_aretePresente() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        assertTrue(graphe.contientArete("chenille.Chenille", "chenille.Anneau"));
    }

    @Test
    @DisplayName("ajouterArete : le graphe crée automatiquement les sommets manquants")
    void ajouterArete_creeSommetsSiAbsents() {
        graphe.ajouterArete("chenille.Tete", "chenille.Anneau", null);
        assertTrue(graphe.contientSommet("chenille.Tete"));
        assertTrue(graphe.contientSommet("chenille.Anneau"));
    }

    @Test
    @DisplayName("ajouterArete : ajouter deux fois la même arête met à jour l'étiquette")
    void ajouterArete_doublon_miseAJourEtiquette() {
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "use");
        assertEquals("use", graphe.getEtiquette("appli.AppliChenille", "chenille.Chenille"));
        assertEquals(1, graphe.getNombreAretes());
    }

    @Test
    @DisplayName("supprimerArete : l'arête n'existe plus après suppression")
    void supprimerArete_areteExistante_plusPresente() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.supprimerArete("chenille.Chenille", "chenille.Anneau");
        assertFalse(graphe.contientArete("chenille.Chenille", "chenille.Anneau"));
    }

    @Test
    @DisplayName("supprimerArete : la suppression ne touche pas les autres arêtes")
    void supprimerArete_neSupprimeQueLAreteCiblee() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.ajouterArete("chenille.Chenille", "chenille.Tete", null);

        graphe.supprimerArete("chenille.Chenille", "chenille.Anneau");

        assertFalse(graphe.contientArete("chenille.Chenille", "chenille.Anneau"));
        assertTrue(graphe.contientArete("chenille.Chenille", "chenille.Tete"));
    }

    @Test
    @DisplayName("supprimerArete : sans effet si l'arête n'existe pas")
    void supprimerArete_areteInconnue_sansErreur() {
        graphe.ajouterSommet("chenille.Anneau");
        assertDoesNotThrow(() -> graphe.supprimerArete("chenille.Anneau", "chenille.Tete"));
    }
    
    @Test
    @DisplayName("getEtiquette : retourne la bonne étiquette")
    void getEtiquette_areteAvecEtiquette_retourneEtiquette() {
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");
        assertEquals("create", graphe.getEtiquette("appli.AppliChenille", "chenille.Chenille"));
    }

    @Test
    @DisplayName("getEtiquette : retourne null pour une arête sans étiquette")
    void getEtiquette_areteSansEtiquette_retourneNull() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Tete", null);
        assertNull(graphe.getEtiquette("chenille.Chenille", "chenille.Tete"));
    }

    @Test
    @DisplayName("getEtiquette : retourne null si l'arête n'existe pas")
    void getEtiquette_areteAbsente_retourneNull() {
        graphe.ajouterSommet("chenille.Anneau");
        assertNull(graphe.getEtiquette("chenille.Anneau", "chenille.Tete"));
    }

    @Test
    @DisplayName("getSuccesseurs : retourne les bons successeurs")
    void getSuccesseurs_sommetAvecVoisins_retourneListe() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.ajouterArete("chenille.Chenille", "chenille.Tete", null);

        List<String> succ = graphe.getSuccesseurs("chenille.Chenille");
        assertEquals(2, succ.size());
        assertTrue(succ.containsAll(List.of("chenille.Anneau", "chenille.Tete")));
    }

    @Test
    @DisplayName("getSuccesseurs : retourne liste vide pour un sommet sans successeur")
    void getSuccesseurs_aucunSuccesseur_listeVide() {
        graphe.ajouterSommet("chenille.Anneau");
        assertTrue(graphe.getSuccesseurs("chenille.Anneau").isEmpty());
    }

    @Test
    @DisplayName("getSuccesseurs : retourne liste vide pour un sommet inconnu")
    void getSuccesseurs_sommetInconnu_listeVide() {
        assertTrue(graphe.getSuccesseurs("inconnu.Classe").isEmpty());
    }

    @Test
    @DisplayName("getPredecesseurs : retourne les bons prédécesseurs")
    void getPredecesseurs_sommetAvecPredecesseurs_retourneListe() {
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.ajouterArete("chenille.Tete", "chenille.Anneau", null);

        List<String> pred = graphe.getPredecesseurs("chenille.Anneau");
        assertEquals(2, pred.size());
        assertTrue(pred.containsAll(List.of("chenille.Chenille", "chenille.Tete")));
    }

    @Test
    @DisplayName("getPredecesseurs : retourne liste vide pour un sommet sans prédécesseur")
    void getPredecesseurs_aucunPredecesseur_listeVide() {
        graphe.ajouterSommet("appli.AppliChenille");
        assertTrue(graphe.getPredecesseurs("appli.AppliChenille").isEmpty());
    }

    @Test
    @DisplayName("getPredecesseurs : le graphe est bien orienté (non symétrique)")
    void getPredecesseurs_orientationRespectee() {
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");
        assertFalse(graphe.getPredecesseurs("appli.AppliChenille")
                .contains("chenille.Chenille"));
    }
    
    @Test
    @DisplayName("getNombreSommets : compte correct sur le graphe chenille complet")
    void getNombreSommets_grapheChenille_retourneQuatre() {
        construireGrapheChenille();
        assertEquals(4, graphe.getNombreSommets());
    }

    @Test
    @DisplayName("getNombreAretes : compte correct sur le graphe chenille complet")
    void getNombreAretes_grapheChenille_retourneQuatre() {
        construireGrapheChenille();
        assertEquals(4, graphe.getNombreAretes());
    }

    private void construireGrapheChenille() {
        graphe.ajouterArete("appli.AppliChenille", "chenille.Chenille", "create");
        graphe.ajouterArete("chenille.Chenille", "chenille.Anneau", null);
        graphe.ajouterArete("chenille.Chenille", "chenille.Tete", null);
        graphe.ajouterArete("chenille.Tete", "chenille.Anneau", null);
    }
}
