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
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBlocService {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @Test
    @Order(0)
    public void testAddBloc() {
        Bloc blocToAdd = new Bloc(1L, "Bloc A", 50L, null, null);
        when(blocRepository.save(blocToAdd)).thenReturn(blocToAdd);

        Bloc result = blocService.addBloc(blocToAdd);

        assertEquals(blocToAdd, result);
        verify(blocRepository, times(1)).save(blocToAdd);
    }

    @Test
    @Order(1)
    public void testUpdateBloc() {
        Bloc blocToUpdate = new Bloc(1L, "Bloc A", 60L, null, null);
        when(blocRepository.save(blocToUpdate)).thenReturn(blocToUpdate);

        Bloc result = blocService.modifyBloc(blocToUpdate);

        assertEquals(blocToUpdate, result);
        verify(blocRepository, times(1)).save(blocToUpdate);
    }

    @Test
    @Order(2)
    public void testRetrieveBloc() {
        long id = 1L;
        Bloc bloc = new Bloc(id, "Bloc A", 50L, null, null);
        when(blocRepository.findById(id)).thenReturn(Optional.of(bloc));

        Bloc result = blocService.retrieveBloc(id);

        assertEquals(bloc, result);
        verify(blocRepository, times(1)).findById(id);
    }

    @Test
    @Order(3)
    public void testDeleteBloc() {
        long id = 1L;
        blocService.removeBloc(id);

        verify(blocRepository, times(1)).deleteById(id);
    }

    @Test
    @Order(4)
    public void testRetrieveAllBlocs() {
        List<Bloc> blocs = Arrays.asList(
                new Bloc(1L, "Bloc A", 50L, null, null),
                new Bloc(2L, "Bloc B", 75L, null, null)
        );
        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveAllBlocs();

        assertEquals(blocs, result);
        verify(blocRepository, times(1)).findAll();
    }
}
