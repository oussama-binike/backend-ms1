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

import ma.zyn.app.bean.core.referentiel.Model;
import ma.zyn.app.service.facade.admin.referentiel.ModelAdminService;
import ma.zyn.app.ws.mapper.referentiel.ModelMapper;
import ma.zyn.app.ws.dto.referentiel.ModelDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/model/")
public class ModelRestAdmin {




    @Operation(summary = "Finds a list of all models")
    @GetMapping("")
    public ResponseEntity<List<ModelDto>> findAll(){
        ResponseEntity<List<ModelDto>> res = null;
        List<Model> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ModelDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all models")
    @GetMapping("optimized")
    public ResponseEntity<List<ModelDto>> findAllOptimized(){
        ResponseEntity<List<ModelDto>> res = null;
        List<Model> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<ModelDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a model by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ModelDto> findById(@PathVariable Long id) {
        Model t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ModelDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a model by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ModelDto> findByLibelle(@PathVariable String libelle) {
	    Model t = service.findByReferenceEntity(new Model(libelle));
        if (t != null) {
            mapper.init(true);
            ModelDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  model")
    @PostMapping("")
    public ResponseEntity<ModelDto> save(@RequestBody ModelDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Model myT = mapper.toItem(dto);
            Model t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ModelDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  model")
    @PutMapping("")
    public ResponseEntity<ModelDto> update(@RequestBody ModelDto dto){
        ResponseEntity<ModelDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Model t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Model updated = service.update(t);
            ModelDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of model")
    @PostMapping("multiple")
    public ResponseEntity<List<ModelDto>> delete(@RequestBody List<ModelDto> dtos){
        ResponseEntity<List<ModelDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Model> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified model")
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


    @Operation(summary = "Finds a model and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ModelDto> findWithAssociatedLists(@PathVariable Long id) {
        Model loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ModelDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ModelDto> findDtos(List<Model> list){
        mapper.initObject(true);
        List<ModelDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ModelDto> getDtoResponseEntity(ModelDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ModelRestAdmin(ModelAdminService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ModelAdminService service;
    private final ModelMapper mapper;





}
