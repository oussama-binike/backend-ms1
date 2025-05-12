package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.CoutEngin;
import ma.zyn.app.dao.facade.core.referentiel.CoutEnginDao;
import ma.zyn.app.service.impl.admin.referentiel.CoutEnginAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.bean.core.referentiel.Engin ;
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
class CoutEnginAdminServiceImplTest {

    @Mock
    private CoutEnginDao repository;
    private AutoCloseable autoCloseable;
    private CoutEnginAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CoutEnginAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCoutEngin() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCoutEngin() {
        // Given
        CoutEngin toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCoutEngin() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCoutEnginById() {
        // Given
        Long idToRetrieve = 1L; // Example CoutEngin ID to retrieve
        CoutEngin expected = new CoutEngin(); // You need to replace CoutEngin with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CoutEngin result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CoutEngin constructSample(int i) {
		CoutEngin given = new CoutEngin();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setEngin(new Engin(1L));
        given.setCoutUnitaire(BigDecimal.TEN);
        given.setUnite(new Unite(1L));
        return given;
    }

}
