package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The visualizeController is used to send by dynamic page
 * */
@Controller
public class VisualizeController {
    @GetMapping("/")
    public String showVisualizationPage(Model model) {
        return "visualization";
    }

}
