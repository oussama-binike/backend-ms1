package ma.zyn.app.unit.dao.facade.core.supply;

import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.dao.facade.core.supply.RealisationTrainDao;

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

import ma.zyn.app.bean.core.referentiel.Train ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain ;
import ma.zyn.app.bean.core.referentiel.DestinationTrain ;
import ma.zyn.app.bean.core.referentiel.TypeWagon ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RealisationTrainDaoTest {

@Autowired
    private RealisationTrainDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        RealisationTrain entity = new RealisationTrain();
        entity.setCode(code);
        underTest.save(entity);
        RealisationTrain loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        RealisationTrain loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        RealisationTrain entity = new RealisationTrain();
        entity.setId(id);
        underTest.save(entity);
        RealisationTrain loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RealisationTrain entity = new RealisationTrain();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RealisationTrain loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RealisationTrain> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RealisationTrain> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RealisationTrain given = constructSample(1);
        RealisationTrain saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RealisationTrain constructSample(int i) {
		RealisationTrain given = new RealisationTrain();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setProvennanceTrain(new ProvennanceTrain(1L));
        given.setDestinationTrain(new DestinationTrain(1L));
        given.setTrain(new Train(1L));
        given.setTypeWagon(new TypeWagon(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setTauxCompletude(BigDecimal.TEN);
        given.setTauxRemplissage(BigDecimal.TEN);
        given.setTauxChargement(BigDecimal.TEN);
        given.setTempsChargement("tempsChargement-"+i);
        given.setTempsDechargement("tempsDechargement-"+i);
        given.setTempsTransite(BigDecimal.TEN);
        given.setExpedie(false);
        given.setPlanifie(false);
        return given;
    }

}
