package ma.zyn.app.service.facade.admin.aleas;

import java.util.List;
import ma.zyn.app.bean.core.aleas.CauseArret;



public interface CauseArretAdminService {







	CauseArret create(CauseArret t);

    CauseArret update(CauseArret t);

    List<CauseArret> update(List<CauseArret> ts,boolean createIfNotExist);

    CauseArret findById(Long id);

    CauseArret findOrSave(CauseArret t);

    CauseArret findByReferenceEntity(CauseArret t);

    CauseArret findWithAssociatedLists(Long id);

    List<CauseArret> findAllOptimized();

    List<CauseArret> findAll();

    List<CauseArret> delete(List<CauseArret> ts);

    boolean deleteById(Long id);

    List<List<CauseArret>> getToBeSavedAndToBeDeleted(List<CauseArret> oldList, List<CauseArret> newList);

}
