package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Axe;



public interface AxeAdminService {



    List<Axe> findBySiteCode(String code);
    List<Axe> findBySiteId(Long id);
    int deleteBySiteId(Long id);
    int deleteBySiteCode(String code);
    long countBySiteCode(String code);




	Axe create(Axe t);

    Axe update(Axe t);

    List<Axe> update(List<Axe> ts,boolean createIfNotExist);

    Axe findById(Long id);

    Axe findOrSave(Axe t);

    Axe findByReferenceEntity(Axe t);

    Axe findWithAssociatedLists(Long id);

    List<Axe> findAllOptimized();

    List<Axe> findAll();

    List<Axe> delete(List<Axe> ts);

    boolean deleteById(Long id);

    List<List<Axe>> getToBeSavedAndToBeDeleted(List<Axe> oldList, List<Axe> newList);

}
