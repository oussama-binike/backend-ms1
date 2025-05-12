package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import java.util.List;


@Repository
public interface DestinationTrainRepository extends AbstractRepository<DestinationTrain,Long>  {
    DestinationTrain findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DestinationTrain(item.id,item.libelle) FROM DestinationTrain item")
    List<DestinationTrain> findAllOptimized();

}
