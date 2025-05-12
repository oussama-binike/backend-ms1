package ma.zyn.app.service.facade.admin.camion;

import java.util.List;
import ma.zyn.app.bean.core.camion.RealisationCamion;



public interface RealisationCamionAdminService {



    List<RealisationCamion> findByProvennanceCamionCode(String code);
    List<RealisationCamion> findByProvennanceCamionId(Long id);
    int deleteByProvennanceCamionId(Long id);
    int deleteByProvennanceCamionCode(String code);
    long countByProvennanceCamionCode(String code);
    List<RealisationCamion> findByDestinationCamionCode(String code);
    List<RealisationCamion> findByDestinationCamionId(Long id);
    int deleteByDestinationCamionId(Long id);
    int deleteByDestinationCamionCode(String code);
    long countByDestinationCamionCode(String code);




	RealisationCamion create(RealisationCamion t);

    RealisationCamion update(RealisationCamion t);

    List<RealisationCamion> update(List<RealisationCamion> ts,boolean createIfNotExist);

    RealisationCamion findById(Long id);

    RealisationCamion findOrSave(RealisationCamion t);

    RealisationCamion findByReferenceEntity(RealisationCamion t);

    RealisationCamion findWithAssociatedLists(Long id);

    List<RealisationCamion> findAllOptimized();

    List<RealisationCamion> findAll();

    List<RealisationCamion> delete(List<RealisationCamion> ts);

    boolean deleteById(Long id);

    List<List<RealisationCamion>> getToBeSavedAndToBeDeleted(List<RealisationCamion> oldList, List<RealisationCamion> newList);

}
