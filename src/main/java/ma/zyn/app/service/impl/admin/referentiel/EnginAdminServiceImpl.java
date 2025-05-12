package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.repository.facade.core.referentiel.EnginRepository;
import ma.zyn.app.service.facade.admin.referentiel.EnginAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.TypeEnginAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeEngin ;
import ma.zyn.app.service.facade.admin.referentiel.OperationStadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;

import java.util.List;
@Service
public class EnginAdminServiceImpl implements EnginAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Engin update(Engin t) {
        Engin loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Engin findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Engin findOrSave(Engin t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Engin result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Engin> findAll() {
        return repository.findAll();
    }


    public List<Engin> findByTypeEnginCode(String code){
        return repository.findByTypeEnginCode(code);
    }
    public List<Engin> findByTypeEnginId(Long id){
        return repository.findByTypeEnginId(id);
    }
    public int deleteByTypeEnginCode(String code){
        return repository.deleteByTypeEnginCode(code);
    }
    public int deleteByTypeEnginId(Long id){
        return repository.deleteByTypeEnginId(id);
    }
    public long countByTypeEnginCode(String code){
        return repository.countByTypeEnginCode(code);
    }
    public List<Engin> findByOperationStadeOperatoireCode(String code){
        return repository.findByOperationStadeOperatoireCode(code);
    }
    public List<Engin> findByOperationStadeOperatoireId(Long id){
        return repository.findByOperationStadeOperatoireId(id);
    }
    public int deleteByOperationStadeOperatoireCode(String code){
        return repository.deleteByOperationStadeOperatoireCode(code);
    }
    public int deleteByOperationStadeOperatoireId(Long id){
        return repository.deleteByOperationStadeOperatoireId(id);
    }
    public long countByOperationStadeOperatoireCode(String code){
        return repository.countByOperationStadeOperatoireCode(code);
    }
    public List<Engin> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<Engin> findByStadeOperatoireId(Long id){
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            repository.deleteById(id);
        }
        return condition;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Engin> delete(List<Engin> list) {
		List<Engin> result = new ArrayList();
        if (list != null) {
            for (Engin t : list) {
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
    public Engin create(Engin t) {
        Engin loaded = findByReferenceEntity(t);
        Engin saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Engin findWithAssociatedLists(Long id){
        Engin result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Engin> update(List<Engin> ts, boolean createIfNotExist) {
        List<Engin> result = new ArrayList<>();
        if (ts != null) {
            for (Engin t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Engin loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Engin t, Engin loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Engin findByReferenceEntity(Engin t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Engin t){
        if( t != null) {
            t.setTypeEngin(typeEnginService.findOrSave(t.getTypeEngin()));
            t.setOperationStadeOperatoire(operationStadeOperatoireService.findOrSave(t.getOperationStadeOperatoire()));
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
        }
    }



    public List<Engin> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Engin>> getToBeSavedAndToBeDeleted(List<Engin> oldList, List<Engin> newList) {
        List<List<Engin>> result = new ArrayList<>();
        List<Engin> resultDelete = new ArrayList<>();
        List<Engin> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Engin> oldList, List<Engin> newList, List<Engin> resultUpdateOrSave, List<Engin> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Engin myOld = oldList.get(i);
                Engin t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Engin myNew = newList.get(i);
                Engin t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;
    private TypeEnginAdminService typeEnginService ;
    private OperationStadeOperatoireAdminService operationStadeOperatoireService ;

    public EnginAdminServiceImpl(EnginRepository repository, StadeOperatoireAdminService stadeOperatoireService, TypeEnginAdminService typeEnginService, OperationStadeOperatoireAdminService operationStadeOperatoireService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
        this.typeEnginService = typeEnginService;
        this.operationStadeOperatoireService = operationStadeOperatoireService;
    }

    private EnginRepository repository;
}
