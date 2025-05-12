package ma.zyn.app.dao.facade.core.navire;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RealisationNavireQualiteRepository extends AbstractRepository<RealisationNavireQualite,Long>  {

    List<RealisationNavireQualite> findByProduitMarchandCode(String code);
    List<RealisationNavireQualite> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<RealisationNavireQualite> findByRealisationNavireId(Long id);
    int deleteByRealisationNavireId(Long id);
    long countByRealisationNavireId(Long id);


}
