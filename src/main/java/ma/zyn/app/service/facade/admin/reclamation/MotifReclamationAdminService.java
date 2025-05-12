package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.MotifReclamation;



public interface MotifReclamationAdminService {







	MotifReclamation create(MotifReclamation t);

    MotifReclamation update(MotifReclamation t);

    List<MotifReclamation> update(List<MotifReclamation> ts,boolean createIfNotExist);

    MotifReclamation findById(Long id);

    MotifReclamation findOrSave(MotifReclamation t);

    MotifReclamation findByReferenceEntity(MotifReclamation t);

    MotifReclamation findWithAssociatedLists(Long id);

    List<MotifReclamation> findAllOptimized();

    List<MotifReclamation> findAll();

    List<MotifReclamation> delete(List<MotifReclamation> ts);

    boolean deleteById(Long id);

    List<List<MotifReclamation>> getToBeSavedAndToBeDeleted(List<MotifReclamation> oldList, List<MotifReclamation> newList);

}
