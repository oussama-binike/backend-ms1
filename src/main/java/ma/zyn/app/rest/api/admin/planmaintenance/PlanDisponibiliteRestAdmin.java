package  ma.zyn.app.api.facade.admin.planmaintenance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.service.facade.admin.planmaintenance.PlanDisponibiliteAdminService;
import ma.zyn.app.ws.mapper.planmaintenance.PlanDisponibiliteMapper;
import ma.zyn.app.ws.dto.planmaintenance.PlanDisponibiliteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/planDisponibilite/")
public class PlanDisponibiliteRestAdmin {




    @Operation(summary = "Finds a list of all planDisponibilites")
    @GetMapping("")
    public ResponseEntity<List<PlanDisponibiliteDto>> findAll(){
        ResponseEntity<List<PlanDisponibiliteDto>> res = null;
        List<PlanDisponibilite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<PlanDisponibiliteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all planDisponibilites")
    @GetMapping("optimized")
    public ResponseEntity<List<PlanDisponibiliteDto>> findAllOptimized(){
        ResponseEntity<List<PlanDisponibiliteDto>> res = null;
        List<PlanDisponibilite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<PlanDisponibiliteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a planDisponibilite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PlanDisponibiliteDto> findById(@PathVariable Long id) {
        PlanDisponibilite t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            PlanDisponibiliteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a planDisponibilite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PlanDisponibiliteDto> findByLibelle(@PathVariable String libelle) {
	    PlanDisponibilite t = service.findByReferenceEntity(new PlanDisponibilite(libelle));
        if (t != null) {
            mapper.init(true);
            PlanDisponibiliteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  planDisponibilite")
    @PostMapping("")
    public ResponseEntity<PlanDisponibiliteDto> save(@RequestBody PlanDisponibiliteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            PlanDisponibilite myT = mapper.toItem(dto);
            PlanDisponibilite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                PlanDisponibiliteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  planDisponibilite")
    @PutMapping("")
    public ResponseEntity<PlanDisponibiliteDto> update(@RequestBody PlanDisponibiliteDto dto){
        ResponseEntity<PlanDisponibiliteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PlanDisponibilite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            PlanDisponibilite updated = service.update(t);
            PlanDisponibiliteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of planDisponibilite")
    @PostMapping("multiple")
    public ResponseEntity<List<PlanDisponibiliteDto>> delete(@RequestBody List<PlanDisponibiliteDto> dtos){
        ResponseEntity<List<PlanDisponibiliteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<PlanDisponibilite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified planDisponibilite")
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


    @Operation(summary = "Finds a planDisponibilite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PlanDisponibiliteDto> findWithAssociatedLists(@PathVariable Long id) {
        PlanDisponibilite loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        PlanDisponibiliteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<PlanDisponibiliteDto> findDtos(List<PlanDisponibilite> list){
        mapper.initObject(true);
        List<PlanDisponibiliteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<PlanDisponibiliteDto> getDtoResponseEntity(PlanDisponibiliteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public PlanDisponibiliteRestAdmin(PlanDisponibiliteAdminService service, PlanDisponibiliteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final PlanDisponibiliteAdminService service;
    private final PlanDisponibiliteMapper mapper;





}
