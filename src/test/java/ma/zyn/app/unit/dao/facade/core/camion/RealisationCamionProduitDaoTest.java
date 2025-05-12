package ma.zyn.app.unit.dao.facade.core.camion;

import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.dao.facade.core.camion.RealisationCamionProduitDao;

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

import ma.zyn.app.bean.core.camion.RealisationCamion ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationCamionProduitDaoTest {

@Autowired
    private RealisationCamionProduitDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationCamionProduit entity = new RealisationCamionProduit();
        entity.setId(id);
        underTest.save(entity);
        RealisationCamionProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationCamionProduit entity = new RealisationCamionProduit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationCamionProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationCamionProduit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationCamionProduit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationCamionProduit given = constructSample(1);
        RealisationCamionProduit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationCamionProduit constructSample(int i) {
		RealisationCamionProduit given = new RealisationCamionProduit();
        given.setProduit(new Produit(1L));
        given.setTsm(BigDecimal.TEN);
        given.setRealisationCamion(new RealisationCamion(1L));
        return given;
    }

}
