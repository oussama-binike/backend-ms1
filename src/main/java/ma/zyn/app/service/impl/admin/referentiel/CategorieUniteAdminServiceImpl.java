package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import ma.zyn.app.repository.facade.core.referentiel.CategorieUniteRepository;
import ma.zyn.app.service.facade.admin.referentiel.CategorieUniteAdminService;

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
public class CategorieUniteAdminServiceImpl implements CategorieUniteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieUnite update(CategorieUnite t) {
        CategorieUnite loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CategorieUnite findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CategorieUnite findOrSave(CategorieUnite t) {
        if (t != null) {
            CategorieUnite result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CategorieUnite> findAll() {
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
    public List<CategorieUnite> delete(List<CategorieUnite> list) {
		List<CategorieUnite> result = new ArrayList();
        if (list != null) {
            for (CategorieUnite t : list) {
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
    public CategorieUnite create(CategorieUnite t) {
        CategorieUnite loaded = findByReferenceEntity(t);
        CategorieUnite saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CategorieUnite findWithAssociatedLists(Long id){
        CategorieUnite result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieUnite> update(List<CategorieUnite> ts, boolean createIfNotExist) {
        List<CategorieUnite> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieUnite t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CategorieUnite loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CategorieUnite t, CategorieUnite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CategorieUnite findByReferenceEntity(CategorieUnite t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<CategorieUnite> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CategorieUnite>> getToBeSavedAndToBeDeleted(List<CategorieUnite> oldList, List<CategorieUnite> newList) {
        List<List<CategorieUnite>> result = new ArrayList<>();
        List<CategorieUnite> resultDelete = new ArrayList<>();
        List<CategorieUnite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CategorieUnite> oldList, List<CategorieUnite> newList, List<CategorieUnite> resultUpdateOrSave, List<CategorieUnite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CategorieUnite myOld = oldList.get(i);
                CategorieUnite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CategorieUnite myNew = newList.get(i);
                CategorieUnite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public CategorieUniteAdminServiceImpl(CategorieUniteRepository repository) {
        this.repository = repository;
    }

    private CategorieUniteRepository repository;
}
