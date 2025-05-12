package ma.zyn.app.unit.service.impl.admin.navire;

import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireQualiteDao;
import ma.zyn.app.service.impl.admin.navire.RealisationNavireQualiteAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.navire.RealisationNavire ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class RealisationNavireQualiteAdminServiceImplTest {

    @Mock
    private RealisationNavireQualiteDao repository;
    private AutoCloseable autoCloseable;
    private RealisationNavireQualiteAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationNavireQualiteAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationNavireQualite() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationNavireQualite() {
        // Given
        RealisationNavireQualite toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationNavireQualite() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationNavireQualiteById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationNavireQualite ID to retrieve
        RealisationNavireQualite expected = new RealisationNavireQualite(); // You need to replace RealisationNavireQualite with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationNavireQualite result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationNavireQualite constructSample(int i) {
		RealisationNavireQualite given = new RealisationNavireQualite();
        given.setProduitMarchand(new ProduitMarchand(1L));
        given.setTsm(BigDecimal.TEN);
        given.setVolume(BigDecimal.TEN);
        given.setRealisationNavire(new RealisationNavire(1L));
        return given;
    }

}
