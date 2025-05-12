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

import ma.zyn.app.bean.core.referentiel.Tranchee;
import ma.zyn.app.service.facade.admin.referentiel.TrancheeAdminService;
import ma.zyn.app.ws.mapper.referentiel.TrancheeMapper;
import ma.zyn.app.ws.dto.referentiel.TrancheeDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/tranchee/")
public class TrancheeRestAdmin {




    @Operation(summary = "Finds a list of all tranchees")
    @GetMapping("")
    public ResponseEntity<List<TrancheeDto>> findAll(){
        ResponseEntity<List<TrancheeDto>> res = null;
        List<Tranchee> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<TrancheeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all tranchees")
    @GetMapping("optimized")
    public ResponseEntity<List<TrancheeDto>> findAllOptimized(){
        ResponseEntity<List<TrancheeDto>> res = null;
        List<Tranchee> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<TrancheeDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a tranchee by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TrancheeDto> findById(@PathVariable Long id) {
        Tranchee t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            TrancheeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a tranchee by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TrancheeDto> findByLibelle(@PathVariable String libelle) {
	    Tranchee t = service.findByReferenceEntity(new Tranchee(libelle));
        if (t != null) {
            mapper.init(true);
            TrancheeDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  tranchee")
    @PostMapping("")
    public ResponseEntity<TrancheeDto> save(@RequestBody TrancheeDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Tranchee myT = mapper.toItem(dto);
            Tranchee t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TrancheeDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  tranchee")
    @PutMapping("")
    public ResponseEntity<TrancheeDto> update(@RequestBody TrancheeDto dto){
        ResponseEntity<TrancheeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Tranchee t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Tranchee updated = service.update(t);
            TrancheeDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tranchee")
    @PostMapping("multiple")
    public ResponseEntity<List<TrancheeDto>> delete(@RequestBody List<TrancheeDto> dtos){
        ResponseEntity<List<TrancheeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Tranchee> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified tranchee")
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

    @Operation(summary = "find by panneau code")
    @GetMapping("panneau/code/{code}")
    public List<TrancheeDto> findByPanneauCode(@PathVariable String code){
        return findDtos(service.findByPanneauCode(code));
    }
    @Operation(summary = "delete by panneau code")
    @DeleteMapping("panneau/code/{code}")
    public int deleteByPanneauCode(@PathVariable String code){
        return service.deleteByPanneauCode(code);
    }

    @Operation(summary = "Finds a tranchee and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TrancheeDto> findWithAssociatedLists(@PathVariable Long id) {
        Tranchee loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        TrancheeDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TrancheeDto> findDtos(List<Tranchee> list){
        mapper.initObject(true);
        List<TrancheeDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TrancheeDto> getDtoResponseEntity(TrancheeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TrancheeRestAdmin(TrancheeAdminService service, TrancheeMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TrancheeAdminService service;
    private final TrancheeMapper mapper;





}
