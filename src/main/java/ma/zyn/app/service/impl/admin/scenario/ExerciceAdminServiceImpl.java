package ma.zyn.app.service.impl.admin.scenario;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.repository.facade.core.scenario.ExerciceRepository;
import ma.zyn.app.service.facade.admin.scenario.ExerciceAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StatusExerciceAdminService ;
import ma.zyn.app.bean.core.referentiel.StatusExercice ;

import java.util.List;
@Service
public class ExerciceAdminServiceImpl implements ExerciceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Exercice update(Exercice t) {
        Exercice loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Exercice findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Exercice findOrSave(Exercice t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Exercice result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Exercice> findAll() {
        return repository.findAll();
    }


    public List<Exercice> findByStatusExerciceCode(String code){
        return repository.findByStatusExerciceCode(code);
    }
    public List<Exercice> findByStatusExerciceId(Long id){
        return repository.findByStatusExerciceId(id);
    }
    public int deleteByStatusExerciceCode(String code){
        return repository.deleteByStatusExerciceCode(code);
    }
    public int deleteByStatusExerciceId(Long id){
        return repository.deleteByStatusExerciceId(id);
    }
    public long countByStatusExerciceCode(String code){
        return repository.countByStatusExerciceCode(code);
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
    public List<Exercice> delete(List<Exercice> list) {
		List<Exercice> result = new ArrayList();
        if (list != null) {
            for (Exercice t : list) {
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
    public Exercice create(Exercice t) {
        Exercice loaded = findByReferenceEntity(t);
        Exercice saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Exercice findWithAssociatedLists(Long id){
        Exercice result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Exercice> update(List<Exercice> ts, boolean createIfNotExist) {
        List<Exercice> result = new ArrayList<>();
        if (ts != null) {
            for (Exercice t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Exercice loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Exercice t, Exercice loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Exercice findByReferenceEntity(Exercice t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Exercice t){
        if( t != null) {
            t.setStatusExercice(statusExerciceService.findOrSave(t.getStatusExercice()));
        }
    }



    public List<Exercice> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Exercice>> getToBeSavedAndToBeDeleted(List<Exercice> oldList, List<Exercice> newList) {
        List<List<Exercice>> result = new ArrayList<>();
        List<Exercice> resultDelete = new ArrayList<>();
        List<Exercice> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Exercice> oldList, List<Exercice> newList, List<Exercice> resultUpdateOrSave, List<Exercice> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Exercice myOld = oldList.get(i);
                Exercice t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Exercice myNew = newList.get(i);
                Exercice t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StatusExerciceAdminService statusExerciceService ;

    public ExerciceAdminServiceImpl(ExerciceRepository repository, StatusExerciceAdminService statusExerciceService) {
        this.repository = repository;
        this.statusExerciceService = statusExerciceService;
    }

    private ExerciceRepository repository;
}
