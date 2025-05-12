package ma.zyn.app.service.impl.admin.supply;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.repository.facade.core.supply.RealisationTrainRepository;
import ma.zyn.app.service.facade.admin.supply.RealisationTrainAdminService;

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

import ma.zyn.app.service.facade.admin.referentiel.TrainAdminService ;
import ma.zyn.app.bean.core.referentiel.Train ;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.ProvennanceTrainAdminService ;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain ;
import ma.zyn.app.service.facade.admin.referentiel.DestinationTrainAdminService ;
import ma.zyn.app.bean.core.referentiel.DestinationTrain ;
import ma.zyn.app.service.facade.admin.supply.RealisationTrainProduitAdminService ;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit ;
import ma.zyn.app.service.facade.admin.referentiel.TypeWagonAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeWagon ;

import java.util.List;
@Service
public class RealisationTrainAdminServiceImpl implements RealisationTrainAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationTrain update(RealisationTrain t) {
        RealisationTrain loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationTrain findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationTrain findOrSave(RealisationTrain t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationTrain result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationTrain> findAll() {
        return repository.findAll();
    }


    public List<RealisationTrain> findByProvennanceTrainCode(String code){
        return repository.findByProvennanceTrainCode(code);
    }
    public List<RealisationTrain> findByProvennanceTrainId(Long id){
        return repository.findByProvennanceTrainId(id);
    }
    public int deleteByProvennanceTrainCode(String code){
        return repository.deleteByProvennanceTrainCode(code);
    }
    public int deleteByProvennanceTrainId(Long id){
        return repository.deleteByProvennanceTrainId(id);
    }
    public long countByProvennanceTrainCode(String code){
        return repository.countByProvennanceTrainCode(code);
    }
    public List<RealisationTrain> findByDestinationTrainCode(String code){
        return repository.findByDestinationTrainCode(code);
    }
    public List<RealisationTrain> findByDestinationTrainId(Long id){
        return repository.findByDestinationTrainId(id);
    }
    public int deleteByDestinationTrainCode(String code){
        return repository.deleteByDestinationTrainCode(code);
    }
    public int deleteByDestinationTrainId(Long id){
        return repository.deleteByDestinationTrainId(id);
    }
    public long countByDestinationTrainCode(String code){
        return repository.countByDestinationTrainCode(code);
    }
    public List<RealisationTrain> findByTrainId(Long id){
        return repository.findByTrainId(id);
    }
    public int deleteByTrainId(Long id){
        return repository.deleteByTrainId(id);
    }
    public long countByTrainCode(String code){
        return repository.countByTrainCode(code);
    }
    public List<RealisationTrain> findByTypeWagonCode(String code){
        return repository.findByTypeWagonCode(code);
    }
    public List<RealisationTrain> findByTypeWagonId(Long id){
        return repository.findByTypeWagonId(id);
    }
    public int deleteByTypeWagonCode(String code){
        return repository.deleteByTypeWagonCode(code);
    }
    public int deleteByTypeWagonId(Long id){
        return repository.deleteByTypeWagonId(id);
    }
    public long countByTypeWagonCode(String code){
        return repository.countByTypeWagonCode(code);
    }
    public List<RealisationTrain> findByStadeOperatoireCode(String code){
        return repository.findByStadeOperatoireCode(code);
    }
    public List<RealisationTrain> findByStadeOperatoireId(Long id){
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
        realisationTrainProduitService.deleteByRealisationTrainId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationTrain> delete(List<RealisationTrain> list) {
		List<RealisationTrain> result = new ArrayList();
        if (list != null) {
            for (RealisationTrain t : list) {
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
    public RealisationTrain create(RealisationTrain t) {
        RealisationTrain loaded = findByReferenceEntity(t);
        RealisationTrain saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getRealisationTrainProduits() != null) {
                t.getRealisationTrainProduits().forEach(element-> {
                    element.setRealisationTrain(saved);
                    realisationTrainProduitService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationTrain findWithAssociatedLists(Long id){
        RealisationTrain result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRealisationTrainProduits(realisationTrainProduitService.findByRealisationTrainId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationTrain> update(List<RealisationTrain> ts, boolean createIfNotExist) {
        List<RealisationTrain> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationTrain t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationTrain loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationTrain t, RealisationTrain loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(RealisationTrain realisationTrain){
    if(realisationTrain !=null && realisationTrain.getId() != null){
        List<List<RealisationTrainProduit>> resultRealisationTrainProduits= realisationTrainProduitService.getToBeSavedAndToBeDeleted(realisationTrainProduitService.findByRealisationTrainId(realisationTrain.getId()),realisationTrain.getRealisationTrainProduits());
            realisationTrainProduitService.delete(resultRealisationTrainProduits.get(1));
        emptyIfNull(resultRealisationTrainProduits.get(0)).forEach(e -> e.setRealisationTrain(realisationTrain));
        realisationTrainProduitService.update(resultRealisationTrainProduits.get(0),true);
        }
    }








    public RealisationTrain findByReferenceEntity(RealisationTrain t){
        return t==null? null : repository.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(RealisationTrain t){
        if( t != null) {
            t.setProvennanceTrain(provennanceTrainService.findOrSave(t.getProvennanceTrain()));
            t.setDestinationTrain(destinationTrainService.findOrSave(t.getDestinationTrain()));
            t.setTrain(trainService.findOrSave(t.getTrain()));
            t.setTypeWagon(typeWagonService.findOrSave(t.getTypeWagon()));
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
        }
    }



    public List<RealisationTrain> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<RealisationTrain>> getToBeSavedAndToBeDeleted(List<RealisationTrain> oldList, List<RealisationTrain> newList) {
        List<List<RealisationTrain>> result = new ArrayList<>();
        List<RealisationTrain> resultDelete = new ArrayList<>();
        List<RealisationTrain> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationTrain> oldList, List<RealisationTrain> newList, List<RealisationTrain> resultUpdateOrSave, List<RealisationTrain> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationTrain myOld = oldList.get(i);
                RealisationTrain t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationTrain myNew = newList.get(i);
                RealisationTrain t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private TrainAdminService trainService ;
    private StadeOperatoireAdminService stadeOperatoireService ;
    private ProvennanceTrainAdminService provennanceTrainService ;
    private DestinationTrainAdminService destinationTrainService ;
    private RealisationTrainProduitAdminService realisationTrainProduitService ;
    private TypeWagonAdminService typeWagonService ;

    public RealisationTrainAdminServiceImpl(RealisationTrainRepository repository, TrainAdminService trainService, StadeOperatoireAdminService stadeOperatoireService, ProvennanceTrainAdminService provennanceTrainService, DestinationTrainAdminService destinationTrainService, RealisationTrainProduitAdminService realisationTrainProduitService, TypeWagonAdminService typeWagonService) {
        this.repository = repository;
        this.trainService = trainService;
        this.stadeOperatoireService = stadeOperatoireService;
        this.provennanceTrainService = provennanceTrainService;
        this.destinationTrainService = destinationTrainService;
        this.realisationTrainProduitService = realisationTrainProduitService;
        this.typeWagonService = typeWagonService;
    }

    private RealisationTrainRepository repository;
}
