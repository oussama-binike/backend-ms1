package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Panneau;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Panneau;
import java.util.List;


@Repository
public interface PanneauRepository extends AbstractRepository<Panneau,Long>  {
    Panneau findByCode(String code);
    int deleteByCode(String code);

    List<Panneau> findByEntiteCode(String code);
    List<Panneau> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);

    @Query("SELECT NEW Panneau(item.id,item.libelle) FROM Panneau item")
    List<Panneau> findAllOptimized();

}
