package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.repository.facade.core.referentiel.TypeClientRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService;

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
public class TypeClientAdminServiceImpl implements TypeClientAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeClient update(TypeClient t) {
        TypeClient loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeClient findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeClient findOrSave(TypeClient t) {
        if (t != null) {
            TypeClient result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeClient> findAll() {
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
    public List<TypeClient> delete(List<TypeClient> list) {
		List<TypeClient> result = new ArrayList();
        if (list != null) {
            for (TypeClient t : list) {
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
    public TypeClient create(TypeClient t) {
        TypeClient loaded = findByReferenceEntity(t);
        TypeClient saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeClient findWithAssociatedLists(Long id){
        TypeClient result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeClient> update(List<TypeClient> ts, boolean createIfNotExist) {
        List<TypeClient> result = new ArrayList<>();
        if (ts != null) {
            for (TypeClient t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeClient loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeClient t, TypeClient loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeClient findByReferenceEntity(TypeClient t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeClient> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeClient>> getToBeSavedAndToBeDeleted(List<TypeClient> oldList, List<TypeClient> newList) {
        List<List<TypeClient>> result = new ArrayList<>();
        List<TypeClient> resultDelete = new ArrayList<>();
        List<TypeClient> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeClient> oldList, List<TypeClient> newList, List<TypeClient> resultUpdateOrSave, List<TypeClient> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeClient myOld = oldList.get(i);
                TypeClient t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeClient myNew = newList.get(i);
                TypeClient t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeClientAdminServiceImpl(TypeClientRepository repository) {
        this.repository = repository;
    }

    private TypeClientRepository repository;
}
