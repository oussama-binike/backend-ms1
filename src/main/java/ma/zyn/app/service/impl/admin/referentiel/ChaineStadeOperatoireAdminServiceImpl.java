package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.repository.facade.core.referentiel.ChaineStadeOperatoireRepository;
import ma.zyn.app.service.facade.admin.referentiel.ChaineStadeOperatoireAdminService;

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
public class ChaineStadeOperatoireAdminServiceImpl implements ChaineStadeOperatoireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ChaineStadeOperatoire update(ChaineStadeOperatoire t) {
        ChaineStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ChaineStadeOperatoire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ChaineStadeOperatoire findOrSave(ChaineStadeOperatoire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ChaineStadeOperatoire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ChaineStadeOperatoire> findAll() {
        return repository.findAll();
    }


    public List<ChaineStadeOperatoire> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<ChaineStadeOperatoire> findByStadeOperatoireId(Long id){
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
    public List<ChaineStadeOperatoire> delete(List<ChaineStadeOperatoire> list) {
		List<ChaineStadeOperatoire> result = new ArrayList();
        if (list != null) {
            for (ChaineStadeOperatoire t : list) {
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
    public ChaineStadeOperatoire create(ChaineStadeOperatoire t) {
        ChaineStadeOperatoire loaded = findByReferenceEntity(t);
        ChaineStadeOperatoire saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ChaineStadeOperatoire findWithAssociatedLists(Long id){
        ChaineStadeOperatoire result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ChaineStadeOperatoire> update(List<ChaineStadeOperatoire> ts, boolean createIfNotExist) {
        List<ChaineStadeOperatoire> result = new ArrayList<>();
        if (ts != null) {
            for (ChaineStadeOperatoire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ChaineStadeOperatoire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ChaineStadeOperatoire t, ChaineStadeOperatoire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ChaineStadeOperatoire findByReferenceEntity(ChaineStadeOperatoire t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ChaineStadeOperatoire t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
        }
    }



    public List<ChaineStadeOperatoire> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ChaineStadeOperatoire>> getToBeSavedAndToBeDeleted(List<ChaineStadeOperatoire> oldList, List<ChaineStadeOperatoire> newList) {
        List<List<ChaineStadeOperatoire>> result = new ArrayList<>();
        List<ChaineStadeOperatoire> resultDelete = new ArrayList<>();
        List<ChaineStadeOperatoire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ChaineStadeOperatoire> oldList, List<ChaineStadeOperatoire> newList, List<ChaineStadeOperatoire> resultUpdateOrSave, List<ChaineStadeOperatoire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ChaineStadeOperatoire myOld = oldList.get(i);
                ChaineStadeOperatoire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ChaineStadeOperatoire myNew = newList.get(i);
                ChaineStadeOperatoire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;

    public ChaineStadeOperatoireAdminServiceImpl(ChaineStadeOperatoireRepository repository, StadeOperatoireAdminService stadeOperatoireService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
    }

    private ChaineStadeOperatoireRepository repository;
}
