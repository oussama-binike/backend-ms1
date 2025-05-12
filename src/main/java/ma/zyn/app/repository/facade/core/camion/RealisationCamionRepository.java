package ma.zyn.app.dao.facade.core.camion;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.camion.RealisationCamion;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationCamionRepository extends AbstractRepository<RealisationCamion,Long>  {

    List<RealisationCamion> findByProvennanceCamionCode(String code);
    List<RealisationCamion> findByProvennanceCamionId(Long id);
    int deleteByProvennanceCamionId(Long id);
    int deleteByProvennanceCamionCode(String code);
    long countByProvennanceCamionCode(String code);
    List<RealisationCamion> findByDestinationCamionCode(String code);
    List<RealisationCamion> findByDestinationCamionId(Long id);
    int deleteByDestinationCamionId(Long id);
    int deleteByDestinationCamionCode(String code);
    long countByDestinationCamionCode(String code);

    @Query("SELECT NEW RealisationCamion(item.id,item.libelle) FROM RealisationCamion item")
    List<RealisationCamion> findAllOptimized();

}
