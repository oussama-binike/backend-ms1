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

import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;
import ma.zyn.app.ws.mapper.referentiel.EntiteMapper;
import ma.zyn.app.ws.dto.referentiel.EntiteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/entite/")
public class EntiteRestAdmin {




    @Operation(summary = "Finds a list of all entites")
    @GetMapping("")
    public ResponseEntity<List<EntiteDto>> findAll(){
        ResponseEntity<List<EntiteDto>> res = null;
        List<Entite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<EntiteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entites")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteDto>> findAllOptimized(){
        ResponseEntity<List<EntiteDto>> res = null;
        List<Entite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<EntiteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteDto> findById(@PathVariable Long id) {
        Entite t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            EntiteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EntiteDto> findByLibelle(@PathVariable String libelle) {
	    Entite t = service.findByReferenceEntity(new Entite(libelle));
        if (t != null) {
            mapper.init(true);
            EntiteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entite")
    @PostMapping("")
    public ResponseEntity<EntiteDto> save(@RequestBody EntiteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Entite myT = mapper.toItem(dto);
            Entite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                EntiteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  entite")
    @PutMapping("")
    public ResponseEntity<EntiteDto> update(@RequestBody EntiteDto dto){
        ResponseEntity<EntiteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Entite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Entite updated = service.update(t);
            EntiteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entite")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteDto>> delete(@RequestBody List<EntiteDto> dtos){
        ResponseEntity<List<EntiteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Entite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified entite")
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

    @Operation(summary = "find by axe code")
    @GetMapping("axe/code/{code}")
    public List<EntiteDto> findByAxeCode(@PathVariable String code){
        return findDtos(service.findByAxeCode(code));
    }
    @Operation(summary = "delete by axe code")
    @DeleteMapping("axe/code/{code}")
    public int deleteByAxeCode(@PathVariable String code){
        return service.deleteByAxeCode(code);
    }

    @Operation(summary = "Finds a entite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteDto> findWithAssociatedLists(@PathVariable Long id) {
        Entite loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        EntiteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<EntiteDto> findDtos(List<Entite> list){
        mapper.initObject(true);
        List<EntiteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteDto> getDtoResponseEntity(EntiteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public EntiteRestAdmin(EntiteAdminService service, EntiteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final EntiteAdminService service;
    private final EntiteMapper mapper;





}
