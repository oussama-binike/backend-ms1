package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CoutEngin;



public interface CoutEnginAdminService {



    List<CoutEngin> findByEnginId(Long id);
    int deleteByEnginId(Long id);
    long countByEnginCode(String code);
    List<CoutEngin> findByUniteCode(String code);
    List<CoutEngin> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	CoutEngin create(CoutEngin t);

    CoutEngin update(CoutEngin t);

    List<CoutEngin> update(List<CoutEngin> ts,boolean createIfNotExist);

    CoutEngin findById(Long id);

    CoutEngin findOrSave(CoutEngin t);

    CoutEngin findByReferenceEntity(CoutEngin t);

    CoutEngin findWithAssociatedLists(Long id);

    List<CoutEngin> findAllOptimized();

    List<CoutEngin> findAll();

    List<CoutEngin> delete(List<CoutEngin> ts);

    boolean deleteById(Long id);

    List<List<CoutEngin>> getToBeSavedAndToBeDeleted(List<CoutEngin> oldList, List<CoutEngin> newList);

}
