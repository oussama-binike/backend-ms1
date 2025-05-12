package ma.zyn.app.service.facade.admin.camion;

import java.util.List;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;



public interface ProvennanceCamionAdminService {







	ProvennanceCamion create(ProvennanceCamion t);

    ProvennanceCamion update(ProvennanceCamion t);

    List<ProvennanceCamion> update(List<ProvennanceCamion> ts,boolean createIfNotExist);

    ProvennanceCamion findById(Long id);

    ProvennanceCamion findOrSave(ProvennanceCamion t);

    ProvennanceCamion findByReferenceEntity(ProvennanceCamion t);

    ProvennanceCamion findWithAssociatedLists(Long id);

    List<ProvennanceCamion> findAllOptimized();

    List<ProvennanceCamion> findAll();

    List<ProvennanceCamion> delete(List<ProvennanceCamion> ts);

    boolean deleteById(Long id);

    List<List<ProvennanceCamion>> getToBeSavedAndToBeDeleted(List<ProvennanceCamion> oldList, List<ProvennanceCamion> newList);

}
