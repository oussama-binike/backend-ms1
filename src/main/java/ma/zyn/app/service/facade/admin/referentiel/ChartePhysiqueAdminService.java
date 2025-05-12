package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ChartePhysique;



public interface ChartePhysiqueAdminService {



    List<ChartePhysique> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);




	ChartePhysique create(ChartePhysique t);

    ChartePhysique update(ChartePhysique t);

    List<ChartePhysique> update(List<ChartePhysique> ts,boolean createIfNotExist);

    ChartePhysique findById(Long id);

    ChartePhysique findOrSave(ChartePhysique t);

    ChartePhysique findByReferenceEntity(ChartePhysique t);

    ChartePhysique findWithAssociatedLists(Long id);

    List<ChartePhysique> findAllOptimized();

    List<ChartePhysique> findAll();

    List<ChartePhysique> delete(List<ChartePhysique> ts);

    boolean deleteById(Long id);

    List<List<ChartePhysique>> getToBeSavedAndToBeDeleted(List<ChartePhysique> oldList, List<ChartePhysique> newList);

}
