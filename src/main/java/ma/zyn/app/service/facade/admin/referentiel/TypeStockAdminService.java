package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeStock;



public interface TypeStockAdminService {







	TypeStock create(TypeStock t);

    TypeStock update(TypeStock t);

    List<TypeStock> update(List<TypeStock> ts,boolean createIfNotExist);

    TypeStock findById(Long id);

    TypeStock findOrSave(TypeStock t);

    TypeStock findByReferenceEntity(TypeStock t);

    TypeStock findWithAssociatedLists(Long id);

    List<TypeStock> findAllOptimized();

    List<TypeStock> findAll();

    List<TypeStock> delete(List<TypeStock> ts);

    boolean deleteById(Long id);

    List<List<TypeStock>> getToBeSavedAndToBeDeleted(List<TypeStock> oldList, List<TypeStock> newList);

}
