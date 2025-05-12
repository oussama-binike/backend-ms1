package ma.zyn.app.service.impl.admin.camion;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.repository.facade.core.camion.DestinationCamionRepository;
import ma.zyn.app.service.facade.admin.camion.DestinationCamionAdminService;

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
public class DestinationCamionAdminServiceImpl implements DestinationCamionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DestinationCamion update(DestinationCamion t) {
        DestinationCamion loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public DestinationCamion findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public DestinationCamion findOrSave(DestinationCamion t) {
        if (t != null) {
            DestinationCamion result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DestinationCamion> findAll() {
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
    public List<DestinationCamion> delete(List<DestinationCamion> list) {
		List<DestinationCamion> result = new ArrayList();
        if (list != null) {
            for (DestinationCamion t : list) {
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
    public DestinationCamion create(DestinationCamion t) {
        DestinationCamion loaded = findByReferenceEntity(t);
        DestinationCamion saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DestinationCamion findWithAssociatedLists(Long id){
        DestinationCamion result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DestinationCamion> update(List<DestinationCamion> ts, boolean createIfNotExist) {
        List<DestinationCamion> result = new ArrayList<>();
        if (ts != null) {
            for (DestinationCamion t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    DestinationCamion loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DestinationCamion t, DestinationCamion loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DestinationCamion findByReferenceEntity(DestinationCamion t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<DestinationCamion> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<DestinationCamion>> getToBeSavedAndToBeDeleted(List<DestinationCamion> oldList, List<DestinationCamion> newList) {
        List<List<DestinationCamion>> result = new ArrayList<>();
        List<DestinationCamion> resultDelete = new ArrayList<>();
        List<DestinationCamion> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DestinationCamion> oldList, List<DestinationCamion> newList, List<DestinationCamion> resultUpdateOrSave, List<DestinationCamion> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DestinationCamion myOld = oldList.get(i);
                DestinationCamion t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DestinationCamion myNew = newList.get(i);
                DestinationCamion t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public DestinationCamionAdminServiceImpl(DestinationCamionRepository repository) {
        this.repository = repository;
    }

    private DestinationCamionRepository repository;
}
