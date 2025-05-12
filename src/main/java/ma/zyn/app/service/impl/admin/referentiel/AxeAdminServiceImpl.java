package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Axe;
import ma.zyn.app.repository.facade.core.referentiel.AxeRepository;
import ma.zyn.app.service.facade.admin.referentiel.AxeAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.SiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Site ;

import java.util.List;
@Service
public class AxeAdminServiceImpl implements AxeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Axe update(Axe t) {
        Axe loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Axe findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Axe findOrSave(Axe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Axe result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Axe> findAll() {
        return repository.findAll();
    }


    public List<Axe> findBySiteCode(String code){
        return repository.findBySiteCode(code);
    }
    public List<Axe> findBySiteId(Long id){
        return repository.findBySiteId(id);
    }
    public int deleteBySiteCode(String code){
        return repository.deleteBySiteCode(code);
    }
    public int deleteBySiteId(Long id){
        return repository.deleteBySiteId(id);
    }
    public long countBySiteCode(String code){
        return repository.countBySiteCode(code);
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
    public List<Axe> delete(List<Axe> list) {
		List<Axe> result = new ArrayList();
        if (list != null) {
            for (Axe t : list) {
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
    public Axe create(Axe t) {
        Axe loaded = findByReferenceEntity(t);
        Axe saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Axe findWithAssociatedLists(Long id){
        Axe result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Axe> update(List<Axe> ts, boolean createIfNotExist) {
        List<Axe> result = new ArrayList<>();
        if (ts != null) {
            for (Axe t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Axe loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Axe t, Axe loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Axe findByReferenceEntity(Axe t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Axe t){
        if( t != null) {
            t.setSite(siteService.findOrSave(t.getSite()));
        }
    }



    public List<Axe> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Axe>> getToBeSavedAndToBeDeleted(List<Axe> oldList, List<Axe> newList) {
        List<List<Axe>> result = new ArrayList<>();
        List<Axe> resultDelete = new ArrayList<>();
        List<Axe> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Axe> oldList, List<Axe> newList, List<Axe> resultUpdateOrSave, List<Axe> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Axe myOld = oldList.get(i);
                Axe t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Axe myNew = newList.get(i);
                Axe t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private SiteAdminService siteService ;

    public AxeAdminServiceImpl(AxeRepository repository, SiteAdminService siteService) {
        this.repository = repository;
        this.siteService = siteService;
    }

    private AxeRepository repository;
}
