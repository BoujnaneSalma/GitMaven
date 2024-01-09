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

    // Obtenir un produit par son ID
    public Produit obtenirProduitParId(Long id) throws Exception {
        verifierProduitExistenceParId(id);
        return produits.get(id);
    }

    // Obtenir un produit par son nom
    public Produit obtenirProduitParNom(String nom) throws Exception {
        verifierProduitExistenceParNom(nom);
        return produits.values().stream()
                .filter(produit -> produit.getNom().equals(nom))
                .findFirst()
                .orElse(null);
    }

        // Vérifier l'existence d'un produit par son ID
    private void verifierProduitExistenceParId(Long id) throws Exception {
        if (!produits.containsKey(id)) {
            throw new Exception("Le produit avec l'ID " + id + " n'existe pas.");
        }
    }

    // Vérifier l'existence d'un produit par son nom
    private void verifierProduitExistenceParNom(String nom) throws Exception {
        if (!produitExisteParNom(nom)) {
            throw new Exception("Aucun produit avec le nom " + nom + " n'a été trouvé.");
        }
    }

    // Vérifier l'existence d'un produit par son nom
    private boolean produitExisteParNom(String nom) {
        return produits.values().stream().anyMatch(produit -> produit.getNom().equals(nom));
    }




}
