package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;



public interface DestinationTrainAdminService {







	DestinationTrain create(DestinationTrain t);

    DestinationTrain update(DestinationTrain t);

    List<DestinationTrain> update(List<DestinationTrain> ts,boolean createIfNotExist);

    DestinationTrain findById(Long id);

    DestinationTrain findOrSave(DestinationTrain t);

    DestinationTrain findByReferenceEntity(DestinationTrain t);

    DestinationTrain findWithAssociatedLists(Long id);

    List<DestinationTrain> findAllOptimized();

    List<DestinationTrain> findAll();

    List<DestinationTrain> delete(List<DestinationTrain> ts);

    boolean deleteById(Long id);

    List<List<DestinationTrain>> getToBeSavedAndToBeDeleted(List<DestinationTrain> oldList, List<DestinationTrain> newList);

}
