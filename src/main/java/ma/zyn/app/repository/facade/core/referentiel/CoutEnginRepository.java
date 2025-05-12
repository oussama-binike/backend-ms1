package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CoutEngin;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CoutEngin;
import java.util.List;


@Repository
public interface CoutEnginRepository extends AbstractRepository<CoutEngin,Long>  {
    CoutEngin findByCode(String code);
    int deleteByCode(String code);

    List<CoutEngin> findByEnginId(Long id);
    int deleteByEnginId(Long id);
    long countByEnginCode(String code);
    List<CoutEngin> findByUniteCode(String code);
    List<CoutEngin> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW CoutEngin(item.id,item.libelle) FROM CoutEngin item")
    List<CoutEngin> findAllOptimized();

}
