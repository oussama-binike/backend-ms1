package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.PointControle;
import ma.zyn.app.dao.facade.core.referentiel.PointControleDao;
import ma.zyn.app.service.impl.admin.referentiel.PointControleAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
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
class PointControleAdminServiceImplTest {

    @Mock
    private PointControleDao repository;
    private AutoCloseable autoCloseable;
    private PointControleAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PointControleAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPointControle() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePointControle() {
        // Given
        PointControle toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePointControle() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPointControleById() {
        // Given
        Long idToRetrieve = 1L; // Example PointControle ID to retrieve
        PointControle expected = new PointControle(); // You need to replace PointControle with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        PointControle result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private PointControle constructSample(int i) {
		PointControle given = new PointControle();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setIndice(i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        return given;
    }

}
