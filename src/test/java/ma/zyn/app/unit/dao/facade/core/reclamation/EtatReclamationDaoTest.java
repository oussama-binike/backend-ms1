package ma.zyn.app.unit.dao.facade.core.reclamation;

import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.dao.facade.core.reclamation.EtatReclamationDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EtatReclamationDaoTest {

@Autowired
    private EtatReclamationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        EtatReclamation entity = new EtatReclamation();
        entity.setCode(code);
        underTest.save(entity);
        EtatReclamation loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        EtatReclamation loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        EtatReclamation entity = new EtatReclamation();
        entity.setId(id);
        underTest.save(entity);
        EtatReclamation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        EtatReclamation entity = new EtatReclamation();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        EtatReclamation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<EtatReclamation> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<EtatReclamation> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        EtatReclamation given = constructSample(1);
        EtatReclamation saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private EtatReclamation constructSample(int i) {
		EtatReclamation given = new EtatReclamation();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
