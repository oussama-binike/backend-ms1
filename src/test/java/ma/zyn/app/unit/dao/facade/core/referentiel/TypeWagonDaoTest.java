package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.dao.facade.core.referentiel.TypeWagonDao;

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
public class TypeWagonDaoTest {

@Autowired
    private TypeWagonDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        TypeWagon entity = new TypeWagon();
        entity.setCode(code);
        underTest.save(entity);
        TypeWagon loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        TypeWagon loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        TypeWagon entity = new TypeWagon();
        entity.setId(id);
        underTest.save(entity);
        TypeWagon loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        TypeWagon entity = new TypeWagon();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        TypeWagon loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<TypeWagon> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<TypeWagon> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        TypeWagon given = constructSample(1);
        TypeWagon saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private TypeWagon constructSample(int i) {
		TypeWagon given = new TypeWagon();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
