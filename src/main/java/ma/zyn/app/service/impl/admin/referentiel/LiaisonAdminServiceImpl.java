package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.repository.facade.core.referentiel.LiaisonRepository;
import ma.zyn.app.service.facade.admin.referentiel.LiaisonAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StockAdminService ;
import ma.zyn.app.bean.core.referentiel.Stock ;
import ma.zyn.app.service.facade.admin.referentiel.EnginAdminService ;
import ma.zyn.app.bean.core.referentiel.Engin ;
import ma.zyn.app.service.facade.admin.referentiel.OperationStadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class LiaisonAdminServiceImpl implements LiaisonAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Liaison update(Liaison t) {
        Liaison loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Liaison findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Liaison findOrSave(Liaison t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Liaison result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Liaison> findAll() {
        return repository.findAll();
    }


    public List<Liaison> findByStockSourceId(Long id){
        return repository.findByStockSourceId(id);
    }
    public int deleteByStockSourceId(Long id){
        return repository.deleteByStockSourceId(id);
    }
    public long countByStockSourceCode(String code){
        return repository.countByStockSourceCode(code);
    }
    public List<Liaison> findByStockDestinationId(Long id){
        return repository.findByStockDestinationId(id);
    }
    public int deleteByStockDestinationId(Long id){
        return repository.deleteByStockDestinationId(id);
    }
    public long countByStockDestinationCode(String code){
        return repository.countByStockDestinationCode(code);
    }
    public List<Liaison> findByEnginId(Long id){
        return repository.findByEnginId(id);
    }
    public int deleteByEnginId(Long id){
        return repository.deleteByEnginId(id);
    }
    public long countByEnginCode(String code){
        return repository.countByEnginCode(code);
    }
    public List<Liaison> findByOperationStadeOperatoireCode(String code){
        return repository.findByOperationStadeOperatoireCode(code);
    }
    public List<Liaison> findByOperationStadeOperatoireId(Long id){
        return repository.findByOperationStadeOperatoireId(id);
    }
    public int deleteByOperationStadeOperatoireCode(String code){
        return repository.deleteByOperationStadeOperatoireCode(code);
    }
    public int deleteByOperationStadeOperatoireId(Long id){
        return repository.deleteByOperationStadeOperatoireId(id);
    }
    public long countByOperationStadeOperatoireCode(String code){
        return repository.countByOperationStadeOperatoireCode(code);
    }
    public List<Liaison> findByProdduitId(Long id){
        return repository.findByProdduitId(id);
    }
    public int deleteByProdduitId(Long id){
        return repository.deleteByProdduitId(id);
    }
    public long countByProdduitCode(String code){
        return repository.countByProdduitCode(code);
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
    public List<Liaison> delete(List<Liaison> list) {
		List<Liaison> result = new ArrayList();
        if (list != null) {
            for (Liaison t : list) {
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
    public Liaison create(Liaison t) {
        Liaison loaded = findByReferenceEntity(t);
        Liaison saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Liaison findWithAssociatedLists(Long id){
        Liaison result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Liaison> update(List<Liaison> ts, boolean createIfNotExist) {
        List<Liaison> result = new ArrayList<>();
        if (ts != null) {
            for (Liaison t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Liaison loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Liaison t, Liaison loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Liaison findByReferenceEntity(Liaison t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Liaison t){
        if( t != null) {
            t.setStockSource(stockService.findOrSave(t.getStockSource()));
            t.setStockDestination(stockService.findOrSave(t.getStockDestination()));
            t.setEngin(enginService.findOrSave(t.getEngin()));
            t.setOperationStadeOperatoire(operationStadeOperatoireService.findOrSave(t.getOperationStadeOperatoire()));
            t.setProdduit(produitService.findOrSave(t.getProdduit()));
        }
    }



    public List<Liaison> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Liaison>> getToBeSavedAndToBeDeleted(List<Liaison> oldList, List<Liaison> newList) {
        List<List<Liaison>> result = new ArrayList<>();
        List<Liaison> resultDelete = new ArrayList<>();
        List<Liaison> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Liaison> oldList, List<Liaison> newList, List<Liaison> resultUpdateOrSave, List<Liaison> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Liaison myOld = oldList.get(i);
                Liaison t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Liaison myNew = newList.get(i);
                Liaison t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StockAdminService stockService ;
    private EnginAdminService enginService ;
    private OperationStadeOperatoireAdminService operationStadeOperatoireService ;
    private ProduitAdminService produitService ;

    public LiaisonAdminServiceImpl(LiaisonRepository repository, StockAdminService stockService, EnginAdminService enginService, OperationStadeOperatoireAdminService operationStadeOperatoireService, ProduitAdminService produitService) {
        this.repository = repository;
        this.stockService = stockService;
        this.enginService = enginService;
        this.operationStadeOperatoireService = operationStadeOperatoireService;
        this.produitService = produitService;
    }

    private LiaisonRepository repository;
}
