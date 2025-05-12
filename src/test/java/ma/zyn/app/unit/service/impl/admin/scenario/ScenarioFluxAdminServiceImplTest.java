package ma.zyn.app.unit.service.impl.admin.scenario;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.dao.facade.core.scenario.ScenarioFluxDao;
import ma.zyn.app.service.impl.admin.scenario.ScenarioFluxAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Liaison ;
import ma.zyn.app.bean.core.referentiel.EtatDemande ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.referentiel.TypeDemande ;
import ma.zyn.app.bean.core.scenario.Exercice ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux ;
import ma.zyn.app.bean.core.demande.Demande ;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite ;
import ma.zyn.app.bean.core.referentiel.Client ;
import ma.zyn.app.bean.core.stock.SuiviStock ;
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
class ScenarioFluxAdminServiceImplTest {

    @Mock
    private ScenarioFluxDao repository;
    private AutoCloseable autoCloseable;
    private ScenarioFluxAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ScenarioFluxAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllScenarioFlux() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveScenarioFlux() {
        // Given
        ScenarioFlux toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteScenarioFlux() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetScenarioFluxById() {
        // Given
        Long idToRetrieve = 1L; // Example ScenarioFlux ID to retrieve
        ScenarioFlux expected = new ScenarioFlux(); // You need to replace ScenarioFlux with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ScenarioFlux result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ScenarioFlux constructSample(int i) {
		ScenarioFlux given = new ScenarioFlux();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setExercice(new Exercice(1L));
        given.setDateEffet(LocalDateTime.now());
        given.setStatusScenarioFlux(new StatusScenarioFlux(1L));
        List<Demande> demandes = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Demande element = new Demande();
                                                element.setId((long)id);
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setProduitMarchand(new ProduitMarchand(Long.valueOf(4)));
                                                element.setClient(new Client(Long.valueOf(5)));
                                                element.setDateDemande(LocalDateTime.now());
                                                element.setDateExpedition(LocalDateTime.now());
                                                element.setVolume(new BigDecimal(8*10));
                                                element.setTypeDemande(new TypeDemande(Long.valueOf(9)));
                                                element.setEtatDemande(new EtatDemande(Long.valueOf(10)));
                                                element.setScenarioFlux(new ScenarioFlux(Long.valueOf(11)));
                                                element.setExercice(new Exercice(Long.valueOf(12)));
                                                element.setActionEntreprise("actionEntreprise"+id);
                                                element.setTrg("trg"+id);
                                                element.setCause("cause"+id);
                                                element.setCommentaire("commentaire"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setDemandes(demandes);
        List<PlanDisponibilite> planDisponibilites = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                PlanDisponibilite element = new PlanDisponibilite();
                                                element.setId((long)id);
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setStadeOperatoire(new StadeOperatoire(Long.valueOf(4)));
                                                element.setNombreHeureArret(new BigDecimal(5*10));
                                                element.setDateArretDebut(LocalDateTime.now());
                                                element.setDateArretFin(LocalDateTime.now());
                                                element.setScenarioFlux(new ScenarioFlux(Long.valueOf(8)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setPlanDisponibilites(planDisponibilites);
        List<TauxRendementStadeOperatoire> tauxRendementStadeOperatoires = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                TauxRendementStadeOperatoire element = new TauxRendementStadeOperatoire();
                                                element.setId((long)id);
                                                element.setJour(LocalDateTime.now());
                                                element.setTauxRendementGlobal(new BigDecimal(2*10));
                                                element.setScenarioFlux(new ScenarioFlux(Long.valueOf(3)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setTauxRendementStadeOperatoires(tauxRendementStadeOperatoires);
        List<SuiviStock> suiviStocks = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                SuiviStock element = new SuiviStock();
                                                element.setId((long)id);
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setLiaison(new Liaison(Long.valueOf(4)));
                                                element.setVolumeReel(new BigDecimal(5*10));
                                                element.setVolumeEstime(new BigDecimal(6*10));
                                                element.setDateFlux(LocalDateTime.now());
                                                element.setRepereSourceDebut(8);
                                                element.setRepereSourceFin(9);
                                                element.setRepereDestinationDebut(10);
                                                element.setRepereDestinationFin(11);
                                                element.setPositionRouePelle(12);
                                                element.setPositionStacker(13);
                                                element.setScenarioFlux(new ScenarioFlux(Long.valueOf(14)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setSuiviStocks(suiviStocks);
        return given;
    }

}
