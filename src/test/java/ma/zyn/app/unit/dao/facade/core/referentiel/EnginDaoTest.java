package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.dao.facade.core.referentiel.EnginDao;

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
import ma.zyn.app.bean.core.referentiel.TypeEngin ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EnginDaoTest {

@Autowired
    private EnginDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Engin entity = new Engin();
        entity.setCode(code);
        underTest.save(entity);
        Engin loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Engin loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Engin entity = new Engin();
        entity.setId(id);
        underTest.save(entity);
        Engin loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Engin entity = new Engin();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Engin loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Engin> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Engin> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Engin given = constructSample(1);
        Engin saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Engin constructSample(int i) {
		Engin given = new Engin();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setTypeEngin(new TypeEngin(1L));
        given.setOperationStadeOperatoire(new OperationStadeOperatoire(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setCapacite(BigDecimal.TEN);
        given.setRendement(BigDecimal.TEN);
        return given;
    }

}
