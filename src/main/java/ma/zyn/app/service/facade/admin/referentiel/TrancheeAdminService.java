package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Tranchee;



public interface TrancheeAdminService {



    List<Tranchee> findByPanneauCode(String code);
    List<Tranchee> findByPanneauId(Long id);
    int deleteByPanneauId(Long id);
    int deleteByPanneauCode(String code);
    long countByPanneauCode(String code);




	Tranchee create(Tranchee t);

    Tranchee update(Tranchee t);

    List<Tranchee> update(List<Tranchee> ts,boolean createIfNotExist);

    Tranchee findById(Long id);

    Tranchee findOrSave(Tranchee t);

    Tranchee findByReferenceEntity(Tranchee t);

    Tranchee findWithAssociatedLists(Long id);

    List<Tranchee> findAllOptimized();

    List<Tranchee> findAll();

    List<Tranchee> delete(List<Tranchee> ts);

    boolean deleteById(Long id);

    List<List<Tranchee>> getToBeSavedAndToBeDeleted(List<Tranchee> oldList, List<Tranchee> newList);

}
