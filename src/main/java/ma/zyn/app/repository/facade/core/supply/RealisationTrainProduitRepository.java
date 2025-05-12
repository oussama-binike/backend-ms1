package ma.zyn.app.dao.facade.core.supply;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationTrainProduitRepository extends AbstractRepository<RealisationTrainProduit,Long>  {

    List<RealisationTrainProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationTrainProduit> findByRealisationTrainId(Long id);
    int deleteByRealisationTrainId(Long id);
    long countByRealisationTrainCode(String code);


}
