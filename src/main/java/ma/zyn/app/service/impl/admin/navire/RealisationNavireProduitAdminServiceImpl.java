package ma.zyn.app.service.impl.admin.navire;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.repository.facade.core.navire.RealisationNavireProduitRepository;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireProduitAdminService;

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

import ma.zyn.app.service.facade.admin.navire.RealisationNavireAdminService ;
import ma.zyn.app.bean.core.navire.RealisationNavire ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class RealisationNavireProduitAdminServiceImpl implements RealisationNavireProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationNavireProduit update(RealisationNavireProduit t) {
        RealisationNavireProduit loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationNavireProduit findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationNavireProduit findOrSave(RealisationNavireProduit t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationNavireProduit result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationNavireProduit> findAll() {
        return repository.findAll();
    }


    public List<RealisationNavireProduit> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
    }
    public List<RealisationNavireProduit> findByRealisationNavireId(Long id){
        return repository.findByRealisationNavireId(id);
    }
    public int deleteByRealisationNavireId(Long id){
        return repository.deleteByRealisationNavireId(id);
    }
    public long countByRealisationNavireId(Long id){
        return repository.countByRealisationNavireId(id);
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
    public List<RealisationNavireProduit> delete(List<RealisationNavireProduit> list) {
		List<RealisationNavireProduit> result = new ArrayList();
        if (list != null) {
            for (RealisationNavireProduit t : list) {
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
    public RealisationNavireProduit create(RealisationNavireProduit t) {
        RealisationNavireProduit loaded = findByReferenceEntity(t);
        RealisationNavireProduit saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationNavireProduit findWithAssociatedLists(Long id){
        RealisationNavireProduit result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationNavireProduit> update(List<RealisationNavireProduit> ts, boolean createIfNotExist) {
        List<RealisationNavireProduit> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationNavireProduit t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationNavireProduit loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationNavireProduit t, RealisationNavireProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RealisationNavireProduit findByReferenceEntity(RealisationNavireProduit t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationNavireProduit t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setRealisationNavire(realisationNavireService.findOrSave(t.getRealisationNavire()));
        }
    }



    public List<RealisationNavireProduit> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<RealisationNavireProduit>> getToBeSavedAndToBeDeleted(List<RealisationNavireProduit> oldList, List<RealisationNavireProduit> newList) {
        List<List<RealisationNavireProduit>> result = new ArrayList<>();
        List<RealisationNavireProduit> resultDelete = new ArrayList<>();
        List<RealisationNavireProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationNavireProduit> oldList, List<RealisationNavireProduit> newList, List<RealisationNavireProduit> resultUpdateOrSave, List<RealisationNavireProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationNavireProduit myOld = oldList.get(i);
                RealisationNavireProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationNavireProduit myNew = newList.get(i);
                RealisationNavireProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private RealisationNavireAdminService realisationNavireService ;
    private ProduitAdminService produitService ;

    public RealisationNavireProduitAdminServiceImpl(RealisationNavireProduitRepository repository, RealisationNavireAdminService realisationNavireService, ProduitAdminService produitService) {
        this.repository = repository;
        this.realisationNavireService = realisationNavireService;
        this.produitService = produitService;
    }

    private RealisationNavireProduitRepository repository;
}
