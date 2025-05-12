package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.repository.facade.core.referentiel.ConsommableRepository;
import ma.zyn.app.service.facade.admin.referentiel.ConsommableAdminService;

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
import ma.zyn.app.service.facade.admin.referentiel.ConsommableStadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire ;

import java.util.List;
@Service
public class ConsommableAdminServiceImpl implements ConsommableAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Consommable update(Consommable t) {
        Consommable loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public Consommable findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Consommable findOrSave(Consommable t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Consommable result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Consommable> findAll() {
        return repository.findAll();
    }


    public List<Consommable> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<Consommable> findByUniteId(Long id){
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
            deleteAssociatedLists(id);
            repository.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        consommableStadeOperatoireService.deleteByConsommableId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Consommable> delete(List<Consommable> list) {
		List<Consommable> result = new ArrayList();
        if (list != null) {
            for (Consommable t : list) {
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
    public Consommable create(Consommable t) {
        Consommable loaded = findByReferenceEntity(t);
        Consommable saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getConsommableStadeOperatoires() != null) {
                t.getConsommableStadeOperatoires().forEach(element-> {
                    element.setConsommable(saved);
                    consommableStadeOperatoireService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Consommable findWithAssociatedLists(Long id){
        Consommable result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setConsommableStadeOperatoires(consommableStadeOperatoireService.findByConsommableId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Consommable> update(List<Consommable> ts, boolean createIfNotExist) {
        List<Consommable> result = new ArrayList<>();
        if (ts != null) {
            for (Consommable t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Consommable loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Consommable t, Consommable loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Consommable consommable){
    if(consommable !=null && consommable.getId() != null){
        List<List<ConsommableStadeOperatoire>> resultConsommableStadeOperatoires= consommableStadeOperatoireService.getToBeSavedAndToBeDeleted(consommableStadeOperatoireService.findByConsommableId(consommable.getId()),consommable.getConsommableStadeOperatoires());
            consommableStadeOperatoireService.delete(resultConsommableStadeOperatoires.get(1));
        emptyIfNull(resultConsommableStadeOperatoires.get(0)).forEach(e -> e.setConsommable(consommable));
        consommableStadeOperatoireService.update(resultConsommableStadeOperatoires.get(0),true);
        }
    }








    public Consommable findByReferenceEntity(Consommable t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Consommable t){
        if( t != null) {
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<Consommable> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Consommable>> getToBeSavedAndToBeDeleted(List<Consommable> oldList, List<Consommable> newList) {
        List<List<Consommable>> result = new ArrayList<>();
        List<Consommable> resultDelete = new ArrayList<>();
        List<Consommable> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Consommable> oldList, List<Consommable> newList, List<Consommable> resultUpdateOrSave, List<Consommable> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Consommable myOld = oldList.get(i);
                Consommable t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Consommable myNew = newList.get(i);
                Consommable t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private ConsommableStadeOperatoireAdminService consommableStadeOperatoireService ;

    public ConsommableAdminServiceImpl(ConsommableRepository repository, UniteAdminService uniteService, ConsommableStadeOperatoireAdminService consommableStadeOperatoireService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.consommableStadeOperatoireService = consommableStadeOperatoireService;
    }

    private ConsommableRepository repository;
}
