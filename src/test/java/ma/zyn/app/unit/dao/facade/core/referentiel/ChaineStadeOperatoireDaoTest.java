package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.ChaineStadeOperatoireDao;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ChaineStadeOperatoireDaoTest {

@Autowired
    private ChaineStadeOperatoireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ChaineStadeOperatoire entity = new ChaineStadeOperatoire();
        entity.setCode(code);
        underTest.save(entity);
        ChaineStadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ChaineStadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ChaineStadeOperatoire entity = new ChaineStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);
        ChaineStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ChaineStadeOperatoire entity = new ChaineStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ChaineStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ChaineStadeOperatoire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ChaineStadeOperatoire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ChaineStadeOperatoire given = constructSample(1);
        ChaineStadeOperatoire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ChaineStadeOperatoire constructSample(int i) {
		ChaineStadeOperatoire given = new ChaineStadeOperatoire();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        return given;
    }

}
