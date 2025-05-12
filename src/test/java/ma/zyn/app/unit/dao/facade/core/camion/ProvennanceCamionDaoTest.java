package ma.zyn.app.unit.dao.facade.core.camion;

import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.dao.facade.core.camion.ProvennanceCamionDao;

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
public class ProvennanceCamionDaoTest {

@Autowired
    private ProvennanceCamionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ProvennanceCamion entity = new ProvennanceCamion();
        entity.setCode(code);
        underTest.save(entity);
        ProvennanceCamion loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ProvennanceCamion loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ProvennanceCamion entity = new ProvennanceCamion();
        entity.setId(id);
        underTest.save(entity);
        ProvennanceCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProvennanceCamion entity = new ProvennanceCamion();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProvennanceCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProvennanceCamion> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProvennanceCamion> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProvennanceCamion given = constructSample(1);
        ProvennanceCamion saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProvennanceCamion constructSample(int i) {
		ProvennanceCamion given = new ProvennanceCamion();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
