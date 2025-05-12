package ma.zyn.app.service.facade.admin.supply;

import java.util.List;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit;



public interface RealisationTrainProduitAdminService {



    List<RealisationTrainProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationTrainProduit> findByRealisationTrainId(Long id);
    int deleteByRealisationTrainId(Long id);
    long countByRealisationTrainCode(String code);




	RealisationTrainProduit create(RealisationTrainProduit t);

    RealisationTrainProduit update(RealisationTrainProduit t);

    List<RealisationTrainProduit> update(List<RealisationTrainProduit> ts,boolean createIfNotExist);

    RealisationTrainProduit findById(Long id);

    RealisationTrainProduit findOrSave(RealisationTrainProduit t);

    RealisationTrainProduit findByReferenceEntity(RealisationTrainProduit t);

    RealisationTrainProduit findWithAssociatedLists(Long id);

    List<RealisationTrainProduit> findAllOptimized();

    List<RealisationTrainProduit> findAll();

    List<RealisationTrainProduit> delete(List<RealisationTrainProduit> ts);

    boolean deleteById(Long id);

    List<List<RealisationTrainProduit>> getToBeSavedAndToBeDeleted(List<RealisationTrainProduit> oldList, List<RealisationTrainProduit> newList);

}
