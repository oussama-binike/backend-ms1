package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Produit;



public interface ProduitAdminService {







	Produit create(Produit t);

    Produit update(Produit t);

    List<Produit> update(List<Produit> ts,boolean createIfNotExist);

    Produit findById(Long id);

    Produit findOrSave(Produit t);

    Produit findByReferenceEntity(Produit t);

    Produit findWithAssociatedLists(Long id);

    List<Produit> findAllOptimized();

    List<Produit> findAll();

    List<Produit> delete(List<Produit> ts);

    boolean deleteById(Long id);

    List<List<Produit>> getToBeSavedAndToBeDeleted(List<Produit> oldList, List<Produit> newList);

}
