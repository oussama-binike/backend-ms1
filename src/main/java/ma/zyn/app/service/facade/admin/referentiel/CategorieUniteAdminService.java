package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CategorieUnite;



public interface CategorieUniteAdminService {







	CategorieUnite create(CategorieUnite t);

    CategorieUnite update(CategorieUnite t);

    List<CategorieUnite> update(List<CategorieUnite> ts,boolean createIfNotExist);

    CategorieUnite findById(Long id);

    CategorieUnite findOrSave(CategorieUnite t);

    CategorieUnite findByReferenceEntity(CategorieUnite t);

    CategorieUnite findWithAssociatedLists(Long id);

    List<CategorieUnite> findAllOptimized();

    List<CategorieUnite> findAll();

    List<CategorieUnite> delete(List<CategorieUnite> ts);

    boolean deleteById(Long id);

    List<List<CategorieUnite>> getToBeSavedAndToBeDeleted(List<CategorieUnite> oldList, List<CategorieUnite> newList);

}
