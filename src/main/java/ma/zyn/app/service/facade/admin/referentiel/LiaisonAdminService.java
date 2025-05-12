package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Liaison;



public interface LiaisonAdminService {



    List<Liaison> findByStockSourceId(Long id);
    int deleteByStockSourceId(Long id);
    long countByStockSourceCode(String code);
    List<Liaison> findByStockDestinationId(Long id);
    int deleteByStockDestinationId(Long id);
    long countByStockDestinationCode(String code);
    List<Liaison> findByEnginId(Long id);
    int deleteByEnginId(Long id);
    long countByEnginCode(String code);
    List<Liaison> findByOperationStadeOperatoireCode(String code);
    List<Liaison> findByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireId(Long id);
    int deleteByOperationStadeOperatoireCode(String code);
    long countByOperationStadeOperatoireCode(String code);
    List<Liaison> findByProdduitId(Long id);
    int deleteByProdduitId(Long id);
    long countByProdduitCode(String code);




	Liaison create(Liaison t);

    Liaison update(Liaison t);

    List<Liaison> update(List<Liaison> ts,boolean createIfNotExist);

    Liaison findById(Long id);

    Liaison findOrSave(Liaison t);

    Liaison findByReferenceEntity(Liaison t);

    Liaison findWithAssociatedLists(Long id);

    List<Liaison> findAllOptimized();

    List<Liaison> findAll();

    List<Liaison> delete(List<Liaison> ts);

    boolean deleteById(Long id);

    List<List<Liaison>> getToBeSavedAndToBeDeleted(List<Liaison> oldList, List<Liaison> newList);

}
