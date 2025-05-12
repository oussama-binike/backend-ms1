package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Niveau;



public interface NiveauAdminService {



    List<Niveau> findByTrancheeCode(String code);
    List<Niveau> findByTrancheeId(Long id);
    int deleteByTrancheeId(Long id);
    int deleteByTrancheeCode(String code);
    long countByTrancheeCode(String code);




	Niveau create(Niveau t);

    Niveau update(Niveau t);

    List<Niveau> update(List<Niveau> ts,boolean createIfNotExist);

    Niveau findById(Long id);

    Niveau findOrSave(Niveau t);

    Niveau findByReferenceEntity(Niveau t);

    Niveau findWithAssociatedLists(Long id);

    List<Niveau> findAllOptimized();

    List<Niveau> findAll();

    List<Niveau> delete(List<Niveau> ts);

    boolean deleteById(Long id);

    List<List<Niveau>> getToBeSavedAndToBeDeleted(List<Niveau> oldList, List<Niveau> newList);

}
