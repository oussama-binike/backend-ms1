package ma.zyn.app.unit.dao.facade.core.reclamation;

import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.dao.facade.core.reclamation.ReclamationDao;

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
import ma.zyn.app.bean.core.reclamation.EtatReclamation ;
import ma.zyn.app.bean.core.referentiel.Entite ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReclamationDaoTest {

@Autowired
    private ReclamationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Reclamation entity = new Reclamation();
        entity.setCode(code);
        underTest.save(entity);
        Reclamation loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Reclamation loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Reclamation entity = new Reclamation();
        entity.setId(id);
        underTest.save(entity);
        Reclamation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Reclamation entity = new Reclamation();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Reclamation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Reclamation> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Reclamation> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Reclamation given = constructSample(1);
        Reclamation saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Reclamation constructSample(int i) {
		Reclamation given = new Reclamation();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setEntiteEmettrice(new Entite(1L));
        given.setEntiteDistinataire(new Entite(1L));
        given.setProduitMarchand(new ProduitMarchand(1L));
        given.setQuantite(BigDecimal.TEN);
        given.setFonde(false);
        given.setMotifReclamation("motifReclamation-"+i);
        given.setEtatReclamation(new EtatReclamation(1L));
        given.setDateOccurence(LocalDateTime.now());
        given.setDateReception(LocalDateTime.now());
        given.setActionEntreprise("actionEntreprise-"+i);
        return given;
    }

}
