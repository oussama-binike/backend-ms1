package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;



public interface ReclamationElementChimiqueAdminService {



    List<ReclamationElementChimique> findByReclamationId(Long id);
    int deleteByReclamationId(Long id);
    long countByReclamationCode(String code);
    List<ReclamationElementChimique> findByElementChimiqueCode(String code);
    List<ReclamationElementChimique> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);




	ReclamationElementChimique create(ReclamationElementChimique t);

    ReclamationElementChimique update(ReclamationElementChimique t);

    List<ReclamationElementChimique> update(List<ReclamationElementChimique> ts,boolean createIfNotExist);

    ReclamationElementChimique findById(Long id);

    ReclamationElementChimique findOrSave(ReclamationElementChimique t);

    ReclamationElementChimique findByReferenceEntity(ReclamationElementChimique t);

    ReclamationElementChimique findWithAssociatedLists(Long id);

    List<ReclamationElementChimique> findAllOptimized();

    List<ReclamationElementChimique> findAll();

    List<ReclamationElementChimique> delete(List<ReclamationElementChimique> ts);

    boolean deleteById(Long id);

    List<List<ReclamationElementChimique>> getToBeSavedAndToBeDeleted(List<ReclamationElementChimique> oldList, List<ReclamationElementChimique> newList);

}
