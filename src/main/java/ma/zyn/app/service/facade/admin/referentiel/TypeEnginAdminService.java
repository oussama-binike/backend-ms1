package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeEngin;



public interface TypeEnginAdminService {







	TypeEngin create(TypeEngin t);

    TypeEngin update(TypeEngin t);

    List<TypeEngin> update(List<TypeEngin> ts,boolean createIfNotExist);

    TypeEngin findById(Long id);

    TypeEngin findOrSave(TypeEngin t);

    TypeEngin findByReferenceEntity(TypeEngin t);

    TypeEngin findWithAssociatedLists(Long id);

    List<TypeEngin> findAllOptimized();

    List<TypeEngin> findAll();

    List<TypeEngin> delete(List<TypeEngin> ts);

    boolean deleteById(Long id);

    List<List<TypeEngin>> getToBeSavedAndToBeDeleted(List<TypeEngin> oldList, List<TypeEngin> newList);

}
