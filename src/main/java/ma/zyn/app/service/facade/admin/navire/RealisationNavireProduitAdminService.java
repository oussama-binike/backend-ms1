package ma.zyn.app.service.facade.admin.navire;

import java.util.List;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit;



public interface RealisationNavireProduitAdminService {



    List<RealisationNavireProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<RealisationNavireProduit> findByRealisationNavireId(Long id);
    int deleteByRealisationNavireId(Long id);
    long countByRealisationNavireId(Long id);




	RealisationNavireProduit create(RealisationNavireProduit t);

    RealisationNavireProduit update(RealisationNavireProduit t);

    List<RealisationNavireProduit> update(List<RealisationNavireProduit> ts,boolean createIfNotExist);

    RealisationNavireProduit findById(Long id);

    RealisationNavireProduit findOrSave(RealisationNavireProduit t);

    RealisationNavireProduit findByReferenceEntity(RealisationNavireProduit t);

    RealisationNavireProduit findWithAssociatedLists(Long id);

    List<RealisationNavireProduit> findAllOptimized();

    List<RealisationNavireProduit> findAll();

    List<RealisationNavireProduit> delete(List<RealisationNavireProduit> ts);

    boolean deleteById(Long id);

    List<List<RealisationNavireProduit>> getToBeSavedAndToBeDeleted(List<RealisationNavireProduit> oldList, List<RealisationNavireProduit> newList);

}
