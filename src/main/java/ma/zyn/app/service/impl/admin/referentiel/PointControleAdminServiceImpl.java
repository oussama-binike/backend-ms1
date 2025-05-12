package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.PointControle;
import ma.zyn.app.repository.facade.core.referentiel.PointControleRepository;
import ma.zyn.app.service.facade.admin.referentiel.PointControleAdminService;

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

import java.util.List;
@Service
public class PointControleAdminServiceImpl implements PointControleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PointControle update(PointControle t) {
        PointControle loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public PointControle findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public PointControle findOrSave(PointControle t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PointControle result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PointControle> findAll() {
        return repository.findAll();
    }


    public List<PointControle> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<PointControle> findByStadeOperatoireId(Long id){
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
    public List<PointControle> delete(List<PointControle> list) {
		List<PointControle> result = new ArrayList();
        if (list != null) {
            for (PointControle t : list) {
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
    public PointControle create(PointControle t) {
        PointControle loaded = findByReferenceEntity(t);
        PointControle saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PointControle findWithAssociatedLists(Long id){
        PointControle result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PointControle> update(List<PointControle> ts, boolean createIfNotExist) {
        List<PointControle> result = new ArrayList<>();
        if (ts != null) {
            for (PointControle t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    PointControle loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PointControle t, PointControle loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PointControle findByReferenceEntity(PointControle t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(PointControle t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
        }
    }



    public List<PointControle> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<PointControle>> getToBeSavedAndToBeDeleted(List<PointControle> oldList, List<PointControle> newList) {
        List<List<PointControle>> result = new ArrayList<>();
        List<PointControle> resultDelete = new ArrayList<>();
        List<PointControle> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PointControle> oldList, List<PointControle> newList, List<PointControle> resultUpdateOrSave, List<PointControle> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PointControle myOld = oldList.get(i);
                PointControle t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PointControle myNew = newList.get(i);
                PointControle t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;

    public PointControleAdminServiceImpl(PointControleRepository repository, StadeOperatoireAdminService stadeOperatoireService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
    }

    private PointControleRepository repository;
}
