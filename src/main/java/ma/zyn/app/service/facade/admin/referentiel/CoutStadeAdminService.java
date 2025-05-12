package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.CoutStade;



public interface CoutStadeAdminService {



    List<CoutStade> findByStadeOperatoireCode(String code);
    List<CoutStade> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<CoutStade> findByUniteCode(String code);
    List<CoutStade> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	CoutStade create(CoutStade t);

    CoutStade update(CoutStade t);

    List<CoutStade> update(List<CoutStade> ts,boolean createIfNotExist);

    CoutStade findById(Long id);

    CoutStade findOrSave(CoutStade t);

    CoutStade findByReferenceEntity(CoutStade t);

    CoutStade findWithAssociatedLists(Long id);

    List<CoutStade> findAllOptimized();

    List<CoutStade> findAll();

    List<CoutStade> delete(List<CoutStade> ts);

    boolean deleteById(Long id);

    List<List<CoutStade>> getToBeSavedAndToBeDeleted(List<CoutStade> oldList, List<CoutStade> newList);

}
