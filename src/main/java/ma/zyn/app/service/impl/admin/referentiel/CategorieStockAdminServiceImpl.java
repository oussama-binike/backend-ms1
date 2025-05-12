package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CategorieStock;
import ma.zyn.app.repository.facade.core.referentiel.CategorieStockRepository;
import ma.zyn.app.service.facade.admin.referentiel.CategorieStockAdminService;

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
public class CategorieStockAdminServiceImpl implements CategorieStockAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieStock update(CategorieStock t) {
        CategorieStock loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CategorieStock findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CategorieStock findOrSave(CategorieStock t) {
        if (t != null) {
            CategorieStock result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CategorieStock> findAll() {
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
    public List<CategorieStock> delete(List<CategorieStock> list) {
		List<CategorieStock> result = new ArrayList();
        if (list != null) {
            for (CategorieStock t : list) {
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
    public CategorieStock create(CategorieStock t) {
        CategorieStock loaded = findByReferenceEntity(t);
        CategorieStock saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CategorieStock findWithAssociatedLists(Long id){
        CategorieStock result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieStock> update(List<CategorieStock> ts, boolean createIfNotExist) {
        List<CategorieStock> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieStock t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CategorieStock loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CategorieStock t, CategorieStock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CategorieStock findByReferenceEntity(CategorieStock t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<CategorieStock> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CategorieStock>> getToBeSavedAndToBeDeleted(List<CategorieStock> oldList, List<CategorieStock> newList) {
        List<List<CategorieStock>> result = new ArrayList<>();
        List<CategorieStock> resultDelete = new ArrayList<>();
        List<CategorieStock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CategorieStock> oldList, List<CategorieStock> newList, List<CategorieStock> resultUpdateOrSave, List<CategorieStock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CategorieStock myOld = oldList.get(i);
                CategorieStock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CategorieStock myNew = newList.get(i);
                CategorieStock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public CategorieStockAdminServiceImpl(CategorieStockRepository repository) {
        this.repository = repository;
    }

    private CategorieStockRepository repository;
}
