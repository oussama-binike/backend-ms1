package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.repository.facade.core.referentiel.ChartePhysiqueRepository;
import ma.zyn.app.service.facade.admin.referentiel.ChartePhysiqueAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class ChartePhysiqueAdminServiceImpl implements ChartePhysiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ChartePhysique update(ChartePhysique t) {
        ChartePhysique loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public ChartePhysique findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public ChartePhysique findOrSave(ChartePhysique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ChartePhysique result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ChartePhysique> findAll() {
        return repository.findAll();
    }


    public List<ChartePhysique> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
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
    public List<ChartePhysique> delete(List<ChartePhysique> list) {
		List<ChartePhysique> result = new ArrayList();
        if (list != null) {
            for (ChartePhysique t : list) {
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
    public ChartePhysique create(ChartePhysique t) {
        ChartePhysique loaded = findByReferenceEntity(t);
        ChartePhysique saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ChartePhysique findWithAssociatedLists(Long id){
        ChartePhysique result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ChartePhysique> update(List<ChartePhysique> ts, boolean createIfNotExist) {
        List<ChartePhysique> result = new ArrayList<>();
        if (ts != null) {
            for (ChartePhysique t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    ChartePhysique loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ChartePhysique t, ChartePhysique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ChartePhysique findByReferenceEntity(ChartePhysique t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ChartePhysique t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
        }
    }



    public List<ChartePhysique> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<ChartePhysique>> getToBeSavedAndToBeDeleted(List<ChartePhysique> oldList, List<ChartePhysique> newList) {
        List<List<ChartePhysique>> result = new ArrayList<>();
        List<ChartePhysique> resultDelete = new ArrayList<>();
        List<ChartePhysique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ChartePhysique> oldList, List<ChartePhysique> newList, List<ChartePhysique> resultUpdateOrSave, List<ChartePhysique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ChartePhysique myOld = oldList.get(i);
                ChartePhysique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ChartePhysique myNew = newList.get(i);
                ChartePhysique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ProduitAdminService produitService ;

    public ChartePhysiqueAdminServiceImpl(ChartePhysiqueRepository repository, ProduitAdminService produitService) {
        this.repository = repository;
        this.produitService = produitService;
    }

    private ChartePhysiqueRepository repository;
}
