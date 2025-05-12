package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.dao.facade.core.referentiel.LiaisonDao;

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

import ma.zyn.app.bean.core.referentiel.Stock ;
import ma.zyn.app.bean.core.referentiel.Engin ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LiaisonDaoTest {

@Autowired
    private LiaisonDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Liaison entity = new Liaison();
        entity.setCode(code);
        underTest.save(entity);
        Liaison loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Liaison loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Liaison entity = new Liaison();
        entity.setId(id);
        underTest.save(entity);
        Liaison loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Liaison entity = new Liaison();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Liaison loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Liaison> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Liaison> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Liaison given = constructSample(1);
        Liaison saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Liaison constructSample(int i) {
		Liaison given = new Liaison();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStockSource(new Stock(1L));
        given.setStockDestination(new Stock(1L));
        given.setEngin(new Engin(1L));
        given.setOperationStadeOperatoire(new OperationStadeOperatoire(1L));
        given.setProdduit(new Produit(1L));
        return given;
    }

}
