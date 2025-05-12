package ma.zyn.app.service.impl.admin.planmaintenance;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.repository.facade.core.planmaintenance.TauxRendementStadeOperatoireRepository;
import ma.zyn.app.service.facade.admin.planmaintenance.TauxRendementStadeOperatoireAdminService;

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

import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import java.util.List;
@Service
public class TauxRendementStadeOperatoireAdminServiceImpl implements TauxRendementStadeOperatoireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TauxRendementStadeOperatoire update(TauxRendementStadeOperatoire t) {
        TauxRendementStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TauxRendementStadeOperatoire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TauxRendementStadeOperatoire findOrSave(TauxRendementStadeOperatoire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            TauxRendementStadeOperatoire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TauxRendementStadeOperatoire> findAll() {
        return repository.findAll();
    }


    public List<TauxRendementStadeOperatoire> findByScenarioFluxId(Long id){
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
    public List<TauxRendementStadeOperatoire> delete(List<TauxRendementStadeOperatoire> list) {
		List<TauxRendementStadeOperatoire> result = new ArrayList();
        if (list != null) {
            for (TauxRendementStadeOperatoire t : list) {
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
    public TauxRendementStadeOperatoire create(TauxRendementStadeOperatoire t) {
        TauxRendementStadeOperatoire loaded = findByReferenceEntity(t);
        TauxRendementStadeOperatoire saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TauxRendementStadeOperatoire findWithAssociatedLists(Long id){
        TauxRendementStadeOperatoire result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TauxRendementStadeOperatoire> update(List<TauxRendementStadeOperatoire> ts, boolean createIfNotExist) {
        List<TauxRendementStadeOperatoire> result = new ArrayList<>();
        if (ts != null) {
            for (TauxRendementStadeOperatoire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TauxRendementStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TauxRendementStadeOperatoire t, TauxRendementStadeOperatoire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TauxRendementStadeOperatoire findByReferenceEntity(TauxRendementStadeOperatoire t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(TauxRendementStadeOperatoire t){
        if( t != null) {
            t.setScenarioFlux(scenarioFluxService.findOrSave(t.getScenarioFlux()));
        }
    }



    public List<TauxRendementStadeOperatoire> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<TauxRendementStadeOperatoire>> getToBeSavedAndToBeDeleted(List<TauxRendementStadeOperatoire> oldList, List<TauxRendementStadeOperatoire> newList) {
        List<List<TauxRendementStadeOperatoire>> result = new ArrayList<>();
        List<TauxRendementStadeOperatoire> resultDelete = new ArrayList<>();
        List<TauxRendementStadeOperatoire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TauxRendementStadeOperatoire> oldList, List<TauxRendementStadeOperatoire> newList, List<TauxRendementStadeOperatoire> resultUpdateOrSave, List<TauxRendementStadeOperatoire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TauxRendementStadeOperatoire myOld = oldList.get(i);
                TauxRendementStadeOperatoire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TauxRendementStadeOperatoire myNew = newList.get(i);
                TauxRendementStadeOperatoire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ScenarioFluxAdminService scenarioFluxService ;

    public TauxRendementStadeOperatoireAdminServiceImpl(TauxRendementStadeOperatoireRepository repository, ScenarioFluxAdminService scenarioFluxService) {
        this.repository = repository;
        this.scenarioFluxService = scenarioFluxService;
    }

    private TauxRendementStadeOperatoireRepository repository;
}
