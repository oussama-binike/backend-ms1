package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.repository.facade.core.referentiel.ElementChimiqueRepository;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;

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

import java.util.List;
@Service
public class ElementChimiqueAdminServiceImpl implements ElementChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ElementChimique update(ElementChimique t) {
        ElementChimique loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ElementChimique findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ElementChimique findOrSave(ElementChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ElementChimique result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ElementChimique> findAll() {
        return repository.findAll();
    }


    public List<ElementChimique> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<ElementChimique> findByUniteId(Long id){
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
    public List<ElementChimique> delete(List<ElementChimique> list) {
		List<ElementChimique> result = new ArrayList();
        if (list != null) {
            for (ElementChimique t : list) {
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
    public ElementChimique create(ElementChimique t) {
        ElementChimique loaded = findByReferenceEntity(t);
        ElementChimique saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ElementChimique findWithAssociatedLists(Long id){
        ElementChimique result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ElementChimique> update(List<ElementChimique> ts, boolean createIfNotExist) {
        List<ElementChimique> result = new ArrayList<>();
        if (ts != null) {
            for (ElementChimique t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ElementChimique loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ElementChimique t, ElementChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ElementChimique findByReferenceEntity(ElementChimique t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ElementChimique t){
        if( t != null) {
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<ElementChimique> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ElementChimique>> getToBeSavedAndToBeDeleted(List<ElementChimique> oldList, List<ElementChimique> newList) {
        List<List<ElementChimique>> result = new ArrayList<>();
        List<ElementChimique> resultDelete = new ArrayList<>();
        List<ElementChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ElementChimique> oldList, List<ElementChimique> newList, List<ElementChimique> resultUpdateOrSave, List<ElementChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ElementChimique myOld = oldList.get(i);
                ElementChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ElementChimique myNew = newList.get(i);
                ElementChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;

    public ElementChimiqueAdminServiceImpl(ElementChimiqueRepository repository, UniteAdminService uniteService) {
        this.repository = repository;
        this.uniteService = uniteService;
    }

    private ElementChimiqueRepository repository;
}
