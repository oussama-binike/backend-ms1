package ma.zyn.app.unit.service.impl.admin.navire;

import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.dao.facade.core.navire.DestinationNavireDao;
import ma.zyn.app.service.impl.admin.navire.DestinationNavireAdminServiceImpl;

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
class DestinationNavireAdminServiceImplTest {

    @Mock
    private DestinationNavireDao repository;
    private AutoCloseable autoCloseable;
    private DestinationNavireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DestinationNavireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDestinationNavire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDestinationNavire() {
        // Given
        DestinationNavire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDestinationNavire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDestinationNavireById() {
        // Given
        Long idToRetrieve = 1L; // Example DestinationNavire ID to retrieve
        DestinationNavire expected = new DestinationNavire(); // You need to replace DestinationNavire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        DestinationNavire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DestinationNavire constructSample(int i) {
		DestinationNavire given = new DestinationNavire();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
