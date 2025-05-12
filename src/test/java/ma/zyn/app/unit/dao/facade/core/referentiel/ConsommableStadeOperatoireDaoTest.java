package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.ConsommableStadeOperatoireDao;

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

import ma.zyn.app.bean.core.referentiel.Consommable ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ConsommableStadeOperatoireDaoTest {

@Autowired
    private ConsommableStadeOperatoireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ConsommableStadeOperatoire entity = new ConsommableStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);
        ConsommableStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ConsommableStadeOperatoire entity = new ConsommableStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ConsommableStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ConsommableStadeOperatoire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ConsommableStadeOperatoire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ConsommableStadeOperatoire given = constructSample(1);
        ConsommableStadeOperatoire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ConsommableStadeOperatoire constructSample(int i) {
		ConsommableStadeOperatoire given = new ConsommableStadeOperatoire();
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setConsommable(new Consommable(1L));
        return given;
    }

}
