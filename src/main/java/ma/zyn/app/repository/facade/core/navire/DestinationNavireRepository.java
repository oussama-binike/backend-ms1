package ma.zyn.app.dao.facade.core.navire;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import java.util.List;


@Repository
public interface DestinationNavireRepository extends AbstractRepository<DestinationNavire,Long>  {
    DestinationNavire findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DestinationNavire(item.id,item.libelle) FROM DestinationNavire item")
    List<DestinationNavire> findAllOptimized();

}
