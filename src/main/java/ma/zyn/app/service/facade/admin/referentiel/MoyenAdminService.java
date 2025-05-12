package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Moyen;



public interface MoyenAdminService {







	Moyen create(Moyen t);

    Moyen update(Moyen t);

    List<Moyen> update(List<Moyen> ts,boolean createIfNotExist);

    Moyen findById(Long id);

    Moyen findOrSave(Moyen t);

    Moyen findByReferenceEntity(Moyen t);

    Moyen findWithAssociatedLists(Long id);

    List<Moyen> findAllOptimized();

    List<Moyen> findAll();

    List<Moyen> delete(List<Moyen> ts);

    boolean deleteById(Long id);

    List<List<Moyen>> getToBeSavedAndToBeDeleted(List<Moyen> oldList, List<Moyen> newList);

}
