package ma.zyn.app.service.impl.admin.camion;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.repository.facade.core.camion.ProvennanceCamionRepository;
import ma.zyn.app.service.facade.admin.camion.ProvennanceCamionAdminService;

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
public class ProvennanceCamionAdminServiceImpl implements ProvennanceCamionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProvennanceCamion update(ProvennanceCamion t) {
        ProvennanceCamion loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ProvennanceCamion findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ProvennanceCamion findOrSave(ProvennanceCamion t) {
        if (t != null) {
            ProvennanceCamion result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProvennanceCamion> findAll() {
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
    public List<ProvennanceCamion> delete(List<ProvennanceCamion> list) {
		List<ProvennanceCamion> result = new ArrayList();
        if (list != null) {
            for (ProvennanceCamion t : list) {
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
    public ProvennanceCamion create(ProvennanceCamion t) {
        ProvennanceCamion loaded = findByReferenceEntity(t);
        ProvennanceCamion saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProvennanceCamion findWithAssociatedLists(Long id){
        ProvennanceCamion result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProvennanceCamion> update(List<ProvennanceCamion> ts, boolean createIfNotExist) {
        List<ProvennanceCamion> result = new ArrayList<>();
        if (ts != null) {
            for (ProvennanceCamion t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ProvennanceCamion loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProvennanceCamion t, ProvennanceCamion loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProvennanceCamion findByReferenceEntity(ProvennanceCamion t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<ProvennanceCamion> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ProvennanceCamion>> getToBeSavedAndToBeDeleted(List<ProvennanceCamion> oldList, List<ProvennanceCamion> newList) {
        List<List<ProvennanceCamion>> result = new ArrayList<>();
        List<ProvennanceCamion> resultDelete = new ArrayList<>();
        List<ProvennanceCamion> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProvennanceCamion> oldList, List<ProvennanceCamion> newList, List<ProvennanceCamion> resultUpdateOrSave, List<ProvennanceCamion> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProvennanceCamion myOld = oldList.get(i);
                ProvennanceCamion t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProvennanceCamion myNew = newList.get(i);
                ProvennanceCamion t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ProvennanceCamionAdminServiceImpl(ProvennanceCamionRepository repository) {
        this.repository = repository;
    }

    private ProvennanceCamionRepository repository;
}
