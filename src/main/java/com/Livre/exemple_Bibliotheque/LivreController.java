package com.Livre.exemple_Bibliotheque;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livre/api")
public class LivreController {
    private List<Livre> livres = new ArrayList<>();

    // --- Initialisation de quelques livres, dont "Vol de nuit" ---
    public LivreController() {
        livres.add(new Livre(1L, "Une si longue lettre", "Mariama Bâ", "978-2-07-037963-7", true));
        livres.add(new Livre(2L, "Sous l'orage", "Seydou Badian", "978-2-02-006576-8", true));
        livres.add(new Livre(3L, "Le Vieux Nègre et la médaille", "Ferdinand Oyono", "978-2-07-036256-1", false));
        livres.add(new Livre(4L, "Vol de nuit", "Antoine de Saint-Exupéry", "978-2-07-020347-5", true));
        livres.add(new Livre(5L, "La Peste", "Albert Camus", "978-2-07-036084-0", true));
        livres.add(new Livre(6L, "Phedre", "De Jean Racine", "978-2-07-036089-0", true));
        livres.add(new Livre(7L, "Une Vie De Boy", "Ferdinant Oyono", "978-2-07-036084-7", true));
        livres.add(new Livre(8L, "Les mots", "Jean-Paul Sartre", "978-2-07-0360100-9", true));
        livres.add(new Livre(9L, "Récits, contes et nouvelles", " Pierre de Marivaux", "978-2-07-036102-10", true));
        livres.add(new Livre(10L, "Correspondance, tome 2 : 1860-1866", "Charles Baudelaire", "978-2-07-0360120-11", true));




    }

    // --- Récupérer tous les livres ---
    @GetMapping
    public List<Livre> getAllLivres() {
        return livres;
    }

    // --- Récupérer un livre par ID ---
    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Long id) {
        return livres.stream()
                .filter(livre -> livre.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID : " + id));
    }

    // --- Récupérer les livres disponibles ---
    @GetMapping("/disponibles")
    public List<Livre> getLivresDisponibles() {
        return livres.stream()
                .filter(Livre::getDisponible)
                .collect(Collectors.toList());
    }

    // --- Rechercher un livre par titre (exemple incluant "Vol de nuit") ---
    @GetMapping("/recherche")
    public List<Livre> rechercherLivreParTitre(@RequestParam String titre) {
        String titreEnMinuscules = titre.toLowerCase();
        return livres.stream()
                .filter(livre -> livre.getTitre().toLowerCase().contains(titreEnMinuscules))
                .collect(Collectors.toList());
    }

    // --- Endpoint spécifique pour récupérer le livre "Vol de nuit" (exemple) ---
    @GetMapping("/vol-de-nuit")
    public Livre getVolDeNuit() {
        return livres.stream()
                .filter(livre -> livre.getTitre().equalsIgnoreCase("Vol de nuit"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Le livre 'Vol de nuit' n'a pas été trouvé."));
    }
    @PostMapping
    public Livre addLivre(@RequestBody Livre livre) {
        livre.setId((long) (livres.size() + 1)); // Assigner un ID unique
        livres.add(livre);
        return livre;
    }
    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livreDetails) {
        Livre livre = getLivreById(id);
        livre.setTitre(livreDetails.getTitre());
        livre.setAuteur(livreDetails.getAuteur());
        livre.setIsbn(livreDetails.getIsbn());
        livre.setDisponible(livreDetails.getDisponible());
        return livre;
    }
    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        Livre livre = getLivreById(id);
        livres.remove(livre);
    }
    // --- Endpoint pour ajouter un livre ---




}
