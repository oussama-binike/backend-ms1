package ma.zyn.app.unit.service.impl.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.dao.facade.core.reclamation.ReclamationDao;
import ma.zyn.app.service.impl.admin.reclamation.ReclamationAdminServiceImpl;

import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique ;
import ma.zyn.app.bean.core.reclamation.Reclamation ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.reclamation.EtatReclamation ;
import ma.zyn.app.bean.core.referentiel.Entite ;
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
class ReclamationAdminServiceImplTest {

    @Mock
    private ReclamationDao repository;
    private AutoCloseable autoCloseable;
    private ReclamationAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ReclamationAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllReclamation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveReclamation() {
        // Given
        Reclamation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteReclamation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetReclamationById() {
        // Given
        Long idToRetrieve = 1L; // Example Reclamation ID to retrieve
        Reclamation expected = new Reclamation(); // You need to replace Reclamation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Reclamation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Reclamation constructSample(int i) {
		Reclamation given = new Reclamation();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setEntiteEmettrice(new Entite(1L));
        given.setEntiteDistinataire(new Entite(1L));
        given.setProduitMarchand(new ProduitMarchand(1L));
        given.setQuantite(BigDecimal.TEN);
        given.setFonde(false);
        given.setMotifReclamation("motifReclamation-"+i);
        given.setEtatReclamation(new EtatReclamation(1L));
        given.setDateOccurence(LocalDateTime.now());
        given.setDateReception(LocalDateTime.now());
        given.setActionEntreprise("actionEntreprise-"+i);
        List<ReclamationElementChimique> reclamationElementChimiques = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ReclamationElementChimique element = new ReclamationElementChimique();
                                                element.setId((long)id);
                                                element.setReclamation(new Reclamation(Long.valueOf(1)));
                                                element.setElementChimique(new ElementChimique(Long.valueOf(2)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setReclamationElementChimiques(reclamationElementChimiques);
        return given;
    }

}
