package ma.zyn.app.service.facade.admin.scenario;

import java.util.List;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;



public interface ScenarioFluxAdminService {



    List<ScenarioFlux> findByExerciceId(Long id);
    int deleteByExerciceId(Long id);
    long countByExerciceCode(String code);
    List<ScenarioFlux> findByStatusScenarioFluxCode(String code);
    List<ScenarioFlux> findByStatusScenarioFluxId(Long id);
    int deleteByStatusScenarioFluxId(Long id);
    int deleteByStatusScenarioFluxCode(String code);
    long countByStatusScenarioFluxCode(String code);




	ScenarioFlux create(ScenarioFlux t);

    ScenarioFlux update(ScenarioFlux t);

    List<ScenarioFlux> update(List<ScenarioFlux> ts,boolean createIfNotExist);

    ScenarioFlux findById(Long id);

    ScenarioFlux findOrSave(ScenarioFlux t);

    ScenarioFlux findByReferenceEntity(ScenarioFlux t);

    ScenarioFlux findWithAssociatedLists(Long id);

    List<ScenarioFlux> findAllOptimized();

    List<ScenarioFlux> findAll();

    List<ScenarioFlux> delete(List<ScenarioFlux> ts);

    boolean deleteById(Long id);

    List<List<ScenarioFlux>> getToBeSavedAndToBeDeleted(List<ScenarioFlux> oldList, List<ScenarioFlux> newList);

}
