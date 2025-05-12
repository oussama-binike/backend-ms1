package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.dao.facade.core.referentiel.StockDao;

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
import ma.zyn.app.bean.core.referentiel.TypeStock ;
import ma.zyn.app.bean.core.referentiel.CategorieStock ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StockDaoTest {

@Autowired
    private StockDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Stock entity = new Stock();
        entity.setCode(code);
        underTest.save(entity);
        Stock loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Stock loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Stock entity = new Stock();
        entity.setId(id);
        underTest.save(entity);
        Stock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Stock entity = new Stock();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Stock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Stock> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Stock> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Stock given = constructSample(1);
        Stock saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Stock constructSample(int i) {
		Stock given = new Stock();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setAutonomie(BigDecimal.TEN);
        given.setCapacite(BigDecimal.TEN);
        given.setNombreRepere(i);
        given.setRepereDebut(i);
        given.setRepereFin(i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setTypeStock(new TypeStock(1L));
        given.setCategorieStock(new CategorieStock(1L));
        return given;
    }

}
