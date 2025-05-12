package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.OperationStadeOperatoireDao;

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
public class OperationStadeOperatoireDaoTest {

@Autowired
    private OperationStadeOperatoireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        OperationStadeOperatoire entity = new OperationStadeOperatoire();
        entity.setCode(code);
        underTest.save(entity);
        OperationStadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        OperationStadeOperatoire loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        OperationStadeOperatoire entity = new OperationStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);
        OperationStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        OperationStadeOperatoire entity = new OperationStadeOperatoire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        OperationStadeOperatoire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<OperationStadeOperatoire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<OperationStadeOperatoire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        OperationStadeOperatoire given = constructSample(1);
        OperationStadeOperatoire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private OperationStadeOperatoire constructSample(int i) {
		OperationStadeOperatoire given = new OperationStadeOperatoire();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
