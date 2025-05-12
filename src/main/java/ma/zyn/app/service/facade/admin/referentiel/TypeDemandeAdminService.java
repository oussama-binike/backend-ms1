package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeDemande;



public interface TypeDemandeAdminService {







	TypeDemande create(TypeDemande t);

    TypeDemande update(TypeDemande t);

    List<TypeDemande> update(List<TypeDemande> ts,boolean createIfNotExist);

    TypeDemande findById(Long id);

    TypeDemande findOrSave(TypeDemande t);

    TypeDemande findByReferenceEntity(TypeDemande t);

    TypeDemande findWithAssociatedLists(Long id);

    List<TypeDemande> findAllOptimized();

    List<TypeDemande> findAll();

    List<TypeDemande> delete(List<TypeDemande> ts);

    boolean deleteById(Long id);

    List<List<TypeDemande>> getToBeSavedAndToBeDeleted(List<TypeDemande> oldList, List<TypeDemande> newList);

}
