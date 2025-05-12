package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import ma.zyn.app.repository.facade.core.reclamation.MotifReclamationRepository;
import ma.zyn.app.service.facade.admin.reclamation.MotifReclamationAdminService;

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
public class MotifReclamationAdminServiceImpl implements MotifReclamationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public MotifReclamation update(MotifReclamation t) {
        MotifReclamation loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public MotifReclamation findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public MotifReclamation findOrSave(MotifReclamation t) {
        if (t != null) {
            MotifReclamation result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<MotifReclamation> findAll() {
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
    public List<MotifReclamation> delete(List<MotifReclamation> list) {
		List<MotifReclamation> result = new ArrayList();
        if (list != null) {
            for (MotifReclamation t : list) {
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
    public MotifReclamation create(MotifReclamation t) {
        MotifReclamation loaded = findByReferenceEntity(t);
        MotifReclamation saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public MotifReclamation findWithAssociatedLists(Long id){
        MotifReclamation result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<MotifReclamation> update(List<MotifReclamation> ts, boolean createIfNotExist) {
        List<MotifReclamation> result = new ArrayList<>();
        if (ts != null) {
            for (MotifReclamation t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    MotifReclamation loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, MotifReclamation t, MotifReclamation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public MotifReclamation findByReferenceEntity(MotifReclamation t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<MotifReclamation> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<MotifReclamation>> getToBeSavedAndToBeDeleted(List<MotifReclamation> oldList, List<MotifReclamation> newList) {
        List<List<MotifReclamation>> result = new ArrayList<>();
        List<MotifReclamation> resultDelete = new ArrayList<>();
        List<MotifReclamation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<MotifReclamation> oldList, List<MotifReclamation> newList, List<MotifReclamation> resultUpdateOrSave, List<MotifReclamation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                MotifReclamation myOld = oldList.get(i);
                MotifReclamation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                MotifReclamation myNew = newList.get(i);
                MotifReclamation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public MotifReclamationAdminServiceImpl(MotifReclamationRepository repository) {
        this.repository = repository;
    }

    private MotifReclamationRepository repository;
}
