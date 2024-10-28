package tn.esprit.tpfoyer.TestReservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());

        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.retrieveAllReservations();

        assertEquals(2, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        Reservation reservation = new Reservation();
        reservation.setIdReservation("1");

        when(reservationRepository.findById("1")).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.retrieveReservation("1");

        assertNotNull(result);
        assertEquals("1", result.getIdReservation());
        verify(reservationRepository, times(1)).findById("1");
    }

    @Test
    void testAddReservation() {
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.addReservation(reservation);

        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.modifyReservation(reservation);

        assertNotNull(result);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testFindReservationsByDateAndStatus() {
        Date date = new Date();
        boolean status = true;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());

        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status)).thenReturn(reservations);

        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, status);

        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }

    @Test
    void testRemoveReservation() {
        String reservationId = "1";

        doNothing().when(reservationRepository).deleteById(reservationId);

        reservationService.removeReservation(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);
    }
}

