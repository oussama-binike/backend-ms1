package ma.zyn.app.service.impl.admin.camion;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.repository.facade.core.camion.RealisationCamionProduitRepository;
import ma.zyn.app.service.facade.admin.camion.RealisationCamionProduitAdminService;

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

import ma.zyn.app.service.facade.admin.camion.RealisationCamionAdminService ;
import ma.zyn.app.bean.core.camion.RealisationCamion ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class RealisationCamionProduitAdminServiceImpl implements RealisationCamionProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationCamionProduit update(RealisationCamionProduit t) {
        RealisationCamionProduit loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationCamionProduit findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationCamionProduit findOrSave(RealisationCamionProduit t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationCamionProduit result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationCamionProduit> findAll() {
        return repository.findAll();
    }


    public List<RealisationCamionProduit> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
    }
    public List<RealisationCamionProduit> findByRealisationCamionId(Long id){
        return repository.findByRealisationCamionId(id);
    }
    public int deleteByRealisationCamionId(Long id){
        return repository.deleteByRealisationCamionId(id);
    }
    public long countByRealisationCamionId(Long id){
        return repository.countByRealisationCamionId(id);
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
    public List<RealisationCamionProduit> delete(List<RealisationCamionProduit> list) {
		List<RealisationCamionProduit> result = new ArrayList();
        if (list != null) {
            for (RealisationCamionProduit t : list) {
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
    public RealisationCamionProduit create(RealisationCamionProduit t) {
        RealisationCamionProduit loaded = findByReferenceEntity(t);
        RealisationCamionProduit saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationCamionProduit findWithAssociatedLists(Long id){
        RealisationCamionProduit result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationCamionProduit> update(List<RealisationCamionProduit> ts, boolean createIfNotExist) {
        List<RealisationCamionProduit> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationCamionProduit t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationCamionProduit loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationCamionProduit t, RealisationCamionProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RealisationCamionProduit findByReferenceEntity(RealisationCamionProduit t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationCamionProduit t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setRealisationCamion(realisationCamionService.findOrSave(t.getRealisationCamion()));
        }
    }



    public List<RealisationCamionProduit> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<RealisationCamionProduit>> getToBeSavedAndToBeDeleted(List<RealisationCamionProduit> oldList, List<RealisationCamionProduit> newList) {
        List<List<RealisationCamionProduit>> result = new ArrayList<>();
        List<RealisationCamionProduit> resultDelete = new ArrayList<>();
        List<RealisationCamionProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationCamionProduit> oldList, List<RealisationCamionProduit> newList, List<RealisationCamionProduit> resultUpdateOrSave, List<RealisationCamionProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationCamionProduit myOld = oldList.get(i);
                RealisationCamionProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationCamionProduit myNew = newList.get(i);
                RealisationCamionProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private RealisationCamionAdminService realisationCamionService ;
    private ProduitAdminService produitService ;

    public RealisationCamionProduitAdminServiceImpl(RealisationCamionProduitRepository repository, RealisationCamionAdminService realisationCamionService, ProduitAdminService produitService) {
        this.repository = repository;
        this.realisationCamionService = realisationCamionService;
        this.produitService = produitService;
    }

    private RealisationCamionProduitRepository repository;
}
