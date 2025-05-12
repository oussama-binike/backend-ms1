package ma.zyn.app.service.impl.admin.demande;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.repository.facade.core.demande.DemandeRepository;
import ma.zyn.app.service.facade.admin.demande.DemandeAdminService;

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

import ma.zyn.app.service.facade.admin.scenario.ScenarioFluxAdminService ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService ;
import ma.zyn.app.bean.core.referentiel.EtatDemande ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeDemande ;
import ma.zyn.app.service.facade.admin.scenario.ExerciceAdminService ;
import ma.zyn.app.bean.core.scenario.Exercice ;
import ma.zyn.app.service.facade.admin.referentiel.ClientAdminService ;
import ma.zyn.app.bean.core.referentiel.Client ;

import java.util.List;
@Service
public class DemandeAdminServiceImpl implements DemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Demande update(Demande t) {
        Demande loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            repository.save(t);
            return loadedItem;
        }
    }

    public Demande findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Demande findOrSave(Demande t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Demande result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Demande> findAll() {
        return repository.findAll();
    }


    public List<Demande> findByProduitMarchandCode(String code){
        return repository.findByProduitMarchandCode(code);
    }
    public List<Demande> findByProduitMarchandId(Long id){
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
    public List<Demande> findByClientId(Long id){
        return repository.findByClientId(id);
    }
    public int deleteByClientId(Long id){
        return repository.deleteByClientId(id);
    }
    public long countByClientCode(String code){
        return repository.countByClientCode(code);
    }
    public List<Demande> findByTypeDemandeCode(String code){
        return repository.findByTypeDemandeCode(code);
    }
    public List<Demande> findByTypeDemandeId(Long id){
        return repository.findByTypeDemandeId(id);
    }
    public int deleteByTypeDemandeCode(String code){
        return repository.deleteByTypeDemandeCode(code);
    }
    public int deleteByTypeDemandeId(Long id){
        return repository.deleteByTypeDemandeId(id);
    }
    public long countByTypeDemandeCode(String code){
        return repository.countByTypeDemandeCode(code);
    }
    public List<Demande> findByEtatDemandeCode(String code){
        return repository.findByEtatDemandeCode(code);
    }
    public List<Demande> findByEtatDemandeId(Long id){
        return repository.findByEtatDemandeId(id);
    }
    public int deleteByEtatDemandeCode(String code){
        return repository.deleteByEtatDemandeCode(code);
    }
    public int deleteByEtatDemandeId(Long id){
        return repository.deleteByEtatDemandeId(id);
    }
    public long countByEtatDemandeCode(String code){
        return repository.countByEtatDemandeCode(code);
    }
    public List<Demande> findByScenarioFluxId(Long id){
        return repository.findByScenarioFluxId(id);
    }
    public int deleteByScenarioFluxId(Long id){
        return repository.deleteByScenarioFluxId(id);
    }
    public long countByScenarioFluxCode(String code){
        return repository.countByScenarioFluxCode(code);
    }
    public List<Demande> findByExerciceId(Long id){
        return repository.findByExerciceId(id);
    }
    public int deleteByExerciceId(Long id){
        return repository.deleteByExerciceId(id);
    }
    public long countByExerciceCode(String code){
        return repository.countByExerciceCode(code);
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
    public List<Demande> delete(List<Demande> list) {
		List<Demande> result = new ArrayList();
        if (list != null) {
            for (Demande t : list) {
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
    public Demande create(Demande t) {
        Demande loaded = findByReferenceEntity(t);
        Demande saved;
        if (loaded == null) {
            saved = repository.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Demande findWithAssociatedLists(Long id){
        Demande result = repository.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Demande> update(List<Demande> ts, boolean createIfNotExist) {
        List<Demande> result = new ArrayList<>();
        if (ts != null) {
            for (Demande t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    Demande loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Demande t, Demande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Demande findByReferenceEntity(Demande t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Demande t){
        if( t != null) {
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setTypeDemande(typeDemandeService.findOrSave(t.getTypeDemande()));
            t.setEtatDemande(etatDemandeService.findOrSave(t.getEtatDemande()));
            t.setScenarioFlux(scenarioFluxService.findOrSave(t.getScenarioFlux()));
            t.setExercice(exerciceService.findOrSave(t.getExercice()));
        }
    }



    public List<Demande> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<Demande>> getToBeSavedAndToBeDeleted(List<Demande> oldList, List<Demande> newList) {
        List<List<Demande>> result = new ArrayList<>();
        List<Demande> resultDelete = new ArrayList<>();
        List<Demande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Demande> oldList, List<Demande> newList, List<Demande> resultUpdateOrSave, List<Demande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Demande myOld = oldList.get(i);
                Demande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Demande myNew = newList.get(i);
                Demande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private ScenarioFluxAdminService scenarioFluxService ;
    private EtatDemandeAdminService etatDemandeService ;
    private ProduitMarchandAdminService produitMarchandService ;
    private TypeDemandeAdminService typeDemandeService ;
    private ExerciceAdminService exerciceService ;
    private ClientAdminService clientService ;

    public DemandeAdminServiceImpl(DemandeRepository repository, ScenarioFluxAdminService scenarioFluxService, EtatDemandeAdminService etatDemandeService, ProduitMarchandAdminService produitMarchandService, TypeDemandeAdminService typeDemandeService, ExerciceAdminService exerciceService, ClientAdminService clientService) {
        this.repository = repository;
        this.scenarioFluxService = scenarioFluxService;
        this.etatDemandeService = etatDemandeService;
        this.produitMarchandService = produitMarchandService;
        this.typeDemandeService = typeDemandeService;
        this.exerciceService = exerciceService;
        this.clientService = clientService;
    }

    private DemandeRepository repository;
}
