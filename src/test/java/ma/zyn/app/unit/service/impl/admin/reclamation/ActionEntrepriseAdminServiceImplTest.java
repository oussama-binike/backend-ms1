package ma.zyn.app.unit.service.impl.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.dao.facade.core.reclamation.ActionEntrepriseDao;
import ma.zyn.app.service.impl.admin.reclamation.ActionEntrepriseAdminServiceImpl;

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
class ActionEntrepriseAdminServiceImplTest {

    @Mock
    private ActionEntrepriseDao repository;
    private AutoCloseable autoCloseable;
    private ActionEntrepriseAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ActionEntrepriseAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllActionEntreprise() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveActionEntreprise() {
        // Given
        ActionEntreprise toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteActionEntreprise() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetActionEntrepriseById() {
        // Given
        Long idToRetrieve = 1L; // Example ActionEntreprise ID to retrieve
        ActionEntreprise expected = new ActionEntreprise(); // You need to replace ActionEntreprise with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ActionEntreprise result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ActionEntreprise constructSample(int i) {
		ActionEntreprise given = new ActionEntreprise();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
