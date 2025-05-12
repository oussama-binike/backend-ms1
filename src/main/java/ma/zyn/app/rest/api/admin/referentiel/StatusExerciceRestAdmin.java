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

import ma.zyn.app.bean.core.referentiel.StatusExercice;
import ma.zyn.app.service.facade.admin.referentiel.StatusExerciceAdminService;
import ma.zyn.app.ws.mapper.referentiel.StatusExerciceMapper;
import ma.zyn.app.ws.dto.referentiel.StatusExerciceDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/statusExercice/")
public class StatusExerciceRestAdmin {




    @Operation(summary = "Finds a list of all statusExercices")
    @GetMapping("")
    public ResponseEntity<List<StatusExerciceDto>> findAll(){
        ResponseEntity<List<StatusExerciceDto>> res = null;
        List<StatusExercice> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusExerciceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all statusExercices")
    @GetMapping("optimized")
    public ResponseEntity<List<StatusExerciceDto>> findAllOptimized(){
        ResponseEntity<List<StatusExerciceDto>> res = null;
        List<StatusExercice> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusExerciceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a statusExercice by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StatusExerciceDto> findById(@PathVariable Long id) {
        StatusExercice t = service.findById(id);
        if (t != null) {
            StatusExerciceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a statusExercice by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StatusExerciceDto> findByLibelle(@PathVariable String libelle) {
	    StatusExercice t = service.findByReferenceEntity(new StatusExercice(libelle));
        if (t != null) {
            StatusExerciceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  statusExercice")
    @PostMapping("")
    public ResponseEntity<StatusExerciceDto> save(@RequestBody StatusExerciceDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            StatusExercice myT = mapper.toItem(dto);
            StatusExercice t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                StatusExerciceDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  statusExercice")
    @PutMapping("")
    public ResponseEntity<StatusExerciceDto> update(@RequestBody StatusExerciceDto dto){
        ResponseEntity<StatusExerciceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StatusExercice t = service.findById(dto.getId());
            mapper.copy(dto,t);
            StatusExercice updated = service.update(t);
            StatusExerciceDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of statusExercice")
    @PostMapping("multiple")
    public ResponseEntity<List<StatusExerciceDto>> delete(@RequestBody List<StatusExerciceDto> dtos){
        ResponseEntity<List<StatusExerciceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<StatusExercice> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified statusExercice")
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


    @Operation(summary = "Finds a statusExercice and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StatusExerciceDto> findWithAssociatedLists(@PathVariable Long id) {
        StatusExercice loaded =  service.findWithAssociatedLists(id);
        StatusExerciceDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<StatusExerciceDto> findDtos(List<StatusExercice> list){
        List<StatusExerciceDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<StatusExerciceDto> getDtoResponseEntity(StatusExerciceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public StatusExerciceRestAdmin(StatusExerciceAdminService service, StatusExerciceMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final StatusExerciceAdminService service;
    private final StatusExerciceMapper mapper;





}
