package ma.zyn.app.service.impl.admin.aleas;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.aleas.CauseArret;
import ma.zyn.app.repository.facade.core.aleas.CauseArretRepository;
import ma.zyn.app.service.facade.admin.aleas.CauseArretAdminService;

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
public class CauseArretAdminServiceImpl implements CauseArretAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CauseArret update(CauseArret t) {
        CauseArret loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CauseArret findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CauseArret findOrSave(CauseArret t) {
        if (t != null) {
            CauseArret result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CauseArret> findAll() {
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
    public List<CauseArret> delete(List<CauseArret> list) {
		List<CauseArret> result = new ArrayList();
        if (list != null) {
            for (CauseArret t : list) {
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
    public CauseArret create(CauseArret t) {
        CauseArret loaded = findByReferenceEntity(t);
        CauseArret saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CauseArret findWithAssociatedLists(Long id){
        CauseArret result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CauseArret> update(List<CauseArret> ts, boolean createIfNotExist) {
        List<CauseArret> result = new ArrayList<>();
        if (ts != null) {
            for (CauseArret t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CauseArret loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CauseArret t, CauseArret loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CauseArret findByReferenceEntity(CauseArret t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<CauseArret> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CauseArret>> getToBeSavedAndToBeDeleted(List<CauseArret> oldList, List<CauseArret> newList) {
        List<List<CauseArret>> result = new ArrayList<>();
        List<CauseArret> resultDelete = new ArrayList<>();
        List<CauseArret> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CauseArret> oldList, List<CauseArret> newList, List<CauseArret> resultUpdateOrSave, List<CauseArret> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CauseArret myOld = oldList.get(i);
                CauseArret t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CauseArret myNew = newList.get(i);
                CauseArret t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public CauseArretAdminServiceImpl(CauseArretRepository repository) {
        this.repository = repository;
    }

    private CauseArretRepository repository;
}
