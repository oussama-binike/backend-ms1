package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;



public interface ActionEntrepriseAdminService {







	ActionEntreprise create(ActionEntreprise t);

    ActionEntreprise update(ActionEntreprise t);

    List<ActionEntreprise> update(List<ActionEntreprise> ts,boolean createIfNotExist);

    ActionEntreprise findById(Long id);

    ActionEntreprise findOrSave(ActionEntreprise t);

    ActionEntreprise findByReferenceEntity(ActionEntreprise t);

    ActionEntreprise findWithAssociatedLists(Long id);

    List<ActionEntreprise> findAllOptimized();

    List<ActionEntreprise> findAll();

    List<ActionEntreprise> delete(List<ActionEntreprise> ts);

    boolean deleteById(Long id);

    List<List<ActionEntreprise>> getToBeSavedAndToBeDeleted(List<ActionEntreprise> oldList, List<ActionEntreprise> newList);

}
