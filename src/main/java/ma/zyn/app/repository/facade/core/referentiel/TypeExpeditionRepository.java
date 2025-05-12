package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeExpedition;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeExpedition;
import java.util.List;


@Repository
public interface TypeExpeditionRepository extends AbstractRepository<TypeExpedition,Long>  {
    TypeExpedition findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeExpedition(item.id,item.libelle) FROM TypeExpedition item")
    List<TypeExpedition> findAllOptimized();

}
