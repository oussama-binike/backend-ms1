package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Consommable;



public interface ConsommableAdminService {



    List<Consommable> findByUniteCode(String code);
    List<Consommable> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	Consommable create(Consommable t);

    Consommable update(Consommable t);

    List<Consommable> update(List<Consommable> ts,boolean createIfNotExist);

    Consommable findById(Long id);

    Consommable findOrSave(Consommable t);

    Consommable findByReferenceEntity(Consommable t);

    Consommable findWithAssociatedLists(Long id);

    List<Consommable> findAllOptimized();

    List<Consommable> findAll();

    List<Consommable> delete(List<Consommable> ts);

    boolean deleteById(Long id);

    List<List<Consommable>> getToBeSavedAndToBeDeleted(List<Consommable> oldList, List<Consommable> newList);

}
