package ma.zyn.app.dao.facade.core.reclamation;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import java.util.List;


@Repository
public interface EtatReclamationRepository extends AbstractRepository<EtatReclamation,Long>  {
    EtatReclamation findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatReclamation(item.id,item.libelle) FROM EtatReclamation item")
    List<EtatReclamation> findAllOptimized();

}
