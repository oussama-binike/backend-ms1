package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Niveau;
import ma.zyn.app.repository.facade.core.referentiel.NiveauRepository;
import ma.zyn.app.service.facade.admin.referentiel.NiveauAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.TrancheeAdminService ;
import ma.zyn.app.bean.core.referentiel.Tranchee ;

import java.util.List;
@Service
public class NiveauAdminServiceImpl implements NiveauAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Niveau update(Niveau t) {
        Niveau loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Niveau findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Niveau findOrSave(Niveau t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Niveau result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Niveau> findAll() {
        return repository.findAll();
    }


    public List<Niveau> findByTrancheeCode(String code){
        return repository.findByTrancheeCode(code);
    }
    public List<Niveau> findByTrancheeId(Long id){
        return repository.findByTrancheeId(id);
    }
    public int deleteByTrancheeCode(String code){
        return repository.deleteByTrancheeCode(code);
    }
    public int deleteByTrancheeId(Long id){
        return repository.deleteByTrancheeId(id);
    }
    public long countByTrancheeCode(String code){
        return repository.countByTrancheeCode(code);
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
    public List<Niveau> delete(List<Niveau> list) {
		List<Niveau> result = new ArrayList();
        if (list != null) {
            for (Niveau t : list) {
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
    public Niveau create(Niveau t) {
        Niveau loaded = findByReferenceEntity(t);
        Niveau saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Niveau findWithAssociatedLists(Long id){
        Niveau result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Niveau> update(List<Niveau> ts, boolean createIfNotExist) {
        List<Niveau> result = new ArrayList<>();
        if (ts != null) {
            for (Niveau t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Niveau loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Niveau t, Niveau loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Niveau findByReferenceEntity(Niveau t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Niveau t){
        if( t != null) {
            t.setTranchee(trancheeService.findOrSave(t.getTranchee()));
        }
    }



    public List<Niveau> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Niveau>> getToBeSavedAndToBeDeleted(List<Niveau> oldList, List<Niveau> newList) {
        List<List<Niveau>> result = new ArrayList<>();
        List<Niveau> resultDelete = new ArrayList<>();
        List<Niveau> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Niveau> oldList, List<Niveau> newList, List<Niveau> resultUpdateOrSave, List<Niveau> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Niveau myOld = oldList.get(i);
                Niveau t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Niveau myNew = newList.get(i);
                Niveau t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private TrancheeAdminService trancheeService ;

    public NiveauAdminServiceImpl(NiveauRepository repository, TrancheeAdminService trancheeService) {
        this.repository = repository;
        this.trancheeService = trancheeService;
    }

    private NiveauRepository repository;
}
