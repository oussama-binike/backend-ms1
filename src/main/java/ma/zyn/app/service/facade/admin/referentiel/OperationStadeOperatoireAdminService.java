package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;



public interface OperationStadeOperatoireAdminService {







	OperationStadeOperatoire create(OperationStadeOperatoire t);

    OperationStadeOperatoire update(OperationStadeOperatoire t);

    List<OperationStadeOperatoire> update(List<OperationStadeOperatoire> ts,boolean createIfNotExist);

    OperationStadeOperatoire findById(Long id);

    OperationStadeOperatoire findOrSave(OperationStadeOperatoire t);

    OperationStadeOperatoire findByReferenceEntity(OperationStadeOperatoire t);

    OperationStadeOperatoire findWithAssociatedLists(Long id);

    List<OperationStadeOperatoire> findAllOptimized();

    List<OperationStadeOperatoire> findAll();

    List<OperationStadeOperatoire> delete(List<OperationStadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<OperationStadeOperatoire>> getToBeSavedAndToBeDeleted(List<OperationStadeOperatoire> oldList, List<OperationStadeOperatoire> newList);

}
