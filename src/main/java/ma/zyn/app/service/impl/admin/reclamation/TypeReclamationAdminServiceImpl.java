package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.TypeReclamation;
import ma.zyn.app.repository.facade.core.reclamation.TypeReclamationRepository;
import ma.zyn.app.service.facade.admin.reclamation.TypeReclamationAdminService;

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
public class TypeReclamationAdminServiceImpl implements TypeReclamationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeReclamation update(TypeReclamation t) {
        TypeReclamation loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeReclamation findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeReclamation findOrSave(TypeReclamation t) {
        if (t != null) {
            TypeReclamation result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeReclamation> findAll() {
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
    public List<TypeReclamation> delete(List<TypeReclamation> list) {
		List<TypeReclamation> result = new ArrayList();
        if (list != null) {
            for (TypeReclamation t : list) {
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
    public TypeReclamation create(TypeReclamation t) {
        TypeReclamation loaded = findByReferenceEntity(t);
        TypeReclamation saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeReclamation findWithAssociatedLists(Long id){
        TypeReclamation result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeReclamation> update(List<TypeReclamation> ts, boolean createIfNotExist) {
        List<TypeReclamation> result = new ArrayList<>();
        if (ts != null) {
            for (TypeReclamation t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeReclamation loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeReclamation t, TypeReclamation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeReclamation findByReferenceEntity(TypeReclamation t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeReclamation> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeReclamation>> getToBeSavedAndToBeDeleted(List<TypeReclamation> oldList, List<TypeReclamation> newList) {
        List<List<TypeReclamation>> result = new ArrayList<>();
        List<TypeReclamation> resultDelete = new ArrayList<>();
        List<TypeReclamation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeReclamation> oldList, List<TypeReclamation> newList, List<TypeReclamation> resultUpdateOrSave, List<TypeReclamation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeReclamation myOld = oldList.get(i);
                TypeReclamation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeReclamation myNew = newList.get(i);
                TypeReclamation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeReclamationAdminServiceImpl(TypeReclamationRepository repository) {
        this.repository = repository;
    }

    private TypeReclamationRepository repository;
}
