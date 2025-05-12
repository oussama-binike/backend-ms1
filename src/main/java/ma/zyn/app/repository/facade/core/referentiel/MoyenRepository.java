package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Moyen;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Moyen;
import java.util.List;


@Repository
public interface MoyenRepository extends AbstractRepository<Moyen,Long>  {
    Moyen findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Moyen(item.id,item.libelle) FROM Moyen item")
    List<Moyen> findAllOptimized();

}
