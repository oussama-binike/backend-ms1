package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Panneau;



public interface PanneauAdminService {



    List<Panneau> findByEntiteCode(String code);
    List<Panneau> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);




	Panneau create(Panneau t);

    Panneau update(Panneau t);

    List<Panneau> update(List<Panneau> ts,boolean createIfNotExist);

    Panneau findById(Long id);

    Panneau findOrSave(Panneau t);

    Panneau findByReferenceEntity(Panneau t);

    Panneau findWithAssociatedLists(Long id);

    List<Panneau> findAllOptimized();

    List<Panneau> findAll();

    List<Panneau> delete(List<Panneau> ts);

    boolean deleteById(Long id);

    List<List<Panneau>> getToBeSavedAndToBeDeleted(List<Panneau> oldList, List<Panneau> newList);

}
