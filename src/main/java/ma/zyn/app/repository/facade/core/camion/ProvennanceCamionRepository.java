package ma.zyn.app.dao.facade.core.camion;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import java.util.List;


@Repository
public interface ProvennanceCamionRepository extends AbstractRepository<ProvennanceCamion,Long>  {
    ProvennanceCamion findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProvennanceCamion(item.id,item.libelle) FROM ProvennanceCamion item")
    List<ProvennanceCamion> findAllOptimized();

}
