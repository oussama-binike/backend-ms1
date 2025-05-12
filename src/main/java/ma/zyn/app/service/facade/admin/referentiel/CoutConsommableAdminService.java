package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CoutConsommable;



public interface CoutConsommableAdminService {



    List<CoutConsommable> findByConsommableCode(String code);
    List<CoutConsommable> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);
    List<CoutConsommable> findByUniteCode(String code);
    List<CoutConsommable> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	CoutConsommable create(CoutConsommable t);

    CoutConsommable update(CoutConsommable t);

    List<CoutConsommable> update(List<CoutConsommable> ts,boolean createIfNotExist);

    CoutConsommable findById(Long id);

    CoutConsommable findOrSave(CoutConsommable t);

    CoutConsommable findByReferenceEntity(CoutConsommable t);

    CoutConsommable findWithAssociatedLists(Long id);

    List<CoutConsommable> findAllOptimized();

    List<CoutConsommable> findAll();

    List<CoutConsommable> delete(List<CoutConsommable> ts);

    boolean deleteById(Long id);

    List<List<CoutConsommable>> getToBeSavedAndToBeDeleted(List<CoutConsommable> oldList, List<CoutConsommable> newList);

}
