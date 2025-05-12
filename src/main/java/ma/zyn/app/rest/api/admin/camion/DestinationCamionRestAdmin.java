package  ma.zyn.app.api.facade.admin.camion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.service.facade.admin.camion.DestinationCamionAdminService;
import ma.zyn.app.ws.mapper.camion.DestinationCamionMapper;
import ma.zyn.app.ws.dto.camion.DestinationCamionDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/destinationCamion/")
public class DestinationCamionRestAdmin {




    @Operation(summary = "Finds a list of all destinationCamions")
    @GetMapping("")
    public ResponseEntity<List<DestinationCamionDto>> findAll(){
        ResponseEntity<List<DestinationCamionDto>> res = null;
        List<DestinationCamion> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all destinationCamions")
    @GetMapping("optimized")
    public ResponseEntity<List<DestinationCamionDto>> findAllOptimized(){
        ResponseEntity<List<DestinationCamionDto>> res = null;
        List<DestinationCamion> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a destinationCamion by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DestinationCamionDto> findById(@PathVariable Long id) {
        DestinationCamion t = service.findById(id);
        if (t != null) {
            DestinationCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a destinationCamion by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DestinationCamionDto> findByLibelle(@PathVariable String libelle) {
	    DestinationCamion t = service.findByReferenceEntity(new DestinationCamion(libelle));
        if (t != null) {
            DestinationCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  destinationCamion")
    @PostMapping("")
    public ResponseEntity<DestinationCamionDto> save(@RequestBody DestinationCamionDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            DestinationCamion myT = mapper.toItem(dto);
            DestinationCamion t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                DestinationCamionDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  destinationCamion")
    @PutMapping("")
    public ResponseEntity<DestinationCamionDto> update(@RequestBody DestinationCamionDto dto){
        ResponseEntity<DestinationCamionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DestinationCamion t = service.findById(dto.getId());
            mapper.copy(dto,t);
            DestinationCamion updated = service.update(t);
            DestinationCamionDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of destinationCamion")
    @PostMapping("multiple")
    public ResponseEntity<List<DestinationCamionDto>> delete(@RequestBody List<DestinationCamionDto> dtos){
        ResponseEntity<List<DestinationCamionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DestinationCamion> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified destinationCamion")
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


    @Operation(summary = "Finds a destinationCamion and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DestinationCamionDto> findWithAssociatedLists(@PathVariable Long id) {
        DestinationCamion loaded =  service.findWithAssociatedLists(id);
        DestinationCamionDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<DestinationCamionDto> findDtos(List<DestinationCamion> list){
        List<DestinationCamionDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<DestinationCamionDto> getDtoResponseEntity(DestinationCamionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public DestinationCamionRestAdmin(DestinationCamionAdminService service, DestinationCamionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final DestinationCamionAdminService service;
    private final DestinationCamionMapper mapper;





}
