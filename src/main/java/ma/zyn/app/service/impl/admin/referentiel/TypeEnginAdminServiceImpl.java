package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import ma.zyn.app.repository.facade.core.referentiel.TypeEnginRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeEnginAdminService;

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
public class TypeEnginAdminServiceImpl implements TypeEnginAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeEngin update(TypeEngin t) {
        TypeEngin loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeEngin findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeEngin findOrSave(TypeEngin t) {
        if (t != null) {
            TypeEngin result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeEngin> findAll() {
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
    public List<TypeEngin> delete(List<TypeEngin> list) {
		List<TypeEngin> result = new ArrayList();
        if (list != null) {
            for (TypeEngin t : list) {
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
    public TypeEngin create(TypeEngin t) {
        TypeEngin loaded = findByReferenceEntity(t);
        TypeEngin saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeEngin findWithAssociatedLists(Long id){
        TypeEngin result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeEngin> update(List<TypeEngin> ts, boolean createIfNotExist) {
        List<TypeEngin> result = new ArrayList<>();
        if (ts != null) {
            for (TypeEngin t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeEngin loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeEngin t, TypeEngin loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeEngin findByReferenceEntity(TypeEngin t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeEngin> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeEngin>> getToBeSavedAndToBeDeleted(List<TypeEngin> oldList, List<TypeEngin> newList) {
        List<List<TypeEngin>> result = new ArrayList<>();
        List<TypeEngin> resultDelete = new ArrayList<>();
        List<TypeEngin> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeEngin> oldList, List<TypeEngin> newList, List<TypeEngin> resultUpdateOrSave, List<TypeEngin> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeEngin myOld = oldList.get(i);
                TypeEngin t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeEngin myNew = newList.get(i);
                TypeEngin t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeEnginAdminServiceImpl(TypeEnginRepository repository) {
        this.repository = repository;
    }

    private TypeEnginRepository repository;
}
