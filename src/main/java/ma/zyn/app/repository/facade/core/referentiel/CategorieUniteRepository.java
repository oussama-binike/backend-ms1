package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import java.util.List;


@Repository
public interface CategorieUniteRepository extends AbstractRepository<CategorieUnite,Long>  {
    CategorieUnite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieUnite(item.id,item.libelle) FROM CategorieUnite item")
    List<CategorieUnite> findAllOptimized();

}
