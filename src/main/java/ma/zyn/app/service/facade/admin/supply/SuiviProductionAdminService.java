package ma.zyn.app.service.facade.admin.supply;

import java.util.List;
import ma.zyn.app.bean.core.supply.SuiviProduction;



public interface SuiviProductionAdminService {



    List<SuiviProduction> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<SuiviProduction> findByStadeOperatoireCode(String code);
    List<SuiviProduction> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<SuiviProduction> findByUniteCode(String code);
    List<SuiviProduction> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);
    List<SuiviProduction> findByMoyenCode(String code);
    List<SuiviProduction> findByMoyenId(Long id);
    int deleteByMoyenId(Long id);
    int deleteByMoyenCode(String code);
    long countByMoyenCode(String code);




	SuiviProduction create(SuiviProduction t);

    SuiviProduction update(SuiviProduction t);

    List<SuiviProduction> update(List<SuiviProduction> ts,boolean createIfNotExist);

    SuiviProduction findById(Long id);

    SuiviProduction findOrSave(SuiviProduction t);

    SuiviProduction findByReferenceEntity(SuiviProduction t);

    SuiviProduction findWithAssociatedLists(Long id);

    List<SuiviProduction> findAllOptimized();

    List<SuiviProduction> findAll();

    List<SuiviProduction> delete(List<SuiviProduction> ts);

    boolean deleteById(Long id);

    List<List<SuiviProduction>> getToBeSavedAndToBeDeleted(List<SuiviProduction> oldList, List<SuiviProduction> newList);

}
