package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import java.util.List;


@Repository
public interface ChartePhysiqueRepository extends AbstractRepository<ChartePhysique,Long>  {
    ChartePhysique findByCode(String code);
    int deleteByCode(String code);

    List<ChartePhysique> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);

    @Query("SELECT NEW ChartePhysique(item.id,item.libelle) FROM ChartePhysique item")
    List<ChartePhysique> findAllOptimized();

}
