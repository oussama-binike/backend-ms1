package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.TypeReclamation;



public interface TypeReclamationAdminService {







	TypeReclamation create(TypeReclamation t);

    TypeReclamation update(TypeReclamation t);

    List<TypeReclamation> update(List<TypeReclamation> ts,boolean createIfNotExist);

    TypeReclamation findById(Long id);

    TypeReclamation findOrSave(TypeReclamation t);

    TypeReclamation findByReferenceEntity(TypeReclamation t);

    TypeReclamation findWithAssociatedLists(Long id);

    List<TypeReclamation> findAllOptimized();

    List<TypeReclamation> findAll();

    List<TypeReclamation> delete(List<TypeReclamation> ts);

    boolean deleteById(Long id);

    List<List<TypeReclamation>> getToBeSavedAndToBeDeleted(List<TypeReclamation> oldList, List<TypeReclamation> newList);

}
