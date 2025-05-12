package ma.zyn.app.service.impl.admin.planmaintenance;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.repository.facade.core.planmaintenance.PlanDisponibiliteRepository;
import ma.zyn.app.service.facade.admin.planmaintenance.PlanDisponibiliteAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import java.util.List;
@Service
public class PlanDisponibiliteAdminServiceImpl implements PlanDisponibiliteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PlanDisponibilite update(PlanDisponibilite t) {
        PlanDisponibilite loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public PlanDisponibilite findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public PlanDisponibilite findOrSave(PlanDisponibilite t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PlanDisponibilite result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PlanDisponibilite> findAll() {
        return repository.findAll();
    }


    public List<PlanDisponibilite> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<PlanDisponibilite> findByStadeOperatoireId(Long id){
        return repository.findByStadeOperatoireId(id);
    }
    public int deleteByStadeOperatoireCode(String code){
        return repository.deleteByStadeOperatoireCode(code);
    }
    public int deleteByStadeOperatoireId(Long id){
        return repository.deleteByStadeOperatoireId(id);
    }
    public long countByStadeOperatoireCode(String code){
        return repository.countByStadeOperatoireCode(code);
    }
    public List<PlanDisponibilite> findByScenarioFluxId(Long id){
        return repository.findByScenarioFluxId(id);
    }
    public int deleteByScenarioFluxId(Long id){
        return repository.deleteByScenarioFluxId(id);
    }
    public long countByScenarioFluxCode(String code){
        return repository.countByScenarioFluxCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            repository.deleteById(id);
        }
        return condition;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PlanDisponibilite> delete(List<PlanDisponibilite> list) {
		List<PlanDisponibilite> result = new ArrayList();
        if (list != null) {
            for (PlanDisponibilite t : list) {
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
    public PlanDisponibilite create(PlanDisponibilite t) {
        PlanDisponibilite loaded = findByReferenceEntity(t);
        PlanDisponibilite saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PlanDisponibilite findWithAssociatedLists(Long id){
        PlanDisponibilite result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PlanDisponibilite> update(List<PlanDisponibilite> ts, boolean createIfNotExist) {
        List<PlanDisponibilite> result = new ArrayList<>();
        if (ts != null) {
            for (PlanDisponibilite t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    PlanDisponibilite loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PlanDisponibilite t, PlanDisponibilite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PlanDisponibilite findByReferenceEntity(PlanDisponibilite t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(PlanDisponibilite t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setScenarioFlux(scenarioFluxService.findOrSave(t.getScenarioFlux()));
        }
    }



    public List<PlanDisponibilite> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<PlanDisponibilite>> getToBeSavedAndToBeDeleted(List<PlanDisponibilite> oldList, List<PlanDisponibilite> newList) {
        List<List<PlanDisponibilite>> result = new ArrayList<>();
        List<PlanDisponibilite> resultDelete = new ArrayList<>();
        List<PlanDisponibilite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PlanDisponibilite> oldList, List<PlanDisponibilite> newList, List<PlanDisponibilite> resultUpdateOrSave, List<PlanDisponibilite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PlanDisponibilite myOld = oldList.get(i);
                PlanDisponibilite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PlanDisponibilite myNew = newList.get(i);
                PlanDisponibilite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;
    private ScenarioFluxAdminService scenarioFluxService ;

    public PlanDisponibiliteAdminServiceImpl(PlanDisponibiliteRepository repository, StadeOperatoireAdminService stadeOperatoireService, ScenarioFluxAdminService scenarioFluxService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
        this.scenarioFluxService = scenarioFluxService;
    }

    private PlanDisponibiliteRepository repository;
}
