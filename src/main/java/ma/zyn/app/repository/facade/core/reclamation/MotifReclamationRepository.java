package ma.zyn.app.dao.facade.core.reclamation;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import java.util.List;


@Repository
public interface MotifReclamationRepository extends AbstractRepository<MotifReclamation,Long>  {
    MotifReclamation findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW MotifReclamation(item.id,item.libelle) FROM MotifReclamation item")
    List<MotifReclamation> findAllOptimized();

}
