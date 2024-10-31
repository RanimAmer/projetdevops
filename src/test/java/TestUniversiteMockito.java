

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUniversiteMockito {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Test
    @Order(0)
    public void testAddUniversite() {
        Universite universiteToAdd = new Universite(1L, "Esrit", "Ariana soghra", null);
        when(universiteRepository.save(universiteToAdd)).thenReturn(universiteToAdd);

        Universite result = universiteService.addUniversite(universiteToAdd);

        assertEquals(universiteToAdd, result);
        verify(universiteRepository, times(1)).save(universiteToAdd);
    }

    @Test
    @Order(1)
    public void testUpdateUniversite() {
        Universite universiteToUpdate = new Universite(1L, "Esprit", "Ghazela", null);
        when(universiteRepository.save(universiteToUpdate)).thenReturn(universiteToUpdate);

        Universite result = universiteService.modifyUniversite(universiteToUpdate);

        assertEquals(universiteToUpdate, result);
        verify(universiteRepository, times(1)).save(universiteToUpdate);
    }

    @Test
    @Order(2)
    public void testRetrieveUniversite() {
        long id = 1L;
        Universite universite = new Universite(id, "Esprit", "Ariana soghra", null);
        when(universiteRepository.findById(id)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(id);

        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(id);
    }

    @Test
    @Order(3)
    public void testDeleteUniversite() {
        long id = 1L;

        universiteService.removeUniversite(id);

        verify(universiteRepository, times(1)).deleteById(id);
    }

    @Test
    @Order(4)
    public void testRetrieveAllUniversites() {
        List<Universite> universites = Arrays.asList(
                new Universite(1L, "Esprit", "Ariana soghra", null),
                new Universite(2L, "Tekup", "Ariana", null)
        );
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(universites, result);
        verify(universiteRepository, times(1)).findAll();
    }
}
