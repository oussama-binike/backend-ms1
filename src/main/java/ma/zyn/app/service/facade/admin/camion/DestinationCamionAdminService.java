package ma.zyn.app.service.facade.admin.camion;

import java.util.List;
import ma.zyn.app.bean.core.camion.DestinationCamion;



public interface DestinationCamionAdminService {







	DestinationCamion create(DestinationCamion t);

    DestinationCamion update(DestinationCamion t);

    List<DestinationCamion> update(List<DestinationCamion> ts,boolean createIfNotExist);

    DestinationCamion findById(Long id);

    DestinationCamion findOrSave(DestinationCamion t);

    DestinationCamion findByReferenceEntity(DestinationCamion t);

    DestinationCamion findWithAssociatedLists(Long id);

    List<DestinationCamion> findAllOptimized();

    List<DestinationCamion> findAll();

    List<DestinationCamion> delete(List<DestinationCamion> ts);

    boolean deleteById(Long id);

    List<List<DestinationCamion>> getToBeSavedAndToBeDeleted(List<DestinationCamion> oldList, List<DestinationCamion> newList);

}
