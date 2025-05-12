package ma.zyn.app.service.facade.admin.camion;

import java.util.List;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit;



public interface RealisationCamionProduitAdminService {



    List<RealisationCamionProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationCamionProduit> findByRealisationCamionId(Long id);
    int deleteByRealisationCamionId(Long id);
    long countByRealisationCamionId(Long id);




	RealisationCamionProduit create(RealisationCamionProduit t);

    RealisationCamionProduit update(RealisationCamionProduit t);

    List<RealisationCamionProduit> update(List<RealisationCamionProduit> ts,boolean createIfNotExist);

    RealisationCamionProduit findById(Long id);

    RealisationCamionProduit findOrSave(RealisationCamionProduit t);

    RealisationCamionProduit findByReferenceEntity(RealisationCamionProduit t);

    RealisationCamionProduit findWithAssociatedLists(Long id);

    List<RealisationCamionProduit> findAllOptimized();

    List<RealisationCamionProduit> findAll();

    List<RealisationCamionProduit> delete(List<RealisationCamionProduit> ts);

    boolean deleteById(Long id);

    List<List<RealisationCamionProduit>> getToBeSavedAndToBeDeleted(List<RealisationCamionProduit> oldList, List<RealisationCamionProduit> newList);

}
