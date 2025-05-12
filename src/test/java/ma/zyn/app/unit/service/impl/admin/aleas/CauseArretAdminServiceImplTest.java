package ma.zyn.app.unit.service.impl.admin.aleas;

import ma.zyn.app.bean.core.aleas.CauseArret;
import ma.zyn.app.dao.facade.core.aleas.CauseArretDao;
import ma.zyn.app.service.impl.admin.aleas.CauseArretAdminServiceImpl;

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
class CauseArretAdminServiceImplTest {

    @Mock
    private CauseArretDao repository;
    private AutoCloseable autoCloseable;
    private CauseArretAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CauseArretAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCauseArret() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCauseArret() {
        // Given
        CauseArret toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCauseArret() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCauseArretById() {
        // Given
        Long idToRetrieve = 1L; // Example CauseArret ID to retrieve
        CauseArret expected = new CauseArret(); // You need to replace CauseArret with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CauseArret result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CauseArret constructSample(int i) {
		CauseArret given = new CauseArret();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
