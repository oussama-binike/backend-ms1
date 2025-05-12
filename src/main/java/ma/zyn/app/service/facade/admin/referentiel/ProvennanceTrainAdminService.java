package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;



public interface ProvennanceTrainAdminService {







	ProvennanceTrain create(ProvennanceTrain t);

    ProvennanceTrain update(ProvennanceTrain t);

    List<ProvennanceTrain> update(List<ProvennanceTrain> ts,boolean createIfNotExist);

    ProvennanceTrain findById(Long id);

    ProvennanceTrain findOrSave(ProvennanceTrain t);

    ProvennanceTrain findByReferenceEntity(ProvennanceTrain t);

    ProvennanceTrain findWithAssociatedLists(Long id);

    List<ProvennanceTrain> findAllOptimized();

    List<ProvennanceTrain> findAll();

    List<ProvennanceTrain> delete(List<ProvennanceTrain> ts);

    boolean deleteById(Long id);

    List<List<ProvennanceTrain>> getToBeSavedAndToBeDeleted(List<ProvennanceTrain> oldList, List<ProvennanceTrain> newList);

}
