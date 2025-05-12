package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Engin;



public interface EnginAdminService {



    List<Engin> findByTypeEnginCode(String code);
    List<Engin> findByTypeEnginId(Long id);
    int deleteByTypeEnginId(Long id);
    int deleteByTypeEnginCode(String code);
    long countByTypeEnginCode(String code);
    List<Engin> findByOperationStadeOperatoireCode(String code);
    List<Engin> findByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireCode(String code);
    long countByOperationStadeOperatoireCode(String code);
    List<Engin> findByStadeOperatoireCode(String code);
    List<Engin> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);




	Engin create(Engin t);

    Engin update(Engin t);

    List<Engin> update(List<Engin> ts,boolean createIfNotExist);

    Engin findById(Long id);

    Engin findOrSave(Engin t);

    Engin findByReferenceEntity(Engin t);

    Engin findWithAssociatedLists(Long id);

    List<Engin> findAllOptimized();

    List<Engin> findAll();

    List<Engin> delete(List<Engin> ts);

    boolean deleteById(Long id);

    List<List<Engin>> getToBeSavedAndToBeDeleted(List<Engin> oldList, List<Engin> newList);

}
