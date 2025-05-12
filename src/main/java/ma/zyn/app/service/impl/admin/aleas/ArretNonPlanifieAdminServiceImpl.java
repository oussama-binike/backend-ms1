package ma.zyn.app.service.impl.admin.aleas;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import ma.zyn.app.repository.facade.core.aleas.ArretNonPlanifieRepository;
import ma.zyn.app.service.facade.admin.aleas.ArretNonPlanifieAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.reclamation.ActionEntrepriseAdminService ;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise ;
import ma.zyn.app.service.facade.admin.aleas.CauseArretAdminService ;
import ma.zyn.app.bean.core.aleas.CauseArret ;

import java.util.List;
@Service
public class ArretNonPlanifieAdminServiceImpl implements ArretNonPlanifieAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ArretNonPlanifie update(ArretNonPlanifie t) {
        ArretNonPlanifie loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ArretNonPlanifie findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ArretNonPlanifie findOrSave(ArretNonPlanifie t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ArretNonPlanifie result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ArretNonPlanifie> findAll() {
        return repository.findAll();
    }


    public List<ArretNonPlanifie> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<ArretNonPlanifie> findByStadeOperatoireId(Long id){
        return repository.findByStadeOperatoireId(id);
    }
    public int deleteByStadeOperatoireCode(String code){
        return repository.deleteByStadeOperatoireCode(code);
    }
    public int deleteByStadeOperatoireId(Long id){
        return repository.deleteByStadeOperatoireId(id);
    }
    public long countByStadeOperatoireCode(String code){
        return repository.countByStadeOperatoireCode(code);
    }
    public List<ArretNonPlanifie> findByCauseArretCode(String code){
        return repository.findByCauseArretCode(code);
    }
    public List<ArretNonPlanifie> findByCauseArretId(Long id){
        return repository.findByCauseArretId(id);
    }
    public int deleteByCauseArretCode(String code){
        return repository.deleteByCauseArretCode(code);
    }
    public int deleteByCauseArretId(Long id){
        return repository.deleteByCauseArretId(id);
    }
    public long countByCauseArretCode(String code){
        return repository.countByCauseArretCode(code);
    }
    public List<ArretNonPlanifie> findByActionEntrepriseCode(String code){
        return repository.findByActionEntrepriseCode(code);
    }
    public List<ArretNonPlanifie> findByActionEntrepriseId(Long id){
        return repository.findByActionEntrepriseId(id);
    }
    public int deleteByActionEntrepriseCode(String code){
        return repository.deleteByActionEntrepriseCode(code);
    }
    public int deleteByActionEntrepriseId(Long id){
        return repository.deleteByActionEntrepriseId(id);
    }
    public long countByActionEntrepriseCode(String code){
        return repository.countByActionEntrepriseCode(code);
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
    public List<ArretNonPlanifie> delete(List<ArretNonPlanifie> list) {
		List<ArretNonPlanifie> result = new ArrayList();
        if (list != null) {
            for (ArretNonPlanifie t : list) {
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
    public ArretNonPlanifie create(ArretNonPlanifie t) {
        ArretNonPlanifie loaded = findByReferenceEntity(t);
        ArretNonPlanifie saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ArretNonPlanifie findWithAssociatedLists(Long id){
        ArretNonPlanifie result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ArretNonPlanifie> update(List<ArretNonPlanifie> ts, boolean createIfNotExist) {
        List<ArretNonPlanifie> result = new ArrayList<>();
        if (ts != null) {
            for (ArretNonPlanifie t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ArretNonPlanifie loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ArretNonPlanifie t, ArretNonPlanifie loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ArretNonPlanifie findByReferenceEntity(ArretNonPlanifie t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ArretNonPlanifie t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setCauseArret(causeArretService.findOrSave(t.getCauseArret()));
            t.setActionEntreprise(actionEntrepriseService.findOrSave(t.getActionEntreprise()));
        }
    }



    public List<ArretNonPlanifie> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ArretNonPlanifie>> getToBeSavedAndToBeDeleted(List<ArretNonPlanifie> oldList, List<ArretNonPlanifie> newList) {
        List<List<ArretNonPlanifie>> result = new ArrayList<>();
        List<ArretNonPlanifie> resultDelete = new ArrayList<>();
        List<ArretNonPlanifie> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ArretNonPlanifie> oldList, List<ArretNonPlanifie> newList, List<ArretNonPlanifie> resultUpdateOrSave, List<ArretNonPlanifie> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ArretNonPlanifie myOld = oldList.get(i);
                ArretNonPlanifie t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ArretNonPlanifie myNew = newList.get(i);
                ArretNonPlanifie t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;
    private ActionEntrepriseAdminService actionEntrepriseService ;
    private CauseArretAdminService causeArretService ;

    public ArretNonPlanifieAdminServiceImpl(ArretNonPlanifieRepository repository, StadeOperatoireAdminService stadeOperatoireService, ActionEntrepriseAdminService actionEntrepriseService, CauseArretAdminService causeArretService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
        this.actionEntrepriseService = actionEntrepriseService;
        this.causeArretService = causeArretService;
    }

    private ArretNonPlanifieRepository repository;
}
