package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class VisualizeApplicationController {

    @GetMapping("/")
    public String showVisualizationPage(Model model) {

        return "visualization"; // Looks for visualization.html in src/main/resources/templates/
    }
}
