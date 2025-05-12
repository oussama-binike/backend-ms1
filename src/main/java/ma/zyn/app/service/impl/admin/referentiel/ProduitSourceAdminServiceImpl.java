package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.repository.facade.core.referentiel.ProduitSourceRepository;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;

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
public class ProduitSourceAdminServiceImpl implements ProduitSourceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProduitSource update(ProduitSource t) {
        ProduitSource loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ProduitSource findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ProduitSource findOrSave(ProduitSource t) {
        if (t != null) {
            ProduitSource result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProduitSource> findAll() {
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
    public List<ProduitSource> delete(List<ProduitSource> list) {
		List<ProduitSource> result = new ArrayList();
        if (list != null) {
            for (ProduitSource t : list) {
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
    public ProduitSource create(ProduitSource t) {
        ProduitSource loaded = findByReferenceEntity(t);
        ProduitSource saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProduitSource findWithAssociatedLists(Long id){
        ProduitSource result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProduitSource> update(List<ProduitSource> ts, boolean createIfNotExist) {
        List<ProduitSource> result = new ArrayList<>();
        if (ts != null) {
            for (ProduitSource t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ProduitSource loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProduitSource t, ProduitSource loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProduitSource findByReferenceEntity(ProduitSource t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<ProduitSource> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ProduitSource>> getToBeSavedAndToBeDeleted(List<ProduitSource> oldList, List<ProduitSource> newList) {
        List<List<ProduitSource>> result = new ArrayList<>();
        List<ProduitSource> resultDelete = new ArrayList<>();
        List<ProduitSource> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProduitSource> oldList, List<ProduitSource> newList, List<ProduitSource> resultUpdateOrSave, List<ProduitSource> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProduitSource myOld = oldList.get(i);
                ProduitSource t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProduitSource myNew = newList.get(i);
                ProduitSource t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ProduitSourceAdminServiceImpl(ProduitSourceRepository repository) {
        this.repository = repository;
    }

    private ProduitSourceRepository repository;
}
