package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.CategorieStock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.CategorieStock;
import java.util.List;


@Repository
public interface CategorieStockRepository extends AbstractRepository<CategorieStock,Long>  {
    CategorieStock findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieStock(item.id,item.libelle) FROM CategorieStock item")
    List<CategorieStock> findAllOptimized();

}
