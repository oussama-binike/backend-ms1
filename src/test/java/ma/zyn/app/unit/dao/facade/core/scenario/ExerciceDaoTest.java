package ma.zyn.app.unit.dao.facade.core.scenario;

import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.dao.facade.core.scenario.ExerciceDao;

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

import ma.zyn.app.bean.core.referentiel.StatusExercice ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExerciceDaoTest {

@Autowired
    private ExerciceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Exercice entity = new Exercice();
        entity.setCode(code);
        underTest.save(entity);
        Exercice loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Exercice loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Exercice entity = new Exercice();
        entity.setId(id);
        underTest.save(entity);
        Exercice loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Exercice entity = new Exercice();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Exercice loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Exercice> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Exercice> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Exercice given = constructSample(1);
        Exercice saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Exercice constructSample(int i) {
		Exercice given = new Exercice();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setMois(i);
        given.setAnnee(i);
        given.setDateDebut(LocalDateTime.now());
        given.setDateFin(LocalDateTime.now());
        given.setDateRetrospective(LocalDateTime.now());
        given.setStatusExercice(new StatusExercice(1L));
        return given;
    }

}
