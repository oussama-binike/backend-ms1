package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeWagon;



public interface TypeWagonAdminService {







	TypeWagon create(TypeWagon t);

    TypeWagon update(TypeWagon t);

    List<TypeWagon> update(List<TypeWagon> ts,boolean createIfNotExist);

    TypeWagon findById(Long id);

    TypeWagon findOrSave(TypeWagon t);

    TypeWagon findByReferenceEntity(TypeWagon t);

    TypeWagon findWithAssociatedLists(Long id);

    List<TypeWagon> findAllOptimized();

    List<TypeWagon> findAll();

    List<TypeWagon> delete(List<TypeWagon> ts);

    boolean deleteById(Long id);

    List<List<TypeWagon>> getToBeSavedAndToBeDeleted(List<TypeWagon> oldList, List<TypeWagon> newList);

}
