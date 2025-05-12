package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeExpedition;



public interface TypeExpeditionAdminService {







	TypeExpedition create(TypeExpedition t);

    TypeExpedition update(TypeExpedition t);

    List<TypeExpedition> update(List<TypeExpedition> ts,boolean createIfNotExist);

    TypeExpedition findById(Long id);

    TypeExpedition findOrSave(TypeExpedition t);

    TypeExpedition findByReferenceEntity(TypeExpedition t);

    TypeExpedition findWithAssociatedLists(Long id);

    List<TypeExpedition> findAllOptimized();

    List<TypeExpedition> findAll();

    List<TypeExpedition> delete(List<TypeExpedition> ts);

    boolean deleteById(Long id);

    List<List<TypeExpedition>> getToBeSavedAndToBeDeleted(List<TypeExpedition> oldList, List<TypeExpedition> newList);

}
