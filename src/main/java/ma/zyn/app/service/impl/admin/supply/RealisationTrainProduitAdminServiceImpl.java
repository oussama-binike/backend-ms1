package ma.zyn.app.service.impl.admin.supply;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.repository.facade.core.supply.RealisationTrainProduitRepository;
import ma.zyn.app.service.facade.admin.supply.RealisationTrainProduitAdminService;

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

import ma.zyn.app.service.facade.admin.supply.RealisationTrainAdminService ;
import ma.zyn.app.bean.core.supply.RealisationTrain ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class RealisationTrainProduitAdminServiceImpl implements RealisationTrainProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationTrainProduit update(RealisationTrainProduit t) {
        RealisationTrainProduit loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationTrainProduit findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationTrainProduit findOrSave(RealisationTrainProduit t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationTrainProduit result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationTrainProduit> findAll() {
        return repository.findAll();
    }


    public List<RealisationTrainProduit> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
    }
    public List<RealisationTrainProduit> findByRealisationTrainId(Long id){
        return repository.findByRealisationTrainId(id);
    }
    public int deleteByRealisationTrainId(Long id){
        return repository.deleteByRealisationTrainId(id);
    }
    public long countByRealisationTrainCode(String code){
        return repository.countByRealisationTrainCode(code);
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
    public List<RealisationTrainProduit> delete(List<RealisationTrainProduit> list) {
		List<RealisationTrainProduit> result = new ArrayList();
        if (list != null) {
            for (RealisationTrainProduit t : list) {
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
    public RealisationTrainProduit create(RealisationTrainProduit t) {
        RealisationTrainProduit loaded = findByReferenceEntity(t);
        RealisationTrainProduit saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationTrainProduit findWithAssociatedLists(Long id){
        RealisationTrainProduit result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationTrainProduit> update(List<RealisationTrainProduit> ts, boolean createIfNotExist) {
        List<RealisationTrainProduit> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationTrainProduit t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationTrainProduit loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationTrainProduit t, RealisationTrainProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RealisationTrainProduit findByReferenceEntity(RealisationTrainProduit t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationTrainProduit t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setRealisationTrain(realisationTrainService.findOrSave(t.getRealisationTrain()));
        }
    }



    public List<RealisationTrainProduit> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<RealisationTrainProduit>> getToBeSavedAndToBeDeleted(List<RealisationTrainProduit> oldList, List<RealisationTrainProduit> newList) {
        List<List<RealisationTrainProduit>> result = new ArrayList<>();
        List<RealisationTrainProduit> resultDelete = new ArrayList<>();
        List<RealisationTrainProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationTrainProduit> oldList, List<RealisationTrainProduit> newList, List<RealisationTrainProduit> resultUpdateOrSave, List<RealisationTrainProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationTrainProduit myOld = oldList.get(i);
                RealisationTrainProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationTrainProduit myNew = newList.get(i);
                RealisationTrainProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private RealisationTrainAdminService realisationTrainService ;
    private ProduitAdminService produitService ;

    public RealisationTrainProduitAdminServiceImpl(RealisationTrainProduitRepository repository, RealisationTrainAdminService realisationTrainService, ProduitAdminService produitService) {
        this.repository = repository;
        this.realisationTrainService = realisationTrainService;
        this.produitService = produitService;
    }

    private RealisationTrainProduitRepository repository;
}
