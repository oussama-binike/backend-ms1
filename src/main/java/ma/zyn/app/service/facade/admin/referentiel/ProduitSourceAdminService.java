package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ProduitSource;



public interface ProduitSourceAdminService {







	ProduitSource create(ProduitSource t);

    ProduitSource update(ProduitSource t);

    List<ProduitSource> update(List<ProduitSource> ts,boolean createIfNotExist);

    ProduitSource findById(Long id);

    ProduitSource findOrSave(ProduitSource t);

    ProduitSource findByReferenceEntity(ProduitSource t);

    ProduitSource findWithAssociatedLists(Long id);

    List<ProduitSource> findAllOptimized();

    List<ProduitSource> findAll();

    List<ProduitSource> delete(List<ProduitSource> ts);

    boolean deleteById(Long id);

    List<List<ProduitSource>> getToBeSavedAndToBeDeleted(List<ProduitSource> oldList, List<ProduitSource> newList);

}
