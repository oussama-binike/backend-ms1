package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.dao.facade.core.referentiel.EnginDao;
import ma.zyn.app.service.impl.admin.referentiel.EnginAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.TypeEngin ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;
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
class EnginAdminServiceImplTest {

    @Mock
    private EnginDao repository;
    private AutoCloseable autoCloseable;
    private EnginAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EnginAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEngin() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEngin() {
        // Given
        Engin toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEngin() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEnginById() {
        // Given
        Long idToRetrieve = 1L; // Example Engin ID to retrieve
        Engin expected = new Engin(); // You need to replace Engin with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Engin result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Engin constructSample(int i) {
		Engin given = new Engin();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setTypeEngin(new TypeEngin(1L));
        given.setOperationStadeOperatoire(new OperationStadeOperatoire(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setCapacite(BigDecimal.TEN);
        given.setRendement(BigDecimal.TEN);
        return given;
    }

}
