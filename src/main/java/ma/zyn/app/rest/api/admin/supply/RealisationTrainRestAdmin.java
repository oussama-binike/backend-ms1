package  ma.zyn.app.api.facade.admin.supply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.service.facade.admin.supply.RealisationTrainAdminService;
import ma.zyn.app.ws.mapper.supply.RealisationTrainMapper;
import ma.zyn.app.ws.dto.supply.RealisationTrainDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationTrain/")
public class RealisationTrainRestAdmin {




    @Operation(summary = "Finds a list of all realisationTrains")
    @GetMapping("")
    public ResponseEntity<List<RealisationTrainDto>> findAll(){
        ResponseEntity<List<RealisationTrainDto>> res = null;
        List<RealisationTrain> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<RealisationTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all realisationTrains")
    @GetMapping("optimized")
    public ResponseEntity<List<RealisationTrainDto>> findAllOptimized(){
        ResponseEntity<List<RealisationTrainDto>> res = null;
        List<RealisationTrain> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a realisationTrain by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationTrainDto> findById(@PathVariable Long id) {
        RealisationTrain t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a realisationTrain by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RealisationTrainDto> findByLibelle(@PathVariable String libelle) {
	    RealisationTrain t = service.findByReferenceEntity(new RealisationTrain(libelle));
        if (t != null) {
            mapper.init(true);
            RealisationTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  realisationTrain")
    @PostMapping("")
    public ResponseEntity<RealisationTrainDto> save(@RequestBody RealisationTrainDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationTrain myT = mapper.toItem(dto);
            RealisationTrain t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationTrainDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationTrain")
    @PutMapping("")
    public ResponseEntity<RealisationTrainDto> update(@RequestBody RealisationTrainDto dto){
        ResponseEntity<RealisationTrainDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationTrain t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationTrain updated = service.update(t);
            RealisationTrainDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationTrain")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationTrainDto>> delete(@RequestBody List<RealisationTrainDto> dtos){
        ResponseEntity<List<RealisationTrainDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationTrain> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationTrain")
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

    @Operation(summary = "find by provennanceTrain code")
    @GetMapping("provennanceTrain/code/{code}")
    public List<RealisationTrainDto> findByProvennanceTrainCode(@PathVariable String code){
        return findDtos(service.findByProvennanceTrainCode(code));
    }
    @Operation(summary = "delete by provennanceTrain code")
    @DeleteMapping("provennanceTrain/code/{code}")
    public int deleteByProvennanceTrainCode(@PathVariable String code){
        return service.deleteByProvennanceTrainCode(code);
    }
    @Operation(summary = "find by typeWagon code")
    @GetMapping("typeWagon/code/{code}")
    public List<RealisationTrainDto> findByTypeWagonCode(@PathVariable String code){
        return findDtos(service.findByTypeWagonCode(code));
    }
    @Operation(summary = "delete by typeWagon code")
    @DeleteMapping("typeWagon/code/{code}")
    public int deleteByTypeWagonCode(@PathVariable String code){
        return service.deleteByTypeWagonCode(code);
    }

    @Operation(summary = "Finds a realisationTrain and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationTrainDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationTrain loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationTrainDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationTrainDto> findDtos(List<RealisationTrain> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationTrainDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationTrainDto> getDtoResponseEntity(RealisationTrainDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationTrainRestAdmin(RealisationTrainAdminService service, RealisationTrainMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationTrainAdminService service;
    private final RealisationTrainMapper mapper;





}
