package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import ma.zyn.app.dao.facade.core.referentiel.CoutConsommableDao;

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

import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.bean.core.referentiel.Consommable ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CoutConsommableDaoTest {

@Autowired
    private CoutConsommableDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CoutConsommable entity = new CoutConsommable();
        entity.setCode(code);
        underTest.save(entity);
        CoutConsommable loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        CoutConsommable loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CoutConsommable entity = new CoutConsommable();
        entity.setId(id);
        underTest.save(entity);
        CoutConsommable loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CoutConsommable entity = new CoutConsommable();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CoutConsommable loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CoutConsommable> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CoutConsommable> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CoutConsommable given = constructSample(1);
        CoutConsommable saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CoutConsommable constructSample(int i) {
		CoutConsommable given = new CoutConsommable();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setConsommable(new Consommable(1L));
        given.setCoutUnitaire(BigDecimal.TEN);
        given.setUnite(new Unite(1L));
        return given;
    }

}
