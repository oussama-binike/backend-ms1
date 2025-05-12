package ma.zyn.app.service.facade.admin.planmaintenance;

import java.util.List;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;



public interface TauxRendementStadeOperatoireAdminService {



    List<TauxRendementStadeOperatoire> findByScenarioFluxId(Long id);
    int deleteByScenarioFluxId(Long id);
    long countByScenarioFluxCode(String code);




	TauxRendementStadeOperatoire create(TauxRendementStadeOperatoire t);

    TauxRendementStadeOperatoire update(TauxRendementStadeOperatoire t);

    List<TauxRendementStadeOperatoire> update(List<TauxRendementStadeOperatoire> ts,boolean createIfNotExist);

    TauxRendementStadeOperatoire findById(Long id);

    TauxRendementStadeOperatoire findOrSave(TauxRendementStadeOperatoire t);

    TauxRendementStadeOperatoire findByReferenceEntity(TauxRendementStadeOperatoire t);

    TauxRendementStadeOperatoire findWithAssociatedLists(Long id);

    List<TauxRendementStadeOperatoire> findAllOptimized();

    List<TauxRendementStadeOperatoire> findAll();

    List<TauxRendementStadeOperatoire> delete(List<TauxRendementStadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<TauxRendementStadeOperatoire>> getToBeSavedAndToBeDeleted(List<TauxRendementStadeOperatoire> oldList, List<TauxRendementStadeOperatoire> newList);

}
