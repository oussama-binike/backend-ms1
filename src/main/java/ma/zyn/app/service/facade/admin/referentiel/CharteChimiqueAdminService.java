package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CharteChimique;



public interface CharteChimiqueAdminService {



    List<CharteChimique> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<CharteChimique> findByElementChimiqueCode(String code);
    List<CharteChimique> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);




	CharteChimique create(CharteChimique t);

    CharteChimique update(CharteChimique t);

    List<CharteChimique> update(List<CharteChimique> ts,boolean createIfNotExist);

    CharteChimique findById(Long id);

    CharteChimique findOrSave(CharteChimique t);

    CharteChimique findByReferenceEntity(CharteChimique t);

    CharteChimique findWithAssociatedLists(Long id);

    List<CharteChimique> findAllOptimized();

    List<CharteChimique> findAll();

    List<CharteChimique> delete(List<CharteChimique> ts);

    boolean deleteById(Long id);

    List<List<CharteChimique>> getToBeSavedAndToBeDeleted(List<CharteChimique> oldList, List<CharteChimique> newList);

}
