package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Panneau;
import ma.zyn.app.repository.facade.core.referentiel.PanneauRepository;
import ma.zyn.app.service.facade.admin.referentiel.PanneauAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Entite ;

import java.util.List;
@Service
public class PanneauAdminServiceImpl implements PanneauAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Panneau update(Panneau t) {
        Panneau loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Panneau findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Panneau findOrSave(Panneau t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Panneau result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Panneau> findAll() {
        return repository.findAll();
    }


    public List<Panneau> findByEntiteCode(String code){
        return repository.findByEntiteCode(code);
    }
    public List<Panneau> findByEntiteId(Long id){
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
            repository.deleteById(id);
        }
        return condition;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Panneau> delete(List<Panneau> list) {
		List<Panneau> result = new ArrayList();
        if (list != null) {
            for (Panneau t : list) {
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
    public Panneau create(Panneau t) {
        Panneau loaded = findByReferenceEntity(t);
        Panneau saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Panneau findWithAssociatedLists(Long id){
        Panneau result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Panneau> update(List<Panneau> ts, boolean createIfNotExist) {
        List<Panneau> result = new ArrayList<>();
        if (ts != null) {
            for (Panneau t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Panneau loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Panneau t, Panneau loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Panneau findByReferenceEntity(Panneau t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Panneau t){
        if( t != null) {
            t.setEntite(entiteService.findOrSave(t.getEntite()));
        }
    }



    public List<Panneau> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Panneau>> getToBeSavedAndToBeDeleted(List<Panneau> oldList, List<Panneau> newList) {
        List<List<Panneau>> result = new ArrayList<>();
        List<Panneau> resultDelete = new ArrayList<>();
        List<Panneau> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Panneau> oldList, List<Panneau> newList, List<Panneau> resultUpdateOrSave, List<Panneau> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Panneau myOld = oldList.get(i);
                Panneau t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Panneau myNew = newList.get(i);
                Panneau t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private EntiteAdminService entiteService ;

    public PanneauAdminServiceImpl(PanneauRepository repository, EntiteAdminService entiteService) {
        this.repository = repository;
        this.entiteService = entiteService;
    }

    private PanneauRepository repository;
}
