package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMockito {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Test
    @Order(0)
    public void testAddEtudiant() {
        Etudiant etudiantToAdd = new Etudiant(1, "ghofrane", "fajraoui", 123587, "05/02/2000");
        when(etudiantRepository.save(etudiantToAdd)).thenReturn(etudiantToAdd);

        Etudiant result = etudiantService.addEtudiant(etudiantToAdd);

        assertEquals(etudiantToAdd, result);
        verify(etudiantRepository, times(1)).save(etudiantToAdd);
    }

    @Test
    @Order(1)
    public void testUpdateEtudiant() {
        Etudiant etudiantToUpdate = new Etudiant(1, "ghofrane", "fajraoui", 123587, "05/02/2000");
        when(etudiantRepository.save(etudiantToUpdate)).thenReturn(etudiantToUpdate);

        Etudiant result = etudiantService.modifyEtudiant(etudiantToUpdate);

        assertEquals(etudiantToUpdate, result);
        verify(etudiantRepository, times(1)).save(etudiantToUpdate);
    }

    @Test
    @Order(2)
    public void testRetrieveEtudiant() {
        long id = 1L;
        Etudiant etudiant = new Etudiant(id, "ghofrane", "fajraoui", 123587, "05/02/2000");
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(id);

        assertEquals(etudiant, result);
        verify(etudiantRepository, times(1)).findById(id);
    }
    @Test
    @Order(3)
    public void testDeleteEtudiant() {
        long id = 1L; // The ID of the Etudiant to delete

        // Call the method to be tested
        etudiantService.removeEtudiant(id);

        // Verify that deleteById is called with the correct id
        verify(etudiantRepository, times(1)).deleteById(id);
    }

    @Test
    @Order(4)
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = Arrays.asList(
                new Etudiant(1, "ghofrane", "fajraoui", 123587, "05/02/2000"),
                new Etudiant(2, "ahmed", "ahmed", 589587, "10/03/2010")
        );
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    @Order(5)
    public void testRecupererEtudiantParCin() {
        long cin = 123587; // Numéro CIN à tester
        Etudiant etudiant = new Etudiant(1, "ghofrane", "fajraoui", cin, "05/02/2000"); // Création d'un étudiant avec le CIN donné
        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(etudiant); // Simulation du comportement du repository

        // Appel de la méthode à tester
        Etudiant result = etudiantService.recupererEtudiantParCin(cin);

        // Vérification du résultat
        assertEquals(etudiant, result); // Vérifie que l'étudiant récupéré est le même que celui attendu
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(cin); // Verify that this method was called
    }
}
