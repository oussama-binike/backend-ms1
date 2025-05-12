package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Produit;
import ma.zyn.app.repository.facade.core.referentiel.ProduitRepository;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService;

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
public class ProduitAdminServiceImpl implements ProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Produit update(Produit t) {
        Produit loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Produit findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Produit findOrSave(Produit t) {
        if (t != null) {
            Produit result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Produit> findAll() {
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
    public List<Produit> delete(List<Produit> list) {
		List<Produit> result = new ArrayList();
        if (list != null) {
            for (Produit t : list) {
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
    public Produit create(Produit t) {
        Produit loaded = findByReferenceEntity(t);
        Produit saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Produit findWithAssociatedLists(Long id){
        Produit result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Produit> update(List<Produit> ts, boolean createIfNotExist) {
        List<Produit> result = new ArrayList<>();
        if (ts != null) {
            for (Produit t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Produit loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Produit t, Produit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Produit findByReferenceEntity(Produit t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<Produit> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Produit>> getToBeSavedAndToBeDeleted(List<Produit> oldList, List<Produit> newList) {
        List<List<Produit>> result = new ArrayList<>();
        List<Produit> resultDelete = new ArrayList<>();
        List<Produit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Produit> oldList, List<Produit> newList, List<Produit> resultUpdateOrSave, List<Produit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Produit myOld = oldList.get(i);
                Produit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Produit myNew = newList.get(i);
                Produit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ProduitAdminServiceImpl(ProduitRepository repository) {
        this.repository = repository;
    }

    private ProduitRepository repository;
}
