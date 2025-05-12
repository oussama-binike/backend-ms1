package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import java.util.List;


@Repository
public interface OperationStadeOperatoireRepository extends AbstractRepository<OperationStadeOperatoire,Long>  {
    OperationStadeOperatoire findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW OperationStadeOperatoire(item.id,item.libelle) FROM OperationStadeOperatoire item")
    List<OperationStadeOperatoire> findAllOptimized();

}
