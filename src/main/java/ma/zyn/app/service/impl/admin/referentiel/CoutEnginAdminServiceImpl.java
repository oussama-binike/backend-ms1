package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CoutEngin;
import ma.zyn.app.repository.facade.core.referentiel.CoutEnginRepository;
import ma.zyn.app.service.facade.admin.referentiel.CoutEnginAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService ;
import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.service.facade.admin.referentiel.EnginAdminService ;
import ma.zyn.app.bean.core.referentiel.Engin ;

import java.util.List;
@Service
public class CoutEnginAdminServiceImpl implements CoutEnginAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CoutEngin update(CoutEngin t) {
        CoutEngin loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CoutEngin findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CoutEngin findOrSave(CoutEngin t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CoutEngin result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CoutEngin> findAll() {
        return repository.findAll();
    }


    public List<CoutEngin> findByEnginId(Long id){
        return repository.findByEnginId(id);
    }
    public int deleteByEnginId(Long id){
        return repository.deleteByEnginId(id);
    }
    public long countByEnginCode(String code){
        return repository.countByEnginCode(code);
    }
    public List<CoutEngin> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<CoutEngin> findByUniteId(Long id){
        return repository.findByUniteId(id);
    }
    public int deleteByUniteCode(String code){
        return repository.deleteByUniteCode(code);
    }
    public int deleteByUniteId(Long id){
        return repository.deleteByUniteId(id);
    }
    public long countByUniteCode(String code){
        return repository.countByUniteCode(code);
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
    public List<CoutEngin> delete(List<CoutEngin> list) {
		List<CoutEngin> result = new ArrayList();
        if (list != null) {
            for (CoutEngin t : list) {
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
    public CoutEngin create(CoutEngin t) {
        CoutEngin loaded = findByReferenceEntity(t);
        CoutEngin saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CoutEngin findWithAssociatedLists(Long id){
        CoutEngin result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CoutEngin> update(List<CoutEngin> ts, boolean createIfNotExist) {
        List<CoutEngin> result = new ArrayList<>();
        if (ts != null) {
            for (CoutEngin t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CoutEngin loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CoutEngin t, CoutEngin loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CoutEngin findByReferenceEntity(CoutEngin t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(CoutEngin t){
        if( t != null) {
            t.setEngin(enginService.findOrSave(t.getEngin()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<CoutEngin> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CoutEngin>> getToBeSavedAndToBeDeleted(List<CoutEngin> oldList, List<CoutEngin> newList) {
        List<List<CoutEngin>> result = new ArrayList<>();
        List<CoutEngin> resultDelete = new ArrayList<>();
        List<CoutEngin> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CoutEngin> oldList, List<CoutEngin> newList, List<CoutEngin> resultUpdateOrSave, List<CoutEngin> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CoutEngin myOld = oldList.get(i);
                CoutEngin t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CoutEngin myNew = newList.get(i);
                CoutEngin t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private EnginAdminService enginService ;

    public CoutEnginAdminServiceImpl(CoutEnginRepository repository, UniteAdminService uniteService, EnginAdminService enginService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.enginService = enginService;
    }

    private CoutEnginRepository repository;
}
