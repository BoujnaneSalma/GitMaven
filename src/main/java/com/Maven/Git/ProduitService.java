package com.Maven.Git;

import java.util.HashMap;
import java.util.Map;

public class ProduitService {
	private Map<Long, Produit> produits;

    public ProduitService() {
        this.produits = new HashMap<>();
    }
// Ajouter un produit
    public void ajouterProduit(Produit produit) throws Exception {
        validerProduit(produit);
        verifierProduitUnique(produit);
        produits.put(produit.getId(), produit);
    }

    
    // Valider les données du produit
    private void validerProduit(Produit produit) throws Exception {
        if (produit.getPrix() <= 0 || produit.getQuantite() <= 0) {
            throw new Exception("Le prix et la quantité du produit doivent être positifs.");
        }
    }

     // Vérifier l'unicité du produit par son ID ou nom
    private void verifierProduitUnique(Produit produit) throws Exception {
        if (produits.containsKey(produit.getId()) || produitExisteParNom(produit.getNom())) {
            throw new Exception("Le produit existe déjà.");
        }
    }
}
