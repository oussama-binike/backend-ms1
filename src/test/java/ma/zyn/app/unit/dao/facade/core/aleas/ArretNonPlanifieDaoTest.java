package ma.zyn.app.unit.dao.facade.core.aleas;

import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import ma.zyn.app.dao.facade.core.aleas.ArretNonPlanifieDao;

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
import ma.zyn.app.bean.core.reclamation.ActionEntreprise ;
import ma.zyn.app.bean.core.aleas.CauseArret ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ArretNonPlanifieDaoTest {

@Autowired
    private ArretNonPlanifieDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ArretNonPlanifie entity = new ArretNonPlanifie();
        entity.setCode(code);
        underTest.save(entity);
        ArretNonPlanifie loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ArretNonPlanifie loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ArretNonPlanifie entity = new ArretNonPlanifie();
        entity.setId(id);
        underTest.save(entity);
        ArretNonPlanifie loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ArretNonPlanifie entity = new ArretNonPlanifie();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ArretNonPlanifie loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ArretNonPlanifie> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ArretNonPlanifie> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ArretNonPlanifie given = constructSample(1);
        ArretNonPlanifie saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ArretNonPlanifie constructSample(int i) {
		ArretNonPlanifie given = new ArretNonPlanifie();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setCauseArret(new CauseArret(1L));
        given.setDureeEstimee(BigDecimal.TEN);
        given.setDateArret(LocalDateTime.now());
        given.setDateDebut(LocalDateTime.now());
        given.setDateFin(LocalDateTime.now());
        given.setActionEntreprise(new ActionEntreprise(1L));
        return given;
    }

}
