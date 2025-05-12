package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.RatioUnite;



public interface RatioUniteAdminService {



    List<RatioUnite> findByEntiteCode(String code);
    List<RatioUnite> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);
    List<RatioUnite> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);




	RatioUnite create(RatioUnite t);

    RatioUnite update(RatioUnite t);

    List<RatioUnite> update(List<RatioUnite> ts,boolean createIfNotExist);

    RatioUnite findById(Long id);

    RatioUnite findOrSave(RatioUnite t);

    RatioUnite findByReferenceEntity(RatioUnite t);

    RatioUnite findWithAssociatedLists(Long id);

    List<RatioUnite> findAllOptimized();

    List<RatioUnite> findAll();

    List<RatioUnite> delete(List<RatioUnite> ts);

    boolean deleteById(Long id);

    List<List<RatioUnite>> getToBeSavedAndToBeDeleted(List<RatioUnite> oldList, List<RatioUnite> newList);

}
