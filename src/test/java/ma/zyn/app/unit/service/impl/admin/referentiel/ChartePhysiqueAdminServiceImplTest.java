package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.dao.facade.core.referentiel.ChartePhysiqueDao;
import ma.zyn.app.service.impl.admin.referentiel.ChartePhysiqueAdminServiceImpl;

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
class ChartePhysiqueAdminServiceImplTest {

    @Mock
    private ChartePhysiqueDao repository;
    private AutoCloseable autoCloseable;
    private ChartePhysiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ChartePhysiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllChartePhysique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveChartePhysique() {
        // Given
        ChartePhysique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteChartePhysique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetChartePhysiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example ChartePhysique ID to retrieve
        ChartePhysique expected = new ChartePhysique(); // You need to replace ChartePhysique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ChartePhysique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ChartePhysique constructSample(int i) {
		ChartePhysique given = new ChartePhysique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduit(new Produit(1L));
        given.setMinimumSize(BigDecimal.TEN);
        given.setMaximumSize(BigDecimal.TEN);
        given.setValeur(BigDecimal.TEN);
        return given;
    }

}
