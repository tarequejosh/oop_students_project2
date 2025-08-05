package com.kairova.simple.controller;

import com.kairova.simple.model.MentalHealthAssessment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("assessment", new MentalHealthAssessment());
        return "index";
    }

    @PostMapping("/assess")
    public String assess(@ModelAttribute MentalHealthAssessment assessment, Model model) {
        List<String> advice = new ArrayList<>();

        // Generate advice based on assessment
        if (assessment.getFeeling() != null) {
            String feeling = assessment.getFeeling().toLowerCase();
            if (feeling.contains("stressed") || feeling.contains("anxious") || assessment.getStressLevel() >= 4) {
                advice.add("🔹 You seem to be under significant pressure. Take deep breaths and short breaks.");
                advice.add("🔹 Try to identify the main source of your stress. Naming it can reduce its impact.");
            } else if (feeling.contains("happy")) {
                advice.add("🔹 Great to hear you're feeling happy! Keep doing what brings you joy.");
            } else if (feeling.contains("tired")) {
                advice.add("🔹 Fatigue can affect your mood. Prioritize rest and consider light physical activity.");
            }
        }

        if (!assessment.isSleepingWell()) {
            advice.add("🔹 Poor sleep affects mental well-being. Try to maintain a consistent sleep schedule.");
            advice.add("🔹 Avoid screens before bedtime and create a calming nighttime routine.");
        } else {
            advice.add("🔹 Good sleep is essential for mental health. Keep up the healthy habits!");
        }

        if (assessment.hasConcentrationIssues()) {
            advice.add("🔹 Break tasks into smaller, manageable parts.");
            advice.add("🔹 Try the Pomodoro Technique: 25 minutes of focused work, 5-minute break.");
        }

        if (!assessment.isSocializing()) {
            advice.add("🔹 Social connection boosts mood. Reach out to a trusted friend or family member.");
            advice.add("🔹 Even a short conversation can make a big difference.");
        } else {
            advice.add("🔹 Staying connected is important for emotional well-being. Keep nurturing relationships!");
        }

        if (assessment.getStressLevel() >= 4) {
            advice.add("\n❗ Your stress level is high. Consider reaching out to a mental health professional for support.");
        }

        model.addAttribute("advice", advice);
        return "results";
    }
}
