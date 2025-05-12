package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.repository.facade.core.referentiel.ProvennanceTrainRepository;
import ma.zyn.app.service.facade.admin.referentiel.ProvennanceTrainAdminService;

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
public class ProvennanceTrainAdminServiceImpl implements ProvennanceTrainAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProvennanceTrain update(ProvennanceTrain t) {
        ProvennanceTrain loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ProvennanceTrain findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ProvennanceTrain findOrSave(ProvennanceTrain t) {
        if (t != null) {
            ProvennanceTrain result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProvennanceTrain> findAll() {
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
    public List<ProvennanceTrain> delete(List<ProvennanceTrain> list) {
		List<ProvennanceTrain> result = new ArrayList();
        if (list != null) {
            for (ProvennanceTrain t : list) {
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
    public ProvennanceTrain create(ProvennanceTrain t) {
        ProvennanceTrain loaded = findByReferenceEntity(t);
        ProvennanceTrain saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProvennanceTrain findWithAssociatedLists(Long id){
        ProvennanceTrain result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProvennanceTrain> update(List<ProvennanceTrain> ts, boolean createIfNotExist) {
        List<ProvennanceTrain> result = new ArrayList<>();
        if (ts != null) {
            for (ProvennanceTrain t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ProvennanceTrain loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProvennanceTrain t, ProvennanceTrain loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProvennanceTrain findByReferenceEntity(ProvennanceTrain t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<ProvennanceTrain> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ProvennanceTrain>> getToBeSavedAndToBeDeleted(List<ProvennanceTrain> oldList, List<ProvennanceTrain> newList) {
        List<List<ProvennanceTrain>> result = new ArrayList<>();
        List<ProvennanceTrain> resultDelete = new ArrayList<>();
        List<ProvennanceTrain> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProvennanceTrain> oldList, List<ProvennanceTrain> newList, List<ProvennanceTrain> resultUpdateOrSave, List<ProvennanceTrain> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProvennanceTrain myOld = oldList.get(i);
                ProvennanceTrain t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProvennanceTrain myNew = newList.get(i);
                ProvennanceTrain t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ProvennanceTrainAdminServiceImpl(ProvennanceTrainRepository repository) {
        this.repository = repository;
    }

    private ProvennanceTrainRepository repository;
}
