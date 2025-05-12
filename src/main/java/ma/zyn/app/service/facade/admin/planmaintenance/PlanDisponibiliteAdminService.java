package ma.zyn.app.service.facade.admin.planmaintenance;

import java.util.List;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;



public interface PlanDisponibiliteAdminService {



    List<PlanDisponibilite> findByStadeOperatoireCode(String code);
    List<PlanDisponibilite> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<PlanDisponibilite> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);




	PlanDisponibilite create(PlanDisponibilite t);

    PlanDisponibilite update(PlanDisponibilite t);

    List<PlanDisponibilite> update(List<PlanDisponibilite> ts,boolean createIfNotExist);

    PlanDisponibilite findById(Long id);

    PlanDisponibilite findOrSave(PlanDisponibilite t);

    PlanDisponibilite findByReferenceEntity(PlanDisponibilite t);

    PlanDisponibilite findWithAssociatedLists(Long id);

    List<PlanDisponibilite> findAllOptimized();

    List<PlanDisponibilite> findAll();

    List<PlanDisponibilite> delete(List<PlanDisponibilite> ts);

    boolean deleteById(Long id);

    List<List<PlanDisponibilite>> getToBeSavedAndToBeDeleted(List<PlanDisponibilite> oldList, List<PlanDisponibilite> newList);

}
