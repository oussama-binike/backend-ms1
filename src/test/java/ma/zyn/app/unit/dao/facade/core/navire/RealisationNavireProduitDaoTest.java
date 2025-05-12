package ma.zyn.app.unit.dao.facade.core.navire;

import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireProduitDao;

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

import ma.zyn.app.bean.core.navire.RealisationNavire ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationNavireProduitDaoTest {

@Autowired
    private RealisationNavireProduitDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationNavireProduit entity = new RealisationNavireProduit();
        entity.setId(id);
        underTest.save(entity);
        RealisationNavireProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationNavireProduit entity = new RealisationNavireProduit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationNavireProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationNavireProduit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationNavireProduit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationNavireProduit given = constructSample(1);
        RealisationNavireProduit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationNavireProduit constructSample(int i) {
		RealisationNavireProduit given = new RealisationNavireProduit();
        given.setProduit(new Produit(1L));
        given.setTsm(BigDecimal.TEN);
        given.setVolume(BigDecimal.TEN);
        given.setRealisationNavire(new RealisationNavire(1L));
        return given;
    }

}
