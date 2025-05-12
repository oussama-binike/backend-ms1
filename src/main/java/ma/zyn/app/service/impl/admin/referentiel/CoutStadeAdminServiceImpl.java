package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CoutStade;
import ma.zyn.app.repository.facade.core.referentiel.CoutStadeRepository;
import ma.zyn.app.service.facade.admin.referentiel.CoutStadeAdminService;

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
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;

import java.util.List;
@Service
public class CoutStadeAdminServiceImpl implements CoutStadeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CoutStade update(CoutStade t) {
        CoutStade loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CoutStade findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CoutStade findOrSave(CoutStade t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CoutStade result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CoutStade> findAll() {
        return repository.findAll();
    }


    public List<CoutStade> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<CoutStade> findByStadeOperatoireId(Long id){
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
    public List<CoutStade> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<CoutStade> findByUniteId(Long id){
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
    public List<CoutStade> delete(List<CoutStade> list) {
		List<CoutStade> result = new ArrayList();
        if (list != null) {
            for (CoutStade t : list) {
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
    public CoutStade create(CoutStade t) {
        CoutStade loaded = findByReferenceEntity(t);
        CoutStade saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CoutStade findWithAssociatedLists(Long id){
        CoutStade result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CoutStade> update(List<CoutStade> ts, boolean createIfNotExist) {
        List<CoutStade> result = new ArrayList<>();
        if (ts != null) {
            for (CoutStade t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CoutStade loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CoutStade t, CoutStade loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CoutStade findByReferenceEntity(CoutStade t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(CoutStade t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<CoutStade> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CoutStade>> getToBeSavedAndToBeDeleted(List<CoutStade> oldList, List<CoutStade> newList) {
        List<List<CoutStade>> result = new ArrayList<>();
        List<CoutStade> resultDelete = new ArrayList<>();
        List<CoutStade> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CoutStade> oldList, List<CoutStade> newList, List<CoutStade> resultUpdateOrSave, List<CoutStade> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CoutStade myOld = oldList.get(i);
                CoutStade t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CoutStade myNew = newList.get(i);
                CoutStade t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private StadeOperatoireAdminService stadeOperatoireService ;

    public CoutStadeAdminServiceImpl(CoutStadeRepository repository, UniteAdminService uniteService, StadeOperatoireAdminService stadeOperatoireService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.stadeOperatoireService = stadeOperatoireService;
    }

    private CoutStadeRepository repository;
}
