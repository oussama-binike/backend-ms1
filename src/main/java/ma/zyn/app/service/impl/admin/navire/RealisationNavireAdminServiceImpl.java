package ma.zyn.app.service.impl.admin.navire;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.repository.facade.core.navire.RealisationNavireRepository;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireAdminService;

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

import ma.zyn.app.service.facade.admin.navire.DestinationNavireAdminService ;
import ma.zyn.app.bean.core.navire.DestinationNavire ;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireQualiteAdminService ;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite ;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireProduitAdminService ;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit ;

import java.util.List;
@Service
public class RealisationNavireAdminServiceImpl implements RealisationNavireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RealisationNavire update(RealisationNavire t) {
        RealisationNavire loadedItem = repository.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            return null;
        } else {
            updateWithAssociatedLists(t);
            repository.save(t);
            return loadedItem;
        }
    }

    public RealisationNavire findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public RealisationNavire findOrSave(RealisationNavire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            RealisationNavire result = findByReferenceEntity(t);
            if (result == null) {
                return repository.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<RealisationNavire> findAll() {
        return repository.findAll();
    }


    public List<RealisationNavire> findByDestinationNavireCode(String code){
        return repository.findByDestinationNavireCode(code);
    }
    public List<RealisationNavire> findByDestinationNavireId(Long id){
        return repository.findByDestinationNavireId(id);
    }
    public int deleteByDestinationNavireCode(String code){
        return repository.deleteByDestinationNavireCode(code);
    }
    public int deleteByDestinationNavireId(Long id){
        return repository.deleteByDestinationNavireId(id);
    }
    public long countByDestinationNavireCode(String code){
        return repository.countByDestinationNavireCode(code);
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
        realisationNavireProduitService.deleteByRealisationNavireId(id);
        realisationNavireQualiteService.deleteByRealisationNavireId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationNavire> delete(List<RealisationNavire> list) {
		List<RealisationNavire> result = new ArrayList();
        if (list != null) {
            for (RealisationNavire t : list) {
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
    public RealisationNavire create(RealisationNavire t) {
        RealisationNavire loaded = findByReferenceEntity(t);
        RealisationNavire saved;
        if (loaded == null) {
            saved = repository.save(t);
            if (t.getRealisationNavireProduits() != null) {
                t.getRealisationNavireProduits().forEach(element-> {
                    element.setRealisationNavire(saved);
                    realisationNavireProduitService.create(element);
                });
            }
            if (t.getRealisationNavireQualites() != null) {
                t.getRealisationNavireQualites().forEach(element-> {
                    element.setRealisationNavire(saved);
                    realisationNavireQualiteService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public RealisationNavire findWithAssociatedLists(Long id){
        RealisationNavire result = repository.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setRealisationNavireProduits(realisationNavireProduitService.findByRealisationNavireId(id));
            result.setRealisationNavireQualites(realisationNavireQualiteService.findByRealisationNavireId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RealisationNavire> update(List<RealisationNavire> ts, boolean createIfNotExist) {
        List<RealisationNavire> result = new ArrayList<>();
        if (ts != null) {
            for (RealisationNavire t : ts) {
                if (t.getId() == null) {
                    repository.save(t);
                } else {
                    RealisationNavire loadedItem = repository.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, RealisationNavire t, RealisationNavire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(RealisationNavire realisationNavire){
    if(realisationNavire !=null && realisationNavire.getId() != null){
        List<List<RealisationNavireProduit>> resultRealisationNavireProduits= realisationNavireProduitService.getToBeSavedAndToBeDeleted(realisationNavireProduitService.findByRealisationNavireId(realisationNavire.getId()),realisationNavire.getRealisationNavireProduits());
            realisationNavireProduitService.delete(resultRealisationNavireProduits.get(1));
        emptyIfNull(resultRealisationNavireProduits.get(0)).forEach(e -> e.setRealisationNavire(realisationNavire));
        realisationNavireProduitService.update(resultRealisationNavireProduits.get(0),true);
        List<List<RealisationNavireQualite>> resultRealisationNavireQualites= realisationNavireQualiteService.getToBeSavedAndToBeDeleted(realisationNavireQualiteService.findByRealisationNavireId(realisationNavire.getId()),realisationNavire.getRealisationNavireQualites());
            realisationNavireQualiteService.delete(resultRealisationNavireQualites.get(1));
        emptyIfNull(resultRealisationNavireQualites.get(0)).forEach(e -> e.setRealisationNavire(realisationNavire));
        realisationNavireQualiteService.update(resultRealisationNavireQualites.get(0),true);
        }
    }








    public RealisationNavire findByReferenceEntity(RealisationNavire t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(RealisationNavire t){
        if( t != null) {
            t.setDestinationNavire(destinationNavireService.findOrSave(t.getDestinationNavire()));
        }
    }



    public List<RealisationNavire> findAllOptimized() {
        return repository.findAllOptimized();
    }

    @Override
    public List<List<RealisationNavire>> getToBeSavedAndToBeDeleted(List<RealisationNavire> oldList, List<RealisationNavire> newList) {
        List<List<RealisationNavire>> result = new ArrayList<>();
        List<RealisationNavire> resultDelete = new ArrayList<>();
        List<RealisationNavire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<RealisationNavire> oldList, List<RealisationNavire> newList, List<RealisationNavire> resultUpdateOrSave, List<RealisationNavire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                RealisationNavire myOld = oldList.get(i);
                RealisationNavire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                RealisationNavire myNew = newList.get(i);
                RealisationNavire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}







    private DestinationNavireAdminService destinationNavireService ;
    private RealisationNavireQualiteAdminService realisationNavireQualiteService ;
    private RealisationNavireProduitAdminService realisationNavireProduitService ;

    public RealisationNavireAdminServiceImpl(RealisationNavireRepository repository, DestinationNavireAdminService destinationNavireService, RealisationNavireQualiteAdminService realisationNavireQualiteService, RealisationNavireProduitAdminService realisationNavireProduitService) {
        this.repository = repository;
        this.destinationNavireService = destinationNavireService;
        this.realisationNavireQualiteService = realisationNavireQualiteService;
        this.realisationNavireProduitService = realisationNavireProduitService;
    }

    private RealisationNavireRepository repository;
}
