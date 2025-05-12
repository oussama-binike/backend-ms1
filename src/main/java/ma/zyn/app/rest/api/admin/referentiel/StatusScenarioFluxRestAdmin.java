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

import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.service.facade.admin.referentiel.StatusScenarioFluxAdminService;
import ma.zyn.app.ws.mapper.referentiel.StatusScenarioFluxMapper;
import ma.zyn.app.ws.dto.referentiel.StatusScenarioFluxDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/statusScenarioFlux/")
public class StatusScenarioFluxRestAdmin {




    @Operation(summary = "Finds a list of all statusScenarioFluxs")
    @GetMapping("")
    public ResponseEntity<List<StatusScenarioFluxDto>> findAll(){
        ResponseEntity<List<StatusScenarioFluxDto>> res = null;
        List<StatusScenarioFlux> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusScenarioFluxDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all statusScenarioFluxs")
    @GetMapping("optimized")
    public ResponseEntity<List<StatusScenarioFluxDto>> findAllOptimized(){
        ResponseEntity<List<StatusScenarioFluxDto>> res = null;
        List<StatusScenarioFlux> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StatusScenarioFluxDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a statusScenarioFlux by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StatusScenarioFluxDto> findById(@PathVariable Long id) {
        StatusScenarioFlux t = service.findById(id);
        if (t != null) {
            StatusScenarioFluxDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a statusScenarioFlux by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StatusScenarioFluxDto> findByLibelle(@PathVariable String libelle) {
	    StatusScenarioFlux t = service.findByReferenceEntity(new StatusScenarioFlux(libelle));
        if (t != null) {
            StatusScenarioFluxDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  statusScenarioFlux")
    @PostMapping("")
    public ResponseEntity<StatusScenarioFluxDto> save(@RequestBody StatusScenarioFluxDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            StatusScenarioFlux myT = mapper.toItem(dto);
            StatusScenarioFlux t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                StatusScenarioFluxDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  statusScenarioFlux")
    @PutMapping("")
    public ResponseEntity<StatusScenarioFluxDto> update(@RequestBody StatusScenarioFluxDto dto){
        ResponseEntity<StatusScenarioFluxDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StatusScenarioFlux t = service.findById(dto.getId());
            mapper.copy(dto,t);
            StatusScenarioFlux updated = service.update(t);
            StatusScenarioFluxDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of statusScenarioFlux")
    @PostMapping("multiple")
    public ResponseEntity<List<StatusScenarioFluxDto>> delete(@RequestBody List<StatusScenarioFluxDto> dtos){
        ResponseEntity<List<StatusScenarioFluxDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<StatusScenarioFlux> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified statusScenarioFlux")
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


    @Operation(summary = "Finds a statusScenarioFlux and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StatusScenarioFluxDto> findWithAssociatedLists(@PathVariable Long id) {
        StatusScenarioFlux loaded =  service.findWithAssociatedLists(id);
        StatusScenarioFluxDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<StatusScenarioFluxDto> findDtos(List<StatusScenarioFlux> list){
        List<StatusScenarioFluxDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<StatusScenarioFluxDto> getDtoResponseEntity(StatusScenarioFluxDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public StatusScenarioFluxRestAdmin(StatusScenarioFluxAdminService service, StatusScenarioFluxMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final StatusScenarioFluxAdminService service;
    private final StatusScenarioFluxMapper mapper;





}
