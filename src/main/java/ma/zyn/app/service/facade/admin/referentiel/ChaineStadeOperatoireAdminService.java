package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;



public interface ChaineStadeOperatoireAdminService {



    List<ChaineStadeOperatoire> findByStadeOperatoireCode(String code);
    List<ChaineStadeOperatoire> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);




	ChaineStadeOperatoire create(ChaineStadeOperatoire t);

    ChaineStadeOperatoire update(ChaineStadeOperatoire t);

    List<ChaineStadeOperatoire> update(List<ChaineStadeOperatoire> ts,boolean createIfNotExist);

    ChaineStadeOperatoire findById(Long id);

    ChaineStadeOperatoire findOrSave(ChaineStadeOperatoire t);

    ChaineStadeOperatoire findByReferenceEntity(ChaineStadeOperatoire t);

    ChaineStadeOperatoire findWithAssociatedLists(Long id);

    List<ChaineStadeOperatoire> findAllOptimized();

    List<ChaineStadeOperatoire> findAll();

    List<ChaineStadeOperatoire> delete(List<ChaineStadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<ChaineStadeOperatoire>> getToBeSavedAndToBeDeleted(List<ChaineStadeOperatoire> oldList, List<ChaineStadeOperatoire> newList);

}
