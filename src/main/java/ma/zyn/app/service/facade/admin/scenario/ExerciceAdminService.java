package ma.zyn.app.service.facade.admin.scenario;

import java.util.List;
import ma.zyn.app.bean.core.scenario.Exercice;



public interface ExerciceAdminService {



    List<Exercice> findByStatusExerciceCode(String code);
    List<Exercice> findByStatusExerciceId(Long id);
    int deleteByStatusExerciceId(Long id);
    int deleteByStatusExerciceCode(String code);
    long countByStatusExerciceCode(String code);




	Exercice create(Exercice t);

    Exercice update(Exercice t);

    List<Exercice> update(List<Exercice> ts,boolean createIfNotExist);

    Exercice findById(Long id);

    Exercice findOrSave(Exercice t);

    Exercice findByReferenceEntity(Exercice t);

    Exercice findWithAssociatedLists(Long id);

    List<Exercice> findAllOptimized();

    List<Exercice> findAll();

    List<Exercice> delete(List<Exercice> ts);

    boolean deleteById(Long id);

    List<List<Exercice>> getToBeSavedAndToBeDeleted(List<Exercice> oldList, List<Exercice> newList);

}
