package  ma.zyn.app.api.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService;
import ma.zyn.app.ws.mapper.referentiel.EtatDemandeMapper;
import ma.zyn.app.ws.dto.referentiel.EtatDemandeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/etatDemande/")
public class EtatDemandeRestAdmin {




    @Operation(summary = "Finds a list of all etatDemandes")
    @GetMapping("")
    public ResponseEntity<List<EtatDemandeDto>> findAll(){
        ResponseEntity<List<EtatDemandeDto>> res = null;
        List<EtatDemande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatDemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatDemandes")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatDemandeDto>> findAllOptimized(){
        ResponseEntity<List<EtatDemandeDto>> res = null;
        List<EtatDemande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatDemandeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatDemande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatDemandeDto> findById(@PathVariable Long id) {
        EtatDemande t = service.findById(id);
        if (t != null) {
            EtatDemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatDemande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatDemandeDto> findByLibelle(@PathVariable String libelle) {
	    EtatDemande t = service.findByReferenceEntity(new EtatDemande(libelle));
        if (t != null) {
            EtatDemandeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatDemande")
    @PostMapping("")
    public ResponseEntity<EtatDemandeDto> save(@RequestBody EtatDemandeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            EtatDemande myT = mapper.toItem(dto);
            EtatDemande t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                EtatDemandeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  etatDemande")
    @PutMapping("")
    public ResponseEntity<EtatDemandeDto> update(@RequestBody EtatDemandeDto dto){
        ResponseEntity<EtatDemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatDemande t = service.findById(dto.getId());
            mapper.copy(dto,t);
            EtatDemande updated = service.update(t);
            EtatDemandeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatDemande")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatDemandeDto>> delete(@RequestBody List<EtatDemandeDto> dtos){
        ResponseEntity<List<EtatDemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatDemande> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatDemande")
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


    @Operation(summary = "Finds a etatDemande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatDemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatDemande loaded =  service.findWithAssociatedLists(id);
        EtatDemandeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<EtatDemandeDto> findDtos(List<EtatDemande> list){
        List<EtatDemandeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatDemandeDto> getDtoResponseEntity(EtatDemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public EtatDemandeRestAdmin(EtatDemandeAdminService service, EtatDemandeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final EtatDemandeAdminService service;
    private final EtatDemandeMapper mapper;





}
