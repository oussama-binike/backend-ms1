package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;



public interface ConsommationSpecifiqueAdminService {



    List<ConsommationSpecifique> findByConsommableCode(String code);
    List<ConsommationSpecifique> findByConsommableId(Long id);
    int deleteByConsommableId(Long id);
    int deleteByConsommableCode(String code);
    long countByConsommableCode(String code);
    List<ConsommationSpecifique> findByStadeOperatoireCode(String code);
    List<ConsommationSpecifique> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ConsommationSpecifique> findByUniteCode(String code);
    List<ConsommationSpecifique> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	ConsommationSpecifique create(ConsommationSpecifique t);

    ConsommationSpecifique update(ConsommationSpecifique t);

    List<ConsommationSpecifique> update(List<ConsommationSpecifique> ts,boolean createIfNotExist);

    ConsommationSpecifique findById(Long id);

    ConsommationSpecifique findOrSave(ConsommationSpecifique t);

    ConsommationSpecifique findByReferenceEntity(ConsommationSpecifique t);

    ConsommationSpecifique findWithAssociatedLists(Long id);

    List<ConsommationSpecifique> findAllOptimized();

    List<ConsommationSpecifique> findAll();

    List<ConsommationSpecifique> delete(List<ConsommationSpecifique> ts);

    boolean deleteById(Long id);

    List<List<ConsommationSpecifique>> getToBeSavedAndToBeDeleted(List<ConsommationSpecifique> oldList, List<ConsommationSpecifique> newList);

}
