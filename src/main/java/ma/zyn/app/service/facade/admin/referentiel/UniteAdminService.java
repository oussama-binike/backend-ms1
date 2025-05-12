package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Unite;



public interface UniteAdminService {



    List<Unite> findByCategorieUniteCode(String code);
    List<Unite> findByCategorieUniteId(Long id);
    int deleteByCategorieUniteId(Long id);
    int deleteByCategorieUniteCode(String code);
    long countByCategorieUniteCode(String code);




	Unite create(Unite t);

    Unite update(Unite t);

    List<Unite> update(List<Unite> ts,boolean createIfNotExist);

    Unite findById(Long id);

    Unite findOrSave(Unite t);

    Unite findByReferenceEntity(Unite t);

    Unite findWithAssociatedLists(Long id);

    List<Unite> findAllOptimized();

    List<Unite> findAll();

    List<Unite> delete(List<Unite> ts);

    boolean deleteById(Long id);

    List<List<Unite>> getToBeSavedAndToBeDeleted(List<Unite> oldList, List<Unite> newList);

}
