package ma.zyn.app.service.impl.admin.stock;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.repository.facade.core.stock.SuiviStockRepository;
import ma.zyn.app.service.facade.admin.stock.SuiviStockAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.LiaisonAdminService ;
import ma.zyn.app.bean.core.referentiel.Liaison ;
import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;

import java.util.List;
@Service
public class SuiviStockAdminServiceImpl implements SuiviStockAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SuiviStock update(SuiviStock t) {
        SuiviStock loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public SuiviStock findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public SuiviStock findOrSave(SuiviStock t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            SuiviStock result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SuiviStock> findAll() {
        return repository.findAll();
    }


    public List<SuiviStock> findByLiaisonId(Long id){
        return repository.findByLiaisonId(id);
    }
    public int deleteByLiaisonId(Long id){
        return repository.deleteByLiaisonId(id);
    }
    public long countByLiaisonCode(String code){
        return repository.countByLiaisonCode(code);
    }
    public List<SuiviStock> findByScenarioFluxId(Long id){
        return repository.findByScenarioFluxId(id);
    }
    public int deleteByScenarioFluxId(Long id){
        return repository.deleteByScenarioFluxId(id);
    }
    public long countByScenarioFluxCode(String code){
        return repository.countByScenarioFluxCode(code);
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
    public List<SuiviStock> delete(List<SuiviStock> list) {
		List<SuiviStock> result = new ArrayList();
        if (list != null) {
            for (SuiviStock t : list) {
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
    public SuiviStock create(SuiviStock t) {
        SuiviStock loaded = findByReferenceEntity(t);
        SuiviStock saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SuiviStock findWithAssociatedLists(Long id){
        SuiviStock result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SuiviStock> update(List<SuiviStock> ts, boolean createIfNotExist) {
        List<SuiviStock> result = new ArrayList<>();
        if (ts != null) {
            for (SuiviStock t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    SuiviStock loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SuiviStock t, SuiviStock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SuiviStock findByReferenceEntity(SuiviStock t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(SuiviStock t){
        if( t != null) {
            t.setLiaison(liaisonService.findOrSave(t.getLiaison()));
            t.setScenarioFlux(scenarioFluxService.findOrSave(t.getScenarioFlux()));
        }
    }



    public List<SuiviStock> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<SuiviStock>> getToBeSavedAndToBeDeleted(List<SuiviStock> oldList, List<SuiviStock> newList) {
        List<List<SuiviStock>> result = new ArrayList<>();
        List<SuiviStock> resultDelete = new ArrayList<>();
        List<SuiviStock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SuiviStock> oldList, List<SuiviStock> newList, List<SuiviStock> resultUpdateOrSave, List<SuiviStock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SuiviStock myOld = oldList.get(i);
                SuiviStock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SuiviStock myNew = newList.get(i);
                SuiviStock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private LiaisonAdminService liaisonService ;
    private ScenarioFluxAdminService scenarioFluxService ;

    public SuiviStockAdminServiceImpl(SuiviStockRepository repository, LiaisonAdminService liaisonService, ScenarioFluxAdminService scenarioFluxService) {
        this.repository = repository;
        this.liaisonService = liaisonService;
        this.scenarioFluxService = scenarioFluxService;
    }

    private SuiviStockRepository repository;
}
