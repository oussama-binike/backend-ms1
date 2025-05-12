package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Train;



public interface TrainAdminService {







	Train create(Train t);

    Train update(Train t);

    List<Train> update(List<Train> ts,boolean createIfNotExist);

    Train findById(Long id);

    Train findOrSave(Train t);

    Train findByReferenceEntity(Train t);

    Train findWithAssociatedLists(Long id);

    List<Train> findAllOptimized();

    List<Train> findAll();

    List<Train> delete(List<Train> ts);

    boolean deleteById(Long id);

    List<List<Train>> getToBeSavedAndToBeDeleted(List<Train> oldList, List<Train> newList);

}
