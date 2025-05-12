package ma.zyn.app.unit.dao.facade.core.reclamation;

import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.dao.facade.core.reclamation.ReclamationElementChimiqueDao;

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

import ma.zyn.app.bean.core.reclamation.Reclamation ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReclamationElementChimiqueDaoTest {

@Autowired
    private ReclamationElementChimiqueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ReclamationElementChimique entity = new ReclamationElementChimique();
        entity.setId(id);
        underTest.save(entity);
        ReclamationElementChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ReclamationElementChimique entity = new ReclamationElementChimique();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ReclamationElementChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ReclamationElementChimique> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ReclamationElementChimique> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ReclamationElementChimique given = constructSample(1);
        ReclamationElementChimique saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ReclamationElementChimique constructSample(int i) {
		ReclamationElementChimique given = new ReclamationElementChimique();
        given.setReclamation(new Reclamation(1L));
        given.setElementChimique(new ElementChimique(1L));
        return given;
    }

}
