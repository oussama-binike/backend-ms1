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

import ma.zyn.app.bean.core.referentiel.TypeExpedition;
import ma.zyn.app.service.facade.admin.referentiel.TypeExpeditionAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeExpeditionMapper;
import ma.zyn.app.ws.dto.referentiel.TypeExpeditionDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeExpedition/")
public class TypeExpeditionRestAdmin {




    @Operation(summary = "Finds a list of all typeExpeditions")
    @GetMapping("")
    public ResponseEntity<List<TypeExpeditionDto>> findAll(){
        ResponseEntity<List<TypeExpeditionDto>> res = null;
        List<TypeExpedition> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeExpeditionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeExpeditions")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeExpeditionDto>> findAllOptimized(){
        ResponseEntity<List<TypeExpeditionDto>> res = null;
        List<TypeExpedition> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeExpeditionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeExpedition by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeExpeditionDto> findById(@PathVariable Long id) {
        TypeExpedition t = service.findById(id);
        if (t != null) {
            TypeExpeditionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeExpedition by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeExpeditionDto> findByLibelle(@PathVariable String libelle) {
	    TypeExpedition t = service.findByReferenceEntity(new TypeExpedition(libelle));
        if (t != null) {
            TypeExpeditionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeExpedition")
    @PostMapping("")
    public ResponseEntity<TypeExpeditionDto> save(@RequestBody TypeExpeditionDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeExpedition myT = mapper.toItem(dto);
            TypeExpedition t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeExpeditionDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeExpedition")
    @PutMapping("")
    public ResponseEntity<TypeExpeditionDto> update(@RequestBody TypeExpeditionDto dto){
        ResponseEntity<TypeExpeditionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeExpedition t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeExpedition updated = service.update(t);
            TypeExpeditionDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeExpedition")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeExpeditionDto>> delete(@RequestBody List<TypeExpeditionDto> dtos){
        ResponseEntity<List<TypeExpeditionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeExpedition> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeExpedition")
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


    @Operation(summary = "Finds a typeExpedition and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeExpeditionDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeExpedition loaded =  service.findWithAssociatedLists(id);
        TypeExpeditionDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeExpeditionDto> findDtos(List<TypeExpedition> list){
        List<TypeExpeditionDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeExpeditionDto> getDtoResponseEntity(TypeExpeditionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeExpeditionRestAdmin(TypeExpeditionAdminService service, TypeExpeditionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeExpeditionAdminService service;
    private final TypeExpeditionMapper mapper;





}
