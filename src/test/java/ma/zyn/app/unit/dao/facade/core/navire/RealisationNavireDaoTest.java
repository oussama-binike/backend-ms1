package ma.zyn.app.unit.dao.facade.core.navire;

import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireDao;

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

import ma.zyn.app.bean.core.navire.DestinationNavire ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationNavireDaoTest {

@Autowired
    private RealisationNavireDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationNavire entity = new RealisationNavire();
        entity.setId(id);
        underTest.save(entity);
        RealisationNavire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationNavire entity = new RealisationNavire();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationNavire loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationNavire> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationNavire> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationNavire given = constructSample(1);
        RealisationNavire saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationNavire constructSample(int i) {
		RealisationNavire given = new RealisationNavire();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setNumeroNavire("numeroNavire-"+i);
        given.setNumeroExpedition("numeroExpedition-"+i);
        given.setJour(LocalDateTime.now());
        given.setDestinationNavire(new DestinationNavire(1L));
        given.setTauxCompletude(BigDecimal.TEN);
        given.setTauxRemplissage(BigDecimal.TEN);
        given.setDateChargement(LocalDateTime.now());
        given.setDateFinChargement(LocalDateTime.now());
        return given;
    }

}
