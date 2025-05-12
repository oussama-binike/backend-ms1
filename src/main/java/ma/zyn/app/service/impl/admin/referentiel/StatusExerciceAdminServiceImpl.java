package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.StatusExercice;
import ma.zyn.app.repository.facade.core.referentiel.StatusExerciceRepository;
import ma.zyn.app.service.facade.admin.referentiel.StatusExerciceAdminService;

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
public class StatusExerciceAdminServiceImpl implements StatusExerciceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StatusExercice update(StatusExercice t) {
        StatusExercice loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public StatusExercice findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public StatusExercice findOrSave(StatusExercice t) {
        if (t != null) {
            StatusExercice result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StatusExercice> findAll() {
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
    public List<StatusExercice> delete(List<StatusExercice> list) {
		List<StatusExercice> result = new ArrayList();
        if (list != null) {
            for (StatusExercice t : list) {
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
    public StatusExercice create(StatusExercice t) {
        StatusExercice loaded = findByReferenceEntity(t);
        StatusExercice saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public StatusExercice findWithAssociatedLists(Long id){
        StatusExercice result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StatusExercice> update(List<StatusExercice> ts, boolean createIfNotExist) {
        List<StatusExercice> result = new ArrayList<>();
        if (ts != null) {
            for (StatusExercice t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    StatusExercice loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StatusExercice t, StatusExercice loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public StatusExercice findByReferenceEntity(StatusExercice t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<StatusExercice> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<StatusExercice>> getToBeSavedAndToBeDeleted(List<StatusExercice> oldList, List<StatusExercice> newList) {
        List<List<StatusExercice>> result = new ArrayList<>();
        List<StatusExercice> resultDelete = new ArrayList<>();
        List<StatusExercice> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StatusExercice> oldList, List<StatusExercice> newList, List<StatusExercice> resultUpdateOrSave, List<StatusExercice> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StatusExercice myOld = oldList.get(i);
                StatusExercice t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StatusExercice myNew = newList.get(i);
                StatusExercice t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public StatusExerciceAdminServiceImpl(StatusExerciceRepository repository) {
        this.repository = repository;
    }

    private StatusExerciceRepository repository;
}
