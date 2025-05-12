package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.StatusExercice;



public interface StatusExerciceAdminService {







	StatusExercice create(StatusExercice t);

    StatusExercice update(StatusExercice t);

    List<StatusExercice> update(List<StatusExercice> ts,boolean createIfNotExist);

    StatusExercice findById(Long id);

    StatusExercice findOrSave(StatusExercice t);

    StatusExercice findByReferenceEntity(StatusExercice t);

    StatusExercice findWithAssociatedLists(Long id);

    List<StatusExercice> findAllOptimized();

    List<StatusExercice> findAll();

    List<StatusExercice> delete(List<StatusExercice> ts);

    boolean deleteById(Long id);

    List<List<StatusExercice>> getToBeSavedAndToBeDeleted(List<StatusExercice> oldList, List<StatusExercice> newList);

}
