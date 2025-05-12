package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeStock;
import ma.zyn.app.repository.facade.core.referentiel.TypeStockRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeStockAdminService;

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
public class TypeStockAdminServiceImpl implements TypeStockAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeStock update(TypeStock t) {
        TypeStock loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeStock findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeStock findOrSave(TypeStock t) {
        if (t != null) {
            TypeStock result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeStock> findAll() {
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
    public List<TypeStock> delete(List<TypeStock> list) {
		List<TypeStock> result = new ArrayList();
        if (list != null) {
            for (TypeStock t : list) {
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
    public TypeStock create(TypeStock t) {
        TypeStock loaded = findByReferenceEntity(t);
        TypeStock saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeStock findWithAssociatedLists(Long id){
        TypeStock result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeStock> update(List<TypeStock> ts, boolean createIfNotExist) {
        List<TypeStock> result = new ArrayList<>();
        if (ts != null) {
            for (TypeStock t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeStock loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeStock t, TypeStock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeStock findByReferenceEntity(TypeStock t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeStock> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeStock>> getToBeSavedAndToBeDeleted(List<TypeStock> oldList, List<TypeStock> newList) {
        List<List<TypeStock>> result = new ArrayList<>();
        List<TypeStock> resultDelete = new ArrayList<>();
        List<TypeStock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeStock> oldList, List<TypeStock> newList, List<TypeStock> resultUpdateOrSave, List<TypeStock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeStock myOld = oldList.get(i);
                TypeStock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeStock myNew = newList.get(i);
                TypeStock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeStockAdminServiceImpl(TypeStockRepository repository) {
        this.repository = repository;
    }

    private TypeStockRepository repository;
}
