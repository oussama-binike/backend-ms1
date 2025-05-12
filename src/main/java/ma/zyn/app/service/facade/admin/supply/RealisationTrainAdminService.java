package ma.zyn.app.service.facade.admin.supply;

import java.util.List;
import ma.zyn.app.bean.core.supply.RealisationTrain;



public interface RealisationTrainAdminService {



    List<RealisationTrain> findByProvennanceTrainCode(String code);
    List<RealisationTrain> findByProvennanceTrainId(Long id);
    int deleteByProvennanceTrainId(Long id);
    int deleteByProvennanceTrainCode(String code);
    long countByProvennanceTrainCode(String code);
    List<RealisationTrain> findByDestinationTrainCode(String code);
    List<RealisationTrain> findByDestinationTrainId(Long id);
    int deleteByDestinationTrainId(Long id);
    int deleteByDestinationTrainCode(String code);
    long countByDestinationTrainCode(String code);
    List<RealisationTrain> findByTrainId(Long id);
    int deleteByTrainId(Long id);
    long countByTrainCode(String code);
    List<RealisationTrain> findByTypeWagonCode(String code);
    List<RealisationTrain> findByTypeWagonId(Long id);
    int deleteByTypeWagonId(Long id);
    int deleteByTypeWagonCode(String code);
    long countByTypeWagonCode(String code);
    List<RealisationTrain> findByStadeOperatoireCode(String code);
    List<RealisationTrain> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);




	RealisationTrain create(RealisationTrain t);

    RealisationTrain update(RealisationTrain t);

    List<RealisationTrain> update(List<RealisationTrain> ts,boolean createIfNotExist);

    RealisationTrain findById(Long id);

    RealisationTrain findOrSave(RealisationTrain t);

    RealisationTrain findByReferenceEntity(RealisationTrain t);

    RealisationTrain findWithAssociatedLists(Long id);

    List<RealisationTrain> findAllOptimized();

    List<RealisationTrain> findAll();

    List<RealisationTrain> delete(List<RealisationTrain> ts);

    boolean deleteById(Long id);

    List<List<RealisationTrain>> getToBeSavedAndToBeDeleted(List<RealisationTrain> oldList, List<RealisationTrain> newList);

}
