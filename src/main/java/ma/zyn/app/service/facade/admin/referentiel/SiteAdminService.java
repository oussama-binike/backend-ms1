package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Site;



public interface SiteAdminService {







	Site create(Site t);

    Site update(Site t);

    List<Site> update(List<Site> ts,boolean createIfNotExist);

    Site findById(Long id);

    Site findOrSave(Site t);

    Site findByReferenceEntity(Site t);

    Site findWithAssociatedLists(Long id);

    List<Site> findAllOptimized();

    List<Site> findAll();

    List<Site> delete(List<Site> ts);

    boolean deleteById(Long id);

    List<List<Site>> getToBeSavedAndToBeDeleted(List<Site> oldList, List<Site> newList);

}
