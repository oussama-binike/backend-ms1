package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.CoutStade;
import ma.zyn.app.dao.facade.core.referentiel.CoutStadeDao;
import ma.zyn.app.service.impl.admin.referentiel.CoutStadeAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
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
class CoutStadeAdminServiceImplTest {

    @Mock
    private CoutStadeDao repository;
    private AutoCloseable autoCloseable;
    private CoutStadeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CoutStadeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCoutStade() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCoutStade() {
        // Given
        CoutStade toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCoutStade() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCoutStadeById() {
        // Given
        Long idToRetrieve = 1L; // Example CoutStade ID to retrieve
        CoutStade expected = new CoutStade(); // You need to replace CoutStade with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CoutStade result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CoutStade constructSample(int i) {
		CoutStade given = new CoutStade();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setCoutUnitaire(BigDecimal.TEN);
        given.setUnite(new Unite(1L));
        return given;
    }

}
