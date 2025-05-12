package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CharteChimique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CharteChimique;
import java.util.List;


@Repository
public interface CharteChimiqueRepository extends AbstractRepository<CharteChimique,Long>  {
    CharteChimique findByCode(String code);
    int deleteByCode(String code);

    List<CharteChimique> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<CharteChimique> findByElementChimiqueCode(String code);
    List<CharteChimique> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);

    @Query("SELECT NEW CharteChimique(item.id,item.libelle) FROM CharteChimique item")
    List<CharteChimique> findAllOptimized();

}
