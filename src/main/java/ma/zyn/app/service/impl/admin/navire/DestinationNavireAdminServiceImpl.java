package ma.zyn.app.service.impl.admin.navire;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.repository.facade.core.navire.DestinationNavireRepository;
import ma.zyn.app.service.facade.admin.navire.DestinationNavireAdminService;

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
public class DestinationNavireAdminServiceImpl implements DestinationNavireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DestinationNavire update(DestinationNavire t) {
        DestinationNavire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public DestinationNavire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public DestinationNavire findOrSave(DestinationNavire t) {
        if (t != null) {
            DestinationNavire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DestinationNavire> findAll() {
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
    public List<DestinationNavire> delete(List<DestinationNavire> list) {
		List<DestinationNavire> result = new ArrayList();
        if (list != null) {
            for (DestinationNavire t : list) {
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
    public DestinationNavire create(DestinationNavire t) {
        DestinationNavire loaded = findByReferenceEntity(t);
        DestinationNavire saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DestinationNavire findWithAssociatedLists(Long id){
        DestinationNavire result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DestinationNavire> update(List<DestinationNavire> ts, boolean createIfNotExist) {
        List<DestinationNavire> result = new ArrayList<>();
        if (ts != null) {
            for (DestinationNavire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    DestinationNavire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DestinationNavire t, DestinationNavire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DestinationNavire findByReferenceEntity(DestinationNavire t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<DestinationNavire> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<DestinationNavire>> getToBeSavedAndToBeDeleted(List<DestinationNavire> oldList, List<DestinationNavire> newList) {
        List<List<DestinationNavire>> result = new ArrayList<>();
        List<DestinationNavire> resultDelete = new ArrayList<>();
        List<DestinationNavire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DestinationNavire> oldList, List<DestinationNavire> newList, List<DestinationNavire> resultUpdateOrSave, List<DestinationNavire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DestinationNavire myOld = oldList.get(i);
                DestinationNavire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DestinationNavire myNew = newList.get(i);
                DestinationNavire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public DestinationNavireAdminServiceImpl(DestinationNavireRepository repository) {
        this.repository = repository;
    }

    private DestinationNavireRepository repository;
}
