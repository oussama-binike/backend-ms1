package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.dao.facade.core.referentiel.ConsommableDao;
import ma.zyn.app.service.impl.admin.referentiel.ConsommableAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.bean.core.referentiel.Consommable ;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire ;
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
class ConsommableAdminServiceImplTest {

    @Mock
    private ConsommableDao repository;
    private AutoCloseable autoCloseable;
    private ConsommableAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ConsommableAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllConsommable() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveConsommable() {
        // Given
        Consommable toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteConsommable() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetConsommableById() {
        // Given
        Long idToRetrieve = 1L; // Example Consommable ID to retrieve
        Consommable expected = new Consommable(); // You need to replace Consommable with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Consommable result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Consommable constructSample(int i) {
		Consommable given = new Consommable();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStyle("style-"+i);
        given.setUnite(new Unite(1L));
        List<ConsommableStadeOperatoire> consommableStadeOperatoires = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ConsommableStadeOperatoire element = new ConsommableStadeOperatoire();
                                                element.setId((long)id);
                                                element.setStadeOperatoire(new StadeOperatoire(Long.valueOf(1)));
                                                element.setConsommable(new Consommable(Long.valueOf(2)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setConsommableStadeOperatoires(consommableStadeOperatoires);
        return given;
    }

}
