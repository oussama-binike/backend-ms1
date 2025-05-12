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

import ma.zyn.app.bean.core.referentiel.Panneau;
import ma.zyn.app.service.facade.admin.referentiel.PanneauAdminService;
import ma.zyn.app.ws.mapper.referentiel.PanneauMapper;
import ma.zyn.app.ws.dto.referentiel.PanneauDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/panneau/")
public class PanneauRestAdmin {




    @Operation(summary = "Finds a list of all panneaus")
    @GetMapping("")
    public ResponseEntity<List<PanneauDto>> findAll(){
        ResponseEntity<List<PanneauDto>> res = null;
        List<Panneau> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<PanneauDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all panneaus")
    @GetMapping("optimized")
    public ResponseEntity<List<PanneauDto>> findAllOptimized(){
        ResponseEntity<List<PanneauDto>> res = null;
        List<Panneau> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<PanneauDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a panneau by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PanneauDto> findById(@PathVariable Long id) {
        Panneau t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            PanneauDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a panneau by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<PanneauDto> findByLibelle(@PathVariable String libelle) {
	    Panneau t = service.findByReferenceEntity(new Panneau(libelle));
        if (t != null) {
            mapper.init(true);
            PanneauDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  panneau")
    @PostMapping("")
    public ResponseEntity<PanneauDto> save(@RequestBody PanneauDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Panneau myT = mapper.toItem(dto);
            Panneau t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                PanneauDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  panneau")
    @PutMapping("")
    public ResponseEntity<PanneauDto> update(@RequestBody PanneauDto dto){
        ResponseEntity<PanneauDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Panneau t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Panneau updated = service.update(t);
            PanneauDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of panneau")
    @PostMapping("multiple")
    public ResponseEntity<List<PanneauDto>> delete(@RequestBody List<PanneauDto> dtos){
        ResponseEntity<List<PanneauDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Panneau> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified panneau")
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

    @Operation(summary = "find by entite code")
    @GetMapping("entite/code/{code}")
    public List<PanneauDto> findByEntiteCode(@PathVariable String code){
        return findDtos(service.findByEntiteCode(code));
    }
    @Operation(summary = "delete by entite code")
    @DeleteMapping("entite/code/{code}")
    public int deleteByEntiteCode(@PathVariable String code){
        return service.deleteByEntiteCode(code);
    }

    @Operation(summary = "Finds a panneau and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PanneauDto> findWithAssociatedLists(@PathVariable Long id) {
        Panneau loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        PanneauDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<PanneauDto> findDtos(List<Panneau> list){
        mapper.initObject(true);
        List<PanneauDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<PanneauDto> getDtoResponseEntity(PanneauDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public PanneauRestAdmin(PanneauAdminService service, PanneauMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final PanneauAdminService service;
    private final PanneauMapper mapper;





}
