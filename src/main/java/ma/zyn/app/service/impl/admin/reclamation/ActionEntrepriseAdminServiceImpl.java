package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.repository.facade.core.reclamation.ActionEntrepriseRepository;
import ma.zyn.app.service.facade.admin.reclamation.ActionEntrepriseAdminService;

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
public class ActionEntrepriseAdminServiceImpl implements ActionEntrepriseAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ActionEntreprise update(ActionEntreprise t) {
        ActionEntreprise loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ActionEntreprise findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ActionEntreprise findOrSave(ActionEntreprise t) {
        if (t != null) {
            ActionEntreprise result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ActionEntreprise> findAll() {
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
    public List<ActionEntreprise> delete(List<ActionEntreprise> list) {
		List<ActionEntreprise> result = new ArrayList();
        if (list != null) {
            for (ActionEntreprise t : list) {
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
    public ActionEntreprise create(ActionEntreprise t) {
        ActionEntreprise loaded = findByReferenceEntity(t);
        ActionEntreprise saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ActionEntreprise findWithAssociatedLists(Long id){
        ActionEntreprise result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ActionEntreprise> update(List<ActionEntreprise> ts, boolean createIfNotExist) {
        List<ActionEntreprise> result = new ArrayList<>();
        if (ts != null) {
            for (ActionEntreprise t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ActionEntreprise loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ActionEntreprise t, ActionEntreprise loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ActionEntreprise findByReferenceEntity(ActionEntreprise t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<ActionEntreprise> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ActionEntreprise>> getToBeSavedAndToBeDeleted(List<ActionEntreprise> oldList, List<ActionEntreprise> newList) {
        List<List<ActionEntreprise>> result = new ArrayList<>();
        List<ActionEntreprise> resultDelete = new ArrayList<>();
        List<ActionEntreprise> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ActionEntreprise> oldList, List<ActionEntreprise> newList, List<ActionEntreprise> resultUpdateOrSave, List<ActionEntreprise> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ActionEntreprise myOld = oldList.get(i);
                ActionEntreprise t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ActionEntreprise myNew = newList.get(i);
                ActionEntreprise t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ActionEntrepriseAdminServiceImpl(ActionEntrepriseRepository repository) {
        this.repository = repository;
    }

    private ActionEntrepriseRepository repository;
}
