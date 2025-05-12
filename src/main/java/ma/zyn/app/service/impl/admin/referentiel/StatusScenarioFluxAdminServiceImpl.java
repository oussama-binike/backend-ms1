package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.repository.facade.core.referentiel.StatusScenarioFluxRepository;
import ma.zyn.app.service.facade.admin.referentiel.StatusScenarioFluxAdminService;

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


import java.util.List;
@Service
public class StatusScenarioFluxAdminServiceImpl implements StatusScenarioFluxAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StatusScenarioFlux update(StatusScenarioFlux t) {
        StatusScenarioFlux loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public StatusScenarioFlux findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public StatusScenarioFlux findOrSave(StatusScenarioFlux t) {
        if (t != null) {
            StatusScenarioFlux result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StatusScenarioFlux> findAll() {
        return repository.findAll();
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
    public List<StatusScenarioFlux> delete(List<StatusScenarioFlux> list) {
		List<StatusScenarioFlux> result = new ArrayList();
        if (list != null) {
            for (StatusScenarioFlux t : list) {
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
    public StatusScenarioFlux create(StatusScenarioFlux t) {
        StatusScenarioFlux loaded = findByReferenceEntity(t);
        StatusScenarioFlux saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public StatusScenarioFlux findWithAssociatedLists(Long id){
        StatusScenarioFlux result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StatusScenarioFlux> update(List<StatusScenarioFlux> ts, boolean createIfNotExist) {
        List<StatusScenarioFlux> result = new ArrayList<>();
        if (ts != null) {
            for (StatusScenarioFlux t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    StatusScenarioFlux loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StatusScenarioFlux t, StatusScenarioFlux loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public StatusScenarioFlux findByReferenceEntity(StatusScenarioFlux t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<StatusScenarioFlux> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<StatusScenarioFlux>> getToBeSavedAndToBeDeleted(List<StatusScenarioFlux> oldList, List<StatusScenarioFlux> newList) {
        List<List<StatusScenarioFlux>> result = new ArrayList<>();
        List<StatusScenarioFlux> resultDelete = new ArrayList<>();
        List<StatusScenarioFlux> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StatusScenarioFlux> oldList, List<StatusScenarioFlux> newList, List<StatusScenarioFlux> resultUpdateOrSave, List<StatusScenarioFlux> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StatusScenarioFlux myOld = oldList.get(i);
                StatusScenarioFlux t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StatusScenarioFlux myNew = newList.get(i);
                StatusScenarioFlux t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public StatusScenarioFluxAdminServiceImpl(StatusScenarioFluxRepository repository) {
        this.repository = repository;
    }

    private StatusScenarioFluxRepository repository;
}
