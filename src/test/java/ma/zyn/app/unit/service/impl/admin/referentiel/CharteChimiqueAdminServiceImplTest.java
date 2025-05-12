package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.CharteChimique;
import ma.zyn.app.dao.facade.core.referentiel.CharteChimiqueDao;
import ma.zyn.app.service.impl.admin.referentiel.CharteChimiqueAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.referentiel.Produit ;
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
class CharteChimiqueAdminServiceImplTest {

    @Mock
    private CharteChimiqueDao repository;
    private AutoCloseable autoCloseable;
    private CharteChimiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CharteChimiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCharteChimique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCharteChimique() {
        // Given
        CharteChimique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCharteChimique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCharteChimiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example CharteChimique ID to retrieve
        CharteChimique expected = new CharteChimique(); // You need to replace CharteChimique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CharteChimique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CharteChimique constructSample(int i) {
		CharteChimique given = new CharteChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduit(new Produit(1L));
        given.setElementChimique(new ElementChimique(1L));
        given.setMinimum(BigDecimal.TEN);
        given.setMaximum(BigDecimal.TEN);
        given.setAverage(BigDecimal.TEN);
        given.setMethodeAnalyse("methodeAnalyse-"+i);
        given.setUnite("unite-"+i);
        return given;
    }

}
