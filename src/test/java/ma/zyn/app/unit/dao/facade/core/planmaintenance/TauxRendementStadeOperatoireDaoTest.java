package ma.zyn.app.unit.dao.facade.core.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.dao.facade.core.planmaintenance.TauxRendementStadeOperatoireDao;

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

import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TauxRendementStadeOperatoireDaoTest {

@Autowired
    private TauxRendementStadeOperatoireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        TauxRendementStadeOperatoire entity = new TauxRendementStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);
        TauxRendementStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        TauxRendementStadeOperatoire entity = new TauxRendementStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        TauxRendementStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<TauxRendementStadeOperatoire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<TauxRendementStadeOperatoire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        TauxRendementStadeOperatoire given = constructSample(1);
        TauxRendementStadeOperatoire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private TauxRendementStadeOperatoire constructSample(int i) {
		TauxRendementStadeOperatoire given = new TauxRendementStadeOperatoire();
        given.setJour(LocalDateTime.now());
        given.setTauxRendementGlobal(BigDecimal.TEN);
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
