package ma.zyn.app.unit.dao.facade.core.scenario;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.dao.facade.core.scenario.ScenarioFluxDao;

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

import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux ;
import ma.zyn.app.bean.core.scenario.Exercice ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ScenarioFluxDaoTest {

@Autowired
    private ScenarioFluxDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ScenarioFlux entity = new ScenarioFlux();
        entity.setCode(code);
        underTest.save(entity);
        ScenarioFlux loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ScenarioFlux loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ScenarioFlux entity = new ScenarioFlux();
        entity.setId(id);
        underTest.save(entity);
        ScenarioFlux loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ScenarioFlux entity = new ScenarioFlux();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ScenarioFlux loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ScenarioFlux> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ScenarioFlux> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ScenarioFlux given = constructSample(1);
        ScenarioFlux saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ScenarioFlux constructSample(int i) {
		ScenarioFlux given = new ScenarioFlux();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setExercice(new Exercice(1L));
        given.setDateEffet(LocalDateTime.now());
        given.setStatusScenarioFlux(new StatusScenarioFlux(1L));
        return given;
    }

}
