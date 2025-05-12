package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import java.util.List;


@Repository
public interface CoutConsommableRepository extends AbstractRepository<CoutConsommable,Long>  {
    CoutConsommable findByCode(String code);
    int deleteByCode(String code);

    List<CoutConsommable> findByConsommableCode(String code);
    List<CoutConsommable> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);
    List<CoutConsommable> findByUniteCode(String code);
    List<CoutConsommable> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW CoutConsommable(item.id,item.libelle) FROM CoutConsommable item")
    List<CoutConsommable> findAllOptimized();

}
