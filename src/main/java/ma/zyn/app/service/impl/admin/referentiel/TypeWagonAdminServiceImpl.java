package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.repository.facade.core.referentiel.TypeWagonRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeWagonAdminService;

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
public class TypeWagonAdminServiceImpl implements TypeWagonAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeWagon update(TypeWagon t) {
        TypeWagon loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeWagon findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeWagon findOrSave(TypeWagon t) {
        if (t != null) {
            TypeWagon result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeWagon> findAll() {
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
    public List<TypeWagon> delete(List<TypeWagon> list) {
		List<TypeWagon> result = new ArrayList();
        if (list != null) {
            for (TypeWagon t : list) {
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
    public TypeWagon create(TypeWagon t) {
        TypeWagon loaded = findByReferenceEntity(t);
        TypeWagon saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeWagon findWithAssociatedLists(Long id){
        TypeWagon result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeWagon> update(List<TypeWagon> ts, boolean createIfNotExist) {
        List<TypeWagon> result = new ArrayList<>();
        if (ts != null) {
            for (TypeWagon t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeWagon loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeWagon t, TypeWagon loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeWagon findByReferenceEntity(TypeWagon t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeWagon> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeWagon>> getToBeSavedAndToBeDeleted(List<TypeWagon> oldList, List<TypeWagon> newList) {
        List<List<TypeWagon>> result = new ArrayList<>();
        List<TypeWagon> resultDelete = new ArrayList<>();
        List<TypeWagon> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeWagon> oldList, List<TypeWagon> newList, List<TypeWagon> resultUpdateOrSave, List<TypeWagon> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeWagon myOld = oldList.get(i);
                TypeWagon t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeWagon myNew = newList.get(i);
                TypeWagon t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeWagonAdminServiceImpl(TypeWagonRepository repository) {
        this.repository = repository;
    }

    private TypeWagonRepository repository;
}
