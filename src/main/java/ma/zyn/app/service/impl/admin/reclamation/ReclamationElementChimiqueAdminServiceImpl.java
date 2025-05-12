package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.repository.facade.core.reclamation.ReclamationElementChimiqueRepository;
import ma.zyn.app.service.facade.admin.reclamation.ReclamationElementChimiqueAdminService;

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

import ma.zyn.app.service.facade.admin.reclamation.ReclamationAdminService ;
import ma.zyn.app.bean.core.reclamation.Reclamation ;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;

import java.util.List;
@Service
public class ReclamationElementChimiqueAdminServiceImpl implements ReclamationElementChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReclamationElementChimique update(ReclamationElementChimique t) {
        ReclamationElementChimique loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ReclamationElementChimique findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ReclamationElementChimique findOrSave(ReclamationElementChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ReclamationElementChimique result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ReclamationElementChimique> findAll() {
        return repository.findAll();
    }


    public List<ReclamationElementChimique> findByReclamationId(Long id){
        return repository.findByReclamationId(id);
    }
    public int deleteByReclamationId(Long id){
        return repository.deleteByReclamationId(id);
    }
    public long countByReclamationCode(String code){
        return repository.countByReclamationCode(code);
    }
    public List<ReclamationElementChimique> findByElementChimiqueCode(String code){
        return repository.findByElementChimiqueCode(code);
    }
    public List<ReclamationElementChimique> findByElementChimiqueId(Long id){
        return repository.findByElementChimiqueId(id);
    }
    public int deleteByElementChimiqueCode(String code){
        return repository.deleteByElementChimiqueCode(code);
    }
    public int deleteByElementChimiqueId(Long id){
        return repository.deleteByElementChimiqueId(id);
    }
    public long countByElementChimiqueCode(String code){
        return repository.countByElementChimiqueCode(code);
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
    public List<ReclamationElementChimique> delete(List<ReclamationElementChimique> list) {
		List<ReclamationElementChimique> result = new ArrayList();
        if (list != null) {
            for (ReclamationElementChimique t : list) {
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
    public ReclamationElementChimique create(ReclamationElementChimique t) {
        ReclamationElementChimique loaded = findByReferenceEntity(t);
        ReclamationElementChimique saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ReclamationElementChimique findWithAssociatedLists(Long id){
        ReclamationElementChimique result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ReclamationElementChimique> update(List<ReclamationElementChimique> ts, boolean createIfNotExist) {
        List<ReclamationElementChimique> result = new ArrayList<>();
        if (ts != null) {
            for (ReclamationElementChimique t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ReclamationElementChimique loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ReclamationElementChimique t, ReclamationElementChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ReclamationElementChimique findByReferenceEntity(ReclamationElementChimique t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ReclamationElementChimique t){
        if( t != null) {
            t.setReclamation(reclamationService.findOrSave(t.getReclamation()));
            t.setElementChimique(elementChimiqueService.findOrSave(t.getElementChimique()));
        }
    }



    public List<ReclamationElementChimique> findAllOptimized() {
        return repository.findAll();
    }

    @Override
    public List<List<ReclamationElementChimique>> getToBeSavedAndToBeDeleted(List<ReclamationElementChimique> oldList, List<ReclamationElementChimique> newList) {
        List<List<ReclamationElementChimique>> result = new ArrayList<>();
        List<ReclamationElementChimique> resultDelete = new ArrayList<>();
        List<ReclamationElementChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ReclamationElementChimique> oldList, List<ReclamationElementChimique> newList, List<ReclamationElementChimique> resultUpdateOrSave, List<ReclamationElementChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ReclamationElementChimique myOld = oldList.get(i);
                ReclamationElementChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ReclamationElementChimique myNew = newList.get(i);
                ReclamationElementChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ReclamationAdminService reclamationService ;
    private ElementChimiqueAdminService elementChimiqueService ;

    public ReclamationElementChimiqueAdminServiceImpl(ReclamationElementChimiqueRepository repository, ReclamationAdminService reclamationService, ElementChimiqueAdminService elementChimiqueService) {
        this.repository = repository;
        this.reclamationService = reclamationService;
        this.elementChimiqueService = elementChimiqueService;
    }

    private ReclamationElementChimiqueRepository repository;
}
