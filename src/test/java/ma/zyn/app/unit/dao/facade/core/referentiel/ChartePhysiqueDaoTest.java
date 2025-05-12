package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.dao.facade.core.referentiel.ChartePhysiqueDao;

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

import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ChartePhysiqueDaoTest {

@Autowired
    private ChartePhysiqueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ChartePhysique entity = new ChartePhysique();
        entity.setCode(code);
        underTest.save(entity);
        ChartePhysique loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ChartePhysique loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ChartePhysique entity = new ChartePhysique();
        entity.setId(id);
        underTest.save(entity);
        ChartePhysique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ChartePhysique entity = new ChartePhysique();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ChartePhysique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ChartePhysique> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ChartePhysique> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ChartePhysique given = constructSample(1);
        ChartePhysique saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ChartePhysique constructSample(int i) {
		ChartePhysique given = new ChartePhysique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduit(new Produit(1L));
        given.setMinimumSize(BigDecimal.TEN);
        given.setMaximumSize(BigDecimal.TEN);
        given.setValeur(BigDecimal.TEN);
        return given;
    }

}
