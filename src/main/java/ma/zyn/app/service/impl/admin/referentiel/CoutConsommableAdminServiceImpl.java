package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import ma.zyn.app.repository.facade.core.referentiel.CoutConsommableRepository;
import ma.zyn.app.service.facade.admin.referentiel.CoutConsommableAdminService;

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
import ma.zyn.app.service.facade.admin.referentiel.ConsommableAdminService ;
import ma.zyn.app.bean.core.referentiel.Consommable ;

import java.util.List;
@Service
public class CoutConsommableAdminServiceImpl implements CoutConsommableAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CoutConsommable update(CoutConsommable t) {
        CoutConsommable loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CoutConsommable findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CoutConsommable findOrSave(CoutConsommable t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CoutConsommable result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CoutConsommable> findAll() {
        return repository.findAll();
    }


    public List<CoutConsommable> findByConsommableCode(String code){
        return repository.findByConsommableCode(code);
    }
    public List<CoutConsommable> findByConsommableId(Long id){
        return repository.findByConsommableId(id);
    }
    public int deleteByConsommableCode(String code){
        return repository.deleteByConsommableCode(code);
    }
    public int deleteByConsommableId(Long id){
        return repository.deleteByConsommableId(id);
    }
    public long countByConsommableCode(String code){
        return repository.countByConsommableCode(code);
    }
    public List<CoutConsommable> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<CoutConsommable> findByUniteId(Long id){
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
    public List<CoutConsommable> delete(List<CoutConsommable> list) {
		List<CoutConsommable> result = new ArrayList();
        if (list != null) {
            for (CoutConsommable t : list) {
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
    public CoutConsommable create(CoutConsommable t) {
        CoutConsommable loaded = findByReferenceEntity(t);
        CoutConsommable saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CoutConsommable findWithAssociatedLists(Long id){
        CoutConsommable result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CoutConsommable> update(List<CoutConsommable> ts, boolean createIfNotExist) {
        List<CoutConsommable> result = new ArrayList<>();
        if (ts != null) {
            for (CoutConsommable t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CoutConsommable loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CoutConsommable t, CoutConsommable loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CoutConsommable findByReferenceEntity(CoutConsommable t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(CoutConsommable t){
        if( t != null) {
            t.setConsommable(consommableService.findOrSave(t.getConsommable()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<CoutConsommable> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CoutConsommable>> getToBeSavedAndToBeDeleted(List<CoutConsommable> oldList, List<CoutConsommable> newList) {
        List<List<CoutConsommable>> result = new ArrayList<>();
        List<CoutConsommable> resultDelete = new ArrayList<>();
        List<CoutConsommable> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CoutConsommable> oldList, List<CoutConsommable> newList, List<CoutConsommable> resultUpdateOrSave, List<CoutConsommable> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CoutConsommable myOld = oldList.get(i);
                CoutConsommable t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CoutConsommable myNew = newList.get(i);
                CoutConsommable t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private ConsommableAdminService consommableService ;

    public CoutConsommableAdminServiceImpl(CoutConsommableRepository repository, UniteAdminService uniteService, ConsommableAdminService consommableService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.consommableService = consommableService;
    }

    private CoutConsommableRepository repository;
}
