package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.repository.facade.core.referentiel.EtatDemandeRepository;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService;

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
public class EtatDemandeAdminServiceImpl implements EtatDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatDemande update(EtatDemande t) {
        EtatDemande loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public EtatDemande findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public EtatDemande findOrSave(EtatDemande t) {
        if (t != null) {
            EtatDemande result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EtatDemande> findAll() {
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
    public List<EtatDemande> delete(List<EtatDemande> list) {
		List<EtatDemande> result = new ArrayList();
        if (list != null) {
            for (EtatDemande t : list) {
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
    public EtatDemande create(EtatDemande t) {
        EtatDemande loaded = findByReferenceEntity(t);
        EtatDemande saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EtatDemande findWithAssociatedLists(Long id){
        EtatDemande result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatDemande> update(List<EtatDemande> ts, boolean createIfNotExist) {
        List<EtatDemande> result = new ArrayList<>();
        if (ts != null) {
            for (EtatDemande t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    EtatDemande loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EtatDemande t, EtatDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EtatDemande findByReferenceEntity(EtatDemande t){
        return t==null? null : repository.findByCode(t.getCode());
    }



    public List<EtatDemande> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<EtatDemande>> getToBeSavedAndToBeDeleted(List<EtatDemande> oldList, List<EtatDemande> newList) {
        List<List<EtatDemande>> result = new ArrayList<>();
        List<EtatDemande> resultDelete = new ArrayList<>();
        List<EtatDemande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EtatDemande> oldList, List<EtatDemande> newList, List<EtatDemande> resultUpdateOrSave, List<EtatDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EtatDemande myOld = oldList.get(i);
                EtatDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EtatDemande myNew = newList.get(i);
                EtatDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public EtatDemandeAdminServiceImpl(EtatDemandeRepository repository) {
        this.repository = repository;
    }

    private EtatDemandeRepository repository;
}
