package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Tranchee;
import ma.zyn.app.repository.facade.core.referentiel.TrancheeRepository;
import ma.zyn.app.service.facade.admin.referentiel.TrancheeAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.PanneauAdminService ;
import ma.zyn.app.bean.core.referentiel.Panneau ;

import java.util.List;
@Service
public class TrancheeAdminServiceImpl implements TrancheeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Tranchee update(Tranchee t) {
        Tranchee loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Tranchee findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Tranchee findOrSave(Tranchee t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Tranchee result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Tranchee> findAll() {
        return repository.findAll();
    }


    public List<Tranchee> findByPanneauCode(String code){
        return repository.findByPanneauCode(code);
    }
    public List<Tranchee> findByPanneauId(Long id){
        return repository.findByPanneauId(id);
    }
    public int deleteByPanneauCode(String code){
        return repository.deleteByPanneauCode(code);
    }
    public int deleteByPanneauId(Long id){
        return repository.deleteByPanneauId(id);
    }
    public long countByPanneauCode(String code){
        return repository.countByPanneauCode(code);
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
    public List<Tranchee> delete(List<Tranchee> list) {
		List<Tranchee> result = new ArrayList();
        if (list != null) {
            for (Tranchee t : list) {
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
    public Tranchee create(Tranchee t) {
        Tranchee loaded = findByReferenceEntity(t);
        Tranchee saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Tranchee findWithAssociatedLists(Long id){
        Tranchee result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tranchee> update(List<Tranchee> ts, boolean createIfNotExist) {
        List<Tranchee> result = new ArrayList<>();
        if (ts != null) {
            for (Tranchee t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Tranchee loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Tranchee t, Tranchee loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Tranchee findByReferenceEntity(Tranchee t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Tranchee t){
        if( t != null) {
            t.setPanneau(panneauService.findOrSave(t.getPanneau()));
        }
    }



    public List<Tranchee> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Tranchee>> getToBeSavedAndToBeDeleted(List<Tranchee> oldList, List<Tranchee> newList) {
        List<List<Tranchee>> result = new ArrayList<>();
        List<Tranchee> resultDelete = new ArrayList<>();
        List<Tranchee> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Tranchee> oldList, List<Tranchee> newList, List<Tranchee> resultUpdateOrSave, List<Tranchee> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Tranchee myOld = oldList.get(i);
                Tranchee t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Tranchee myNew = newList.get(i);
                Tranchee t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private PanneauAdminService panneauService ;

    public TrancheeAdminServiceImpl(TrancheeRepository repository, PanneauAdminService panneauService) {
        this.repository = repository;
        this.panneauService = panneauService;
    }

    private TrancheeRepository repository;
}
