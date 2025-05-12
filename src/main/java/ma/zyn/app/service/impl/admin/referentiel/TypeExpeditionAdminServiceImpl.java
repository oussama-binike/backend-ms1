package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeExpedition;
import ma.zyn.app.repository.facade.core.referentiel.TypeExpeditionRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeExpeditionAdminService;

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
public class TypeExpeditionAdminServiceImpl implements TypeExpeditionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeExpedition update(TypeExpedition t) {
        TypeExpedition loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeExpedition findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeExpedition findOrSave(TypeExpedition t) {
        if (t != null) {
            TypeExpedition result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeExpedition> findAll() {
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
    public List<TypeExpedition> delete(List<TypeExpedition> list) {
		List<TypeExpedition> result = new ArrayList();
        if (list != null) {
            for (TypeExpedition t : list) {
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
    public TypeExpedition create(TypeExpedition t) {
        TypeExpedition loaded = findByReferenceEntity(t);
        TypeExpedition saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeExpedition findWithAssociatedLists(Long id){
        TypeExpedition result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeExpedition> update(List<TypeExpedition> ts, boolean createIfNotExist) {
        List<TypeExpedition> result = new ArrayList<>();
        if (ts != null) {
            for (TypeExpedition t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeExpedition loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeExpedition t, TypeExpedition loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeExpedition findByReferenceEntity(TypeExpedition t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeExpedition> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeExpedition>> getToBeSavedAndToBeDeleted(List<TypeExpedition> oldList, List<TypeExpedition> newList) {
        List<List<TypeExpedition>> result = new ArrayList<>();
        List<TypeExpedition> resultDelete = new ArrayList<>();
        List<TypeExpedition> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeExpedition> oldList, List<TypeExpedition> newList, List<TypeExpedition> resultUpdateOrSave, List<TypeExpedition> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeExpedition myOld = oldList.get(i);
                TypeExpedition t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeExpedition myNew = newList.get(i);
                TypeExpedition t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeExpeditionAdminServiceImpl(TypeExpeditionRepository repository) {
        this.repository = repository;
    }

    private TypeExpeditionRepository repository;
}
