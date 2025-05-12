package ma.zyn.app.unit.dao.facade.core.navire;

import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireQualiteDao;

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

import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.navire.RealisationNavire ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationNavireQualiteDaoTest {

@Autowired
    private RealisationNavireQualiteDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationNavireQualite entity = new RealisationNavireQualite();
        entity.setId(id);
        underTest.save(entity);
        RealisationNavireQualite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationNavireQualite entity = new RealisationNavireQualite();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationNavireQualite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationNavireQualite> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationNavireQualite> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationNavireQualite given = constructSample(1);
        RealisationNavireQualite saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationNavireQualite constructSample(int i) {
		RealisationNavireQualite given = new RealisationNavireQualite();
        given.setProduitMarchand(new ProduitMarchand(1L));
        given.setTsm(BigDecimal.TEN);
        given.setVolume(BigDecimal.TEN);
        given.setRealisationNavire(new RealisationNavire(1L));
        return given;
    }

}
