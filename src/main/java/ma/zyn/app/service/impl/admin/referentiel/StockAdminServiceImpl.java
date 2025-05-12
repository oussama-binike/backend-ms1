package ma.zyn.app.service.impl.admin.referentiel;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.repository.facade.core.referentiel.StockRepository;
import ma.zyn.app.service.facade.admin.referentiel.StockAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.TypeStockAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeStock ;
import ma.zyn.app.service.facade.admin.referentiel.CategorieStockAdminService ;
import ma.zyn.app.bean.core.referentiel.CategorieStock ;

import java.util.List;
@Service
public class StockAdminServiceImpl implements StockAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Stock update(Stock t) {
        Stock loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Stock findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Stock findOrSave(Stock t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Stock result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Stock> findAll() {
        return repository.findAll();
    }


    public List<Stock> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<Stock> findByStadeOperatoireId(Long id){
        return repository.findByStadeOperatoireId(id);
    }
    public int deleteByStadeOperatoireCode(String code){
        return repository.deleteByStadeOperatoireCode(code);
    }
    public int deleteByStadeOperatoireId(Long id){
        return repository.deleteByStadeOperatoireId(id);
    }
    public long countByStadeOperatoireCode(String code){
        return repository.countByStadeOperatoireCode(code);
    }
    public List<Stock> findByTypeStockCode(String code){
        return repository.findByTypeStockCode(code);
    }
    public List<Stock> findByTypeStockId(Long id){
        return repository.findByTypeStockId(id);
    }
    public int deleteByTypeStockCode(String code){
        return repository.deleteByTypeStockCode(code);
    }
    public int deleteByTypeStockId(Long id){
        return repository.deleteByTypeStockId(id);
    }
    public long countByTypeStockCode(String code){
        return repository.countByTypeStockCode(code);
    }
    public List<Stock> findByCategorieStockCode(String code){
        return repository.findByCategorieStockCode(code);
    }
    public List<Stock> findByCategorieStockId(Long id){
        return repository.findByCategorieStockId(id);
    }
    public int deleteByCategorieStockCode(String code){
        return repository.deleteByCategorieStockCode(code);
    }
    public int deleteByCategorieStockId(Long id){
        return repository.deleteByCategorieStockId(id);
    }
    public long countByCategorieStockCode(String code){
        return repository.countByCategorieStockCode(code);
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
    public List<Stock> delete(List<Stock> list) {
		List<Stock> result = new ArrayList();
        if (list != null) {
            for (Stock t : list) {
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
    public Stock create(Stock t) {
        Stock loaded = findByReferenceEntity(t);
        Stock saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Stock findWithAssociatedLists(Long id){
        Stock result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stock> update(List<Stock> ts, boolean createIfNotExist) {
        List<Stock> result = new ArrayList<>();
        if (ts != null) {
            for (Stock t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Stock loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Stock t, Stock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Stock findByReferenceEntity(Stock t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Stock t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setTypeStock(typeStockService.findOrSave(t.getTypeStock()));
            t.setCategorieStock(categorieStockService.findOrSave(t.getCategorieStock()));
        }
    }



    public List<Stock> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Stock>> getToBeSavedAndToBeDeleted(List<Stock> oldList, List<Stock> newList) {
        List<List<Stock>> result = new ArrayList<>();
        List<Stock> resultDelete = new ArrayList<>();
        List<Stock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Stock> oldList, List<Stock> newList, List<Stock> resultUpdateOrSave, List<Stock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Stock myOld = oldList.get(i);
                Stock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Stock myNew = newList.get(i);
                Stock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private StadeOperatoireAdminService stadeOperatoireService ;
    private TypeStockAdminService typeStockService ;
    private CategorieStockAdminService categorieStockService ;

    public StockAdminServiceImpl(StockRepository repository, StadeOperatoireAdminService stadeOperatoireService, TypeStockAdminService typeStockService, CategorieStockAdminService categorieStockService) {
        this.repository = repository;
        this.stadeOperatoireService = stadeOperatoireService;
        this.typeStockService = typeStockService;
        this.categorieStockService = categorieStockService;
    }

    private StockRepository repository;
}
