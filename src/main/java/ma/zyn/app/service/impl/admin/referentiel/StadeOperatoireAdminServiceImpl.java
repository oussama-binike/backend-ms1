package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.repository.facade.core.referentiel.StadeOperatoireRepository;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit ;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Entite ;

import java.util.List;
@Service
public class StadeOperatoireAdminServiceImpl implements StadeOperatoireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StadeOperatoire update(StadeOperatoire t) {
        StadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public StadeOperatoire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public StadeOperatoire findOrSave(StadeOperatoire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StadeOperatoire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StadeOperatoire> findAll() {
        return repository.findAll();
    }


    public List<StadeOperatoire> findByEntiteCode(String code){
        return repository.findByEntiteCode(code);
    }
    public List<StadeOperatoire> findByEntiteId(Long id){
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            repository.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        stadeOperatoireProduitService.deleteByStadeOperatoireId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StadeOperatoire> delete(List<StadeOperatoire> list) {
		List<StadeOperatoire> result = new ArrayList();
        if (list != null) {
            for (StadeOperatoire t : list) {
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
    public StadeOperatoire create(StadeOperatoire t) {
        StadeOperatoire loaded = findByReferenceEntity(t);
        StadeOperatoire saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getStadeOperatoireProduits() != null) {
                t.getStadeOperatoireProduits().forEach(element-> {
                    element.setStadeOperatoire(saved);
                    stadeOperatoireProduitService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public StadeOperatoire findWithAssociatedLists(Long id){
        StadeOperatoire result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setStadeOperatoireProduits(stadeOperatoireProduitService.findByStadeOperatoireId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StadeOperatoire> update(List<StadeOperatoire> ts, boolean createIfNotExist) {
        List<StadeOperatoire> result = new ArrayList<>();
        if (ts != null) {
            for (StadeOperatoire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    StadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StadeOperatoire t, StadeOperatoire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(StadeOperatoire stadeOperatoire){
    if(stadeOperatoire !=null && stadeOperatoire.getId() != null){
        List<List<StadeOperatoireProduit>> resultStadeOperatoireProduits= stadeOperatoireProduitService.getToBeSavedAndToBeDeleted(stadeOperatoireProduitService.findByStadeOperatoireId(stadeOperatoire.getId()),stadeOperatoire.getStadeOperatoireProduits());
            stadeOperatoireProduitService.delete(resultStadeOperatoireProduits.get(1));
        emptyIfNull(resultStadeOperatoireProduits.get(0)).forEach(e -> e.setStadeOperatoire(stadeOperatoire));
        stadeOperatoireProduitService.update(resultStadeOperatoireProduits.get(0),true);
        }
    }








    public StadeOperatoire findByReferenceEntity(StadeOperatoire t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(StadeOperatoire t){
        if( t != null) {
            t.setEntite(entiteService.findOrSave(t.getEntite()));
        }
    }



    public List<StadeOperatoire> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<StadeOperatoire>> getToBeSavedAndToBeDeleted(List<StadeOperatoire> oldList, List<StadeOperatoire> newList) {
        List<List<StadeOperatoire>> result = new ArrayList<>();
        List<StadeOperatoire> resultDelete = new ArrayList<>();
        List<StadeOperatoire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StadeOperatoire> oldList, List<StadeOperatoire> newList, List<StadeOperatoire> resultUpdateOrSave, List<StadeOperatoire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StadeOperatoire myOld = oldList.get(i);
                StadeOperatoire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StadeOperatoire myNew = newList.get(i);
                StadeOperatoire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireProduitAdminService stadeOperatoireProduitService ;
    private EntiteAdminService entiteService ;

    public StadeOperatoireAdminServiceImpl(StadeOperatoireRepository repository, StadeOperatoireProduitAdminService stadeOperatoireProduitService, EntiteAdminService entiteService) {
        this.repository = repository;
        this.stadeOperatoireProduitService = stadeOperatoireProduitService;
        this.entiteService = entiteService;
    }

    private StadeOperatoireRepository repository;
}
