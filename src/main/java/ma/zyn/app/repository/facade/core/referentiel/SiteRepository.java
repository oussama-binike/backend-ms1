package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Site;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Site;
import java.util.List;


@Repository
public interface SiteRepository extends AbstractRepository<Site,Long>  {
    Site findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Site(item.id,item.libelle) FROM Site item")
    List<Site> findAllOptimized();

}
