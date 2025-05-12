package  ma.zyn.app.api.facade.admin.demande;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.service.facade.admin.demande.DemandeAdminService;
import ma.zyn.app.ws.mapper.demande.DemandeMapper;
import ma.zyn.app.ws.dto.demande.DemandeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/demande/")
public class DemandeRestAdmin {




    @Operation(summary = "Finds a list of all demandes")
    @GetMapping("")
    public ResponseEntity<List<DemandeDto>> findAll(){
        ResponseEntity<List<DemandeDto>> res = null;
        List<Demande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<DemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all demandes")
    @GetMapping("optimized")
    public ResponseEntity<List<DemandeDto>> findAllOptimized(){
        ResponseEntity<List<DemandeDto>> res = null;
        List<Demande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<DemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a demande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DemandeDto> findById(@PathVariable Long id) {
        Demande t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            DemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a demande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DemandeDto> findByLibelle(@PathVariable String libelle) {
	    Demande t = service.findByReferenceEntity(new Demande(libelle));
        if (t != null) {
            mapper.init(true);
            DemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  demande")
    @PostMapping("")
    public ResponseEntity<DemandeDto> save(@RequestBody DemandeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Demande myT = mapper.toItem(dto);
            Demande t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                DemandeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  demande")
    @PutMapping("")
    public ResponseEntity<DemandeDto> update(@RequestBody DemandeDto dto){
        ResponseEntity<DemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Demande t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Demande updated = service.update(t);
            DemandeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of demande")
    @PostMapping("multiple")
    public ResponseEntity<List<DemandeDto>> delete(@RequestBody List<DemandeDto> dtos){
        ResponseEntity<List<DemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Demande> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified demande")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by produitMarchand code")
    @GetMapping("produitMarchand/code/{code}")
    public List<DemandeDto> findByProduitMarchandCode(@PathVariable String code){
        return findDtos(service.findByProduitMarchandCode(code));
    }
    @Operation(summary = "delete by produitMarchand code")
    @DeleteMapping("produitMarchand/code/{code}")
    public int deleteByProduitMarchandCode(@PathVariable String code){
        return service.deleteByProduitMarchandCode(code);
    }
    @Operation(summary = "find by client id")
    @GetMapping("client/id/{id}")
    public List<DemandeDto> findByClientId(@PathVariable Long id){
        return findDtos(service.findByClientId(id));
    }
    @Operation(summary = "delete by client id")
    @DeleteMapping("client/id/{id}")
    public int deleteByClientId(@PathVariable Long id){
        return service.deleteByClientId(id);
    }
    @Operation(summary = "find by typeDemande code")
    @GetMapping("typeDemande/code/{code}")
    public List<DemandeDto> findByTypeDemandeCode(@PathVariable String code){
        return findDtos(service.findByTypeDemandeCode(code));
    }
    @Operation(summary = "delete by typeDemande code")
    @DeleteMapping("typeDemande/code/{code}")
    public int deleteByTypeDemandeCode(@PathVariable String code){
        return service.deleteByTypeDemandeCode(code);
    }
    @Operation(summary = "find by etatDemande code")
    @GetMapping("etatDemande/code/{code}")
    public List<DemandeDto> findByEtatDemandeCode(@PathVariable String code){
        return findDtos(service.findByEtatDemandeCode(code));
    }
    @Operation(summary = "delete by etatDemande code")
    @DeleteMapping("etatDemande/code/{code}")
    public int deleteByEtatDemandeCode(@PathVariable String code){
        return service.deleteByEtatDemandeCode(code);
    }
    @Operation(summary = "find by scenarioFlux id")
    @GetMapping("scenarioFlux/id/{id}")
    public List<DemandeDto> findByScenarioFluxId(@PathVariable Long id){
        return findDtos(service.findByScenarioFluxId(id));
    }
    @Operation(summary = "delete by scenarioFlux id")
    @DeleteMapping("scenarioFlux/id/{id}")
    public int deleteByScenarioFluxId(@PathVariable Long id){
        return service.deleteByScenarioFluxId(id);
    }
    @Operation(summary = "find by exercice id")
    @GetMapping("exercice/id/{id}")
    public List<DemandeDto> findByExerciceId(@PathVariable Long id){
        return findDtos(service.findByExerciceId(id));
    }
    @Operation(summary = "delete by exercice id")
    @DeleteMapping("exercice/id/{id}")
    public int deleteByExerciceId(@PathVariable Long id){
        return service.deleteByExerciceId(id);
    }

    @Operation(summary = "Finds a demande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        Demande loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        DemandeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<DemandeDto> findDtos(List<Demande> list){
        mapper.initObject(true);
        List<DemandeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<DemandeDto> getDtoResponseEntity(DemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public DemandeRestAdmin(DemandeAdminService service, DemandeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final DemandeAdminService service;
    private final DemandeMapper mapper;





}
