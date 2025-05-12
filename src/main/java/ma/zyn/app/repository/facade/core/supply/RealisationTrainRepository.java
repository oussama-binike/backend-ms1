package ma.zyn.app.dao.facade.core.supply;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.supply.RealisationTrain;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.supply.RealisationTrain;
import java.util.List;


@Repository
public interface RealisationTrainRepository extends AbstractRepository<RealisationTrain,Long>  {
    RealisationTrain findByCode(String code);
    int deleteByCode(String code);

    List<RealisationTrain> findByProvennanceTrainCode(String code);
    List<RealisationTrain> findByProvennanceTrainId(Long id);
    int deleteByProvennanceTrainId(Long id);
    int deleteByProvennanceTrainCode(String code);
    long countByProvennanceTrainCode(String code);
    List<RealisationTrain> findByDestinationTrainCode(String code);
    List<RealisationTrain> findByDestinationTrainId(Long id);
    int deleteByDestinationTrainId(Long id);
    int deleteByDestinationTrainCode(String code);
    long countByDestinationTrainCode(String code);
    List<RealisationTrain> findByTrainId(Long id);
    int deleteByTrainId(Long id);
    long countByTrainCode(String code);
    List<RealisationTrain> findByTypeWagonCode(String code);
    List<RealisationTrain> findByTypeWagonId(Long id);
    int deleteByTypeWagonId(Long id);
    int deleteByTypeWagonCode(String code);
    long countByTypeWagonCode(String code);
    List<RealisationTrain> findByStadeOperatoireCode(String code);
    List<RealisationTrain> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);

    @Query("SELECT NEW RealisationTrain(item.id,item.libelle) FROM RealisationTrain item")
    List<RealisationTrain> findAllOptimized();

}
