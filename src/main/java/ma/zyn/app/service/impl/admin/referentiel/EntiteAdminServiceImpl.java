package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.repository.facade.core.referentiel.EntiteRepository;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.AxeAdminService ;
import ma.zyn.app.bean.core.referentiel.Axe ;

import java.util.List;
@Service
public class EntiteAdminServiceImpl implements EntiteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Entite update(Entite t) {
        Entite loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Entite findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Entite findOrSave(Entite t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Entite result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Entite> findAll() {
        return repository.findAll();
    }


    public List<Entite> findByAxeCode(String code){
        return repository.findByAxeCode(code);
    }
    public List<Entite> findByAxeId(Long id){
        return repository.findByAxeId(id);
    }
    public int deleteByAxeCode(String code){
        return repository.deleteByAxeCode(code);
    }
    public int deleteByAxeId(Long id){
        return repository.deleteByAxeId(id);
    }
    public long countByAxeCode(String code){
        return repository.countByAxeCode(code);
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
    public List<Entite> delete(List<Entite> list) {
		List<Entite> result = new ArrayList();
        if (list != null) {
            for (Entite t : list) {
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
    public Entite create(Entite t) {
        Entite loaded = findByReferenceEntity(t);
        Entite saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Entite findWithAssociatedLists(Long id){
        Entite result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Entite> update(List<Entite> ts, boolean createIfNotExist) {
        List<Entite> result = new ArrayList<>();
        if (ts != null) {
            for (Entite t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Entite loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Entite t, Entite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Entite findByReferenceEntity(Entite t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Entite t){
        if( t != null) {
            t.setAxe(axeService.findOrSave(t.getAxe()));
        }
    }



    public List<Entite> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Entite>> getToBeSavedAndToBeDeleted(List<Entite> oldList, List<Entite> newList) {
        List<List<Entite>> result = new ArrayList<>();
        List<Entite> resultDelete = new ArrayList<>();
        List<Entite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Entite> oldList, List<Entite> newList, List<Entite> resultUpdateOrSave, List<Entite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Entite myOld = oldList.get(i);
                Entite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Entite myNew = newList.get(i);
                Entite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private AxeAdminService axeService ;

    public EntiteAdminServiceImpl(EntiteRepository repository, AxeAdminService axeService) {
        this.repository = repository;
        this.axeService = axeService;
    }

    private EntiteRepository repository;
}
