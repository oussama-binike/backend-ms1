package ma.zyn.app.service.impl.admin.camion;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.repository.facade.core.camion.RealisationCamionRepository;
import ma.zyn.app.service.facade.admin.camion.RealisationCamionAdminService;

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

import ma.zyn.app.service.facade.admin.camion.RealisationCamionProduitAdminService ;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit ;
import ma.zyn.app.service.facade.admin.camion.ProvennanceCamionAdminService ;
import ma.zyn.app.bean.core.camion.ProvennanceCamion ;
import ma.zyn.app.service.facade.admin.camion.DestinationCamionAdminService ;
import ma.zyn.app.bean.core.camion.DestinationCamion ;

import java.util.List;
@Service
public class RealisationCamionAdminServiceImpl implements RealisationCamionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationCamion update(RealisationCamion t) {
        RealisationCamion loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationCamion findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationCamion findOrSave(RealisationCamion t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationCamion result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationCamion> findAll() {
        return repository.findAll();
    }


    public List<RealisationCamion> findByProvennanceCamionCode(String code){
        return repository.findByProvennanceCamionCode(code);
    }
    public List<RealisationCamion> findByProvennanceCamionId(Long id){
        return repository.findByProvennanceCamionId(id);
    }
    public int deleteByProvennanceCamionCode(String code){
        return repository.deleteByProvennanceCamionCode(code);
    }
    public int deleteByProvennanceCamionId(Long id){
        return repository.deleteByProvennanceCamionId(id);
    }
    public long countByProvennanceCamionCode(String code){
        return repository.countByProvennanceCamionCode(code);
    }
    public List<RealisationCamion> findByDestinationCamionCode(String code){
        return repository.findByDestinationCamionCode(code);
    }
    public List<RealisationCamion> findByDestinationCamionId(Long id){
        return repository.findByDestinationCamionId(id);
    }
    public int deleteByDestinationCamionCode(String code){
        return repository.deleteByDestinationCamionCode(code);
    }
    public int deleteByDestinationCamionId(Long id){
        return repository.deleteByDestinationCamionId(id);
    }
    public long countByDestinationCamionCode(String code){
        return repository.countByDestinationCamionCode(code);
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
        realisationCamionProduitService.deleteByRealisationCamionId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationCamion> delete(List<RealisationCamion> list) {
		List<RealisationCamion> result = new ArrayList();
        if (list != null) {
            for (RealisationCamion t : list) {
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
    public RealisationCamion create(RealisationCamion t) {
        RealisationCamion loaded = findByReferenceEntity(t);
        RealisationCamion saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getRealisationCamionProduits() != null) {
                t.getRealisationCamionProduits().forEach(element-> {
                    element.setRealisationCamion(saved);
                    realisationCamionProduitService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationCamion findWithAssociatedLists(Long id){
        RealisationCamion result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRealisationCamionProduits(realisationCamionProduitService.findByRealisationCamionId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationCamion> update(List<RealisationCamion> ts, boolean createIfNotExist) {
        List<RealisationCamion> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationCamion t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationCamion loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationCamion t, RealisationCamion loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(RealisationCamion realisationCamion){
    if(realisationCamion !=null && realisationCamion.getId() != null){
        List<List<RealisationCamionProduit>> resultRealisationCamionProduits= realisationCamionProduitService.getToBeSavedAndToBeDeleted(realisationCamionProduitService.findByRealisationCamionId(realisationCamion.getId()),realisationCamion.getRealisationCamionProduits());
            realisationCamionProduitService.delete(resultRealisationCamionProduits.get(1));
        emptyIfNull(resultRealisationCamionProduits.get(0)).forEach(e -> e.setRealisationCamion(realisationCamion));
        realisationCamionProduitService.update(resultRealisationCamionProduits.get(0),true);
        }
    }








    public RealisationCamion findByReferenceEntity(RealisationCamion t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationCamion t){
        if( t != null) {
            t.setProvennanceCamion(provennanceCamionService.findOrSave(t.getProvennanceCamion()));
            t.setDestinationCamion(destinationCamionService.findOrSave(t.getDestinationCamion()));
        }
    }



    public List<RealisationCamion> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<RealisationCamion>> getToBeSavedAndToBeDeleted(List<RealisationCamion> oldList, List<RealisationCamion> newList) {
        List<List<RealisationCamion>> result = new ArrayList<>();
        List<RealisationCamion> resultDelete = new ArrayList<>();
        List<RealisationCamion> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationCamion> oldList, List<RealisationCamion> newList, List<RealisationCamion> resultUpdateOrSave, List<RealisationCamion> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationCamion myOld = oldList.get(i);
                RealisationCamion t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationCamion myNew = newList.get(i);
                RealisationCamion t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private RealisationCamionProduitAdminService realisationCamionProduitService ;
    private ProvennanceCamionAdminService provennanceCamionService ;
    private DestinationCamionAdminService destinationCamionService ;

    public RealisationCamionAdminServiceImpl(RealisationCamionRepository repository, RealisationCamionProduitAdminService realisationCamionProduitService, ProvennanceCamionAdminService provennanceCamionService, DestinationCamionAdminService destinationCamionService) {
        this.repository = repository;
        this.realisationCamionProduitService = realisationCamionProduitService;
        this.provennanceCamionService = provennanceCamionService;
        this.destinationCamionService = destinationCamionService;
    }

    private RealisationCamionRepository repository;
}
