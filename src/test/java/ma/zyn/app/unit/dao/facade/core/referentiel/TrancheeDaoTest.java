package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.Tranchee;
import ma.zyn.app.dao.facade.core.referentiel.TrancheeDao;

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

import ma.zyn.app.bean.core.referentiel.Panneau ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TrancheeDaoTest {

@Autowired
    private TrancheeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Tranchee entity = new Tranchee();
        entity.setCode(code);
        underTest.save(entity);
        Tranchee loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Tranchee loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Tranchee entity = new Tranchee();
        entity.setId(id);
        underTest.save(entity);
        Tranchee loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Tranchee entity = new Tranchee();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Tranchee loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Tranchee> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Tranchee> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Tranchee given = constructSample(1);
        Tranchee saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Tranchee constructSample(int i) {
		Tranchee given = new Tranchee();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setPanneau(new Panneau(1L));
        return given;
    }

}
