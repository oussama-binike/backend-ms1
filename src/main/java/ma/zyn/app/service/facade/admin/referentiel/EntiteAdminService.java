package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Entite;



public interface EntiteAdminService {



    List<Entite> findByAxeCode(String code);
    List<Entite> findByAxeId(Long id);
    int deleteByAxeId(Long id);
    int deleteByAxeCode(String code);
    long countByAxeCode(String code);




	Entite create(Entite t);

    Entite update(Entite t);

    List<Entite> update(List<Entite> ts,boolean createIfNotExist);

    Entite findById(Long id);

    Entite findOrSave(Entite t);

    Entite findByReferenceEntity(Entite t);

    Entite findWithAssociatedLists(Long id);

    List<Entite> findAllOptimized();

    List<Entite> findAll();

    List<Entite> delete(List<Entite> ts);

    boolean deleteById(Long id);

    List<List<Entite>> getToBeSavedAndToBeDeleted(List<Entite> oldList, List<Entite> newList);

}
