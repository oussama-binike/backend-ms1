package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.CharteChimique;
import ma.zyn.app.repository.facade.core.referentiel.CharteChimiqueRepository;
import ma.zyn.app.service.facade.admin.referentiel.CharteChimiqueAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class CharteChimiqueAdminServiceImpl implements CharteChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CharteChimique update(CharteChimique t) {
        CharteChimique loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public CharteChimique findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public CharteChimique findOrSave(CharteChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CharteChimique result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CharteChimique> findAll() {
        return repository.findAll();
    }


    public List<CharteChimique> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
    }
    public List<CharteChimique> findByElementChimiqueCode(String code){
        return repository.findByElementChimiqueCode(code);
    }
    public List<CharteChimique> findByElementChimiqueId(Long id){
        return repository.findByElementChimiqueId(id);
    }
    public int deleteByElementChimiqueCode(String code){
        return repository.deleteByElementChimiqueCode(code);
    }
    public int deleteByElementChimiqueId(Long id){
        return repository.deleteByElementChimiqueId(id);
    }
    public long countByElementChimiqueCode(String code){
        return repository.countByElementChimiqueCode(code);
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
    public List<CharteChimique> delete(List<CharteChimique> list) {
		List<CharteChimique> result = new ArrayList();
        if (list != null) {
            for (CharteChimique t : list) {
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
    public CharteChimique create(CharteChimique t) {
        CharteChimique loaded = findByReferenceEntity(t);
        CharteChimique saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CharteChimique findWithAssociatedLists(Long id){
        CharteChimique result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CharteChimique> update(List<CharteChimique> ts, boolean createIfNotExist) {
        List<CharteChimique> result = new ArrayList<>();
        if (ts != null) {
            for (CharteChimique t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    CharteChimique loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CharteChimique t, CharteChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CharteChimique findByReferenceEntity(CharteChimique t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(CharteChimique t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setElementChimique(elementChimiqueService.findOrSave(t.getElementChimique()));
        }
    }



    public List<CharteChimique> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<CharteChimique>> getToBeSavedAndToBeDeleted(List<CharteChimique> oldList, List<CharteChimique> newList) {
        List<List<CharteChimique>> result = new ArrayList<>();
        List<CharteChimique> resultDelete = new ArrayList<>();
        List<CharteChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CharteChimique> oldList, List<CharteChimique> newList, List<CharteChimique> resultUpdateOrSave, List<CharteChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CharteChimique myOld = oldList.get(i);
                CharteChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CharteChimique myNew = newList.get(i);
                CharteChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ElementChimiqueAdminService elementChimiqueService ;
    private ProduitAdminService produitService ;

    public CharteChimiqueAdminServiceImpl(CharteChimiqueRepository repository, ElementChimiqueAdminService elementChimiqueService, ProduitAdminService produitService) {
        this.repository = repository;
        this.elementChimiqueService = elementChimiqueService;
        this.produitService = produitService;
    }

    private CharteChimiqueRepository repository;
}
