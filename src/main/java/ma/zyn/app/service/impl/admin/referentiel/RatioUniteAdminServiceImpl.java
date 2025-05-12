package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.repository.facade.core.referentiel.RatioUniteRepository;
import ma.zyn.app.service.facade.admin.referentiel.RatioUniteAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Entite ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class RatioUniteAdminServiceImpl implements RatioUniteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RatioUnite update(RatioUnite t) {
        RatioUnite loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public RatioUnite findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RatioUnite findOrSave(RatioUnite t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RatioUnite result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RatioUnite> findAll() {
        return repository.findAll();
    }


    public List<RatioUnite> findByEntiteCode(String code){
        return repository.findByEntiteCode(code);
    }
    public List<RatioUnite> findByEntiteId(Long id){
        return repository.findByEntiteId(id);
    }
    public int deleteByEntiteCode(String code){
        return repository.deleteByEntiteCode(code);
    }
    public int deleteByEntiteId(Long id){
        return repository.deleteByEntiteId(id);
    }
    public long countByEntiteCode(String code){
        return repository.countByEntiteCode(code);
    }
    public List<RatioUnite> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
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
    public List<RatioUnite> delete(List<RatioUnite> list) {
		List<RatioUnite> result = new ArrayList();
        if (list != null) {
            for (RatioUnite t : list) {
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
    public RatioUnite create(RatioUnite t) {
        RatioUnite loaded = findByReferenceEntity(t);
        RatioUnite saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RatioUnite findWithAssociatedLists(Long id){
        RatioUnite result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RatioUnite> update(List<RatioUnite> ts, boolean createIfNotExist) {
        List<RatioUnite> result = new ArrayList<>();
        if (ts != null) {
            for (RatioUnite t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RatioUnite loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RatioUnite t, RatioUnite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RatioUnite findByReferenceEntity(RatioUnite t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RatioUnite t){
        if( t != null) {
            t.setEntite(entiteService.findOrSave(t.getEntite()));
            t.setProduit(produitService.findOrSave(t.getProduit()));
        }
    }



    public List<RatioUnite> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<RatioUnite>> getToBeSavedAndToBeDeleted(List<RatioUnite> oldList, List<RatioUnite> newList) {
        List<List<RatioUnite>> result = new ArrayList<>();
        List<RatioUnite> resultDelete = new ArrayList<>();
        List<RatioUnite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RatioUnite> oldList, List<RatioUnite> newList, List<RatioUnite> resultUpdateOrSave, List<RatioUnite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RatioUnite myOld = oldList.get(i);
                RatioUnite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RatioUnite myNew = newList.get(i);
                RatioUnite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private EntiteAdminService entiteService ;
    private ProduitAdminService produitService ;

    public RatioUniteAdminServiceImpl(RatioUniteRepository repository, EntiteAdminService entiteService, ProduitAdminService produitService) {
        this.repository = repository;
        this.entiteService = entiteService;
        this.produitService = produitService;
    }

    private RatioUniteRepository repository;
}
