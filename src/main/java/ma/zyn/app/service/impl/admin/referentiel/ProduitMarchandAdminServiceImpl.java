package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.repository.facade.core.referentiel.ProduitMarchandRepository;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService;

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
public class ProduitMarchandAdminServiceImpl implements ProduitMarchandAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProduitMarchand update(ProduitMarchand t) {
        ProduitMarchand loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ProduitMarchand findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ProduitMarchand findOrSave(ProduitMarchand t) {
        if (t != null) {
            ProduitMarchand result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProduitMarchand> findAll() {
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
    public List<ProduitMarchand> delete(List<ProduitMarchand> list) {
		List<ProduitMarchand> result = new ArrayList();
        if (list != null) {
            for (ProduitMarchand t : list) {
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
    public ProduitMarchand create(ProduitMarchand t) {
        ProduitMarchand loaded = findByReferenceEntity(t);
        ProduitMarchand saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProduitMarchand findWithAssociatedLists(Long id){
        ProduitMarchand result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProduitMarchand> update(List<ProduitMarchand> ts, boolean createIfNotExist) {
        List<ProduitMarchand> result = new ArrayList<>();
        if (ts != null) {
            for (ProduitMarchand t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ProduitMarchand loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProduitMarchand t, ProduitMarchand loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProduitMarchand findByReferenceEntity(ProduitMarchand t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<ProduitMarchand> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ProduitMarchand>> getToBeSavedAndToBeDeleted(List<ProduitMarchand> oldList, List<ProduitMarchand> newList) {
        List<List<ProduitMarchand>> result = new ArrayList<>();
        List<ProduitMarchand> resultDelete = new ArrayList<>();
        List<ProduitMarchand> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProduitMarchand> oldList, List<ProduitMarchand> newList, List<ProduitMarchand> resultUpdateOrSave, List<ProduitMarchand> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProduitMarchand myOld = oldList.get(i);
                ProduitMarchand t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProduitMarchand myNew = newList.get(i);
                ProduitMarchand t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ProduitMarchandAdminServiceImpl(ProduitMarchandRepository repository) {
        this.repository = repository;
    }

    private ProduitMarchandRepository repository;
}
