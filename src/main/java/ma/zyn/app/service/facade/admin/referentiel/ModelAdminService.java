package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Model;



public interface ModelAdminService {



    List<Model> findByStadeOperatoireCode(String code);
    List<Model> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);




	Model create(Model t);

    Model update(Model t);

    List<Model> update(List<Model> ts,boolean createIfNotExist);

    Model findById(Long id);

    Model findOrSave(Model t);

    Model findByReferenceEntity(Model t);

    Model findWithAssociatedLists(Long id);

    List<Model> findAllOptimized();

    List<Model> findAll();

    List<Model> delete(List<Model> ts);

    boolean deleteById(Long id);

    List<List<Model>> getToBeSavedAndToBeDeleted(List<Model> oldList, List<Model> newList);

}
