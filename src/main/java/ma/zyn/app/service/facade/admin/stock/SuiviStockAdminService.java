package ma.zyn.app.service.facade.admin.stock;

import java.util.List;
import ma.zyn.app.bean.core.stock.SuiviStock;



public interface SuiviStockAdminService {



    List<SuiviStock> findByLiaisonId(Long id);
    int deleteByLiaisonId(Long id);
    long countByLiaisonCode(String code);
    List<SuiviStock> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);




	SuiviStock create(SuiviStock t);

    SuiviStock update(SuiviStock t);

    List<SuiviStock> update(List<SuiviStock> ts,boolean createIfNotExist);

    SuiviStock findById(Long id);

    SuiviStock findOrSave(SuiviStock t);

    SuiviStock findByReferenceEntity(SuiviStock t);

    SuiviStock findWithAssociatedLists(Long id);

    List<SuiviStock> findAllOptimized();

    List<SuiviStock> findAll();

    List<SuiviStock> delete(List<SuiviStock> ts);

    boolean deleteById(Long id);

    List<List<SuiviStock>> getToBeSavedAndToBeDeleted(List<SuiviStock> oldList, List<SuiviStock> newList);

}
