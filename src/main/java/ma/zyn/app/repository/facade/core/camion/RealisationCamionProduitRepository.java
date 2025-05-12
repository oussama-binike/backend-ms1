package ma.zyn.app.dao.facade.core.camion;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationCamionProduitRepository extends AbstractRepository<RealisationCamionProduit,Long>  {

    List<RealisationCamionProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationCamionProduit> findByRealisationCamionId(Long id);
    int deleteByRealisationCamionId(Long id);
    long countByRealisationCamionId(Long id);


}
