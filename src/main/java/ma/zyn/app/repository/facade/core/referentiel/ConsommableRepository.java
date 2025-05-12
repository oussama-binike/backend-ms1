package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Consommable;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Consommable;
import java.util.List;


@Repository
public interface ConsommableRepository extends AbstractRepository<Consommable,Long>  {
    Consommable findByCode(String code);
    int deleteByCode(String code);

    List<Consommable> findByUniteCode(String code);
    List<Consommable> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);

    @Query("SELECT NEW Consommable(item.id,item.libelle) FROM Consommable item")
    List<Consommable> findAllOptimized();

}
