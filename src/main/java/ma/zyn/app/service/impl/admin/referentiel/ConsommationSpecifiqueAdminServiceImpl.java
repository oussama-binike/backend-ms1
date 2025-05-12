package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import ma.zyn.app.repository.facade.core.referentiel.ConsommationSpecifiqueRepository;
import ma.zyn.app.service.facade.admin.referentiel.ConsommationSpecifiqueAdminService;

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
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;

import java.util.List;
@Service
public class ConsommationSpecifiqueAdminServiceImpl implements ConsommationSpecifiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ConsommationSpecifique update(ConsommationSpecifique t) {
        ConsommationSpecifique loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ConsommationSpecifique findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ConsommationSpecifique findOrSave(ConsommationSpecifique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ConsommationSpecifique result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ConsommationSpecifique> findAll() {
        return repository.findAll();
    }


    public List<ConsommationSpecifique> findByConsommableCode(String code){
        return repository.findByConsommableCode(code);
    }
    public List<ConsommationSpecifique> findByConsommableId(Long id){
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
    public List<ConsommationSpecifique> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<ConsommationSpecifique> findByStadeOperatoireId(Long id){
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
    public List<ConsommationSpecifique> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<ConsommationSpecifique> findByUniteId(Long id){
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
    public List<ConsommationSpecifique> delete(List<ConsommationSpecifique> list) {
		List<ConsommationSpecifique> result = new ArrayList();
        if (list != null) {
            for (ConsommationSpecifique t : list) {
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
    public ConsommationSpecifique create(ConsommationSpecifique t) {
        ConsommationSpecifique loaded = findByReferenceEntity(t);
        ConsommationSpecifique saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ConsommationSpecifique findWithAssociatedLists(Long id){
        ConsommationSpecifique result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ConsommationSpecifique> update(List<ConsommationSpecifique> ts, boolean createIfNotExist) {
        List<ConsommationSpecifique> result = new ArrayList<>();
        if (ts != null) {
            for (ConsommationSpecifique t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ConsommationSpecifique loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ConsommationSpecifique t, ConsommationSpecifique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ConsommationSpecifique findByReferenceEntity(ConsommationSpecifique t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ConsommationSpecifique t){
        if( t != null) {
            t.setConsommable(consommableService.findOrSave(t.getConsommable()));
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<ConsommationSpecifique> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ConsommationSpecifique>> getToBeSavedAndToBeDeleted(List<ConsommationSpecifique> oldList, List<ConsommationSpecifique> newList) {
        List<List<ConsommationSpecifique>> result = new ArrayList<>();
        List<ConsommationSpecifique> resultDelete = new ArrayList<>();
        List<ConsommationSpecifique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ConsommationSpecifique> oldList, List<ConsommationSpecifique> newList, List<ConsommationSpecifique> resultUpdateOrSave, List<ConsommationSpecifique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ConsommationSpecifique myOld = oldList.get(i);
                ConsommationSpecifique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ConsommationSpecifique myNew = newList.get(i);
                ConsommationSpecifique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private ConsommableAdminService consommableService ;
    private StadeOperatoireAdminService stadeOperatoireService ;

    public ConsommationSpecifiqueAdminServiceImpl(ConsommationSpecifiqueRepository repository, UniteAdminService uniteService, ConsommableAdminService consommableService, StadeOperatoireAdminService stadeOperatoireService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.consommableService = consommableService;
        this.stadeOperatoireService = stadeOperatoireService;
    }

    private ConsommationSpecifiqueRepository repository;
}
