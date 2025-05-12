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

import ma.zyn.app.bean.core.referentiel.Niveau;
import ma.zyn.app.service.facade.admin.referentiel.NiveauAdminService;
import ma.zyn.app.ws.mapper.referentiel.NiveauMapper;
import ma.zyn.app.ws.dto.referentiel.NiveauDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/niveau/")
public class NiveauRestAdmin {




    @Operation(summary = "Finds a list of all niveaus")
    @GetMapping("")
    public ResponseEntity<List<NiveauDto>> findAll(){
        ResponseEntity<List<NiveauDto>> res = null;
        List<Niveau> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<NiveauDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all niveaus")
    @GetMapping("optimized")
    public ResponseEntity<List<NiveauDto>> findAllOptimized(){
        ResponseEntity<List<NiveauDto>> res = null;
        List<Niveau> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<NiveauDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a niveau by id")
    @GetMapping("id/{id}")
    public ResponseEntity<NiveauDto> findById(@PathVariable Long id) {
        Niveau t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            NiveauDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a niveau by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<NiveauDto> findByLibelle(@PathVariable String libelle) {
	    Niveau t = service.findByReferenceEntity(new Niveau(libelle));
        if (t != null) {
            mapper.init(true);
            NiveauDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  niveau")
    @PostMapping("")
    public ResponseEntity<NiveauDto> save(@RequestBody NiveauDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Niveau myT = mapper.toItem(dto);
            Niveau t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                NiveauDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  niveau")
    @PutMapping("")
    public ResponseEntity<NiveauDto> update(@RequestBody NiveauDto dto){
        ResponseEntity<NiveauDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Niveau t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Niveau updated = service.update(t);
            NiveauDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of niveau")
    @PostMapping("multiple")
    public ResponseEntity<List<NiveauDto>> delete(@RequestBody List<NiveauDto> dtos){
        ResponseEntity<List<NiveauDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Niveau> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified niveau")
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


    @Operation(summary = "Finds a niveau and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<NiveauDto> findWithAssociatedLists(@PathVariable Long id) {
        Niveau loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        NiveauDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<NiveauDto> findDtos(List<Niveau> list){
        mapper.initObject(true);
        List<NiveauDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<NiveauDto> getDtoResponseEntity(NiveauDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public NiveauRestAdmin(NiveauAdminService service, NiveauMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final NiveauAdminService service;
    private final NiveauMapper mapper;





}
