package ma.zyn.app.unit.dao.facade.core.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.dao.facade.core.planmaintenance.PlanDisponibiliteDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PlanDisponibiliteDaoTest {

@Autowired
    private PlanDisponibiliteDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        PlanDisponibilite entity = new PlanDisponibilite();
        entity.setCode(code);
        underTest.save(entity);
        PlanDisponibilite loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        PlanDisponibilite loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        PlanDisponibilite entity = new PlanDisponibilite();
        entity.setId(id);
        underTest.save(entity);
        PlanDisponibilite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        PlanDisponibilite entity = new PlanDisponibilite();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        PlanDisponibilite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<PlanDisponibilite> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<PlanDisponibilite> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        PlanDisponibilite given = constructSample(1);
        PlanDisponibilite saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private PlanDisponibilite constructSample(int i) {
		PlanDisponibilite given = new PlanDisponibilite();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setNombreHeureArret(BigDecimal.TEN);
        given.setDateArretDebut(LocalDateTime.now());
        given.setDateArretFin(LocalDateTime.now());
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
