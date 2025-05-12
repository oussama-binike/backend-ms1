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

import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import ma.zyn.app.service.facade.admin.reclamation.MotifReclamationAdminService;
import ma.zyn.app.ws.mapper.reclamation.MotifReclamationMapper;
import ma.zyn.app.ws.dto.reclamation.MotifReclamationDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/motifReclamation/")
public class MotifReclamationRestAdmin {




    @Operation(summary = "Finds a list of all motifReclamations")
    @GetMapping("")
    public ResponseEntity<List<MotifReclamationDto>> findAll(){
        ResponseEntity<List<MotifReclamationDto>> res = null;
        List<MotifReclamation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MotifReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all motifReclamations")
    @GetMapping("optimized")
    public ResponseEntity<List<MotifReclamationDto>> findAllOptimized(){
        ResponseEntity<List<MotifReclamationDto>> res = null;
        List<MotifReclamation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MotifReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a motifReclamation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MotifReclamationDto> findById(@PathVariable Long id) {
        MotifReclamation t = service.findById(id);
        if (t != null) {
            MotifReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a motifReclamation by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MotifReclamationDto> findByLibelle(@PathVariable String libelle) {
	    MotifReclamation t = service.findByReferenceEntity(new MotifReclamation(libelle));
        if (t != null) {
            MotifReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  motifReclamation")
    @PostMapping("")
    public ResponseEntity<MotifReclamationDto> save(@RequestBody MotifReclamationDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            MotifReclamation myT = mapper.toItem(dto);
            MotifReclamation t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                MotifReclamationDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  motifReclamation")
    @PutMapping("")
    public ResponseEntity<MotifReclamationDto> update(@RequestBody MotifReclamationDto dto){
        ResponseEntity<MotifReclamationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            MotifReclamation t = service.findById(dto.getId());
            mapper.copy(dto,t);
            MotifReclamation updated = service.update(t);
            MotifReclamationDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of motifReclamation")
    @PostMapping("multiple")
    public ResponseEntity<List<MotifReclamationDto>> delete(@RequestBody List<MotifReclamationDto> dtos){
        ResponseEntity<List<MotifReclamationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<MotifReclamation> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified motifReclamation")
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


    @Operation(summary = "Finds a motifReclamation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MotifReclamationDto> findWithAssociatedLists(@PathVariable Long id) {
        MotifReclamation loaded =  service.findWithAssociatedLists(id);
        MotifReclamationDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<MotifReclamationDto> findDtos(List<MotifReclamation> list){
        List<MotifReclamationDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<MotifReclamationDto> getDtoResponseEntity(MotifReclamationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public MotifReclamationRestAdmin(MotifReclamationAdminService service, MotifReclamationMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final MotifReclamationAdminService service;
    private final MotifReclamationMapper mapper;





}
