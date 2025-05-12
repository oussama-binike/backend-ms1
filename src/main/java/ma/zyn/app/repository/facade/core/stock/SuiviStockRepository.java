package ma.zyn.app.dao.facade.core.stock;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.stock.SuiviStock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.stock.SuiviStock;
import java.util.List;


@Repository
public interface SuiviStockRepository extends AbstractRepository<SuiviStock,Long>  {
    SuiviStock findByCode(String code);
    int deleteByCode(String code);

    List<SuiviStock> findByLiaisonId(Long id);
    int deleteByLiaisonId(Long id);
    long countByLiaisonCode(String code);
    List<SuiviStock> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);

    @Query("SELECT NEW SuiviStock(item.id,item.libelle) FROM SuiviStock item")
    List<SuiviStock> findAllOptimized();

}
