package ma.zyn.app.unit.service.impl.admin.camion;

import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.dao.facade.core.camion.ProvennanceCamionDao;
import ma.zyn.app.service.impl.admin.camion.ProvennanceCamionAdminServiceImpl;

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
class ProvennanceCamionAdminServiceImplTest {

    @Mock
    private ProvennanceCamionDao repository;
    private AutoCloseable autoCloseable;
    private ProvennanceCamionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProvennanceCamionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProvennanceCamion() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProvennanceCamion() {
        // Given
        ProvennanceCamion toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProvennanceCamion() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProvennanceCamionById() {
        // Given
        Long idToRetrieve = 1L; // Example ProvennanceCamion ID to retrieve
        ProvennanceCamion expected = new ProvennanceCamion(); // You need to replace ProvennanceCamion with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProvennanceCamion result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProvennanceCamion constructSample(int i) {
		ProvennanceCamion given = new ProvennanceCamion();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
