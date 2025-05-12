package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Stock;



public interface StockAdminService {



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




	Stock create(Stock t);

    Stock update(Stock t);

    List<Stock> update(List<Stock> ts,boolean createIfNotExist);

    Stock findById(Long id);

    Stock findOrSave(Stock t);

    Stock findByReferenceEntity(Stock t);

    Stock findWithAssociatedLists(Long id);

    List<Stock> findAllOptimized();

    List<Stock> findAll();

    List<Stock> delete(List<Stock> ts);

    boolean deleteById(Long id);

    List<List<Stock>> getToBeSavedAndToBeDeleted(List<Stock> oldList, List<Stock> newList);

}
