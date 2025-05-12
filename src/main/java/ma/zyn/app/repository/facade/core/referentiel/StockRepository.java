package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Stock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Stock;
import java.util.List;


@Repository
public interface StockRepository extends AbstractRepository<Stock,Long>  {
    Stock findByCode(String code);
    int deleteByCode(String code);

    List<Stock> findByStadeOperatoireCode(String code);
    List<Stock> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<Stock> findByTypeStockCode(String code);
    List<Stock> findByTypeStockId(Long id);
    int deleteByTypeStockId(Long id);
    int deleteByTypeStockCode(String code);
    long countByTypeStockCode(String code);
    List<Stock> findByCategorieStockCode(String code);
    List<Stock> findByCategorieStockId(Long id);
    int deleteByCategorieStockId(Long id);
    int deleteByCategorieStockCode(String code);
    long countByCategorieStockCode(String code);

    @Query("SELECT NEW Stock(item.id,item.libelle) FROM Stock item")
    List<Stock> findAllOptimized();

}
