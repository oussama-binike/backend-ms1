package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;



public interface StadeOperatoireAdminService {



    List<StadeOperatoire> findByEntiteCode(String code);
    List<StadeOperatoire> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);




	StadeOperatoire create(StadeOperatoire t);

    StadeOperatoire update(StadeOperatoire t);

    List<StadeOperatoire> update(List<StadeOperatoire> ts,boolean createIfNotExist);

    StadeOperatoire findById(Long id);

    StadeOperatoire findOrSave(StadeOperatoire t);

    StadeOperatoire findByReferenceEntity(StadeOperatoire t);

    StadeOperatoire findWithAssociatedLists(Long id);

    List<StadeOperatoire> findAllOptimized();

    List<StadeOperatoire> findAll();

    List<StadeOperatoire> delete(List<StadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<StadeOperatoire>> getToBeSavedAndToBeDeleted(List<StadeOperatoire> oldList, List<StadeOperatoire> newList);

}
