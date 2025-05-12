package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;



public interface StatusScenarioFluxAdminService {







	StatusScenarioFlux create(StatusScenarioFlux t);

    StatusScenarioFlux update(StatusScenarioFlux t);

    List<StatusScenarioFlux> update(List<StatusScenarioFlux> ts,boolean createIfNotExist);

    StatusScenarioFlux findById(Long id);

    StatusScenarioFlux findOrSave(StatusScenarioFlux t);

    StatusScenarioFlux findByReferenceEntity(StatusScenarioFlux t);

    StatusScenarioFlux findWithAssociatedLists(Long id);

    List<StatusScenarioFlux> findAllOptimized();

    List<StatusScenarioFlux> findAll();

    List<StatusScenarioFlux> delete(List<StatusScenarioFlux> ts);

    boolean deleteById(Long id);

    List<List<StatusScenarioFlux>> getToBeSavedAndToBeDeleted(List<StatusScenarioFlux> oldList, List<StatusScenarioFlux> newList);

}
