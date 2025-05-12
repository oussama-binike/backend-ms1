package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.OperationStadeOperatoireDao;
import ma.zyn.app.service.impl.admin.referentiel.OperationStadeOperatoireAdminServiceImpl;

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
class OperationStadeOperatoireAdminServiceImplTest {

    @Mock
    private OperationStadeOperatoireDao repository;
    private AutoCloseable autoCloseable;
    private OperationStadeOperatoireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new OperationStadeOperatoireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllOperationStadeOperatoire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveOperationStadeOperatoire() {
        // Given
        OperationStadeOperatoire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteOperationStadeOperatoire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetOperationStadeOperatoireById() {
        // Given
        Long idToRetrieve = 1L; // Example OperationStadeOperatoire ID to retrieve
        OperationStadeOperatoire expected = new OperationStadeOperatoire(); // You need to replace OperationStadeOperatoire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        OperationStadeOperatoire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private OperationStadeOperatoire constructSample(int i) {
		OperationStadeOperatoire given = new OperationStadeOperatoire();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
