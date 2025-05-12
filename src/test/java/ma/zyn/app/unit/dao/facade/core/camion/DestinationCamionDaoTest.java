package ma.zyn.app.unit.dao.facade.core.camion;

import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.dao.facade.core.camion.DestinationCamionDao;

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
public class DestinationCamionDaoTest {

@Autowired
    private DestinationCamionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        DestinationCamion entity = new DestinationCamion();
        entity.setCode(code);
        underTest.save(entity);
        DestinationCamion loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        DestinationCamion loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        DestinationCamion entity = new DestinationCamion();
        entity.setId(id);
        underTest.save(entity);
        DestinationCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DestinationCamion entity = new DestinationCamion();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DestinationCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DestinationCamion> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DestinationCamion> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DestinationCamion given = constructSample(1);
        DestinationCamion saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private DestinationCamion constructSample(int i) {
		DestinationCamion given = new DestinationCamion();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
