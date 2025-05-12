package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Model;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Model;
import java.util.List;


@Repository
public interface ModelRepository extends AbstractRepository<Model,Long>  {
    Model findByCode(String code);
    int deleteByCode(String code);

    List<Model> findByStadeOperatoireCode(String code);
    List<Model> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);

    @Query("SELECT NEW Model(item.id,item.libelle) FROM Model item")
    List<Model> findAllOptimized();

}
