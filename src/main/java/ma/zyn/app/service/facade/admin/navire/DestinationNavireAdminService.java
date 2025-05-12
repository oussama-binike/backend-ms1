package ma.zyn.app.service.facade.admin.navire;

import java.util.List;
import ma.zyn.app.bean.core.navire.DestinationNavire;



public interface DestinationNavireAdminService {







	DestinationNavire create(DestinationNavire t);

    DestinationNavire update(DestinationNavire t);

    List<DestinationNavire> update(List<DestinationNavire> ts,boolean createIfNotExist);

    DestinationNavire findById(Long id);

    DestinationNavire findOrSave(DestinationNavire t);

    DestinationNavire findByReferenceEntity(DestinationNavire t);

    DestinationNavire findWithAssociatedLists(Long id);

    List<DestinationNavire> findAllOptimized();

    List<DestinationNavire> findAll();

    List<DestinationNavire> delete(List<DestinationNavire> ts);

    boolean deleteById(Long id);

    List<List<DestinationNavire>> getToBeSavedAndToBeDeleted(List<DestinationNavire> oldList, List<DestinationNavire> newList);

}
