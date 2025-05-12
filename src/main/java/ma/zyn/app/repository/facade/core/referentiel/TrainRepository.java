package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Train;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Train;
import java.util.List;


@Repository
public interface TrainRepository extends AbstractRepository<Train,Long>  {
    Train findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Train(item.id,item.libelle) FROM Train item")
    List<Train> findAllOptimized();

}
