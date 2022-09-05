package br.com.qintess.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboardcliente")
public class DashboardClienteController {


  @GetMapping
  public String cadastrar(){
    return "dashboard/add";
  }


}
