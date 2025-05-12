package ma.zyn.app.dao.facade.core.camion;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import java.util.List;


@Repository
public interface DestinationCamionRepository extends AbstractRepository<DestinationCamion,Long>  {
    DestinationCamion findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DestinationCamion(item.id,item.libelle) FROM DestinationCamion item")
    List<DestinationCamion> findAllOptimized();

}
