package ma.zyn.app.service.facade.admin.aleas;

import java.util.List;
import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;



public interface ArretNonPlanifieAdminService {



    List<ArretNonPlanifie> findByStadeOperatoireCode(String code);
    List<ArretNonPlanifie> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<ArretNonPlanifie> findByCauseArretCode(String code);
    List<ArretNonPlanifie> findByCauseArretId(Long id);
    int deleteByCauseArretId(Long id);
    int deleteByCauseArretCode(String code);
    long countByCauseArretCode(String code);
    List<ArretNonPlanifie> findByActionEntrepriseCode(String code);
    List<ArretNonPlanifie> findByActionEntrepriseId(Long id);
    int deleteByActionEntrepriseId(Long id);
    int deleteByActionEntrepriseCode(String code);
    long countByActionEntrepriseCode(String code);




	ArretNonPlanifie create(ArretNonPlanifie t);

    ArretNonPlanifie update(ArretNonPlanifie t);

    List<ArretNonPlanifie> update(List<ArretNonPlanifie> ts,boolean createIfNotExist);

    ArretNonPlanifie findById(Long id);

    ArretNonPlanifie findOrSave(ArretNonPlanifie t);

    ArretNonPlanifie findByReferenceEntity(ArretNonPlanifie t);

    ArretNonPlanifie findWithAssociatedLists(Long id);

    List<ArretNonPlanifie> findAllOptimized();

    List<ArretNonPlanifie> findAll();

    List<ArretNonPlanifie> delete(List<ArretNonPlanifie> ts);

    boolean deleteById(Long id);

    List<List<ArretNonPlanifie>> getToBeSavedAndToBeDeleted(List<ArretNonPlanifie> oldList, List<ArretNonPlanifie> newList);

}
