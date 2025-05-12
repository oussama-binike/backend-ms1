package ma.zyn.app.service.impl.admin.reclamation;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.repository.facade.core.reclamation.ReclamationRepository;
import ma.zyn.app.service.facade.admin.reclamation.ReclamationAdminService;

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

import ma.zyn.app.service.facade.admin.reclamation.ReclamationElementChimiqueAdminService ;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.service.facade.admin.reclamation.EtatReclamationAdminService ;
import ma.zyn.app.bean.core.reclamation.EtatReclamation ;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Entite ;

import java.util.List;
@Service
public class ReclamationAdminServiceImpl implements ReclamationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Reclamation update(Reclamation t) {
        Reclamation loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public Reclamation findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Reclamation findOrSave(Reclamation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Reclamation result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Reclamation> findAll() {
        return repository.findAll();
    }


    public List<Reclamation> findByEntiteEmettriceCode(String code){
        return repository.findByEntiteEmettriceCode(code);
    }
    public List<Reclamation> findByEntiteEmettriceId(Long id){
        return repository.findByEntiteEmettriceId(id);
    }
    public int deleteByEntiteEmettriceCode(String code){
        return repository.deleteByEntiteEmettriceCode(code);
    }
    public int deleteByEntiteEmettriceId(Long id){
        return repository.deleteByEntiteEmettriceId(id);
    }
    public long countByEntiteEmettriceCode(String code){
        return repository.countByEntiteEmettriceCode(code);
    }
    public List<Reclamation> findByEntiteDistinataireCode(String code){
        return repository.findByEntiteDistinataireCode(code);
    }
    public List<Reclamation> findByEntiteDistinataireId(Long id){
        return repository.findByEntiteDistinataireId(id);
    }
    public int deleteByEntiteDistinataireCode(String code){
        return repository.deleteByEntiteDistinataireCode(code);
    }
    public int deleteByEntiteDistinataireId(Long id){
        return repository.deleteByEntiteDistinataireId(id);
    }
    public long countByEntiteDistinataireCode(String code){
        return repository.countByEntiteDistinataireCode(code);
    }
    public List<Reclamation> findByProduitMarchandCode(String code){
        return repository.findByProduitMarchandCode(code);
    }
    public List<Reclamation> findByProduitMarchandId(Long id){
        return repository.findByProduitMarchandId(id);
    }
    public int deleteByProduitMarchandCode(String code){
        return repository.deleteByProduitMarchandCode(code);
    }
    public int deleteByProduitMarchandId(Long id){
        return repository.deleteByProduitMarchandId(id);
    }
    public long countByProduitMarchandCode(String code){
        return repository.countByProduitMarchandCode(code);
    }
    public List<Reclamation> findByEtatReclamationCode(String code){
        return repository.findByEtatReclamationCode(code);
    }
    public List<Reclamation> findByEtatReclamationId(Long id){
        return repository.findByEtatReclamationId(id);
    }
    public int deleteByEtatReclamationCode(String code){
        return repository.deleteByEtatReclamationCode(code);
    }
    public int deleteByEtatReclamationId(Long id){
        return repository.deleteByEtatReclamationId(id);
    }
    public long countByEtatReclamationCode(String code){
        return repository.countByEtatReclamationCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            repository.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        reclamationElementChimiqueService.deleteByReclamationId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Reclamation> delete(List<Reclamation> list) {
		List<Reclamation> result = new ArrayList();
        if (list != null) {
            for (Reclamation t : list) {
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
    public Reclamation create(Reclamation t) {
        Reclamation loaded = findByReferenceEntity(t);
        Reclamation saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getReclamationElementChimiques() != null) {
                t.getReclamationElementChimiques().forEach(element-> {
                    element.setReclamation(saved);
                    reclamationElementChimiqueService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Reclamation findWithAssociatedLists(Long id){
        Reclamation result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setReclamationElementChimiques(reclamationElementChimiqueService.findByReclamationId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Reclamation> update(List<Reclamation> ts, boolean createIfNotExist) {
        List<Reclamation> result = new ArrayList<>();
        if (ts != null) {
            for (Reclamation t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Reclamation loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Reclamation t, Reclamation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Reclamation reclamation){
    if(reclamation !=null && reclamation.getId() != null){
        List<List<ReclamationElementChimique>> resultReclamationElementChimiques= reclamationElementChimiqueService.getToBeSavedAndToBeDeleted(reclamationElementChimiqueService.findByReclamationId(reclamation.getId()),reclamation.getReclamationElementChimiques());
            reclamationElementChimiqueService.delete(resultReclamationElementChimiques.get(1));
        emptyIfNull(resultReclamationElementChimiques.get(0)).forEach(e -> e.setReclamation(reclamation));
        reclamationElementChimiqueService.update(resultReclamationElementChimiques.get(0),true);
        }
    }








    public Reclamation findByReferenceEntity(Reclamation t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Reclamation t){
        if( t != null) {
            t.setEntiteEmettrice(entiteService.findOrSave(t.getEntiteEmettrice()));
            t.setEntiteDistinataire(entiteService.findOrSave(t.getEntiteDistinataire()));
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
            t.setEtatReclamation(etatReclamationService.findOrSave(t.getEtatReclamation()));
        }
    }



    public List<Reclamation> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Reclamation>> getToBeSavedAndToBeDeleted(List<Reclamation> oldList, List<Reclamation> newList) {
        List<List<Reclamation>> result = new ArrayList<>();
        List<Reclamation> resultDelete = new ArrayList<>();
        List<Reclamation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Reclamation> oldList, List<Reclamation> newList, List<Reclamation> resultUpdateOrSave, List<Reclamation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Reclamation myOld = oldList.get(i);
                Reclamation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Reclamation myNew = newList.get(i);
                Reclamation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ReclamationElementChimiqueAdminService reclamationElementChimiqueService ;
    private ProduitMarchandAdminService produitMarchandService ;
    private EtatReclamationAdminService etatReclamationService ;
    private EntiteAdminService entiteService ;

    public ReclamationAdminServiceImpl(ReclamationRepository repository, ReclamationElementChimiqueAdminService reclamationElementChimiqueService, ProduitMarchandAdminService produitMarchandService, EtatReclamationAdminService etatReclamationService, EntiteAdminService entiteService) {
        this.repository = repository;
        this.reclamationElementChimiqueService = reclamationElementChimiqueService;
        this.produitMarchandService = produitMarchandService;
        this.etatReclamationService = etatReclamationService;
        this.entiteService = entiteService;
    }

    private ReclamationRepository repository;
}
