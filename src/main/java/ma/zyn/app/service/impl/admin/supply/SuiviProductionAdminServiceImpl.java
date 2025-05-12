package ma.zyn.app.service.impl.admin.supply;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.repository.facade.core.supply.SuiviProductionRepository;
import ma.zyn.app.service.facade.admin.supply.SuiviProductionAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService ;
import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;
import ma.zyn.app.service.facade.admin.referentiel.MoyenAdminService ;
import ma.zyn.app.bean.core.referentiel.Moyen ;

import java.util.List;
@Service
public class SuiviProductionAdminServiceImpl implements SuiviProductionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SuiviProduction update(SuiviProduction t) {
        SuiviProduction loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public SuiviProduction findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public SuiviProduction findOrSave(SuiviProduction t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            SuiviProduction result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SuiviProduction> findAll() {
        return repository.findAll();
    }


    public List<SuiviProduction> findByProduitId(Long id){
        return repository.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return repository.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return repository.countByProduitCode(code);
    }
    public List<SuiviProduction> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<SuiviProduction> findByStadeOperatoireId(Long id){
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
    public List<SuiviProduction> findByUniteCode(String code){
        return repository.findByUniteCode(code);
    }
    public List<SuiviProduction> findByUniteId(Long id){
        return repository.findByUniteId(id);
    }
    public int deleteByUniteCode(String code){
        return repository.deleteByUniteCode(code);
    }
    public int deleteByUniteId(Long id){
        return repository.deleteByUniteId(id);
    }
    public long countByUniteCode(String code){
        return repository.countByUniteCode(code);
    }
    public List<SuiviProduction> findByMoyenCode(String code){
        return repository.findByMoyenCode(code);
    }
    public List<SuiviProduction> findByMoyenId(Long id){
        return repository.findByMoyenId(id);
    }
    public int deleteByMoyenCode(String code){
        return repository.deleteByMoyenCode(code);
    }
    public int deleteByMoyenId(Long id){
        return repository.deleteByMoyenId(id);
    }
    public long countByMoyenCode(String code){
        return repository.countByMoyenCode(code);
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
    public List<SuiviProduction> delete(List<SuiviProduction> list) {
		List<SuiviProduction> result = new ArrayList();
        if (list != null) {
            for (SuiviProduction t : list) {
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
    public SuiviProduction create(SuiviProduction t) {
        SuiviProduction loaded = findByReferenceEntity(t);
        SuiviProduction saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SuiviProduction findWithAssociatedLists(Long id){
        SuiviProduction result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SuiviProduction> update(List<SuiviProduction> ts, boolean createIfNotExist) {
        List<SuiviProduction> result = new ArrayList<>();
        if (ts != null) {
            for (SuiviProduction t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    SuiviProduction loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SuiviProduction t, SuiviProduction loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SuiviProduction findByReferenceEntity(SuiviProduction t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(SuiviProduction t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
            t.setMoyen(moyenService.findOrSave(t.getMoyen()));
        }
    }



    public List<SuiviProduction> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<SuiviProduction>> getToBeSavedAndToBeDeleted(List<SuiviProduction> oldList, List<SuiviProduction> newList) {
        List<List<SuiviProduction>> result = new ArrayList<>();
        List<SuiviProduction> resultDelete = new ArrayList<>();
        List<SuiviProduction> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SuiviProduction> oldList, List<SuiviProduction> newList, List<SuiviProduction> resultUpdateOrSave, List<SuiviProduction> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SuiviProduction myOld = oldList.get(i);
                SuiviProduction t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SuiviProduction myNew = newList.get(i);
                SuiviProduction t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private UniteAdminService uniteService ;
    private StadeOperatoireAdminService stadeOperatoireService ;
    private ProduitAdminService produitService ;
    private MoyenAdminService moyenService ;

    public SuiviProductionAdminServiceImpl(SuiviProductionRepository repository, UniteAdminService uniteService, StadeOperatoireAdminService stadeOperatoireService, ProduitAdminService produitService, MoyenAdminService moyenService) {
        this.repository = repository;
        this.uniteService = uniteService;
        this.stadeOperatoireService = stadeOperatoireService;
        this.produitService = produitService;
        this.moyenService = moyenService;
    }

    private SuiviProductionRepository repository;
}
