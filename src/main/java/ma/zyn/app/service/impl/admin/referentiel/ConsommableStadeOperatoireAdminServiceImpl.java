package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.repository.facade.core.referentiel.ConsommableStadeOperatoireRepository;
import ma.zyn.app.service.facade.admin.referentiel.ConsommableStadeOperatoireAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.ConsommableAdminService ;
import ma.zyn.app.bean.core.referentiel.Consommable ;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;

import java.util.List;
@Service
public class ConsommableStadeOperatoireAdminServiceImpl implements ConsommableStadeOperatoireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ConsommableStadeOperatoire update(ConsommableStadeOperatoire t) {
        ConsommableStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ConsommableStadeOperatoire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ConsommableStadeOperatoire findOrSave(ConsommableStadeOperatoire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ConsommableStadeOperatoire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ConsommableStadeOperatoire> findAll() {
        return repository.findAll();
    }


    public List<ConsommableStadeOperatoire> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<ConsommableStadeOperatoire> findByStadeOperatoireId(Long id){
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
    public List<ConsommableStadeOperatoire> findByConsommableCode(String code){
        return repository.findByConsommableCode(code);
    }
    public List<ConsommableStadeOperatoire> findByConsommableId(Long id){
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            repository.deleteById(id);
        }
        return condition;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ConsommableStadeOperatoire> delete(List<ConsommableStadeOperatoire> list) {
		List<ConsommableStadeOperatoire> result = new ArrayList();
        if (list != null) {
            for (ConsommableStadeOperatoire t : list) {
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
    public ConsommableStadeOperatoire create(ConsommableStadeOperatoire t) {
        ConsommableStadeOperatoire loaded = findByReferenceEntity(t);
        ConsommableStadeOperatoire saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ConsommableStadeOperatoire findWithAssociatedLists(Long id){
        ConsommableStadeOperatoire result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ConsommableStadeOperatoire> update(List<ConsommableStadeOperatoire> ts, boolean createIfNotExist) {
        List<ConsommableStadeOperatoire> result = new ArrayList<>();
        if (ts != null) {
            for (ConsommableStadeOperatoire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ConsommableStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ConsommableStadeOperatoire t, ConsommableStadeOperatoire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ConsommableStadeOperatoire findByReferenceEntity(ConsommableStadeOperatoire t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ConsommableStadeOperatoire t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setConsommable(consommableService.findOrSave(t.getConsommable()));
        }
    }



    public List<ConsommableStadeOperatoire> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<ConsommableStadeOperatoire>> getToBeSavedAndToBeDeleted(List<ConsommableStadeOperatoire> oldList, List<ConsommableStadeOperatoire> newList) {
        List<List<ConsommableStadeOperatoire>> result = new ArrayList<>();
        List<ConsommableStadeOperatoire> resultDelete = new ArrayList<>();
        List<ConsommableStadeOperatoire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ConsommableStadeOperatoire> oldList, List<ConsommableStadeOperatoire> newList, List<ConsommableStadeOperatoire> resultUpdateOrSave, List<ConsommableStadeOperatoire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ConsommableStadeOperatoire myOld = oldList.get(i);
                ConsommableStadeOperatoire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ConsommableStadeOperatoire myNew = newList.get(i);
                ConsommableStadeOperatoire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ConsommableAdminService consommableService ;
    private StadeOperatoireAdminService stadeOperatoireService ;

    public ConsommableStadeOperatoireAdminServiceImpl(ConsommableStadeOperatoireRepository repository, ConsommableAdminService consommableService, StadeOperatoireAdminService stadeOperatoireService) {
        this.repository = repository;
        this.consommableService = consommableService;
        this.stadeOperatoireService = stadeOperatoireService;
    }

    private ConsommableStadeOperatoireRepository repository;
}
