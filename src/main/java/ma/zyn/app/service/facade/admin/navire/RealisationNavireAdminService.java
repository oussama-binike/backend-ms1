package ma.zyn.app.service.facade.admin.navire;

import java.util.List;
import ma.zyn.app.bean.core.navire.RealisationNavire;



public interface RealisationNavireAdminService {



    List<RealisationNavire> findByDestinationNavireCode(String code);
    List<RealisationNavire> findByDestinationNavireId(Long id);
    int deleteByDestinationNavireId(Long id);
    int deleteByDestinationNavireCode(String code);
    long countByDestinationNavireCode(String code);




	RealisationNavire create(RealisationNavire t);

    RealisationNavire update(RealisationNavire t);

    List<RealisationNavire> update(List<RealisationNavire> ts,boolean createIfNotExist);

    RealisationNavire findById(Long id);

    RealisationNavire findOrSave(RealisationNavire t);

    RealisationNavire findByReferenceEntity(RealisationNavire t);

    RealisationNavire findWithAssociatedLists(Long id);

    List<RealisationNavire> findAllOptimized();

    List<RealisationNavire> findAll();

    List<RealisationNavire> delete(List<RealisationNavire> ts);

    boolean deleteById(Long id);

    List<List<RealisationNavire>> getToBeSavedAndToBeDeleted(List<RealisationNavire> oldList, List<RealisationNavire> newList);

}
