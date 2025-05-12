package ma.zyn.app.dao.facade.core.reclamation;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import java.util.List;


@Repository
public interface ActionEntrepriseRepository extends AbstractRepository<ActionEntreprise,Long>  {
    ActionEntreprise findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ActionEntreprise(item.id,item.libelle) FROM ActionEntreprise item")
    List<ActionEntreprise> findAllOptimized();

}
