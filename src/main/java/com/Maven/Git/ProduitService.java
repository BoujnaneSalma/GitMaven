package com.Maven.Git;

import java.util.HashMap;
import java.util.Map;

public class ProduitService {
	private Map<Long, Produit> produits;

    public ProduitService() {
        this.produits = new HashMap<>();
    }
    // Create
    public void ajouterProduit(Produit produit) throws Exception {
        validerProduit(produit);
        if (produits.containsKey(produit.getId()) || produitExisteParNom(produit.getNom())) {
            throw new Exception("Le produit existe déjà.");
        }
        produits.put(produit.getId(), produit);
    }
}
