package ma.zyn.app.service.impl.admin.navire;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.repository.facade.core.navire.RealisationNavireQualiteRepository;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireQualiteAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireAdminService ;
import ma.zyn.app.bean.core.navire.RealisationNavire ;

import java.util.List;
@Service
public class RealisationNavireQualiteAdminServiceImpl implements RealisationNavireQualiteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationNavireQualite update(RealisationNavireQualite t) {
        RealisationNavireQualite loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationNavireQualite findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationNavireQualite findOrSave(RealisationNavireQualite t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationNavireQualite result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationNavireQualite> findAll() {
        return repository.findAll();
    }


    public List<RealisationNavireQualite> findByProduitMarchandCode(String code){
        return repository.findByProduitMarchandCode(code);
    }
    public List<RealisationNavireQualite> findByProduitMarchandId(Long id){
        return repository.findByProduitMarchandId(id);
    }
    public int deleteByProduitMarchandCode(String code){
        return repository.deleteByProduitMarchandCode(code);
    }
    public int deleteByProduitMarchandId(Long id){
        return repository.deleteByProduitMarchandId(id);
    }
    public long countByProduitMarchandCode(String code){
        return repository.countByProduitMarchandCode(code);
    }
    public List<RealisationNavireQualite> findByRealisationNavireId(Long id){
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
    public List<RealisationNavireQualite> delete(List<RealisationNavireQualite> list) {
		List<RealisationNavireQualite> result = new ArrayList();
        if (list != null) {
            for (RealisationNavireQualite t : list) {
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
    public RealisationNavireQualite create(RealisationNavireQualite t) {
        RealisationNavireQualite loaded = findByReferenceEntity(t);
        RealisationNavireQualite saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationNavireQualite findWithAssociatedLists(Long id){
        RealisationNavireQualite result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationNavireQualite> update(List<RealisationNavireQualite> ts, boolean createIfNotExist) {
        List<RealisationNavireQualite> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationNavireQualite t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationNavireQualite loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationNavireQualite t, RealisationNavireQualite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public RealisationNavireQualite findByReferenceEntity(RealisationNavireQualite t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationNavireQualite t){
        if( t != null) {
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
            t.setRealisationNavire(realisationNavireService.findOrSave(t.getRealisationNavire()));
        }
    }



    public List<RealisationNavireQualite> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<RealisationNavireQualite>> getToBeSavedAndToBeDeleted(List<RealisationNavireQualite> oldList, List<RealisationNavireQualite> newList) {
        List<List<RealisationNavireQualite>> result = new ArrayList<>();
        List<RealisationNavireQualite> resultDelete = new ArrayList<>();
        List<RealisationNavireQualite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationNavireQualite> oldList, List<RealisationNavireQualite> newList, List<RealisationNavireQualite> resultUpdateOrSave, List<RealisationNavireQualite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationNavireQualite myOld = oldList.get(i);
                RealisationNavireQualite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationNavireQualite myNew = newList.get(i);
                RealisationNavireQualite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ProduitMarchandAdminService produitMarchandService ;
    private RealisationNavireAdminService realisationNavireService ;

    public RealisationNavireQualiteAdminServiceImpl(RealisationNavireQualiteRepository repository, ProduitMarchandAdminService produitMarchandService, RealisationNavireAdminService realisationNavireService) {
        this.repository = repository;
        this.produitMarchandService = produitMarchandService;
        this.realisationNavireService = realisationNavireService;
    }

    private RealisationNavireQualiteRepository repository;
}
