package ma.zyn.app.unit.dao.facade.core.camion;

import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.dao.facade.core.camion.RealisationCamionDao;

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

import ma.zyn.app.bean.core.camion.ProvennanceCamion ;
import ma.zyn.app.bean.core.camion.DestinationCamion ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationCamionDaoTest {

@Autowired
    private RealisationCamionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationCamion entity = new RealisationCamion();
        entity.setId(id);
        underTest.save(entity);
        RealisationCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationCamion entity = new RealisationCamion();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationCamion loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationCamion> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationCamion> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationCamion given = constructSample(1);
        RealisationCamion saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationCamion constructSample(int i) {
		RealisationCamion given = new RealisationCamion();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setNombreCamions(BigDecimal.TEN);
        given.setDureeMoyenneTransport(BigDecimal.TEN);
        given.setTotalTms(BigDecimal.TEN);
        given.setProvennanceCamion(new ProvennanceCamion(1L));
        given.setDestinationCamion(new DestinationCamion(1L));
        return given;
    }

}
