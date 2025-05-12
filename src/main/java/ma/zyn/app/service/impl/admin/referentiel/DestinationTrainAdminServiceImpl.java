package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.repository.facade.core.referentiel.DestinationTrainRepository;
import ma.zyn.app.service.facade.admin.referentiel.DestinationTrainAdminService;

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
public class DestinationTrainAdminServiceImpl implements DestinationTrainAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DestinationTrain update(DestinationTrain t) {
        DestinationTrain loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public DestinationTrain findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public DestinationTrain findOrSave(DestinationTrain t) {
        if (t != null) {
            DestinationTrain result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DestinationTrain> findAll() {
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
    public List<DestinationTrain> delete(List<DestinationTrain> list) {
		List<DestinationTrain> result = new ArrayList();
        if (list != null) {
            for (DestinationTrain t : list) {
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
    public DestinationTrain create(DestinationTrain t) {
        DestinationTrain loaded = findByReferenceEntity(t);
        DestinationTrain saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DestinationTrain findWithAssociatedLists(Long id){
        DestinationTrain result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DestinationTrain> update(List<DestinationTrain> ts, boolean createIfNotExist) {
        List<DestinationTrain> result = new ArrayList<>();
        if (ts != null) {
            for (DestinationTrain t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    DestinationTrain loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DestinationTrain t, DestinationTrain loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DestinationTrain findByReferenceEntity(DestinationTrain t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<DestinationTrain> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<DestinationTrain>> getToBeSavedAndToBeDeleted(List<DestinationTrain> oldList, List<DestinationTrain> newList) {
        List<List<DestinationTrain>> result = new ArrayList<>();
        List<DestinationTrain> resultDelete = new ArrayList<>();
        List<DestinationTrain> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DestinationTrain> oldList, List<DestinationTrain> newList, List<DestinationTrain> resultUpdateOrSave, List<DestinationTrain> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DestinationTrain myOld = oldList.get(i);
                DestinationTrain t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DestinationTrain myNew = newList.get(i);
                DestinationTrain t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public DestinationTrainAdminServiceImpl(DestinationTrainRepository repository) {
        this.repository = repository;
    }

    private DestinationTrainRepository repository;
}
