package ma.zyn.app.unit.dao.facade.core.supply;

import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.dao.facade.core.supply.RealisationTrainProduitDao;

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

import ma.zyn.app.bean.core.supply.RealisationTrain ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationTrainProduitDaoTest {

@Autowired
    private RealisationTrainProduitDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationTrainProduit entity = new RealisationTrainProduit();
        entity.setId(id);
        underTest.save(entity);
        RealisationTrainProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationTrainProduit entity = new RealisationTrainProduit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationTrainProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationTrainProduit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationTrainProduit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationTrainProduit given = constructSample(1);
        RealisationTrainProduit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationTrainProduit constructSample(int i) {
		RealisationTrainProduit given = new RealisationTrainProduit();
        given.setProduit(new Produit(1L));
        given.setVolume(BigDecimal.TEN);
        given.setTsm(BigDecimal.TEN);
        given.setRealisationTrain(new RealisationTrain(1L));
        return given;
    }

}
