package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;



public interface EtatReclamationAdminService {







	EtatReclamation create(EtatReclamation t);

    EtatReclamation update(EtatReclamation t);

    List<EtatReclamation> update(List<EtatReclamation> ts,boolean createIfNotExist);

    EtatReclamation findById(Long id);

    EtatReclamation findOrSave(EtatReclamation t);

    EtatReclamation findByReferenceEntity(EtatReclamation t);

    EtatReclamation findWithAssociatedLists(Long id);

    List<EtatReclamation> findAllOptimized();

    List<EtatReclamation> findAll();

    List<EtatReclamation> delete(List<EtatReclamation> ts);

    boolean deleteById(Long id);

    List<List<EtatReclamation>> getToBeSavedAndToBeDeleted(List<EtatReclamation> oldList, List<EtatReclamation> newList);

}
