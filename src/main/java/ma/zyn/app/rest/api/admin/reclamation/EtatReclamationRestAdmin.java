package  ma.zyn.app.api.facade.admin.reclamation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.service.facade.admin.reclamation.EtatReclamationAdminService;
import ma.zyn.app.ws.mapper.reclamation.EtatReclamationMapper;
import ma.zyn.app.ws.dto.reclamation.EtatReclamationDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/etatReclamation/")
public class EtatReclamationRestAdmin {




    @Operation(summary = "Finds a list of all etatReclamations")
    @GetMapping("")
    public ResponseEntity<List<EtatReclamationDto>> findAll(){
        ResponseEntity<List<EtatReclamationDto>> res = null;
        List<EtatReclamation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatReclamations")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatReclamationDto>> findAllOptimized(){
        ResponseEntity<List<EtatReclamationDto>> res = null;
        List<EtatReclamation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatReclamation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatReclamationDto> findById(@PathVariable Long id) {
        EtatReclamation t = service.findById(id);
        if (t != null) {
            EtatReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatReclamation by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatReclamationDto> findByLibelle(@PathVariable String libelle) {
	    EtatReclamation t = service.findByReferenceEntity(new EtatReclamation(libelle));
        if (t != null) {
            EtatReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatReclamation")
    @PostMapping("")
    public ResponseEntity<EtatReclamationDto> save(@RequestBody EtatReclamationDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            EtatReclamation myT = mapper.toItem(dto);
            EtatReclamation t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                EtatReclamationDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  etatReclamation")
    @PutMapping("")
    public ResponseEntity<EtatReclamationDto> update(@RequestBody EtatReclamationDto dto){
        ResponseEntity<EtatReclamationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatReclamation t = service.findById(dto.getId());
            mapper.copy(dto,t);
            EtatReclamation updated = service.update(t);
            EtatReclamationDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatReclamation")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatReclamationDto>> delete(@RequestBody List<EtatReclamationDto> dtos){
        ResponseEntity<List<EtatReclamationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatReclamation> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatReclamation")
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


    @Operation(summary = "Finds a etatReclamation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatReclamationDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatReclamation loaded =  service.findWithAssociatedLists(id);
        EtatReclamationDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<EtatReclamationDto> findDtos(List<EtatReclamation> list){
        List<EtatReclamationDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatReclamationDto> getDtoResponseEntity(EtatReclamationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public EtatReclamationRestAdmin(EtatReclamationAdminService service, EtatReclamationMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final EtatReclamationAdminService service;
    private final EtatReclamationMapper mapper;





}
