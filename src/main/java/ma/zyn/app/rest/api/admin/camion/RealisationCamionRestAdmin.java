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

import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.service.facade.admin.camion.RealisationCamionAdminService;
import ma.zyn.app.ws.mapper.camion.RealisationCamionMapper;
import ma.zyn.app.ws.dto.camion.RealisationCamionDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationCamion/")
public class RealisationCamionRestAdmin {




    @Operation(summary = "Finds a list of all realisationCamions")
    @GetMapping("")
    public ResponseEntity<List<RealisationCamionDto>> findAll(){
        ResponseEntity<List<RealisationCamionDto>> res = null;
        List<RealisationCamion> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<RealisationCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all realisationCamions")
    @GetMapping("optimized")
    public ResponseEntity<List<RealisationCamionDto>> findAllOptimized(){
        ResponseEntity<List<RealisationCamionDto>> res = null;
        List<RealisationCamion> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a realisationCamion by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationCamionDto> findById(@PathVariable Long id) {
        RealisationCamion t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a realisationCamion by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RealisationCamionDto> findByLibelle(@PathVariable String libelle) {
	    RealisationCamion t = service.findByReferenceEntity(new RealisationCamion(libelle));
        if (t != null) {
            mapper.init(true);
            RealisationCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  realisationCamion")
    @PostMapping("")
    public ResponseEntity<RealisationCamionDto> save(@RequestBody RealisationCamionDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationCamion myT = mapper.toItem(dto);
            RealisationCamion t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationCamionDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationCamion")
    @PutMapping("")
    public ResponseEntity<RealisationCamionDto> update(@RequestBody RealisationCamionDto dto){
        ResponseEntity<RealisationCamionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationCamion t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationCamion updated = service.update(t);
            RealisationCamionDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationCamion")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationCamionDto>> delete(@RequestBody List<RealisationCamionDto> dtos){
        ResponseEntity<List<RealisationCamionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationCamion> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationCamion")
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

    @Operation(summary = "find by provennanceCamion code")
    @GetMapping("provennanceCamion/code/{code}")
    public List<RealisationCamionDto> findByProvennanceCamionCode(@PathVariable String code){
        return findDtos(service.findByProvennanceCamionCode(code));
    }
    @Operation(summary = "delete by provennanceCamion code")
    @DeleteMapping("provennanceCamion/code/{code}")
    public int deleteByProvennanceCamionCode(@PathVariable String code){
        return service.deleteByProvennanceCamionCode(code);
    }
    @Operation(summary = "find by destinationCamion code")
    @GetMapping("destinationCamion/code/{code}")
    public List<RealisationCamionDto> findByDestinationCamionCode(@PathVariable String code){
        return findDtos(service.findByDestinationCamionCode(code));
    }
    @Operation(summary = "delete by destinationCamion code")
    @DeleteMapping("destinationCamion/code/{code}")
    public int deleteByDestinationCamionCode(@PathVariable String code){
        return service.deleteByDestinationCamionCode(code);
    }

    @Operation(summary = "Finds a realisationCamion and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationCamionDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationCamion loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationCamionDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationCamionDto> findDtos(List<RealisationCamion> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationCamionDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationCamionDto> getDtoResponseEntity(RealisationCamionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationCamionRestAdmin(RealisationCamionAdminService service, RealisationCamionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationCamionAdminService service;
    private final RealisationCamionMapper mapper;





}
