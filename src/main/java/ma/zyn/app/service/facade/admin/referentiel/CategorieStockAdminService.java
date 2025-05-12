package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CategorieStock;



public interface CategorieStockAdminService {







	CategorieStock create(CategorieStock t);

    CategorieStock update(CategorieStock t);

    List<CategorieStock> update(List<CategorieStock> ts,boolean createIfNotExist);

    CategorieStock findById(Long id);

    CategorieStock findOrSave(CategorieStock t);

    CategorieStock findByReferenceEntity(CategorieStock t);

    CategorieStock findWithAssociatedLists(Long id);

    List<CategorieStock> findAllOptimized();

    List<CategorieStock> findAll();

    List<CategorieStock> delete(List<CategorieStock> ts);

    boolean deleteById(Long id);

    List<List<CategorieStock>> getToBeSavedAndToBeDeleted(List<CategorieStock> oldList, List<CategorieStock> newList);

}
