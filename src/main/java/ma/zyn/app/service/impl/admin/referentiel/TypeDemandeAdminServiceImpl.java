package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.repository.facade.core.referentiel.TypeDemandeRepository;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;

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
public class TypeDemandeAdminServiceImpl implements TypeDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeDemande update(TypeDemande t) {
        TypeDemande loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public TypeDemande findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public TypeDemande findOrSave(TypeDemande t) {
        if (t != null) {
            TypeDemande result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeDemande> findAll() {
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
    public List<TypeDemande> delete(List<TypeDemande> list) {
		List<TypeDemande> result = new ArrayList();
        if (list != null) {
            for (TypeDemande t : list) {
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
    public TypeDemande create(TypeDemande t) {
        TypeDemande loaded = findByReferenceEntity(t);
        TypeDemande saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeDemande findWithAssociatedLists(Long id){
        TypeDemande result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeDemande> update(List<TypeDemande> ts, boolean createIfNotExist) {
        List<TypeDemande> result = new ArrayList<>();
        if (ts != null) {
            for (TypeDemande t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    TypeDemande loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeDemande t, TypeDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeDemande findByReferenceEntity(TypeDemande t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<TypeDemande> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<TypeDemande>> getToBeSavedAndToBeDeleted(List<TypeDemande> oldList, List<TypeDemande> newList) {
        List<List<TypeDemande>> result = new ArrayList<>();
        List<TypeDemande> resultDelete = new ArrayList<>();
        List<TypeDemande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeDemande> oldList, List<TypeDemande> newList, List<TypeDemande> resultUpdateOrSave, List<TypeDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeDemande myOld = oldList.get(i);
                TypeDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeDemande myNew = newList.get(i);
                TypeDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public TypeDemandeAdminServiceImpl(TypeDemandeRepository repository) {
        this.repository = repository;
    }

    private TypeDemandeRepository repository;
}
