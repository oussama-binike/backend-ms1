package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.PointControle;



public interface PointControleAdminService {



    List<PointControle> findByStadeOperatoireCode(String code);
    List<PointControle> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);




	PointControle create(PointControle t);

    PointControle update(PointControle t);

    List<PointControle> update(List<PointControle> ts,boolean createIfNotExist);

    PointControle findById(Long id);

    PointControle findOrSave(PointControle t);

    PointControle findByReferenceEntity(PointControle t);

    PointControle findWithAssociatedLists(Long id);

    List<PointControle> findAllOptimized();

    List<PointControle> findAll();

    List<PointControle> delete(List<PointControle> ts);

    boolean deleteById(Long id);

    List<List<PointControle>> getToBeSavedAndToBeDeleted(List<PointControle> oldList, List<PointControle> newList);

}
