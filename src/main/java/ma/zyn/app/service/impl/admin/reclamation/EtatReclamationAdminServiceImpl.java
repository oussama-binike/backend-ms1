package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.repository.facade.core.reclamation.EtatReclamationRepository;
import ma.zyn.app.service.facade.admin.reclamation.EtatReclamationAdminService;

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
public class EtatReclamationAdminServiceImpl implements EtatReclamationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatReclamation update(EtatReclamation t) {
        EtatReclamation loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public EtatReclamation findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public EtatReclamation findOrSave(EtatReclamation t) {
        if (t != null) {
            EtatReclamation result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EtatReclamation> findAll() {
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
    public List<EtatReclamation> delete(List<EtatReclamation> list) {
		List<EtatReclamation> result = new ArrayList();
        if (list != null) {
            for (EtatReclamation t : list) {
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
    public EtatReclamation create(EtatReclamation t) {
        EtatReclamation loaded = findByReferenceEntity(t);
        EtatReclamation saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EtatReclamation findWithAssociatedLists(Long id){
        EtatReclamation result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatReclamation> update(List<EtatReclamation> ts, boolean createIfNotExist) {
        List<EtatReclamation> result = new ArrayList<>();
        if (ts != null) {
            for (EtatReclamation t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    EtatReclamation loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EtatReclamation t, EtatReclamation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EtatReclamation findByReferenceEntity(EtatReclamation t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<EtatReclamation> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<EtatReclamation>> getToBeSavedAndToBeDeleted(List<EtatReclamation> oldList, List<EtatReclamation> newList) {
        List<List<EtatReclamation>> result = new ArrayList<>();
        List<EtatReclamation> resultDelete = new ArrayList<>();
        List<EtatReclamation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EtatReclamation> oldList, List<EtatReclamation> newList, List<EtatReclamation> resultUpdateOrSave, List<EtatReclamation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EtatReclamation myOld = oldList.get(i);
                EtatReclamation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EtatReclamation myNew = newList.get(i);
                EtatReclamation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public EtatReclamationAdminServiceImpl(EtatReclamationRepository repository) {
        this.repository = repository;
    }

    private EtatReclamationRepository repository;
}
