package ma.zyn.app.service.facade.admin.reclamation;

import java.util.List;
import ma.zyn.app.bean.core.reclamation.Reclamation;



public interface ReclamationAdminService {



    List<Reclamation> findByEntiteEmettriceCode(String code);
    List<Reclamation> findByEntiteEmettriceId(Long id);
    int deleteByEntiteEmettriceId(Long id);
    int deleteByEntiteEmettriceCode(String code);
    long countByEntiteEmettriceCode(String code);
    List<Reclamation> findByEntiteDistinataireCode(String code);
    List<Reclamation> findByEntiteDistinataireId(Long id);
    int deleteByEntiteDistinataireId(Long id);
    int deleteByEntiteDistinataireCode(String code);
    long countByEntiteDistinataireCode(String code);
    List<Reclamation> findByProduitMarchandCode(String code);
    List<Reclamation> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<Reclamation> findByEtatReclamationCode(String code);
    List<Reclamation> findByEtatReclamationId(Long id);
    int deleteByEtatReclamationId(Long id);
    int deleteByEtatReclamationCode(String code);
    long countByEtatReclamationCode(String code);




	Reclamation create(Reclamation t);

    Reclamation update(Reclamation t);

    List<Reclamation> update(List<Reclamation> ts,boolean createIfNotExist);

    Reclamation findById(Long id);

    Reclamation findOrSave(Reclamation t);

    Reclamation findByReferenceEntity(Reclamation t);

    Reclamation findWithAssociatedLists(Long id);

    List<Reclamation> findAllOptimized();

    List<Reclamation> findAll();

    List<Reclamation> delete(List<Reclamation> ts);

    boolean deleteById(Long id);

    List<List<Reclamation>> getToBeSavedAndToBeDeleted(List<Reclamation> oldList, List<Reclamation> newList);

}
