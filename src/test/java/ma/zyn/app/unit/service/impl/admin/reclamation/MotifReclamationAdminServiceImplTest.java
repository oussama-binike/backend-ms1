package ma.zyn.app.unit.service.impl.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import ma.zyn.app.dao.facade.core.reclamation.MotifReclamationDao;
import ma.zyn.app.service.impl.admin.reclamation.MotifReclamationAdminServiceImpl;

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
class MotifReclamationAdminServiceImplTest {

    @Mock
    private MotifReclamationDao repository;
    private AutoCloseable autoCloseable;
    private MotifReclamationAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MotifReclamationAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMotifReclamation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMotifReclamation() {
        // Given
        MotifReclamation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMotifReclamation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMotifReclamationById() {
        // Given
        Long idToRetrieve = 1L; // Example MotifReclamation ID to retrieve
        MotifReclamation expected = new MotifReclamation(); // You need to replace MotifReclamation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        MotifReclamation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private MotifReclamation constructSample(int i) {
		MotifReclamation given = new MotifReclamation();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
