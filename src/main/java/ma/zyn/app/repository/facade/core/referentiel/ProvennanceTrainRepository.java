package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import java.util.List;


@Repository
public interface ProvennanceTrainRepository extends AbstractRepository<ProvennanceTrain,Long>  {
    ProvennanceTrain findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProvennanceTrain(item.id,item.libelle) FROM ProvennanceTrain item")
    List<ProvennanceTrain> findAllOptimized();

}
