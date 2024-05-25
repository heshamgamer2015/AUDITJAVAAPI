package TarefaCrud.demo.Controller;

import TarefaCrud.demo.DTO.ReportDTO;
import TarefaCrud.demo.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/lista")
    public List<ReportDTO> listar(){
        return reportService.listar();
    }

}