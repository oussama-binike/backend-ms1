package  ma.zyn.app.api.facade.admin.scenario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.service.facade.admin.scenario.ExerciceAdminService;
import ma.zyn.app.ws.mapper.scenario.ExerciceMapper;
import ma.zyn.app.ws.dto.scenario.ExerciceDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/exercice/")
public class ExerciceRestAdmin {




    @Operation(summary = "Finds a list of all exercices")
    @GetMapping("")
    public ResponseEntity<List<ExerciceDto>> findAll(){
        ResponseEntity<List<ExerciceDto>> res = null;
        List<Exercice> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ExerciceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all exercices")
    @GetMapping("optimized")
    public ResponseEntity<List<ExerciceDto>> findAllOptimized(){
        ResponseEntity<List<ExerciceDto>> res = null;
        List<Exercice> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ExerciceDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a exercice by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExerciceDto> findById(@PathVariable Long id) {
        Exercice t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ExerciceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a exercice by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ExerciceDto> findByLibelle(@PathVariable String libelle) {
	    Exercice t = service.findByReferenceEntity(new Exercice(libelle));
        if (t != null) {
            mapper.init(true);
            ExerciceDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  exercice")
    @PostMapping("")
    public ResponseEntity<ExerciceDto> save(@RequestBody ExerciceDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Exercice myT = mapper.toItem(dto);
            Exercice t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ExerciceDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  exercice")
    @PutMapping("")
    public ResponseEntity<ExerciceDto> update(@RequestBody ExerciceDto dto){
        ResponseEntity<ExerciceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Exercice t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Exercice updated = service.update(t);
            ExerciceDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of exercice")
    @PostMapping("multiple")
    public ResponseEntity<List<ExerciceDto>> delete(@RequestBody List<ExerciceDto> dtos){
        ResponseEntity<List<ExerciceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Exercice> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified exercice")
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

    @Operation(summary = "find by statusExercice code")
    @GetMapping("statusExercice/code/{code}")
    public List<ExerciceDto> findByStatusExerciceCode(@PathVariable String code){
        return findDtos(service.findByStatusExerciceCode(code));
    }
    @Operation(summary = "delete by statusExercice code")
    @DeleteMapping("statusExercice/code/{code}")
    public int deleteByStatusExerciceCode(@PathVariable String code){
        return service.deleteByStatusExerciceCode(code);
    }

    @Operation(summary = "Finds a exercice and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ExerciceDto> findWithAssociatedLists(@PathVariable Long id) {
        Exercice loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ExerciceDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ExerciceDto> findDtos(List<Exercice> list){
        mapper.initObject(true);
        List<ExerciceDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ExerciceDto> getDtoResponseEntity(ExerciceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ExerciceRestAdmin(ExerciceAdminService service, ExerciceMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ExerciceAdminService service;
    private final ExerciceMapper mapper;





}
