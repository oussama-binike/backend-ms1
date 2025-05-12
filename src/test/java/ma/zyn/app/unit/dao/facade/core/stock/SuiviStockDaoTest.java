package ma.zyn.app.unit.dao.facade.core.stock;

import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.dao.facade.core.stock.SuiviStockDao;

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

import ma.zyn.app.bean.core.referentiel.Liaison ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SuiviStockDaoTest {

@Autowired
    private SuiviStockDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        SuiviStock entity = new SuiviStock();
        entity.setCode(code);
        underTest.save(entity);
        SuiviStock loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        SuiviStock loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        SuiviStock entity = new SuiviStock();
        entity.setId(id);
        underTest.save(entity);
        SuiviStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SuiviStock entity = new SuiviStock();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SuiviStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SuiviStock> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SuiviStock> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SuiviStock given = constructSample(1);
        SuiviStock saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private SuiviStock constructSample(int i) {
		SuiviStock given = new SuiviStock();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setLiaison(new Liaison(1L));
        given.setVolumeReel(BigDecimal.TEN);
        given.setVolumeEstime(BigDecimal.TEN);
        given.setDateFlux(LocalDateTime.now());
        given.setRepereSourceDebut(i);
        given.setRepereSourceFin(i);
        given.setRepereDestinationDebut(i);
        given.setRepereDestinationFin(i);
        given.setPositionRouePelle(i);
        given.setPositionStacker(i);
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
