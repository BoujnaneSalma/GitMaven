package com.Maven.Git;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProduitServiceTest {
	private ProduitService produitService;

    @BeforeEach
    public void setUp() {
        produitService = new ProduitService();
    }

    @Test
    public void testAjouterProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);
        produitService.ajouterProduit(produit);
        assertEquals(produit, produitService.obtenirProduitParId(1L));
    }

    @Test
    public void testAjouterProduitExistence() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);
        produitService.ajouterProduit(produit);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.ajouterProduit(new Produit(1L, "ProduitTest2", 15.0, 3)));
        assertEquals("Le produit existe déjà.", exception.getMessage());
    }

    @Test
    public void testObtenirProduitParId() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);
        produitService.ajouterProduit(produit);
        assertEquals(produit, produitService.obtenirProduitParId(1L));
    }

    @Test
    public void testMettreAJourProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);
        produitService.ajouterProduit(produit);

        produit.setPrix(15.0);
        produitService.modifierProduit(produit);
        assertEquals(15.0, produitService.obtenirProduitParId(1L).getPrix());
    }

    @Test
    public void testMettreAJourProduitInexistant() {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.modifierProduit(produit));
        assertEquals("Le produit avec l'ID 1 n'existe pas.", exception.getMessage());
    }

    @Test
    public void testSupprimerProduit() throws Exception {
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);
        produitService.ajouterProduit(produit);

        produitService.supprimerProduit(1L);
        assertThrows(Exception.class, () ->
                produitService.obtenirProduitParId(1L));
    }

    @Test
    public void testSupprimerProduitInexistant() {
        Exception exception = assertThrows(Exception.class, () ->
                produitService.supprimerProduit(1L));
        assertEquals("Le produit avec l'ID 1 n'existe pas.", exception.getMessage());
    }

    @Test
    public void testValiderProduitPrixNegatif() {
        Produit produit = new Produit(1L, "ProduitTest", -10.0, 5);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.ajouterProduit(produit));
        assertEquals("Le prix et la quantité du produit doivent être positifs.", exception.getMessage());
    }

    @Test
    public void testValiderProduitQuantiteNegative() {
        Produit produit = new Produit(1, "ProduitTest", 10.0, -5);

        Exception exception = assertThrows(Exception.class, () ->
                produitService.ajouterProduit(produit));
        assertEquals("Le prix et la quantité du produit doivent être positifs.", exception.getMessage());
    }
}
