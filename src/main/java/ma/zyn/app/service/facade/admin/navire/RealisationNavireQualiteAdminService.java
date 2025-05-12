package ma.zyn.app.service.facade.admin.navire;

import java.util.List;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite;



public interface RealisationNavireQualiteAdminService {



    List<RealisationNavireQualite> findByProduitMarchandCode(String code);
    List<RealisationNavireQualite> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<RealisationNavireQualite> findByRealisationNavireId(Long id);
    int deleteByRealisationNavireId(Long id);
    long countByRealisationNavireId(Long id);




	RealisationNavireQualite create(RealisationNavireQualite t);

    RealisationNavireQualite update(RealisationNavireQualite t);

    List<RealisationNavireQualite> update(List<RealisationNavireQualite> ts,boolean createIfNotExist);

    RealisationNavireQualite findById(Long id);

    RealisationNavireQualite findOrSave(RealisationNavireQualite t);

    RealisationNavireQualite findByReferenceEntity(RealisationNavireQualite t);

    RealisationNavireQualite findWithAssociatedLists(Long id);

    List<RealisationNavireQualite> findAllOptimized();

    List<RealisationNavireQualite> findAll();

    List<RealisationNavireQualite> delete(List<RealisationNavireQualite> ts);

    boolean deleteById(Long id);

    List<List<RealisationNavireQualite>> getToBeSavedAndToBeDeleted(List<RealisationNavireQualite> oldList, List<RealisationNavireQualite> newList);

}
