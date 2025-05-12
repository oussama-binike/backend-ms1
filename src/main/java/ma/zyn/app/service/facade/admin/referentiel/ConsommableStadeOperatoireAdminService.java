package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;



public interface ConsommableStadeOperatoireAdminService {



    List<ConsommableStadeOperatoire> findByStadeOperatoireCode(String code);
    List<ConsommableStadeOperatoire> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ConsommableStadeOperatoire> findByConsommableCode(String code);
    List<ConsommableStadeOperatoire> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);




	ConsommableStadeOperatoire create(ConsommableStadeOperatoire t);

    ConsommableStadeOperatoire update(ConsommableStadeOperatoire t);

    List<ConsommableStadeOperatoire> update(List<ConsommableStadeOperatoire> ts,boolean createIfNotExist);

    ConsommableStadeOperatoire findById(Long id);

    ConsommableStadeOperatoire findOrSave(ConsommableStadeOperatoire t);

    ConsommableStadeOperatoire findByReferenceEntity(ConsommableStadeOperatoire t);

    ConsommableStadeOperatoire findWithAssociatedLists(Long id);

    List<ConsommableStadeOperatoire> findAllOptimized();

    List<ConsommableStadeOperatoire> findAll();

    List<ConsommableStadeOperatoire> delete(List<ConsommableStadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<ConsommableStadeOperatoire>> getToBeSavedAndToBeDeleted(List<ConsommableStadeOperatoire> oldList, List<ConsommableStadeOperatoire> newList);

}
