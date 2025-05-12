package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Moyen;
import ma.zyn.app.repository.facade.core.referentiel.MoyenRepository;
import ma.zyn.app.service.facade.admin.referentiel.MoyenAdminService;

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


import java.util.List;
@Service
public class MoyenAdminServiceImpl implements MoyenAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Moyen update(Moyen t) {
        Moyen loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Moyen findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Moyen findOrSave(Moyen t) {
        if (t != null) {
            Moyen result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Moyen> findAll() {
        return repository.findAll();
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
    public List<Moyen> delete(List<Moyen> list) {
		List<Moyen> result = new ArrayList();
        if (list != null) {
            for (Moyen t : list) {
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
    public Moyen create(Moyen t) {
        Moyen loaded = findByReferenceEntity(t);
        Moyen saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Moyen findWithAssociatedLists(Long id){
        Moyen result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Moyen> update(List<Moyen> ts, boolean createIfNotExist) {
        List<Moyen> result = new ArrayList<>();
        if (ts != null) {
            for (Moyen t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Moyen loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Moyen t, Moyen loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Moyen findByReferenceEntity(Moyen t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<Moyen> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Moyen>> getToBeSavedAndToBeDeleted(List<Moyen> oldList, List<Moyen> newList) {
        List<List<Moyen>> result = new ArrayList<>();
        List<Moyen> resultDelete = new ArrayList<>();
        List<Moyen> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Moyen> oldList, List<Moyen> newList, List<Moyen> resultUpdateOrSave, List<Moyen> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Moyen myOld = oldList.get(i);
                Moyen t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Moyen myNew = newList.get(i);
                Moyen t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public MoyenAdminServiceImpl(MoyenRepository repository) {
        this.repository = repository;
    }

    private MoyenRepository repository;
}
