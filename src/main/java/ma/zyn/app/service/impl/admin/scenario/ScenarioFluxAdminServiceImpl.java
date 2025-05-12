package ma.zyn.app.service.impl.admin.scenario;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.repository.facade.core.scenario.ScenarioFluxRepository;
import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.planmaintenance.TauxRendementStadeOperatoireAdminService ;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.StatusScenarioFluxAdminService ;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux ;
import ma.zyn.app.service.facade.admin.demande.DemandeAdminService ;
import ma.zyn.app.bean.core.demande.Demande ;
import ma.zyn.app.service.facade.admin.planmaintenance.PlanDisponibiliteAdminService ;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite ;
import ma.zyn.app.service.facade.admin.scenario.ExerciceAdminService ;
import ma.zyn.app.bean.core.scenario.Exercice ;
import ma.zyn.app.service.facade.admin.stock.SuiviStockAdminService ;
import ma.zyn.app.bean.core.stock.SuiviStock ;

import java.util.List;
@Service
public class ScenarioFluxAdminServiceImpl implements ScenarioFluxAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ScenarioFlux update(ScenarioFlux t) {
        ScenarioFlux loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public ScenarioFlux findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ScenarioFlux findOrSave(ScenarioFlux t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ScenarioFlux result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ScenarioFlux> findAll() {
        return repository.findAll();
    }


    public List<ScenarioFlux> findByExerciceId(Long id){
        return repository.findByExerciceId(id);
    }
    public int deleteByExerciceId(Long id){
        return repository.deleteByExerciceId(id);
    }
    public long countByExerciceCode(String code){
        return repository.countByExerciceCode(code);
    }
    public List<ScenarioFlux> findByStatusScenarioFluxCode(String code){
        return repository.findByStatusScenarioFluxCode(code);
    }
    public List<ScenarioFlux> findByStatusScenarioFluxId(Long id){
        return repository.findByStatusScenarioFluxId(id);
    }
    public int deleteByStatusScenarioFluxCode(String code){
        return repository.deleteByStatusScenarioFluxCode(code);
    }
    public int deleteByStatusScenarioFluxId(Long id){
        return repository.deleteByStatusScenarioFluxId(id);
    }
    public long countByStatusScenarioFluxCode(String code){
        return repository.countByStatusScenarioFluxCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            repository.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        demandeService.deleteByScenarioFluxId(id);
        planDisponibiliteService.deleteByScenarioFluxId(id);
        tauxRendementStadeOperatoireService.deleteByScenarioFluxId(id);
        suiviStockService.deleteByScenarioFluxId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ScenarioFlux> delete(List<ScenarioFlux> list) {
		List<ScenarioFlux> result = new ArrayList();
        if (list != null) {
            for (ScenarioFlux t : list) {
                if(repository.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    repository.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ScenarioFlux create(ScenarioFlux t) {
        ScenarioFlux loaded = findByReferenceEntity(t);
        ScenarioFlux saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getDemandes() != null) {
                t.getDemandes().forEach(element-> {
                    element.setScenarioFlux(saved);
                    demandeService.create(element);
                });
            }
            if (t.getPlanDisponibilites() != null) {
                t.getPlanDisponibilites().forEach(element-> {
                    element.setScenarioFlux(saved);
                    planDisponibiliteService.create(element);
                });
            }
            if (t.getTauxRendementStadeOperatoires() != null) {
                t.getTauxRendementStadeOperatoires().forEach(element-> {
                    element.setScenarioFlux(saved);
                    tauxRendementStadeOperatoireService.create(element);
                });
            }
            if (t.getSuiviStocks() != null) {
                t.getSuiviStocks().forEach(element-> {
                    element.setScenarioFlux(saved);
                    suiviStockService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public ScenarioFlux findWithAssociatedLists(Long id){
        ScenarioFlux result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setDemandes(demandeService.findByScenarioFluxId(id));
            result.setPlanDisponibilites(planDisponibiliteService.findByScenarioFluxId(id));
            result.setTauxRendementStadeOperatoires(tauxRendementStadeOperatoireService.findByScenarioFluxId(id));
            result.setSuiviStocks(suiviStockService.findByScenarioFluxId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ScenarioFlux> update(List<ScenarioFlux> ts, boolean createIfNotExist) {
        List<ScenarioFlux> result = new ArrayList<>();
        if (ts != null) {
            for (ScenarioFlux t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ScenarioFlux loadedItem = repository.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        repository.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ScenarioFlux t, ScenarioFlux loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(ScenarioFlux scenarioFlux){
    if(scenarioFlux !=null && scenarioFlux.getId() != null){
        List<List<Demande>> resultDemandes= demandeService.getToBeSavedAndToBeDeleted(demandeService.findByScenarioFluxId(scenarioFlux.getId()),scenarioFlux.getDemandes());
            demandeService.delete(resultDemandes.get(1));
        emptyIfNull(resultDemandes.get(0)).forEach(e -> e.setScenarioFlux(scenarioFlux));
        demandeService.update(resultDemandes.get(0),true);
        List<List<PlanDisponibilite>> resultPlanDisponibilites= planDisponibiliteService.getToBeSavedAndToBeDeleted(planDisponibiliteService.findByScenarioFluxId(scenarioFlux.getId()),scenarioFlux.getPlanDisponibilites());
            planDisponibiliteService.delete(resultPlanDisponibilites.get(1));
        emptyIfNull(resultPlanDisponibilites.get(0)).forEach(e -> e.setScenarioFlux(scenarioFlux));
        planDisponibiliteService.update(resultPlanDisponibilites.get(0),true);
        List<List<TauxRendementStadeOperatoire>> resultTauxRendementStadeOperatoires= tauxRendementStadeOperatoireService.getToBeSavedAndToBeDeleted(tauxRendementStadeOperatoireService.findByScenarioFluxId(scenarioFlux.getId()),scenarioFlux.getTauxRendementStadeOperatoires());
            tauxRendementStadeOperatoireService.delete(resultTauxRendementStadeOperatoires.get(1));
        emptyIfNull(resultTauxRendementStadeOperatoires.get(0)).forEach(e -> e.setScenarioFlux(scenarioFlux));
        tauxRendementStadeOperatoireService.update(resultTauxRendementStadeOperatoires.get(0),true);
        List<List<SuiviStock>> resultSuiviStocks= suiviStockService.getToBeSavedAndToBeDeleted(suiviStockService.findByScenarioFluxId(scenarioFlux.getId()),scenarioFlux.getSuiviStocks());
            suiviStockService.delete(resultSuiviStocks.get(1));
        emptyIfNull(resultSuiviStocks.get(0)).forEach(e -> e.setScenarioFlux(scenarioFlux));
        suiviStockService.update(resultSuiviStocks.get(0),true);
        }
    }








    public ScenarioFlux findByReferenceEntity(ScenarioFlux t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ScenarioFlux t){
        if( t != null) {
            t.setExercice(exerciceService.findOrSave(t.getExercice()));
            t.setStatusScenarioFlux(statusScenarioFluxService.findOrSave(t.getStatusScenarioFlux()));
        }
    }



    public List<ScenarioFlux> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ScenarioFlux>> getToBeSavedAndToBeDeleted(List<ScenarioFlux> oldList, List<ScenarioFlux> newList) {
        List<List<ScenarioFlux>> result = new ArrayList<>();
        List<ScenarioFlux> resultDelete = new ArrayList<>();
        List<ScenarioFlux> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<ScenarioFlux> oldList, List<ScenarioFlux> newList, List<ScenarioFlux> resultUpdateOrSave, List<ScenarioFlux> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ScenarioFlux myOld = oldList.get(i);
                ScenarioFlux t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ScenarioFlux myNew = newList.get(i);
                ScenarioFlux t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private TauxRendementStadeOperatoireAdminService tauxRendementStadeOperatoireService ;
    private StatusScenarioFluxAdminService statusScenarioFluxService ;
    private DemandeAdminService demandeService ;
    private PlanDisponibiliteAdminService planDisponibiliteService ;
    private ExerciceAdminService exerciceService ;
    private SuiviStockAdminService suiviStockService ;

    public ScenarioFluxAdminServiceImpl(ScenarioFluxRepository repository, TauxRendementStadeOperatoireAdminService tauxRendementStadeOperatoireService, StatusScenarioFluxAdminService statusScenarioFluxService, DemandeAdminService demandeService, PlanDisponibiliteAdminService planDisponibiliteService, ExerciceAdminService exerciceService, SuiviStockAdminService suiviStockService) {
        this.repository = repository;
        this.tauxRendementStadeOperatoireService = tauxRendementStadeOperatoireService;
        this.statusScenarioFluxService = statusScenarioFluxService;
        this.demandeService = demandeService;
        this.planDisponibiliteService = planDisponibiliteService;
        this.exerciceService = exerciceService;
        this.suiviStockService = suiviStockService;
    }

    private ScenarioFluxRepository repository;
}
