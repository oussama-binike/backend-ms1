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

import ma.zyn.app.bean.core.reclamation.TypeReclamation;
import ma.zyn.app.service.facade.admin.reclamation.TypeReclamationAdminService;
import ma.zyn.app.ws.mapper.reclamation.TypeReclamationMapper;
import ma.zyn.app.ws.dto.reclamation.TypeReclamationDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeReclamation/")
public class TypeReclamationRestAdmin {




    @Operation(summary = "Finds a list of all typeReclamations")
    @GetMapping("")
    public ResponseEntity<List<TypeReclamationDto>> findAll(){
        ResponseEntity<List<TypeReclamationDto>> res = null;
        List<TypeReclamation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeReclamations")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeReclamationDto>> findAllOptimized(){
        ResponseEntity<List<TypeReclamationDto>> res = null;
        List<TypeReclamation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeReclamationDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeReclamation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeReclamationDto> findById(@PathVariable Long id) {
        TypeReclamation t = service.findById(id);
        if (t != null) {
            TypeReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeReclamation by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeReclamationDto> findByLibelle(@PathVariable String libelle) {
	    TypeReclamation t = service.findByReferenceEntity(new TypeReclamation(libelle));
        if (t != null) {
            TypeReclamationDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeReclamation")
    @PostMapping("")
    public ResponseEntity<TypeReclamationDto> save(@RequestBody TypeReclamationDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeReclamation myT = mapper.toItem(dto);
            TypeReclamation t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeReclamationDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeReclamation")
    @PutMapping("")
    public ResponseEntity<TypeReclamationDto> update(@RequestBody TypeReclamationDto dto){
        ResponseEntity<TypeReclamationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeReclamation t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeReclamation updated = service.update(t);
            TypeReclamationDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeReclamation")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeReclamationDto>> delete(@RequestBody List<TypeReclamationDto> dtos){
        ResponseEntity<List<TypeReclamationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeReclamation> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeReclamation")
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


    @Operation(summary = "Finds a typeReclamation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeReclamationDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeReclamation loaded =  service.findWithAssociatedLists(id);
        TypeReclamationDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeReclamationDto> findDtos(List<TypeReclamation> list){
        List<TypeReclamationDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeReclamationDto> getDtoResponseEntity(TypeReclamationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeReclamationRestAdmin(TypeReclamationAdminService service, TypeReclamationMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeReclamationAdminService service;
    private final TypeReclamationMapper mapper;





}
