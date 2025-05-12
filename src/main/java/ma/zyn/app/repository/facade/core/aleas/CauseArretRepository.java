package ma.zyn.app.dao.facade.core.aleas;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.aleas.CauseArret;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.aleas.CauseArret;
import java.util.List;


@Repository
public interface CauseArretRepository extends AbstractRepository<CauseArret,Long>  {
    CauseArret findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CauseArret(item.id,item.libelle) FROM CauseArret item")
    List<CauseArret> findAllOptimized();

}
